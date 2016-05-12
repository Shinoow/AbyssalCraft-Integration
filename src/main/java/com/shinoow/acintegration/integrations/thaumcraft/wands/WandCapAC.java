package com.shinoow.acintegration.integrations.thaumcraft.wands;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.WandCap;

public class WandCapAC extends WandCap {

	public WandCapAC(String tag, float discount, ItemStack item, int craftCost) {
		super(tag, discount, 0, item, craftCost, new ResourceLocation("acintegration","model/wands/wand_cap_"+tag));
	}

	public WandCapAC(String tag, float discount, List<Aspect> specialAspects, float discountSpecial, ItemStack item, int craftCost) {
		super(tag, discount, 0, specialAspects, discountSpecial, item, craftCost, new ResourceLocation("acintegration","model/wands/wand_cap_"+tag));
	}

	@Override
	public String getResearch() {
		return "";
	}
}
