package com.shinoow.acintegration.integrations.minetweaker;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.item.ItemUpgradeKit;
import com.shinoow.abyssalcraft.api.recipe.UpgradeKitRecipes;
import com.shinoow.abyssalcraft.common.util.ACLogger;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.UpgradeKit")
public class UpgradeKit {

	@ZenMethod
	public static void addUpgrade(IItemStack kit, IItemStack input, IItemStack output){

		if(ACMT.toStack(kit).getItem() instanceof ItemUpgradeKit)
			MineTweakerAPI.apply(new Add(ACMT.toStack(kit), ACMT.toStack(input), ACMT.toStack(output)));
		else ACLogger.warning("%s is not a Upgrade Kit!", ACMT.toStack(kit).getDisplayName());
	}

	private static class Add implements IUndoableAction
	{
		private ItemStack input, output;
		private ItemUpgradeKit kit;
		private Object recipe;

		public Add(ItemStack kit, ItemStack input, ItemStack output){
			this.kit = (ItemUpgradeKit)kit.getItem();
			this.input = input;
			this.output = output;
			recipe = ACMTJEIUtil.getUpgradeRecipe(this.kit, input, output);
		}

		@Override
		public void apply() {

			AbyssalCraftAPI.addUpgrade(kit, input, output);
			if(recipe != null)
				MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(recipe);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Upgrade Kit recipe for " + output.getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Removing Upgrade Kit recipe for " + output.getDisplayName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			UpgradeKitRecipes.instance().getUpgrades(kit).remove(input);
			if(recipe != null)
				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(recipe);
		}
	}

	@ZenMethod
	public static void removeUpgrade(IItemStack input){
		MineTweakerAPI.apply(new Remove(ACMT.toStack(input)));
	}

	private static class Remove implements IUndoableAction {

		private ItemStack input;

		public Remove(ItemStack input){
			this.input = input;
		}

		Map<ItemUpgradeKit, Map<ItemStack, ItemStack>> recipes;
		List<Object> recipeObjs;

		@Override
		public void apply() {

			recipes = Maps.newHashMap();
			recipeObjs = Lists.newArrayList();

			for(Entry<ItemUpgradeKit, Map<ItemStack, ItemStack>> e : UpgradeKitRecipes.instance().getAllUpgrades().entrySet())
				for(Entry<ItemStack, ItemStack> e1 : e.getValue().entrySet())
					if(areStacksEqual(input, e1.getKey())){
						if(!recipes.containsKey(e1.getKey()))
							recipes.put(e.getKey(), Maps.newHashMap());
						recipes.get(e.getKey()).put(e1.getKey(), e1.getValue());
					}
			for(Entry<ItemUpgradeKit, Map<ItemStack, ItemStack>> e : recipes.entrySet())
				for(Entry<ItemStack, ItemStack> e1 : e.getValue().entrySet()){
					UpgradeKitRecipes.instance().getUpgrades(e.getKey()).remove(e1.getKey(), e1.getValue());
					recipeObjs.add(ACMTJEIUtil.getUpgradeRecipe(e.getKey(), e1.getKey(), e1.getValue()));
				}

			for(Object o : recipeObjs)
				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(o);
		}

		private boolean areStacksEqual(ItemStack input, ItemStack compare)
		{
			return compare.getItem() == input.getItem() && (compare.getItemDamage() == OreDictionary.WILDCARD_VALUE || compare.getItemDamage() == input.getItemDamage());
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing " + (recipes != null ? recipes.size() : 0) + " Upgrade Kit recipe(s) for " + input.getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Re-adding " + (recipes != null ? recipes.size() : 0) + " Upgrade Kit recipe(s) for " + input.getDisplayName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {
			if(recipes != null)
				for(Entry<ItemUpgradeKit, Map<ItemStack, ItemStack>> e : recipes.entrySet())
					for(Entry<ItemStack, ItemStack> e1 : e.getValue().entrySet())
						AbyssalCraftAPI.addUpgrade(e.getKey(), e1.getKey(), e1.getValue());
			if(recipeObjs != null)
				for(Object o : recipeObjs)
					MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(o);
		}
	}
}