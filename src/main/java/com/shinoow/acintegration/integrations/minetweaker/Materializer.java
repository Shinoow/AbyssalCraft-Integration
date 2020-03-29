package com.shinoow.acintegration.integrations.minetweaker;

import java.util.List;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.Materialization;
import com.shinoow.abyssalcraft.api.recipe.MaterializerRecipes;

import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.Materializer")
public class Materializer {

	@ZenMethod
	public static void addMaterialization(IItemStack output, IItemStack[] input){

		ItemStack stack = ACMT.toStack(output);
		ItemStack[] stacks = new ItemStack[input.length];
		for(int i = 0; i < stacks.length; i++)
			stacks[i] = ACMT.toStack(input[i]);
		ACMTMisc.TASKS.add(new Add(stack, stacks));
	}

	private static class Add implements IAction {

		private ItemStack output;
		private ItemStack[] input;

		public Add(ItemStack output, ItemStack[] input){
			this.output = output;
			this.input = input;
		}

		@Override
		public void apply() {
			AbyssalCraftAPI.addMaterialization(output, input);
		}

		@Override
		public String describe() {

			return String.format("Adding Materialization recipe for %s (input: %s)", output.getDisplayName(), getArrayContent(input));
		}

		private String getArrayContent(ItemStack[] array){
			String stuff = "[";
			for(ItemStack stack : array)
				stuff+=stack.getDisplayName()+", ";
			stuff = stuff.substring(0, stuff.length()-2);
			return stuff+="]";
		}
	}

	@ZenMethod
	public static void removeMaterialization(IItemStack output){

		ACMTMisc.TASKS.add(new Remove(ACMT.toStack(output)));
	}

	private static class Remove implements IAction {

		private ItemStack output;

		public Remove(ItemStack output){
			this.output = output;
		}

		@Override
		public void apply() {
			List<Materialization> temp = Lists.newArrayList();
			for(Materialization mat : MaterializerRecipes.instance().getMaterializationList())
				if(APIUtils.areStacksEqual(output, mat.output))
					temp.add(mat);
			for(Materialization mat : temp)
				MaterializerRecipes.instance().getMaterializationList().remove(mat);
		}

		@Override
		public String describe() {

			return String.format("Removing Materialization recipes for %s", output.getDisplayName());
		}
	}

	@ZenMethod
	public static void addCrystal(IItemStack stack){
		ACMTMisc.TASKS.add(new AddCrystal(ACMT.toStack(stack)));
	}

	private static class AddCrystal implements IAction {

		private ItemStack stack;

		public AddCrystal(ItemStack stack){
			this.stack = stack;
		}

		@Override
		public void apply() {
			AbyssalCraftAPI.addCrystal(stack);
		}

		@Override
		public String describe() {

			return String.format("Added %s to the Crystal List", stack.getDisplayName());
		}
	}

	@ZenMethod
	public static void addCrystal(IItemStack stack, int burnTime) {
		ACMTMisc.TASKS.add(new AddCrystalFuel(ACMT.toStack(stack), burnTime));
	}

	private static class AddCrystalFuel implements IAction {

		private ItemStack stack;
		private int burnTime;

		public AddCrystalFuel(ItemStack stack, int burnTime) {
			this.stack = stack;
			this.burnTime = burnTime;
		}

		@Override
		public void apply() {
			AbyssalCraftAPI.addCrystal(stack);
			ACMT.CRYSTALLIZER_FUELS.put(stack, burnTime);
			ACMT.TRANSMUTATOR_FUELS.put(stack, burnTime);
		}

		@Override
		public String describe() {

			return String.format("Added %s to the Crystal List, and added it as Crystallizer and Transmutator fuel (burntime: %d)", stack.getDisplayName(), burnTime);
		}
	}
}
