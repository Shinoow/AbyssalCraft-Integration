package com.shinoow.acintegration.integrations.minetweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;
import com.shinoow.abyssalcraft.lib.util.RitualUtil;

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

	@ZenMethod
	public static void modifyRitualBookType(String name, int bookType){
		MineTweakerAPI.apply(new ModifyRitual(name, 0, bookType));
	}

	@ZenMethod
	public static void modifyRitualDimension(String name, int dimension){
		MineTweakerAPI.apply(new ModifyRitual(name, 1, dimension));
	}

	@ZenMethod
	public static void modifyRitualSacrificeRequirement(String name, boolean requiresSacrifice){
		MineTweakerAPI.apply(new ModifyRitual(name, 2, requiresSacrifice));
	}

	@ZenMethod
	public static void modifyRitualEnergyRequirement(String name, float requiredEnergy){
		MineTweakerAPI.apply(new ModifyRitual(name, 3, requiredEnergy));
	}

	@ZenMethod
	public static void modifyRitualSacrifice(String name, IIngredient sacrifice){
		MineTweakerAPI.apply(new ModifyRitual(name, 4, ACMT.toObject(sacrifice)));
	}

	@ZenMethod
	public static void modifyRitualNbtSensitivity(String name, boolean nbtSensitive){
		MineTweakerAPI.apply(new ModifyRitual(name, 5, nbtSensitive));
	}

	@ZenMethod
	public static void modifyRitualNbtSensitivitySacrifice(String name, boolean nbtSensitiveSacrifice){
		MineTweakerAPI.apply(new ModifyRitual(name, 6, nbtSensitiveSacrifice));
	}

	@ZenMethod
	public static void modifyRitualOfferings(String name, IIngredient[] offerings){
		MineTweakerAPI.apply(new ModifyRitual(name, 7, ACMT.toObjects(offerings)));
	}

	@ZenMethod
	public static void modifyRitualReplaceOffering(String name, IIngredient original, IIngredient replace, boolean nbt){
		MineTweakerAPI.apply(new ModifyRitual(name, 8, new Object[]{ACMT.toObject(original), ACMT.toObject(replace), nbt}));
	}

	private static class ModifyRitual implements IUndoableAction {

		private int type;
		private Object param;
		private String name;
		private Object old;

		public ModifyRitual(String name, int type, Object param){
			this.name = name;
			this.type = type;
			this.param = param;
		}

		@Override
		public void apply() {

			NecronomiconRitual r = getRitual(name);

			if(r == null) return;

			MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(r);

			switch(type){
			case 0:
				old = r.getBookType();
				RitualUtil.modifyRitualBookType(name, (int)param);
				break;
			case 1:
				old = r.getDimension();
				RitualUtil.modifyRitualDimension(name, (int)param);
				break;
			case 2:
				old = r.requiresSacrifice();
				RitualUtil.modifyRitualSacrificeRequirement(name, (boolean)param);
				break;
			case 3:
				old = r.getReqEnergy();
				RitualUtil.modifyRitualEnergyRequirement(name, (float)param);
				break;
			case 4:
				old = r.getSacrifice();
				RitualUtil.modifyRitualSacrifice(name, param);
				break;
			case 5:
				old = r.isNBTSensitive();
				RitualUtil.modifyRitualNbtSensitivity(name, (boolean)param);
				break;
			case 6:
				old = r.isSacrificeNBTSensitive();
				RitualUtil.modifyRitualNbtSensitivitySacrifice(name, (boolean)param);
				break;
			case 7:
				old = r.getOfferings();
				RitualUtil.modifyRitualOfferings(name, (Object[])param);
				break;
			case 8:
				RitualUtil.modifyRitualReplaceOffering(name, ((Object[])param)[0], ((Object[])param)[1], (boolean)((Object[])param)[2]);
				break;
			}

			MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(r);
		}

		private NecronomiconRitual getRitual(String name){
			for(NecronomiconRitual r : RitualRegistry.instance().getRituals())
				if(r.getUnlocalizedName().substring("ac.ritual.".length()).equals(name))
					return r;
			return null;
		}

		private String getProperty(int i){
			switch(i){
			case 0:
				return "Book Type";
			case 1:
				return "Dimension";
			case 2:
				return "Sacrifice requirement";
			case 3:
				return "Required Energy";
			case 4:
				return "Altar Sacrifice";
			case 5:
				return "NBT sensitive offerings";
			case 6:
				return "NBT sensitive altar sacrifice";
			case 7:
				return "Offerings";
			default:
				return "Unknown";
			}
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			if(type == 8)
				return String.format("Replacing offering %s in ritual %s with %s (NBT sensitivity: %s)", ((Object[])param)[0], name, ((Object[])param)[1], ((Object[])param)[2]);
			else return String.format("Modifying property %s of ritual %s with value %s", getProperty(type), name, param);
		}

		@Override
		public String describeUndo() {

			if(type == 8)
				return String.format("Reverting offering %s in ritual %s back to %s (NBT sensitivity: %s)", ((Object[])param)[1], name, ((Object[])param)[0], ((Object[])param)[2]);
			else return String.format("Reverting property %s of ritual %s back to &s", getProperty(type), name, old);
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			NecronomiconRitual r = getRitual(name);

			MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(r);

			switch(type){
			case 0:
				RitualUtil.modifyRitualBookType(name, (int)old);
				break;
			case 1:
				RitualUtil.modifyRitualDimension(name, (int)old);
				break;
			case 2:
				RitualUtil.modifyRitualSacrificeRequirement(name, (boolean)old);
				break;
			case 3:
				RitualUtil.modifyRitualEnergyRequirement(name, (float)old);
				break;
			case 4:
				RitualUtil.modifyRitualSacrifice(name, old);
				break;
			case 5:
				RitualUtil.modifyRitualNbtSensitivity(name, (boolean)old);
				break;
			case 6:
				RitualUtil.modifyRitualNbtSensitivitySacrifice(name, (boolean)old);
				break;
			case 7:
				RitualUtil.modifyRitualOfferings(name, (Object[])old);
				break;
			case 8:
				RitualUtil.modifyRitualReplaceOffering(name, ((Object[])param)[1], ((Object[])param)[0], (boolean)((Object[])param)[2]);
				break;
			}

			MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(r);
		}

	}
}
