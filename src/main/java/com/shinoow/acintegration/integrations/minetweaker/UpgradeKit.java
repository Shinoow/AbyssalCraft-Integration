package com.shinoow.acintegration.integrations.minetweaker;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.item.ItemUpgradeKit;
import com.shinoow.abyssalcraft.api.recipe.UpgradeKitRecipes;
import com.shinoow.abyssalcraft.common.util.ACLogger;

import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.UpgradeKit")
public class UpgradeKit {

	@ZenMethod
	public static void addUpgrade(IItemStack kit, IItemStack input, IItemStack output){

		if(ACMT.toStack(kit).getItem() instanceof ItemUpgradeKit)
			ACMTMisc.TASKS.add(new Add(ACMT.toStack(kit), ACMT.toStack(input), ACMT.toStack(output)));
		else ACLogger.warning("%s is not a Upgrade Kit!", ACMT.toStack(kit).getDisplayName());
	}

	private static class Add implements IAction
	{
		private ItemStack input, output;
		private ItemUpgradeKit kit;

		public Add(ItemStack kit, ItemStack input, ItemStack output){
			this.kit = (ItemUpgradeKit)kit.getItem();
			this.input = input;
			this.output = output;
		}

		@Override
		public void apply() {

			AbyssalCraftAPI.addUpgrade(kit, input, output);
		}

		@Override
		public String describe() {

			return "Adding Upgrade Kit recipe for " + output.getDisplayName();
		}
	}

	@ZenMethod
	public static void removeUpgrade(IItemStack input){
		ACMTMisc.TASKS.add(new Remove(ACMT.toStack(input)));
	}

	private static class Remove implements IAction {

		private ItemStack input;

		public Remove(ItemStack input){
			this.input = input;
		}

		Map<ItemUpgradeKit, Map<ItemStack, ItemStack>> recipes;

		@Override
		public void apply() {

			recipes = Maps.newHashMap();

			for(Entry<ItemUpgradeKit, Map<ItemStack, ItemStack>> e : UpgradeKitRecipes.instance().getAllUpgrades().entrySet())
				for(Entry<ItemStack, ItemStack> e1 : e.getValue().entrySet())
					if(APIUtils.areStacksEqual(input, e1.getKey())){
						if(!recipes.containsKey(e.getKey()))
							recipes.put(e.getKey(), Maps.newHashMap());
						recipes.get(e.getKey()).put(e1.getKey(), e1.getValue());
					}
			for(Entry<ItemUpgradeKit, Map<ItemStack, ItemStack>> e : recipes.entrySet())
				for(Entry<ItemStack, ItemStack> e1 : e.getValue().entrySet())
					UpgradeKitRecipes.instance().getUpgrades(e.getKey()).remove(e1.getKey(), e1.getValue());
		}

		@Override
		public String describe() {

			return "Removing " + (recipes != null ? recipes.size() : 0) + " Upgrade Kit recipe(s) for " + input.getDisplayName();
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			UpgradeKitRecipes.instance().getAllUpgrades().entrySet().forEach(e -> e.getValue().clear());
		}

		@Override
		public String describe() {

			return "Removing all Upgrade Kit recipes";
		}
	}
}