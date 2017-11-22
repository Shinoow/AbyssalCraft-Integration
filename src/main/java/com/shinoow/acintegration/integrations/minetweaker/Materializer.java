package com.shinoow.acintegration.integrations.minetweaker;

import java.util.List;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.Materialization;
import com.shinoow.abyssalcraft.api.recipe.MaterializerRecipes;

@ZenClass("mods.abyssalcraft.Materializer")
public class Materializer {

	@ZenMethod
	public static void addMaterialization(IItemStack output, IItemStack[] input){

		ItemStack stack = ACMT.toStack(output);
		ItemStack[] stacks = new ItemStack[input.length];
		for(int i = 0; i < stacks.length; i++)
			stacks[i] = ACMT.toStack(input[i]);
		MineTweakerAPI.apply(new Add(stack, stacks));
	}

	private static class Add implements IUndoableAction {

		private Materialization mat;

		public Add(ItemStack output, ItemStack[] input){
			mat = new Materialization(input, output);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public void apply() {
			AbyssalCraftAPI.addMaterialization(mat);
			MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(mat);
		}

		@Override
		public String describe() {

			return String.format("Adding Materialization recipe for %s (input: %s)", mat.output, getArrayContent(mat.input));
		}

		@Override
		public String describeUndo() {

			return String.format("Removing Materialization recipe for %s", mat.output);
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		private String getArrayContent(ItemStack[] array){
			String stuff = "[";
			for(ItemStack stack : array)
				stuff+=stack+", ";
			stuff = stuff.substring(0, stuff.length()-2);
			return stuff+="]";
		}

		@Override
		public void undo() {
			MaterializerRecipes.instance().getMaterializationList().remove(mat);
			MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(mat);
		}
	}

	@ZenMethod
	public static void removeMaterialization(IItemStack output){

		MineTweakerAPI.apply(new Remove(ACMT.toStack(output)));
	}

	private static class Remove implements IUndoableAction {

		private ItemStack output;
		private List<Materialization> removed;

		public Remove(ItemStack output){
			this.output = output;
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public void apply() {
			removed = Lists.newArrayList();
			for(Materialization mat : MaterializerRecipes.instance().getMaterializationList())
				if(APIUtils.areStacksEqual(output, mat.output))
					removed.add(mat);
			for(Materialization mat : removed){
				MaterializerRecipes.instance().getMaterializationList().remove(mat);
				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(mat);
			}
		}

		@Override
		public String describe() {

			return String.format("Removing Materialization recipes for %s", output);
		}

		@Override
		public String describeUndo() {

			return String.format("Re-adding Materialization recipes for %s", output);
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removed != null)
				for(Materialization mat : removed){
					AbyssalCraftAPI.addMaterialization(mat);
					MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(mat);
				}
		}
	}

	@ZenMethod
	public static void addCrystal(IItemStack stack){
		MineTweakerAPI.apply(new AddCrystal(ACMT.toStack(stack)));
	}

	private static class AddCrystal implements IUndoableAction {

		private ItemStack stack;

		public AddCrystal(ItemStack stack){
			this.stack = stack;
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public void apply() {
			AbyssalCraftAPI.addCrystal(stack);
		}

		@Override
		public String describe() {

			return String.format("Adding %s to the Crystal List", stack);
		}

		@Override
		public String describeUndo() {

			return String.format("Removing %s from the Crystal List", stack);
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			AbyssalCraftAPI.getCrystals().remove(stack);
		}
	}
}
