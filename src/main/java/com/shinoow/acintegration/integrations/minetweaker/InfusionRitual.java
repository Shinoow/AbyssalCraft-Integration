package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconInfusionRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.InfusionRitual")
public class InfusionRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean remnantHelp, IItemStack item, IIngredient sacrifice, IIngredient...offerings){

		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, remnantHelp, item, sacrifice, offerings, false);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean remnantHelp, IItemStack item, IIngredient sacrifice, IIngredient[] offerings, boolean nbt){

		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, remnantHelp, item, sacrifice, offerings, nbt, new String[0]);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean remnantHelp, IItemStack item, IIngredient sacrifice, IIngredient[] offerings, boolean nbt, String...tags){

		Object[] offers = ACMT.toObjects(offerings);

		NecronomiconInfusionRitual ritual = new NecronomiconInfusionRitual(unlocalizedName, bookType, dimension, requiredEnergy, remnantHelp, ACMT.toStack(item), ACMT.toObject(sacrifice), offers);

		if(nbt) ritual.setNBTSensitive().setNBTSensitiveSacrifice();

		if(tags != null && tags.length > 0) ritual.setTags(tags);

		ACMTMisc.TASKS.add(new Add(ritual));
	}

	private static class Add implements IAction
	{

		private final NecronomiconInfusionRitual ritual;

		public Add(NecronomiconInfusionRitual ritual){

			this.ritual = ritual;
		}

		@Override
		public void apply() {

			RitualRegistry.instance().registerRitual(ritual);
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Infusion Ritual for " + ritual.getItem().getDisplayName();
		}
	}

	@ZenMethod
	public static void removeRitual(IItemStack item){

		ACMTMisc.TASKS.add(new Remove(ACMT.toStack(item)));
	}

	private static class Remove implements IAction
	{
		private final ItemStack item;

		public Remove(ItemStack item){
			this.item = item;
		}

		@Override
		public void apply() {

			List<NecronomiconInfusionRitual> temp = new ArrayList<NecronomiconInfusionRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconInfusionRitual &&
						ritual.getClass().getSuperclass() != NecronomiconInfusionRitual.class &&
						ritual.getClass().getSuperclass().getSuperclass() != NecronomiconInfusionRitual.class)
					temp.add((NecronomiconInfusionRitual) ritual);
			for(NecronomiconInfusionRitual ritual : temp)
				if(APIUtils.areStacksEqual(item, ritual.getItem()))
					RitualRegistry.instance().getRituals().remove(ritual);
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Infusion Ritual for "+ item.getDisplayName();
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			RitualRegistry.instance().getRituals().removeIf(r -> r instanceof NecronomiconInfusionRitual &&
					r.getClass().getSuperclass() != NecronomiconInfusionRitual.class &&
					r.getClass().getSuperclass().getSuperclass() != NecronomiconInfusionRitual.class);
		}

		@Override
		public String describe() {

			return "Removing all Infusion Ritual recipes";
		}
	}
}