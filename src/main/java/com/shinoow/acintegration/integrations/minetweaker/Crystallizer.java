package com.shinoow.acintegration.integrations.minetweaker;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.CrystallizerRecipes;

import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.Crystallizer")
public class Crystallizer {

	@ZenMethod
	public static void addCrystallization(IItemStack input, IItemStack output1, IItemStack output2, float exp){

		ACMTMisc.TASKS.add(new Add(ACMT.toStack(input), ACMT.toStack(output1), ACMT.toStack(output2), exp));
	}

	@ZenMethod
	public static void addSingleCrystallization(IItemStack input, IItemStack output, float exp){
		ACMTMisc.TASKS.add(new Add(ACMT.toStack(input), ACMT.toStack(output), exp));
	}

	private static class Add implements IAction
	{
		private ItemStack input, output1, output2;
		private float exp;
		private boolean isSingle;

		public Add(ItemStack input, ItemStack output1, ItemStack output2, float exp){
			this.input = input;
			this.output1 = output1;
			this.output2 = output2;
			this.exp = exp;
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
		}

		@Override
		public String describe() {

			return "Adding Crystallization recipe for " + output1.getDisplayName() + (!output2.isEmpty() ? " (and "+ output2.getDisplayName() + ")" : "");
		}
	}

	@ZenMethod
	public static void removeCrystallization(IItemStack input){
		removeCrystallizationInput(input);
	}

	@ZenMethod
	public static void removeCrystallizationInput(IItemStack input){
		ACMTMisc.TASKS.add(new RemoveInput(ACMT.toStack(input)));
	}

	private static class RemoveInput implements IAction {

		private ItemStack input;

		public RemoveInput(ItemStack input){
			this.input = input;
		}

		@Override
		public void apply() {
			CrystallizerRecipes.instance().getCrystallizationList().entrySet().removeIf(e -> APIUtils.areStacksEqual(input, e.getKey()));
		}

		@Override
		public String describe() {

			return "Removing Crystallization recipe with input: " + input.getDisplayName();
		}
	}

	@ZenMethod
	public static void removeCrystallizationOutput(IItemStack output){
		ACMTMisc.TASKS.add(new RemoveOutput(ACMT.toStack(output)));
	}

	private static class RemoveOutput implements IAction {

		private ItemStack output;

		public RemoveOutput(ItemStack output){

			this.output = output;
		}

		@Override
		public void apply() {
			CrystallizerRecipes.instance().getCrystallizationList().entrySet()
			.removeIf(e -> APIUtils.areStacksEqual(output, e.getValue()[0]) || APIUtils.areStacksEqual(output, e.getValue()[1]));
		}

		@Override
		public String describe() {

			return "Removing Crystallization recipe(s) for " + output.getDisplayName();
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
			ACMT.CRYSTALLIZER_FUELS.put(stack, burnTime);
		}

		@Override
		public String describe() {

			return String.format("Adding %s as Crystallizer fuel with a burntime of %d ", stack.getDisplayName(), burnTime);
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
			ACMT.CRYSTALLIZER_FUELS.put(stack, 0);
		}

		@Override
		public String describe() {

			return String.format("Removing %s as Crystallizer fuel", stack.getDisplayName());
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			CrystallizerRecipes.instance().getCrystallizationList().clear();
		}

		@Override
		public String describe() {

			return "Removing all Crystallizer recipes";
		}
	}
}