package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconCreationRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.CreationRitual")
public class CreationRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, IItemStack item, IIngredient...offerings){

		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, item, offerings, false);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, IItemStack item, IIngredient[] offerings, boolean nbt){

		Object[] offers = ACMT.toObjects(offerings);

		NecronomiconCreationRitual ritual = new NecronomiconCreationRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, ACMT.toStack(item), offers);

		if(nbt) ritual.setNBTSensitive();

		ACMTMisc.TASKS.add(new Add(ritual));
	}

	private static class Add implements IAction
	{

		private final NecronomiconCreationRitual ritual;

		public Add(NecronomiconCreationRitual ritual){

			this.ritual = ritual;
		}

		@Override
		public void apply() {

			RitualRegistry.instance().registerRitual(ritual);
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Creation Ritual for " + ritual.getItem().getDisplayName();
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

			List<NecronomiconCreationRitual> temp = new ArrayList<NecronomiconCreationRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconCreationRitual &&
						ritual.getClass().getSuperclass() != NecronomiconCreationRitual.class &&
						ritual.getClass().getSuperclass().getSuperclass() != NecronomiconCreationRitual.class)
					temp.add((NecronomiconCreationRitual) ritual);
			for(NecronomiconCreationRitual ritual : temp)
				if(APIUtils.areStacksEqual(item, ritual.getItem()))
					RitualRegistry.instance().getRituals().remove(ritual);
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Creation Ritual for "+ item.getDisplayName();
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			RitualRegistry.instance().getRituals().removeIf(r -> r instanceof NecronomiconCreationRitual &&
					r.getClass().getSuperclass() != NecronomiconCreationRitual.class &&
					r.getClass().getSuperclass().getSuperclass() != NecronomiconCreationRitual.class);
		}

		@Override
		public String describe() {

			return "Removing all Creation Ritual recipes";
		}
	}
}