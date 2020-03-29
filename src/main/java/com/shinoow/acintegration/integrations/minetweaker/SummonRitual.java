package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconSummonRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;
import com.shinoow.abyssalcraft.common.util.ACLogger;

import crafttweaker.IAction;
import crafttweaker.api.data.IData;
import crafttweaker.api.item.IIngredient;
import crafttweaker.mc1120.data.NBTConverter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.SummonRitual")
public class SummonRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String entity, IIngredient[] offerings){
		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, entity, offerings, false);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String entity, IIngredient[] offerings, IData customNBT){
		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, entity, offerings, false, customNBT);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String entity, IIngredient[] offerings, boolean nbt){
		addRitual(unlocalizedName, bookType, dimension, requiredEnergy, requiresSacrifice, entity, offerings, nbt, null);
	}

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean requiresSacrifice, String entity, IIngredient[] offerings, boolean nbt, IData customNBT){

		Object[] offers = ACMT.toObjects(offerings);

		Class<? extends Entity> e;

		if(entity.split(":").length == 2)
			e = EntityList.getClass(new ResourceLocation(entity));
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

		if(customNBT != null)
			ritual.setCustomNBT((NBTTagCompound) NBTConverter.from(customNBT));

		ACMTMisc.TASKS.add(new Add(ritual));
	}

	private static class Add implements IAction
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

			return "Adding Necronomicon Summoning Ritual for " + (EntityList.getKey(ritual.getEntity()) != null ? EntityList.getKey(ritual.getEntity()).toString() : "");
		}
	}

	@ZenMethod
	public static void removeRitual(String entity){

		Class<? extends Entity> e;

		if(entity.split(":").length == 2)
			e = EntityList.getClass(new ResourceLocation(entity));
		else
			try {
				e = (Class<? extends Entity>) Class.forName(entity);
			} catch(ClassNotFoundException ex){
				ACLogger.warning("Could not find Entity class %s", entity);
				return;
			}

		if(e == null) return;

		ACMTMisc.TASKS.add(new Remove(e));
	}

	private static class Remove implements IAction
	{
		private final Class<? extends Entity> entity;

		public Remove(Class<? extends Entity> entity){
			this.entity = entity;
		}

		@Override
		public void apply() {

			List<NecronomiconSummonRitual> temp = new ArrayList<NecronomiconSummonRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
				if(ritual instanceof NecronomiconSummonRitual)
					temp.add((NecronomiconSummonRitual) ritual);
			for(NecronomiconSummonRitual ritual : temp)
				if(entity.getName().equals(ritual.getEntity().getName()) &&
						EntityList.getKey(entity) == EntityList.getKey(ritual.getEntity()))
					RitualRegistry.instance().getRituals().remove(ritual);
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Summoning Ritual for "+ (EntityList.getKey(entity) != null ? EntityList.getKey(entity).toString() : "");
		}
	}
}
