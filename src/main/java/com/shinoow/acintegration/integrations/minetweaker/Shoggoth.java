package com.shinoow.acintegration.integrations.minetweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import net.minecraft.entity.EntityLivingBase;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
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

	private static class AddFood implements IUndoableAction {

		private Class<? extends EntityLivingBase> clazz;

		public AddFood(Class<? extends EntityLivingBase> clazz){
			this.clazz = clazz;
		}

		@Override
		public void apply() {

			AbyssalCraftAPI.addShoggothFood(clazz);
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

			AbyssalCraftAPI.getShoggothFood().remove(clazz);
		}
	}
}