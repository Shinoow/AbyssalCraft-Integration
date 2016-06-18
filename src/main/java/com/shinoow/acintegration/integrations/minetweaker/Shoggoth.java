package com.shinoow.acintegration.integrations.minetweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.entity.EntityUtil;
import com.shinoow.abyssalcraft.common.util.ACLogger;

@ZenClass("mods.abyssalcraft.shoggoth")
public class Shoggoth {

	@ZenMethod
	public static void addShoggothFood(String clazz){
		try {
			MineTweakerAPI.apply(new AddFood((Class<? extends EntityLivingBase>) Class.forName(clazz)));
		} catch (ClassNotFoundException e) {
			ACLogger.warning("Could not find Entity class %s", clazz);
		}
	}

	@ZenMethod
	public static void addShoggothBlacklist(IItemStack istack){
		ItemStack stack = ACMT.toStack(istack);
		if(Block.getBlockFromItem(stack.getItem()) != null)
			MineTweakerAPI.apply(new AddBlacklist(Block.getBlockFromItem(stack.getItem())));
		else ACLogger.warning("This is not a Block: %s", stack.getDisplayName());
	}

	private static class AddFood implements IUndoableAction {

		private Class<? extends EntityLivingBase> clazz;

		public AddFood(Class<? extends EntityLivingBase> clazz){
			this.clazz = clazz;
		}

		@Override
		public void apply() {

			EntityUtil.addShoggothFood(clazz);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Entity Class " + clazz.getCanonicalName() + " to the Lesser Shoggoth food list";
		}

		@Override
		public String describeUndo() {

			return "Removing Entity Class " + clazz.getCanonicalName() + " from the Lesser Shoggoth food list";
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			EntityUtil.getShoggothFood().remove(clazz);
		}
	}

	private static class AddBlacklist implements IUndoableAction {

		private Block block;

		public AddBlacklist(Block block){
			this.block = block;
		}

		@Override
		public void apply() {

			AbyssalCraftAPI.addShoggothBlacklist(block);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Added Block " + I18n.translateToLocal(block.getUnlocalizedName() + ".name") + " to the Lesser Shoggoth Block Blacklist";
		}

		@Override
		public String describeUndo() {

			return "Removed Block " + I18n.translateToLocal(block.getUnlocalizedName() + ".name") + " from the Lesser Shoggoth Block Blacklist";
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			AbyssalCraftAPI.getShoggothBlockBlacklist().remove(block);
		}
	}
}