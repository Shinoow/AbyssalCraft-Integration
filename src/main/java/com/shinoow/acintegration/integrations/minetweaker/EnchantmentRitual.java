package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconEnchantmentRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.EnchantmentRitual")
public class EnchantmentRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String enchantment, IIngredient[] offerings){
		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, enchantment, offerings, false);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String enchantment, IIngredient[] offerings, boolean nbt){

		Object[] offers = ACMT.toObjects(offerings);

		EnchantmentData ench = getEnch(enchantment);

		if(ench == null) return;

		NecronomiconEnchantmentRitual ritual = new NecronomiconEnchantmentRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, ench, offers);

		if(nbt) ritual.setNBTSensitive();

		MineTweakerAPI.apply(new Add(ritual));
	}

	private static EnchantmentData getEnch(String string){
		if(string.length() > 0){
			String[] stuff = string.split(":");

			Enchantment ench = Enchantment.getEnchantmentByLocation(String.format("%s:%s", stuff[0], stuff[1]));

			int level = 1;

			if(stuff.length > 2)
				level = Integer.valueOf(stuff[2]);

			if(ench != null)
				return new EnchantmentData(ench, level);
		}
		return null;
	}

	private static class Add implements IUndoableAction
	{

		private final NecronomiconEnchantmentRitual ritual;

		public Add(NecronomiconEnchantmentRitual ritual){

			this.ritual = ritual;
		}

		@Override
		public void apply() {

			RitualRegistry.instance().registerRitual(ritual);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Enchantment Ritual for " + ritual.getEnchantment().enchantmentobj.getTranslatedName(ritual.getEnchantment().enchantmentLevel);
		}

		@Override
		public String describeUndo() {

			return "Removing Necronomicon Enchantment Ritual for " + ritual.getEnchantment().enchantmentobj.getTranslatedName(ritual.getEnchantment().enchantmentLevel);
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			RitualRegistry.instance().getRituals().remove(ritual);
		}
	}

	@ZenMethod
	public static void removeRitual(String enchantment){

		EnchantmentData ench = getEnch(enchantment);

		if(ench == null) return;

		MineTweakerAPI.apply(new Remove(ench));
	}

	private static class Remove implements IUndoableAction
	{
		private final EnchantmentData ench;
		List<NecronomiconEnchantmentRitual> removedRituals;

		public Remove(EnchantmentData ench){
			this.ench = ench;
		}

		@Override
		public void apply() {

			removedRituals = new ArrayList<NecronomiconEnchantmentRitual>();

			List<NecronomiconEnchantmentRitual> temp = new ArrayList<NecronomiconEnchantmentRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconEnchantmentRitual)
					temp.add((NecronomiconEnchantmentRitual) ritual);
			for(NecronomiconEnchantmentRitual ritual : temp)
				if(ritual.getEnchantment().enchantmentobj == ench.enchantmentobj &&
				ritual.getEnchantment().enchantmentLevel == ench.enchantmentLevel){
					removedRituals.add(ritual);
					RitualRegistry.instance().getRituals().remove(ritual);
				}
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Enchantment Ritual for "+ ench.enchantmentobj.getTranslatedName(ench.enchantmentLevel);
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Necronomicon Enchantment Ritual for "+ ench.enchantmentobj.getTranslatedName(ench.enchantmentLevel);
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removedRituals != null)
				for(NecronomiconEnchantmentRitual ritual : removedRituals)
					if(ritual != null)
						RitualRegistry.instance().registerRitual(ritual);
		}
	}
}