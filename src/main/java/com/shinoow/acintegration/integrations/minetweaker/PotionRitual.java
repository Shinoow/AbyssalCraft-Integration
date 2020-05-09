package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconPotionRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import net.minecraft.potion.Potion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.PotionRitual")
public class PotionRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String potion, IIngredient[] offerings){
		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, potion, offerings, false);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String potion, IIngredient[] offerings, boolean nbt){

		Object[] offers = ACMT.toObjects(offerings);

		Potion pot = Potion.getPotionFromResourceLocation(potion);

		if(pot == null) return;

		NecronomiconPotionRitual ritual = new NecronomiconPotionRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, pot, offers);

		if(nbt) ritual.setNBTSensitive();

		ACMTMisc.TASKS.add(new Add(ritual));
	}

	private static class Add implements IAction
	{

		private final NecronomiconPotionRitual ritual;

		public Add(NecronomiconPotionRitual ritual){

			this.ritual = ritual;
		}

		@Override
		public void apply() {

			RitualRegistry.instance().registerRitual(ritual);
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Potion Ritual for " + ritual.getPotionEffect().getName();
		}
	}

	@ZenMethod
	public static void removeRitual(String potion){

		Potion pot = Potion.getPotionFromResourceLocation(potion);

		if(pot == null) return;

		ACMTMisc.TASKS.add(new Remove(pot));
	}

	private static class Remove implements IAction
	{
		private final Potion potion;

		public Remove(Potion potion){
			this.potion = potion;
		}

		@Override
		public void apply() {

			List<NecronomiconPotionRitual> temp = new ArrayList<NecronomiconPotionRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconPotionRitual)
					temp.add((NecronomiconPotionRitual) ritual);
			for(NecronomiconPotionRitual ritual : temp)
				if(potion.getName().equals(ritual.getPotionEffect().getName()) &&
						Potion.getIdFromPotion(potion) == Potion.getIdFromPotion(ritual.getPotionEffect()))
					RitualRegistry.instance().getRituals().remove(ritual);
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Potion Ritual for "+ potion.getName();
		}
	}

	@ZenMethod
	public static void removeAll() {
		ACMTMisc.TASKS.add(new RemoveAll());
	}

	private static class RemoveAll implements IAction {

		@Override
		public void apply() {
			RitualRegistry.instance().getRituals().removeIf(r -> r instanceof NecronomiconPotionRitual);
		}

		@Override
		public String describe() {

			return "Removing all Potion Ritual recipes";
		}
	}
}