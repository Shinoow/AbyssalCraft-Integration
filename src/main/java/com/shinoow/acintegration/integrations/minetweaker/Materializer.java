package com.shinoow.acintegration.integrations.minetweaker;

import java.util.List;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.Materialization;
import com.shinoow.abyssalcraft.api.recipe.MaterializerRecipes;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.Materializer")
public class Materializer {

	@ZenMethod
	public static void addMaterialization(IIngredient output, IItemStack[] input){

		Object obj = ACMT.toObject(output);
		ItemStack[] stacks = new ItemStack[input.length];
		for(int i = 0; i < stacks.length; i++)
			stacks[i] = ACMT.toStack(input[i]);
		ACMTMisc.TASKS.add(new Add(obj, stacks));
	}

	private static class Add implements IAction {

		private Object output;
		private ItemStack[] input;
		private String outputName;

		public Add(Object output, ItemStack[] input){
			this.output = output;
			this.input = input;
		}

		@Override
		public void apply() {
			if(output instanceof String) {
				outputName = (String)output;
				AbyssalCraftAPI.addMaterialization(outputName, input);
			} else if(output instanceof ItemStack) {
				ItemStack stack = (ItemStack)output;
				outputName = ACMT.getItemNameSafely(stack);
				if(stack.getHasSubtypes() && stack.getMetadata() == OreDictionary.WILDCARD_VALUE) {
					NonNullList<ItemStack> list = NonNullList.create();
					stack.getItem().getSubItems(stack.getItem().getCreativeTab(), list);
					list.stream().filter(Predicates.not(ItemStack::isEmpty))
					.forEach(is -> AbyssalCraftAPI.addMaterialization(is, input));
				} else AbyssalCraftAPI.addMaterialization(stack, input);
			}
		}

		@Override
		public String describe() {

			if(outputName == null)
				return "Failed to add Materialization recipe, invalid output";
			return String.format("Adding Materialization recipe for %s (input: %s)", outputName, getArrayContent(input));
		}

		private String getArrayContent(ItemStack[] array){
			String stuff = "[";
			for(ItemStack stack : array)
				stuff+=ACMT.getItemNameSafely(stack)+", ";
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
			if(output.getMetadata() == OreDictionary.WILDCARD_VALUE && output.getHasSubtypes()) {
				NonNullList<ItemStack> list = NonNullList.create();
				output.getItem().getSubItems(output.getItem().getCreativeTab(), list);
				list.stream().filter(Predicates.not(ItemStack::isEmpty)).forEach(is -> {
					for(Materialization mat : MaterializerRecipes.instance().getMaterializationList())
						if(APIUtils.areStacksEqual(is, mat.output))
							temp.add(mat);
				});
			} else {
				for(Materialization mat : MaterializerRecipes.instance().getMaterializationList())
					if(APIUtils.areStacksEqual(output, mat.output))
						temp.add(mat);
			}

			for(Materialization mat : temp)
				MaterializerRecipes.instance().getMaterializationList().remove(mat);
		}

		@Override
		public String describe() {

			return String.format("Removing Materialization recipes for %s", ACMT.getItemNameSafely(output));
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

			return String.format("Adding %s to the Crystal List", ACMT.getItemNameSafely(stack));
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

			return String.format("Adding %s to the Crystal List, and added it as Crystallizer and Transmutator fuel (burntime: %d)", ACMT.getItemNameSafely(stack), burnTime);
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			MaterializerRecipes.instance().getMaterializationList().clear();
		}

		@Override
		public String describe() {

			return "Removing all Materializer recipes";
		}
	}
}
