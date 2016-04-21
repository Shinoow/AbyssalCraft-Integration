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
import thaumcraft.api.wands.WandCap;
import thaumcraft.common.items.wands.ItemWand;

public class OmotholRodOnUpdate implements IWandRodOnUpdate {

	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);

	@Override
	public void onUpdate(ItemStack itemstack, EntityPlayer player) {
		if(player.worldObj.provider.getDimensionId() == 1){
			if(((ItemWand)itemstack.getItem()).getCap(itemstack) == WandCap.caps.get("void")){
				((ItemWand)itemstack.getItem()).getRod(itemstack).setCapacity(100);
				for(int i = 0; i < primals.length; i++)
					if(((ItemWand)itemstack.getItem()).getVis(itemstack, primals[i])< 10000)
						((ItemWand)itemstack.getItem()).addVis(itemstack, primals[i], 10000, true);
			}
		}
		else if(player.worldObj.provider.getDimensionId() == AbyssalCraft.configDimId3){
			if(((ItemWand)itemstack.getItem()).getCap(itemstack) == WandCap.caps.get("ethaxium")){
				((ItemWand)itemstack.getItem()).getRod(itemstack).setCapacity(100);
				for(int i = 0; i < primals.length; i++)
					if(((ItemWand)itemstack.getItem()).getVis(itemstack, primals[i])< 10000)
						((ItemWand)itemstack.getItem()).addVis(itemstack, primals[i], 10000, true);
			}
		} else
			for(int i = 0; i < primals.length; i++)
				if(((ItemWand)itemstack.getItem()).getVis(itemstack, primals[i]) > 0)
					((ItemWand)itemstack.getItem()).consumeVis(itemstack, player, primals[i], 100, false);
				else ((ItemWand)itemstack.getItem()).getRod(itemstack).setCapacity(0);
	}
}
