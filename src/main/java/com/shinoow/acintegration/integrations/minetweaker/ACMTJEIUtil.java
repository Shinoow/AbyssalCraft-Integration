package com.shinoow.acintegration.integrations.minetweaker;

import java.util.Collections;
import java.util.List;

import mezz.jei.Internal;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.gui.Focus;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import com.shinoow.abyssalcraft.api.item.ItemUpgradeKit;
import com.shinoow.abyssalcraft.integration.jei.AbyssalCraftRecipeCategoryUid;
import com.shinoow.abyssalcraft.integration.jei.crystallizer.CrystallizationRecipe;
import com.shinoow.abyssalcraft.integration.jei.transmutator.TransmutationRecipe;
import com.shinoow.abyssalcraft.integration.jei.upgrades.UpgradeRecipeWrapper;

public class ACMTJEIUtil {

	public static Object getTransRecipe(ItemStack input, ItemStack output, float exp){
		if(!Loader.isModLoaded("jei")) return null;
		return new TransmutationRecipe(Collections.singletonList(input), output, exp);
	}

	public static Object getTransRecipe(ItemStack input){
		if(!Loader.isModLoaded("jei")) return null;

		if(Internal.getRuntime() != null){
			List<IRecipeCategory> l = Internal.getRuntime().getRecipeRegistry().getRecipeCategories(Collections.singletonList(AbyssalCraftRecipeCategoryUid.TRANSMUTATION));
			if(l.isEmpty()) return null;
			List<IRecipeWrapper> stuff = Internal.getRuntime().getRecipeRegistry().getRecipeWrappers(l.get(0), new Focus<ItemStack>(IFocus.Mode.INPUT, input));
			return !stuff.isEmpty() ? stuff.get(0) : null;
		}
		return null;
	}

	public static Object getCrystRecipe(ItemStack input, ItemStack output1, ItemStack output2, float exp){
		if(!Loader.isModLoaded("jei")) return null;
		return new CrystallizationRecipe(Collections.singletonList(input), output1, output2, exp);
	}

	public static Object getCrystRecipe(ItemStack input){
		if(!Loader.isModLoaded("jei")) return null;

		if(Internal.getRuntime() != null){
			List<IRecipeCategory> l = Internal.getRuntime().getRecipeRegistry().getRecipeCategories(Collections.singletonList(AbyssalCraftRecipeCategoryUid.CRYSTALLIZATION));
			if(l.isEmpty()) return null;
			List<IRecipeWrapper> stuff = Internal.getRuntime().getRecipeRegistry().getRecipeWrappers(l.get(0), new Focus<ItemStack>(IFocus.Mode.INPUT, input));
			return !stuff.isEmpty() ? stuff.get(0) : null;
		}
		return null;
	}

	public static Object getUpgradeRecipe(ItemUpgradeKit kit, ItemStack input, ItemStack output){
		if(!Loader.isModLoaded("jei")) return null;
		return new UpgradeRecipeWrapper(kit, input, output);
	}

	public static Object getUpgradeRecipe(ItemStack input){
		if(!Loader.isModLoaded("jei")) return null;

		if(Internal.getRuntime() != null){
			List<IRecipeCategory> l = Internal.getRuntime().getRecipeRegistry().getRecipeCategories(Collections.singletonList(AbyssalCraftRecipeCategoryUid.UPGRADE));
			if(l.isEmpty()) return null;
			List<IRecipeWrapper> stuff = Internal.getRuntime().getRecipeRegistry().getRecipeWrappers(l.get(0), new Focus<ItemStack>(IFocus.Mode.INPUT, input));
			return !stuff.isEmpty() ? stuff.get(0) : null;
		}
		return null;
	}
}
