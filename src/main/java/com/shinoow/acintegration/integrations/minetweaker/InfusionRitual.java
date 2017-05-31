package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconInfusionRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
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

		if(nbt) ritual.setNBTSensitive();

		if(tags != null && tags.length > 0) ritual.setTags(tags);

		MineTweakerAPI.apply(new Add(ritual));
	}

	private static class Add implements IUndoableAction
	{

		private final NecronomiconInfusionRitual ritual;

		public Add(NecronomiconInfusionRitual ritual){

			this.ritual = ritual;
		}

		@Override
		public void apply() {

			RitualRegistry.instance().registerRitual(ritual);
			MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(ritual);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Infusion Ritual for " + ritual.getItem().getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Removing Necronomicon Infusion Ritual for " + ritual.getItem().getDisplayName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			RitualRegistry.instance().getRituals().remove(ritual);
			MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(ritual);
		}
	}

	@ZenMethod
	public static void removeRitual(IItemStack item){

		MineTweakerAPI.apply(new Remove(ACMT.toStack(item)));
	}

	private static class Remove implements IUndoableAction
	{
		private final ItemStack item;
		List<NecronomiconInfusionRitual> removedRituals;

		public Remove(ItemStack item){
			this.item = item;
		}

		@Override
		public void apply() {

			removedRituals = new ArrayList<NecronomiconInfusionRitual>();

			List<NecronomiconInfusionRitual> temp = new ArrayList<NecronomiconInfusionRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconInfusionRitual &&
						ritual.getClass().getSuperclass() != NecronomiconInfusionRitual.class &&
						ritual.getClass().getSuperclass().getSuperclass() != NecronomiconInfusionRitual.class)
					temp.add((NecronomiconInfusionRitual) ritual);
			for(NecronomiconInfusionRitual ritual : temp)
				if(RitualRegistry.instance().areStacksEqual(item, ritual.getItem())){
					removedRituals.add(ritual);
					RitualRegistry.instance().getRituals().remove(ritual);
					MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(ritual);
				}
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Infusion Ritual for "+ item.getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Necronomicon Infusion Ritual for "+ item.getDisplayName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removedRituals != null)
				for(NecronomiconInfusionRitual ritual : removedRituals)
					if(ritual != null){
						RitualRegistry.instance().registerRitual(ritual);
						MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(ritual);
					}
		}
	}
}