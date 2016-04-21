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
package com.shinoow.acintegration.integrations.thaumcraft.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.shinoow.acintegration.integrations.thaumcraft.creativetabs.TabACThaum;

public class ItemACThaumcraft extends Item {

	private String[] names;

	/**
	 * Non-metadata item
	 * @param name unlocalized name
	 */
	public ItemACThaumcraft(String name){
		this(name, false, (String[])null);
	}

	/**
	 * Metadata item
	 * @param name unlocalized name
	 * @param meta if it's a metadata item
	 * @param names additional names (for metadata)
	 */
	public ItemACThaumcraft(String name, boolean meta, String...names) {
		super();
		this.names = names;
		if(names != null)
			setMaxDamage(0);
		setUnlocalizedName(name);
		setHasSubtypes(meta);
		if(FMLCommonHandler.instance().getEffectiveSide().isClient())
			TabACThaum.instance.addItem(this);
	}

	@Override
	public int getMetadata(int meta) {
		return names == null ? 0 : meta;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1Item, CreativeTabs par2CreativeTab, List par3List){
		if(names != null)
			for(int i = 0; i < names.length; ++i)
				par3List.add(new ItemStack(par1Item, 1, i));
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {
		if(names == null)
			return StatCollector.translateToLocal(getUnlocalizedName() + ".name");
		else return StatCollector.translateToLocal(getUnlocalizedName() + "." + names[par1ItemStack.getItemDamage()] + ".name");
	}
}
