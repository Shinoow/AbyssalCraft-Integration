package com.shinoow.acintegration.integrations.minetweaker;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.acintegration.integrations.gamestages.ACGS;

import crafttweaker.IAction;

@ZenClass("mods.abyssalcraft.RitualStages")
public class RitualStages {

	@ZenMethod
	public static void addRitualStage(String stage, String ritual){
		ACMTMisc.TASKS.add(new Add(stage, ritual));
	}

	private static class Add implements IAction {

		private String stage, ritual;

		public Add(String stage, String ritual){
			this.stage = stage;
			this.ritual = ritual.startsWith("ac.ritual.") ? ritual.substring(10) : ritual;
		}

		@Override
		public void apply() {
			ACGS.RITUAL_MAP.put(ritual, stage);
		}

		@Override
		public String describe() {

			return String.format("Ritual %s has been added to stage %s", ritual, stage);
		}

	}
}
