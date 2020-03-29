package com.shinoow.acintegration.integrations.minetweaker;

import java.util.Iterator;
import java.util.Map.Entry;

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

			return "Adding Transmutation recipe for " + output.getDisplayName();
		}
	}

	@ZenMethod
	public static void removeTransmutation(IItemStack input){
		ACMTMisc.TASKS.add(new Remove(ACMT.toStack(input)));
	}

	private static class Remove implements IAction {

		private ItemStack input, output;

		public Remove(ItemStack input){
			this.input = input;
			output = ItemStack.EMPTY;
		}

		@Override
		public void apply() {
			for(Iterator<Entry<ItemStack, ItemStack>> i = TransmutatorRecipes.instance().getTransmutationList().entrySet().iterator(); i.hasNext();){
				Entry<ItemStack, ItemStack> e = i.next();
				if(APIUtils.areStacksEqual(input, e.getKey())){
					i.remove();
					break;
				}
			}
		}

		@Override
		public String describe() {

			return "Removing Transmutation recipe for " + output.getDisplayName() + " (input: " + input.getDisplayName() + ")";
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

			return String.format("Added %s as Transmutator fuel with a burntime of %d ", stack.getDisplayName(), burnTime);
		}
	}
}