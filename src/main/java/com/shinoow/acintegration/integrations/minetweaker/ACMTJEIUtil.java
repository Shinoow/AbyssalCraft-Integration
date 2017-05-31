package com.shinoow.acintegration.integrations.minetweaker;

import java.util.Collections;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import com.shinoow.abyssalcraft.api.item.ItemUpgradeKit;
import com.shinoow.abyssalcraft.integration.jei.crystallizer.CrystallizationRecipe;
import com.shinoow.abyssalcraft.integration.jei.transmutator.TransmutationRecipe;
import com.shinoow.abyssalcraft.integration.jei.upgrades.UpgradeRecipeWrapper;

public class ACMTJEIUtil {

	public static Object getTransRecipe(ItemStack input, ItemStack output, float exp){
		if(!Loader.isModLoaded("jei")) return null;
		return new TransmutationRecipe(Collections.singletonList(input), output, exp);
	}

	public static Object getCrystRecipe(ItemStack input, ItemStack output1, ItemStack output2, float exp){
		if(!Loader.isModLoaded("jei")) return null;
		return new CrystallizationRecipe(Collections.singletonList(input), output1, output2, exp);
	}

	public static Object getUpgradeRecipe(ItemUpgradeKit kit, ItemStack input, ItemStack output){
		if(!Loader.isModLoaded("jei")) return null;
		return new UpgradeRecipeWrapper(kit, input, output);
	}
}
