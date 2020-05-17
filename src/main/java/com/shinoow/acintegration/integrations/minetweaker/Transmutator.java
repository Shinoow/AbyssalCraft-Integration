package com.shinoow.acintegration.integrations.minetweaker;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.TransmutatorRecipes;

import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.Transmutator")
public class Transmutator {

	@ZenMethod
	public static void addTransmutation(IItemStack input, IItemStack output, float exp){

		ACMTMisc.TASKS.add(new Add(ACMT.toStack(input), ACMT.toStack(output), exp));
	}

	private static class Add implements IAction
	{
		private ItemStack input, output;
		private float exp;

		public Add(ItemStack input, ItemStack output, float exp){
			this.input = input;
			this.output = output;
			this.exp = exp;
		}

		@Override
		public void apply() {

			AbyssalCraftAPI.addTransmutation(input, output, exp);

		}

		@Override
		public String describe() {

			return "Adding Transmutation recipe for " + ACMT.getItemNameSafely(output);
		}
	}

	@ZenMethod
	public static void removeTransmutation(IItemStack input){
		removeTransmutationInput(input);
	}

	@ZenMethod
	public static void removeTransmutationInput(IItemStack input){
		ACMTMisc.TASKS.add(new RemoveInput(ACMT.toStack(input)));
	}

	private static class RemoveInput implements IAction {

		private ItemStack input;

		public RemoveInput(ItemStack input){
			this.input = input;
		}

		@Override
		public void apply() {
			TransmutatorRecipes.instance().getTransmutationList().entrySet().removeIf(e -> APIUtils.areStacksEqual(input, e.getKey()));
		}

		@Override
		public String describe() {

			return "Removing Transmutation recipe with input: " + ACMT.getItemNameSafely(input);
		}
	}

	@ZenMethod
	public static void removeTransmutationOutput(IItemStack output){
		ACMTMisc.TASKS.add(new RemoveOutput(ACMT.toStack(output)));
	}

	private static class RemoveOutput implements IAction {

		private ItemStack output;

		public RemoveOutput(ItemStack output){
			this.output = output;
		}

		@Override
		public void apply() {
			TransmutatorRecipes.instance().getTransmutationList().entrySet().removeIf(e -> APIUtils.areStacksEqual(output, e.getValue()));
		}

		@Override
		public String describe() {

			return "Removing Transmutation recipe(s) for " + ACMT.getItemNameSafely(output);
		}
	}

	@ZenMethod
	public static void addFuel(IItemStack stack, int burnTime) {
		ACMTMisc.TASKS.add(new AddFuel(ACMT.toStack(stack), burnTime));
	}

	private static class AddFuel implements IAction {

		private ItemStack stack;
		private int burnTime;

		public AddFuel(ItemStack stack, int burnTime) {
			this.stack = stack;
			this.burnTime = burnTime;
		}

		@Override
		public void apply() {
			ACMT.TRANSMUTATOR_FUELS.put(stack, burnTime);
		}

		@Override
		public String describe() {

			return String.format("Adding %s as Transmutator fuel with a burntime of %d ", ACMT.getItemNameSafely(stack), burnTime);
		}
	}

	@ZenMethod
	public static void removeFuel(IItemStack stack) {
		ACMTMisc.TASKS.add(new RemoveFuel(ACMT.toStack(stack)));
	}

	private static class RemoveFuel implements IAction {

		private ItemStack stack;

		public RemoveFuel(ItemStack stack) {
			this.stack = stack;
		}

		@Override
		public void apply() {
			ACMT.TRANSMUTATOR_FUELS.put(stack, 0);
		}

		@Override
		public String describe() {

			return String.format("Removing %s as Transmutator fuel", ACMT.getItemNameSafely(stack));
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			TransmutatorRecipes.instance().getTransmutationList().clear();
		}

		@Override
		public String describe() {

			return "Removing all Transmutator recipes";
		}
	}
}