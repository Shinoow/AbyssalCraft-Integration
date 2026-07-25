package com.shinoow.acintegration.integrations.crafttweaker;

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

		ACCTMisc.TASKS.add(new Add(ACCT.toStack(input), ACCT.toStack(output), exp));
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

			return "Adding Transmutation recipe for " + ACCT.getItemNameSafely(output);
		}
	}

	@ZenMethod
	public static void removeTransmutation(IItemStack input){
		removeTransmutationInput(input);
	}

	@ZenMethod
	public static void removeTransmutationInput(IItemStack input){
		ACCTMisc.TASKS.add(new RemoveInput(ACCT.toStack(input)));
	}

	private static class RemoveInput implements IAction {

		private ItemStack input;

		public RemoveInput(ItemStack input){
			this.input = input;
		}

		@Override
		public void apply() {
			TransmutatorRecipes.instance().removeTransmutationInput(input);
		}

		@Override
		public String describe() {

			return "Removing Transmutation recipe with input: " + ACCT.getItemNameSafely(input);
		}
	}

	@ZenMethod
	public static void removeTransmutationOutput(IItemStack output){
		ACCTMisc.TASKS.add(new RemoveOutput(ACCT.toStack(output)));
	}

	private static class RemoveOutput implements IAction {

		private ItemStack output;

		public RemoveOutput(ItemStack output){
			this.output = output;
		}

		@Override
		public void apply() {
			TransmutatorRecipes.instance().removeTransmutation(output);
		}

		@Override
		public String describe() {

			return "Removing Transmutation recipe(s) for " + ACCT.getItemNameSafely(output);
		}
	}

	@ZenMethod
	public static void addFuel(IItemStack stack, int burnTime) {
		ACCTMisc.TASKS.add(new AddFuel(ACCT.toStack(stack), burnTime));
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
			ACCT.TRANSMUTATOR_FUELS.put(stack, burnTime);
		}

		@Override
		public String describe() {

			return String.format("Adding %s as Transmutator fuel with a burntime of %d ", ACCT.getItemNameSafely(stack), burnTime);
		}
	}

	@ZenMethod
	public static void removeFuel(IItemStack stack) {
		ACCTMisc.TASKS.add(new RemoveFuel(ACCT.toStack(stack)));
	}

	private static class RemoveFuel implements IAction {

		private ItemStack stack;

		public RemoveFuel(ItemStack stack) {
			this.stack = stack;
		}

		@Override
		public void apply() {
			ACCT.TRANSMUTATOR_FUELS.put(stack, 0);
		}

		@Override
		public String describe() {

			return String.format("Removing %s as Transmutator fuel", ACCT.getItemNameSafely(stack));
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACCTMisc.TASKS.add(new RemoveAll());
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