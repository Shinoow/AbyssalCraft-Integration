package com.shinoow.acintegration.integrations.minetweaker;

import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.TransmutatorRecipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;

@ZenClass("mods.abyssalcraft.Transmutator")
public class Transmutator {

	@ZenMethod
	public static void addTransmutation(IItemStack input, IItemStack output, float exp){

		ACMTMisc.ADDITIONS.add(new Add(ACMT.toStack(input), ACMT.toStack(output), exp));
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
		CraftTweakerAPI.apply(new Remove(ACMT.toStack(input)));
	}

	private static class Remove implements IAction {

		private ItemStack input, output;

		public Remove(ItemStack input){
			this.input = input;
			output = TransmutatorRecipes.instance().getTransmutationResult(input);
		}

		@Override
		public void apply() {
			TransmutatorRecipes.instance().getTransmutationList().remove(input);
		}

		@Override
		public String describe() {

			return "Removing Transmutation recipe for " + output.getDisplayName() + " (input: " + input.getDisplayName() + ")";
		}
	}
}