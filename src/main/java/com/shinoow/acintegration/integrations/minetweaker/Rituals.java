package com.shinoow.acintegration.integrations.minetweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

@ZenClass("mods.abyssalcraft.Rituals")
public class Rituals {

	@ZenMethod
	public static void mapDimensionToBookType(int dim, int bookType){
		if(bookType <= 4 && bookType >= 0)
			if(dim != -1 && dim != 1)
				MineTweakerAPI.apply(new AddBookType(dim, bookType));
	}

	@ZenMethod
	public static void mapDimensionToName(int dim, String name){
		if(dim != -1 && dim != 1)
			MineTweakerAPI.apply(new AddName(dim, name));
	}

	@ZenMethod
	public static void mapDimensionToBookTypeAndName(int dim, int bookType, String name){
		mapDimensionToBookType(dim, bookType);
		mapDimensionToName(dim, name);
	}

	private static class AddBookType implements IUndoableAction {

		private int dim, bookType;

		public AddBookType(int dim, int bookType){
			this.dim = dim;
			this.bookType = bookType;
		}

		@Override
		public void apply() {
			RitualRegistry.instance().addDimensionToBookType(dim, bookType);
		}

		@Override
		public String describe() {

			return String.format("Mapped dimension ID %d to Book Type %d", dim, bookType);
		}

		@Override
		public boolean canUndo() {

			return false;
		}

		@Override
		public String describeUndo() {

			return null;
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {}

	}

	private static class AddName implements IUndoableAction {

		private int dim;
		private String name;

		public AddName(int dim, String name){
			this.dim = dim;
			this.name = name;
		}

		@Override
		public void apply() {
			RitualRegistry.instance().addDimensionToName(dim, name);
		}

		@Override
		public String describe() {

			return String.format("Mapped dimension ID %d to the name %s", dim, name);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describeUndo() {

			return String.format("Removing name mapping %s from dimension ID %d", name, dim);
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {
			RitualRegistry.instance().getDimensionNameMappings().remove(dim);
		}

	}
}
