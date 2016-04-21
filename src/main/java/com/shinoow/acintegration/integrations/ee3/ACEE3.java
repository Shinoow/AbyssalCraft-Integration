/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2015 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.acintegration.integrations.ee3;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.exchange.RecipeRegistryProxy;
import com.pahimar.ee3.api.knowledge.AbilityRegistryProxy;
import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.abyssalcraft.api.ritual.*;
import com.shinoow.acintegration.ACIntegration;

import cpw.mods.fml.common.Loader;

@ACPlugin
public class ACEE3 implements IACPlugin {

	@Override
	public String getModName() {

		return "Equivalent Exchange 3";
	}

	public static ACEE3 instance = new ACEE3();

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("EE3") && ACIntegration.loadEE3;
	}

	@Override
	public void preInit() {

		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Darkstone, 1);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Darkstone_cobble, 1);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.abystone, 1);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.cstone, 16);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.dreadstone, 1);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.abydreadstone, 1);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.omotholstone, 1);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.ethaxium, 16);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Darkgrass, 1);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.dreadgrass, 1);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.monolithStone, 16);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.shoggothBlock, 8);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.shoggothBiomass, 16);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.abyore, 3072);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.abydreadore, 3072);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.dreadore, 3072);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.AbyLCorOre, 4096);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.nitreOre, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.AbyNitOre, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.AbyTinOre, 256);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.AbyCopOre, 128);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Coraliumore, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.AbyCorOre, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.AbyPCorOre, 262);

		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.cbrick, 16);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.ethaxium_brick, 16);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Coralium, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Corflesh, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Corbone, 80);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.dreadfragment, 48);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.omotholFlesh, 64);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.eldritchScale, 24);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.cthulhuCoin, 1028);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.elderCoin, 1028);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.jzaharCoin, 1028);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.hasturCoin, 1028);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.azathothCoin, 1028);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.nyarlathotepCoin, 1028);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.yogsothothCoin, 1028);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.shubniggurathCoin, 1028);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Cpearl, 262);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.shadowfragment, 16);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.abyingot, 3072);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Cingot, 4096);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.dreadiumingot, 5120);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.abychunk, 3072);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.dreadchunk, 6144);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Dreadshard, 3072);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.EoA, 32768);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.PSDL, 32768);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.dreadKey, 251904);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.portalPlacerJzh, 251904);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.CFluid, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antifluid, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.Cwater, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.anticwater, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antiBone, 48);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.friedegg, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antiBeef, 24);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antiChicken, 24);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antiFlesh, 24);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antiCorflesh, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antiCorbone, 80);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antiSpider_eye, 128);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.antiPork, 24);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(AbyssalCraft.essence, 1, 0), 256);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(AbyssalCraft.essence, 1, 1), 512);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(AbyssalCraft.essence, 1, 2), 1024);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 0), 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 1), 64);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 2), 96);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 3), 128);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 4), 160);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.sulfur, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.nitre, 32);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.tinIngot, 256);
		EnergyValueRegistryProxy.addPreAssignedEnergyValue(AbyssalCraft.copperIngot, 128);

		for(String name :OreDictionary.getOreNames()){
			if(name.startsWith("crystal") && !name.startsWith("crystalShard")){
				List<ItemStack> ores = OreDictionary.getOres(name);
				Iterator iter = ores.iterator();
				while(iter.hasNext())
					EnergyValueRegistryProxy.addPreAssignedEnergyValue(iter.next(), 1024);
			}
			if(name.startsWith("crystalShard")){
				List<ItemStack> ores = OreDictionary.getOres(name);
				Iterator iter = ores.iterator();
				while(iter.hasNext())
					EnergyValueRegistryProxy.addPreAssignedEnergyValue(iter.next(), 1024/9);
			}
		}
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {
		for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
			if(ritual instanceof NecronomiconCreationRitual)
				RecipeRegistryProxy.addRecipe(((NecronomiconCreationRitual) ritual).getItem(), getInputs(ritual));
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.OC);
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.essence, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.skin, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.necronomicon_cor);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.necronomicon_dre);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.necronomicon_omt);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.abyssalnomicon);
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.cthulhuCharm, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.hasturCharm, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.jzaharCharm, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.azathothCharm, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.nyarlathotepCharm, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.yogsothothCharm, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.shubniggurathCharm, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.cthulhuStatue);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.hasturStatue);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.jzaharStatue);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.azathothStatue);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.nyarlathotepStatue);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.yogsothothStatue);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.shubniggurathStatue);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.portalPlacer);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.portalPlacerDL);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.dreadKey);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.portalPlacerJzh);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.PSDL);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.dreadaltartop);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.dreadaltarbottom);
		AbilityRegistryProxy.setAsNotLearnable(AbyssalCraft.EoA);
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.tieredEnergyPedestal, 1, OreDictionary.WILDCARD_VALUE));
		AbilityRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.tieredSacrificialAltar, 1, OreDictionary.WILDCARD_VALUE));

		for(String name :OreDictionary.getOreNames()){
			if(name.startsWith("crystal")){
				List<ItemStack> ores = OreDictionary.getOres(name);
				Iterator iter = ores.iterator();
				while(iter.hasNext())
					AbilityRegistryProxy.setAsNotLearnable(iter.next());
			}
		}
	}

	private List getInputs(NecronomiconRitual ritual){
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		inputs.addAll(Arrays.asList(getStacks(ritual.getOfferings())));
		if(ritual.getSacrifice() != null)
			inputs.add(getStack(ritual.getSacrifice()));
		return inputs;
	}

	private ItemStack[] getStacks(Object[] obj){
		ItemStack[] stacks = new ItemStack[obj.length];

		for(int i = 0; i < obj.length; i++)
			stacks[i] = getStack(obj[i]);
		return stacks;
	}

	private ItemStack getStack(Object obj){
		if(obj instanceof Item)
			return new ItemStack((Item)obj);
		if(obj instanceof Block)
			return new ItemStack((Block)obj);
		if(obj instanceof ItemStack)
			return (ItemStack)obj;
		if(obj instanceof String)
			return OreDictionary.getOres((String)obj).iterator().next();
		return null;
	}
}