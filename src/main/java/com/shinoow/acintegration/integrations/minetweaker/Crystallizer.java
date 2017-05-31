package com.shinoow.acintegration.integrations.minetweaker;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.CrystallizerRecipes;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.Crystallizer")
public class Crystallizer {

	@ZenMethod
	public static void addCrystallization(IItemStack input, IItemStack output1, IItemStack output2, float exp){

		MineTweakerAPI.apply(new Add(ACMT.toStack(input), ACMT.toStack(output1), ACMT.toStack(output2), exp));
	}

	@ZenMethod
	public static void addSingleCrystallization(IItemStack input, IItemStack output, float exp){
		MineTweakerAPI.apply(new Add(ACMT.toStack(input), ACMT.toStack(output), exp));
	}

	private static class Add implements IUndoableAction
	{
		private ItemStack input, output1, output2;
		private float exp;
		private boolean isSingle;
		private Object recipe;

		public Add(ItemStack input, ItemStack output1, ItemStack output2, float exp){
			this.input = input;
			this.output1 = output1;
			this.output2 = output2;
			this.exp = exp;
			recipe = ACMTJEIUtil.getCrystRecipe(input, output1, output2, exp);
		}

		public Add(ItemStack input, ItemStack output, float exp){
			this(input, output, ItemStack.EMPTY, exp);
			isSingle = true;
		}

		@Override
		public void apply() {

			if(!isSingle)
				AbyssalCraftAPI.addCrystallization(input, output1, output2, exp);
			else AbyssalCraftAPI.addSingleCrystallization(input, output1, exp);
			if(recipe != null)
				MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(recipe);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Crystallization recipe for " + output1.getDisplayName() + (!output2.isEmpty() ? " (and "+ output2.getDisplayName() + ")" : "");
		}

		@Override
		public String describeUndo() {

			return "Removing Crystallization recipe for " + output1.getDisplayName() + (!output2.isEmpty() ? " (and "+ output2.getDisplayName() + ")" : "");
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			CrystallizerRecipes.instance().getCrystallizationList().remove(input);
			if(recipe != null)
				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(recipe);
		}
	}

	@ZenMethod
	public static void removeCrystallization(IItemStack input){
		MineTweakerAPI.apply(new Remove(ACMT.toStack(input)));
	}

	private static class Remove implements IUndoableAction {

		private ItemStack input, output1, output2;
		private Object recipe;

		public Remove(ItemStack input){
			this.input = input;
			ItemStack[] outputs = CrystallizerRecipes.instance().getCrystallizationResult(input);

			output1 = outputs[0];
			output2 = outputs[1];
			recipe = ACMTJEIUtil.getCrystRecipe(input, output1, output2, CrystallizerRecipes.instance().getExperience(output1));
		}

		@Override
		public void apply() {
			CrystallizerRecipes.instance().getCrystallizationList().remove(input);
			if(recipe != null)
				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(recipe);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing Crystallization recipe for " + output1.getDisplayName() + (!output2.isEmpty() ? " (and "+ output2.getDisplayName() + ")" : "") + " (input: " + input.getDisplayName() + ")";
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Crystallization recipe for " + output1.getDisplayName() + (!output2.isEmpty() ? " (and "+ output2.getDisplayName() + ")" : "") + " (input: " + input.getDisplayName() + ")";
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {
			if(!input.isEmpty() && !output1.isEmpty()){
				AbyssalCraftAPI.addCrystallization(input, output1, output2, CrystallizerRecipes.instance().getExperience(output1));
				if(recipe != null)
					MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(recipe);
			}
		}
	}
}