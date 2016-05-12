package com.shinoow.acintegration.integrations.thaumcraft.creativetabs;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.acintegration.integrations.thaumcraft.ACTC;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabACThaum extends CreativeTabs {

	public static TabACThaum instance;

	public ArrayList<ItemStack> creativeTabQueue = new ArrayList<ItemStack>();
	List list = new ArrayList();

	public TabACThaum() {
		super("acthaum");
	}

	@Override
	public Item getTabIconItem() {

		return ACTC.wandCap;
	}

	@Override
	public void displayAllReleventItems(List list) {
		list.addAll(this.list);
	}

	public void addWands(){
		list.add(ACTC.darkWand);
		list.add(ACTC.corWand);
		list.add(ACTC.dreadWand);
		list.add(ACTC.omotholWand);
		list.add(ACTC.endWand);
	}

	public void addItem(Item item) {
		item.getSubItems(item, this, creativeTabQueue);
	}

	public void addBlock(Block block) {
		block.getSubBlocks(Item.getItemFromBlock(block), this, creativeTabQueue);
	}

	public void addAllItemsAndBlocks() {
		list.addAll(creativeTabQueue);
	}
}
