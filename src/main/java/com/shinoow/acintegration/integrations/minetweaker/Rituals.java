package com.shinoow.acintegration.integrations.minetweaker;

import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;
import com.shinoow.abyssalcraft.lib.util.RitualUtil;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.Rituals")
public class Rituals {

	@ZenMethod
	public static void mapDimensionToBookType(int dim, int bookType){
		if(bookType <= 4 && bookType >= 0)
			if(dim != -1 && dim != 1)
				ACMTMisc.TASKS.add(new AddBookType(dim, bookType));
	}

	@ZenMethod
	public static void mapDimensionToName(int dim, String name){
		if(dim != -1 && dim != 1)
			ACMTMisc.TASKS.add(new AddName(dim, name));
	}

	@ZenMethod
	public static void mapDimensionToBookTypeAndName(int dim, int bookType, String name){
		mapDimensionToBookType(dim, bookType);
		mapDimensionToName(dim, name);
	}

	private static class AddBookType implements IAction {

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

	}

	private static class AddName implements IAction {

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

	}

	@ZenMethod
	public static void modifyRitualBookType(String name, int bookType){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 0, bookType));
	}

	@ZenMethod
	public static void modifyRitualDimension(String name, int dimension){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 1, dimension));
	}

	@ZenMethod
	public static void modifyRitualSacrificeRequirement(String name, boolean requiresSacrifice){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 2, requiresSacrifice));
	}

	@ZenMethod
	public static void modifyRitualEnergyRequirement(String name, float requiredEnergy){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 3, requiredEnergy));
	}

	@ZenMethod
	public static void modifyRitualSacrifice(String name, IIngredient sacrifice){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 4, ACMT.toObject(sacrifice)));
	}

	@ZenMethod
	public static void modifyRitualNbtSensitivity(String name, boolean nbtSensitive){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 5, nbtSensitive));
	}

	@ZenMethod
	public static void modifyRitualNbtSensitivitySacrifice(String name, boolean nbtSensitiveSacrifice){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 6, nbtSensitiveSacrifice));
	}

	@ZenMethod
	public static void modifyRitualOfferings(String name, IIngredient[] offerings){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 7, ACMT.toObjects(offerings)));
	}

	@ZenMethod
	public static void modifyRitualReplaceOffering(String name, IIngredient original, IIngredient replace, boolean nbt){
		ACMTMisc.TASKS.add(new ModifyRitual(name, 8, new Object[]{ACMT.toObject(original), ACMT.toObject(replace), nbt}));
	}

	private static class ModifyRitual implements IAction {

		private int type;
		private Object param;
		private String name;

		public ModifyRitual(String name, int type, Object param){
			this.name = name;
			this.type = type;
			this.param = param;
		}

		@Override
		public void apply() {

			switch(type){
			case 0:
				RitualUtil.modifyRitualBookType(name, (int)param);
				break;
			case 1:
				RitualUtil.modifyRitualDimension(name, (int)param);
				break;
			case 2:
				RitualUtil.modifyRitualSacrificeRequirement(name, (boolean)param);
				break;
			case 3:
				RitualUtil.modifyRitualEnergyRequirement(name, (float)param);
				break;
			case 4:
				RitualUtil.modifyRitualSacrifice(name, param);
				break;
			case 5:
				RitualUtil.modifyRitualNbtSensitivity(name, (boolean)param);
				break;
			case 6:
				RitualUtil.modifyRitualNbtSensitivitySacrifice(name, (boolean)param);
				break;
			case 7:
				RitualUtil.modifyRitualOfferings(name, (Object[])param);
				break;
			case 8:
				RitualUtil.modifyRitualReplaceOffering(name, ((Object[])param)[0], ((Object[])param)[1], (boolean)((Object[])param)[2]);
				break;
			}
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
		public String describe() {

			if(type == 8)
				return String.format("Replacing offering %s in ritual %s with %s (NBT sensitivity: %s)", ((Object[])param)[0], name, ((Object[])param)[1], ((Object[])param)[2]);
			else return String.format("Modifying property %s of ritual %s with value %s", getProperty(type), name, param);
		}
	}

	@ZenMethod
	public static void removeRitual(String name) {
		ACMTMisc.TASKS.add(new Remove(name));
	}

	private static class Remove implements IAction {

		private String name;

		public Remove(String name) {
			this.name = name;
		}

		@Override
		public void apply() {
			RitualRegistry.instance().getRituals().removeIf(r -> r.getUnlocalizedName().substring("ac.ritual.".length()).equals(name));
		}

		@Override
		public String describe() {

			return "Removing ritual with the following name: " + name;
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			RitualRegistry.instance().getRituals().clear();
		}

		@Override
		public String describe() {

			return "Removing all Ritual recipes";
		}
	}
}
