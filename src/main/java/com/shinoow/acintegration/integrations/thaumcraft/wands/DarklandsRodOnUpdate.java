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

import com.shinoow.abyssalcraft.AbyssalCraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWand;

public class DarklandsRodOnUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

	@Override
	public void onUpdate(ItemStack itemstack, EntityPlayer player) {
		if(player.ticksExisted % 100 == 0)
			if(player.worldObj.getBiomeGenForCoords(player.getPosition()) == AbyssalCraft.Darklands ||
			player.worldObj.getBiomeGenForCoords(player.getPosition()) == AbyssalCraft.DarklandsForest ||
			player.worldObj.getBiomeGenForCoords(player.getPosition()) == AbyssalCraft.DarklandsHills ||
			player.worldObj.getBiomeGenForCoords(player.getPosition()) == AbyssalCraft.DarklandsPlains ||
			player.worldObj.getBiomeGenForCoords(player.getPosition()) == AbyssalCraft.DarklandsMountains)
				for(int x = 0;x < primals.length;x++)
					if(((ItemWand)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWand)itemstack.getItem()).getMaxVis(itemstack) / 10)
						((ItemWand)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
	}
}
