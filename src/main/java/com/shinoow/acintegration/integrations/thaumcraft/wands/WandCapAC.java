/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2015 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
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
