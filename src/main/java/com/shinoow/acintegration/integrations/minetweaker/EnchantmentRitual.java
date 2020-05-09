package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconEnchantmentRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
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

		ACMTMisc.TASKS.add(new Add(ritual));
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

	private static class Add implements IAction
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
		public String describe() {

			return "Adding Necronomicon Enchantment Ritual for " + ritual.getEnchantment().enchantment.getTranslatedName(ritual.getEnchantment().enchantmentLevel);
		}
	}

	@ZenMethod
	public static void removeRitual(String enchantment){

		EnchantmentData ench = getEnch(enchantment);

		if(ench == null) return;

		ACMTMisc.TASKS.add(new Remove(ench));
	}

	private static class Remove implements IAction
	{
		private final EnchantmentData ench;

		public Remove(EnchantmentData ench){
			this.ench = ench;
		}

		@Override
		public void apply() {

			List<NecronomiconEnchantmentRitual> temp = new ArrayList<NecronomiconEnchantmentRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconEnchantmentRitual)
					temp.add((NecronomiconEnchantmentRitual) ritual);
			for(NecronomiconEnchantmentRitual ritual : temp)
				if(ritual.getEnchantment().enchantment == ench.enchantment &&
				ritual.getEnchantment().enchantmentLevel == ench.enchantmentLevel)
					RitualRegistry.instance().getRituals().remove(ritual);
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Enchantment Ritual for "+ ench.enchantment.getTranslatedName(ench.enchantmentLevel);
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			RitualRegistry.instance().getRituals().removeIf(r -> r instanceof NecronomiconEnchantmentRitual);
		}

		@Override
		public String describe() {

			return "Removing all Enchantment Ritual recipes";
		}
	}
}