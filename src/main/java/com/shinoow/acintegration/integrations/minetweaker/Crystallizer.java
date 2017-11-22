package com.shinoow.acintegration.integrations.minetweaker;

import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.recipe.CrystallizerRecipes;

import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;

@ZenClass("mods.abyssalcraft.Crystallizer")
public class Crystallizer {

	@ZenMethod
	public static void addCrystallization(IItemStack input, IItemStack output1, IItemStack output2, float exp){

		ACMTMisc.ADDITIONS.add(new Add(ACMT.toStack(input), ACMT.toStack(output1), ACMT.toStack(output2), exp));
	}

	@ZenMethod
	public static void addSingleCrystallization(IItemStack input, IItemStack output, float exp){
		ACMTMisc.ADDITIONS.add(new Add(ACMT.toStack(input), ACMT.toStack(output), exp));
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
		ACMTMisc.REMOVALS.add(new Remove(ACMT.toStack(input)));
	}

	private static class Remove implements IAction {

		private ItemStack input, output1, output2;

		public Remove(ItemStack input){
			this.input = input;
			ItemStack[] outputs = {ItemStack.EMPTY, ItemStack.EMPTY};

			output1 = outputs[0];
			output2 = outputs[1];
		}

		@Override
		public void apply() {
			for(Iterator<Entry<ItemStack, ItemStack[]>> i = CrystallizerRecipes.instance().getCrystallizationList().entrySet().iterator(); i.hasNext();){
				Entry<ItemStack, ItemStack[]> e = i.next();
				if(APIUtils.areStacksEqual(input, e.getKey())){
					i.remove();
					break;
				}
			}
		}

		@Override
		public String describe() {

			return "Removing Crystallization recipe for " + output1.getDisplayName() + (!output2.isEmpty() ? " (and "+ output2.getDisplayName() + ")" : "") + " (input: " + input.getDisplayName() + ")";
		}
	}
}