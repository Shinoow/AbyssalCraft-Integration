package com.shinoow.acintegration.integrations.minetweaker;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.TransmutatorRecipes;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.Transmutator")
public class Transmutator {

	@ZenMethod
	public static void addTransmutation(IItemStack input, IItemStack output, float exp){

		MineTweakerAPI.apply(new Add(ACMT.toStack(input), ACMT.toStack(output), exp));
	}

	private static class Add implements IUndoableAction
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
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Transmutation recipe for " + output.getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Removing Transmutation recipe for " + output.getDisplayName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			TransmutatorRecipes.instance().getTransmutationList().remove(input);
		}
	}

	@ZenMethod
	public static void removeTransmutation(IItemStack input){
		MineTweakerAPI.apply(new Remove(ACMT.toStack(input)));
	}

	private static class Remove implements IUndoableAction {

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
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing Transmutation recipe for " + output.getDisplayName() + " (input: " + input.getDisplayName() + ")";
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Transmutation recipe for " + output.getDisplayName() + " (input: " + input.getDisplayName() + ")";
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {
			if(input != null && output != null){
				AbyssalCraftAPI.addTransmutation(input, output, TransmutatorRecipes.instance().getExperience(output));
			}
		}	
	}
}