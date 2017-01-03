package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconPotionAoERitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import net.minecraft.potion.Potion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

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

		MineTweakerAPI.apply(new Add(ritual));
	}

	private static class Add implements IUndoableAction
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
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Potion AoE Ritual for " + ritual.getPotionEffect().getName();
		}

		@Override
		public String describeUndo() {

			return "Removing Necronomicon Potion AoE Ritual for " + ritual.getPotionEffect().getName();
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
	public static void removeRitual(String potion){

		Potion pot = Potion.getPotionFromResourceLocation(potion);
		
		if(pot == null) return;

		MineTweakerAPI.apply(new Remove(pot));
	}

	private static class Remove implements IUndoableAction
	{
		private final Potion potion;
		List<NecronomiconPotionAoERitual> removedRituals;

		public Remove(Potion potion){
			this.potion = potion;
		}

		@Override
		public void apply() {

			removedRituals = new ArrayList<NecronomiconPotionAoERitual>();

			List<NecronomiconPotionAoERitual> temp = new ArrayList<NecronomiconPotionAoERitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconPotionAoERitual)
					temp.add((NecronomiconPotionAoERitual) ritual);
			for(NecronomiconPotionAoERitual ritual : temp)
				if(potion.getName().equals(ritual.getPotionEffect().getName()) &&
						potion.getId() == ritual.getPotionEffect().getId()){
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

			return "Removing Necronomicon Potion AoE Ritual for "+ potion.getName();
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Necronomicon Potion AoE Ritual for "+ potion.getName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removedRituals != null)
				for(NecronomiconPotionAoERitual ritual : removedRituals)
					if(ritual != null)
						RitualRegistry.instance().registerRitual(ritual);
		}
	}
}