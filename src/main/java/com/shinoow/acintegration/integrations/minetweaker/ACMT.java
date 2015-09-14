package com.shinoow.acintegration.integrations.minetweaker;

import static minetweaker.api.minecraft.MineTweakerMC.getItemStack;

import com.shinoow.abyssalcraft.api.integration.IACPlugin;

import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;

public class ACMT implements IACPlugin {

	@Override
	public String getModName() {

		return "MineTweaker 3";
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {
		MineTweakerAPI.registerClass(Crystallizer.class);
		MineTweakerAPI.registerClass(Transmutator.class);
		MineTweakerAPI.registerClass(CreationRitual.class);
		MineTweakerAPI.registerClass(InfusionRitual.class);
	}

	// Start of borrowed code from Immersive Engineering
	// https://github.com/BluSunrize/ImmersiveEngineering/blob/master/src/main/java/blusunrize/immersiveengineering/common/util/compat/minetweaker/MTHelper.java

	public static ItemStack toStack(IItemStack iStack)
	{
		return getItemStack(iStack);
	}

	// End of borrowed code
}