package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconInfusionRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.InfusionRitual")
public class InfusionRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean remnantHelp, IItemStack item, IItemStack sacrifice, IItemStack...offerings){

		ItemStack[] offers = new ItemStack[offerings.length];
		for(int i = 0; i < offerings.length; i++)
			offers[i] = ACMT.toStack(offerings[i]);

		NecronomiconInfusionRitual ritual = new NecronomiconInfusionRitual(unlocalizedName, bookType, dimension, requiredEnergy, remnantHelp, ACMT.toStack(item), ACMT.toStack(sacrifice), offers);
		MineTweakerAPI.apply(new Add(ritual));
	}

	private static class Add implements IUndoableAction
	{

		private final NecronomiconInfusionRitual ritual;

		public Add(NecronomiconInfusionRitual ritual){

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

			return "Adding Necronomicon Infusion Ritual for " + ritual.getItem().getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Removing Necronomicon Infusion Ritual for " + ritual.getItem().getDisplayName();
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
	public static void removeRitual(IItemStack item){

		MineTweakerAPI.apply(new Remove(ACMT.toStack(item)));
	}

	private static class Remove implements IUndoableAction
	{
		private final ItemStack item;
		List<NecronomiconInfusionRitual> removedRituals;

		public Remove(ItemStack item){
			this.item = item;
		}

		@Override
		public void apply() {

			List<NecronomiconInfusionRitual> temp = new ArrayList<NecronomiconInfusionRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals()){
				if(ritual instanceof NecronomiconInfusionRitual &&
						ritual.getClass().getSuperclass() != NecronomiconInfusionRitual.class &&
						ritual.getClass().getSuperclass().getSuperclass() != NecronomiconInfusionRitual.class)
					temp.add((NecronomiconInfusionRitual) ritual);
			}
			for(NecronomiconInfusionRitual ritual : temp){
				if(ritual.getItem() == item){
					removedRituals.add(ritual);
					RitualRegistry.instance().getRituals().remove(ritual);
				}
			}
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Infusion Ritual for "+ item.getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Necronomicon Infusion Ritual for "+ item.getDisplayName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removedRituals != null)
				for(NecronomiconInfusionRitual ritual : removedRituals)
					if(ritual != null)
						RitualRegistry.instance().registerRitual(ritual);
		}
	}
}