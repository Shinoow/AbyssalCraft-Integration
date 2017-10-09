package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconSummonRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;
import com.shinoow.abyssalcraft.common.util.ACLogger;

@ZenClass("mods.abyssalcraft.SummonRitual")
public class SummonRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String entity, IIngredient[] offerings){
		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, entity, offerings, false);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String entity, IIngredient[] offerings, boolean nbt){

		Object[] offers = ACMT.toObjects(offerings);

		Class<? extends Entity> e;

		if(EntityList.isStringValidEntityName(entity))
			e = EntityList.NAME_TO_CLASS.get(entity);
		else
			try {
				e = (Class<? extends Entity>) Class.forName(entity);
			} catch(ClassNotFoundException ex){
				ACLogger.warning("Could not find Entity class %s", entity);
				return;
			}

		if(e == null) return;

		NecronomiconSummonRitual ritual = new NecronomiconSummonRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, (Class<? extends EntityLivingBase>) e, offers);

		if(nbt) ritual.setNBTSensitive();

		MineTweakerAPI.apply(new Add(ritual));
	}

	private static class Add implements IUndoableAction
	{

		private final NecronomiconSummonRitual ritual;

		public Add(NecronomiconSummonRitual ritual){

			this.ritual = ritual;
		}

		@Override
		public void apply() {

			RitualRegistry.instance().registerRitual(ritual);
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Summoning Ritual for " + (EntityList.CLASS_TO_NAME.get(ritual.getEntity()) != null ? EntityList.CLASS_TO_NAME.get(ritual.getEntity()) : "");
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describeUndo() {

			return "Removing Necronomicon Summoning Ritual for " + (EntityList.CLASS_TO_NAME.get(ritual.getEntity()) != null ? EntityList.CLASS_TO_NAME.get(ritual.getEntity()) : "");
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
	public static void removeRitual(String entity){

		Class<? extends Entity> e;

		if(EntityList.isStringValidEntityName(entity))
			e = EntityList.NAME_TO_CLASS.get(entity);
		else
			try {
				e = (Class<? extends Entity>) Class.forName(entity);
			} catch(ClassNotFoundException ex){
				ACLogger.warning("Could not find Entity class %s", entity);
				return;
			}

		if(e == null) return;

		MineTweakerAPI.apply(new Remove(e));
	}

	private static class Remove implements IUndoableAction
	{
		private final Class<? extends Entity> entity;
		List<NecronomiconSummonRitual> removedRituals;

		public Remove(Class<? extends Entity> entity){
			this.entity = entity;
		}

		@Override
		public void apply() {

			removedRituals = Lists.newArrayList();

			List<NecronomiconSummonRitual> temp = new ArrayList<NecronomiconSummonRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconSummonRitual)
					temp.add((NecronomiconSummonRitual) ritual);
			for(NecronomiconSummonRitual ritual : temp)
				if(entity.getName().equals(ritual.getEntity().getName()) &&
						EntityList.CLASS_TO_NAME.get(entity) == EntityList.CLASS_TO_NAME.get(ritual.getEntity())){
					removedRituals.add(ritual);
					RitualRegistry.instance().getRituals().remove(ritual);
				}
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Summoning Ritual for "+ (EntityList.CLASS_TO_NAME.get(entity) != null ? EntityList.CLASS_TO_NAME.get(entity).toString() : "");
		}

		@Override
		public boolean canUndo() {

			return false;
		}

		@Override
		public String describeUndo() {

			return "Re-adding Necronomicon Summoning Ritual for "+ (EntityList.CLASS_TO_NAME.get(entity) != null ? EntityList.CLASS_TO_NAME.get(entity).toString() : "");
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {
			if(removedRituals != null)
				for(NecronomiconSummonRitual ritual : removedRituals)
					RitualRegistry.instance().registerRitual(ritual);
		}
	}
}
