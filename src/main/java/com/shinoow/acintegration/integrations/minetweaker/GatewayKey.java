package com.shinoow.acintegration.integrations.minetweaker;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;

import crafttweaker.IAction;

@ZenClass("mods.abyssalcraft.GatewayKey")
public class GatewayKey {

	@ZenMethod
	public static void addOverride(int dimId, int type){
		if(type > -1 && type < 3)
			ACMTMisc.ADDITIONS.add(new Add(dimId, type));
	}

	private static class Add implements IAction {

		private int dimId, type;

		public Add(int dimId, int type){
			this.dimId = dimId;
			this.type = type;
		}

		@Override
		public void apply() {
			AbyssalCraftAPI.addGatewayKeyOverride(dimId, type);
		}

		private String getDim(int i){
			switch(i){
			case 0:
				return "The Abyssal Wasteland";
			case 1:
				return "The Dreadlands";
			case 2:
				return "Omothol";
			default:
				return "The Abyssal Wasteland";
			}
		}

		@Override
		public String describe() {

			return String.format("Added a Gateway Key override for dimension %d, allowing creation of portals to %s", dimId, getDim(type));
		}
	}
}
