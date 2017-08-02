package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.potion.Potion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconPotionAoERitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;

@ZenClass("mods.abyssalcraft.PotionAoERitual")
public class PotionAoERitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String potion, IIngredient[] offerings){
		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, potion, offerings, false);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String potion, IIngredient[] offerings, boolean nbt){

		Object[] offers = ACMT.toObjects(offerings);

		Potion pot = Potion.getPotionFromResourceLocation(potion);

		if(pot == null) return;

		NecronomiconPotionAoERitual ritual = new NecronomiconPotionAoERitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, pot, offers);

		if(nbt) ritual.setNBTSensitive();

		ACMTMisc.ADDITIONS.add(new Add(ritual));
	}

	private static class Add implements IAction
	{

		private final NecronomiconPotionAoERitual ritual;

		public Add(NecronomiconPotionAoERitual ritual){

			this.ritual = ritual;
		}

		@Override
		public void apply() {

			RitualRegistry.instance().registerRitual(ritual);
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Potion AoE Ritual for " + ritual.getPotionEffect().getName();
		}
	}

	@ZenMethod
	public static void removeRitual(String potion){

		Potion pot = Potion.getPotionFromResourceLocation(potion);

		if(pot == null) return;

		ACMTMisc.REMOVALS.add(new Remove(pot));
	}

	private static class Remove implements IAction
	{
		private final Potion potion;

		public Remove(Potion potion){
			this.potion = potion;
		}

		@Override
		public void apply() {

			List<NecronomiconPotionAoERitual> temp = new ArrayList<NecronomiconPotionAoERitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconPotionAoERitual)
					temp.add((NecronomiconPotionAoERitual) ritual);
			for(NecronomiconPotionAoERitual ritual : temp)
				if(potion.getName().equals(ritual.getPotionEffect().getName()) &&
						Potion.getIdFromPotion(potion) == Potion.getIdFromPotion(ritual.getPotionEffect()))
					RitualRegistry.instance().getRituals().remove(ritual);
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Potion AoE Ritual for "+ potion.getName();
		}
	}
}