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

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
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

		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Darkstone, 1);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Darkstone_cobble, 1);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.abystone, 1);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.cstone, 16);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.dreadstone, 1);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.abydreadstone, 1);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.omotholstone, 1);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.ethaxium, 16);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Darkgrass, 1);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.dreadgrass, 1);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.monolithStone, 16);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.shoggothBlock, 8);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.shoggothBiomass, 16);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.abyore, 3072);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.abydreadore, 3072);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.dreadore, 3072);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.AbyLCorOre, 4096);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.nitreOre, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.AbyNitOre, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.AbyTinOre, 256);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.AbyCopOre, 128);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Coraliumore, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.AbyCorOre, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.AbyPCorOre, 262);

		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.cbrick, 16);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.ethaxium_brick, 16);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Coralium, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Corflesh, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Corbone, 80);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.dreadfragment, 48);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.omotholFlesh, 64);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.eldritchScale, 24);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.cthulhuCoin, 1028);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.elderCoin, 1028);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.jzaharCoin, 1028);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.hasturCoin, 1028);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.azathothCoin, 1028);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.nyarlathotepCoin, 1028);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.yogsothothCoin, 1028);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.shubniggurathCoin, 1028);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Cpearl, 262);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.shadowfragment, 16);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.abyingot, 3072);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Cingot, 4096);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.dreadiumingot, 5120);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.abychunk, 3072);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.dreadchunk, 6144);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Dreadshard, 3072);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.EoA, 32768);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.PSDL, 32768);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.dreadKey, 251904);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.portalPlacerJzh, 251904);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.CFluid, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antifluid, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.Cwater, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.anticwater, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antiBone, 48);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.friedegg, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antiBeef, 24);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antiChicken, 24);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antiFlesh, 24);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antiCorflesh, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antiCorbone, 80);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antiSpider_eye, 128);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.antiPork, 24);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.essence, 1, 0), 256);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.essence, 1, 1), 512);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.essence, 1, 2), 1024);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 0), 32);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 1), 64);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 2), 96);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 3), 128);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 4), 160);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.sulfur, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.nitre, 32);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.tinIngot, 256);
		EnergyValueRegistryProxy.setEnergyValue(AbyssalCraft.copperIngot, 128);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.crystal, 1, OreDictionary.WILDCARD_VALUE), 1350);
		EnergyValueRegistryProxy.setEnergyValue(new ItemStack(AbyssalCraft.crystalShard, 1, OreDictionary.WILDCARD_VALUE), 150);
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {
		for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals())
			if(ritual instanceof NecronomiconCreationRitual)
				RecipeRegistryProxy.addRecipe(((NecronomiconCreationRitual) ritual).getItem(), getInputs(ritual));
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.OC);
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.essence, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.skin, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.necronomicon_cor);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.necronomicon_dre);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.necronomicon_omt);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.abyssalnomicon);
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.cthulhuCharm, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.hasturCharm, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.jzaharCharm, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.azathothCharm, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.nyarlathotepCharm, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.yogsothothCharm, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.shubniggurathCharm, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.cthulhuStatue);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.hasturStatue);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.jzaharStatue);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.azathothStatue);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.nyarlathotepStatue);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.yogsothothStatue);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.shubniggurathStatue);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.portalPlacer);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.portalPlacerDL);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.dreadKey);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.portalPlacerJzh);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.PSDL);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.dreadaltartop);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.dreadaltarbottom);
		BlacklistRegistryProxy.setAsNotLearnable(AbyssalCraft.EoA);
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.tieredEnergyPedestal, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.tieredSacrificialAltar, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.crystal, 1, OreDictionary.WILDCARD_VALUE));
		BlacklistRegistryProxy.setAsNotLearnable(new ItemStack(AbyssalCraft.crystalShard, 1, OreDictionary.WILDCARD_VALUE));
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