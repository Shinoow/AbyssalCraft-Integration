package com.shinoow.acintegration.integrations.minetweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.acintegration.integrations.gamestages.ACGS;

@ZenClass("mods.abyssalcraft.RitualStages")
public class RitualStages {

	@ZenMethod
	public static void addRitualStage(String stage, String ritual){
		MineTweakerAPI.apply(new Add(stage, ritual));
	}

	private static class Add implements IUndoableAction {

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

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describeUndo() {

			return null;
		}

		@Override
		public Object getOverrideKey() {

			return String.format("Ritual %s has been removed from stage %s", ritual, stage);
		}

		@Override
		public void undo() {
			ACGS.RITUAL_MAP.remove(ritual);
		}

	}
}
