package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconPotionRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import net.minecraft.potion.Potion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.PotionRitual")
public class PotionRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, int potion, IIngredient[] offerings){

		Object[] offers = ACMT.toObjects(offerings);

		Potion pot = Potion.potionTypes[potion];

		if(pot == null) return;

		NecronomiconPotionRitual ritual = new NecronomiconPotionRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, pot, offers);

		MineTweakerAPI.apply(new Add(ritual));
	}

	private static class Add implements IUndoableAction
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
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Potion Ritual for " + ritual.getPotionEffect().getName();
		}

		@Override
		public String describeUndo() {

			return "Removing Necronomicon Potion Ritual for " + ritual.getPotionEffect().getName();
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
	public static void removeRitual(int potion){

		Potion pot = Potion.potionTypes[potion];

		if(pot == null) return;

		MineTweakerAPI.apply(new Remove(pot));
	}

	private static class Remove implements IUndoableAction
	{
		private final Potion potion;
		List<NecronomiconPotionRitual> removedRituals;

		public Remove(Potion potion){
			this.potion = potion;
		}

		@Override
		public void apply() {

			removedRituals = new ArrayList<NecronomiconPotionRitual>();

			List<NecronomiconPotionRitual> temp = new ArrayList<NecronomiconPotionRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconPotionRitual)
					temp.add((NecronomiconPotionRitual) ritual);
			for(NecronomiconPotionRitual ritual : temp)
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

			return "Removing Necronomicon Potion Ritual for "+ potion.getName();
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Necronomicon Potion Ritual for "+ potion.getName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removedRituals != null)
				for(NecronomiconPotionRitual ritual : removedRituals)
					if(ritual != null)
						RitualRegistry.instance().registerRitual(ritual);
		}
	}
}