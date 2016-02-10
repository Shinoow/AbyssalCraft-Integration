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
package com.shinoow.acintegration.integrations.thaumcraft;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI.ACEntities;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;

public class ACTC implements IACPlugin {

//	public static WandCap abyssalniteCap, coraliumCap, dreadiumCap, ethaxiumCap;
//	public static WandRod darklandsRod, coraliumRod, dreadlandsRod, omotholRod;
//	public static Item wandCap, wandCore, thaumiumU, voidmetalU;
//	public static Aspect CORALIUM, DREAD;
//	public static ItemStack darkWand, corWand, dreadWand, omotholWand, endWand;

	@Override
	public String getModName(){
		return "Thaumcraft";
	}

	public static ACTC instance = new ACTC();

	@Override
	public void preInit() {

//		if(ACIntegration.tcWarp)
//			MinecraftForge.EVENT_BUS.register(new ACTCEvents());
//
//		if(ACIntegration.tcItems){
//
//			//did it like this solely for disabling everything item-related if something toggles it off
//			TabACThaum.instance = new TabACThaum();
//
//			wandCap = new ItemACThaumcraft("wandcap", true, "abyssalnite", "coralium", "dreadium", "ethaxium");
//			wandCore = new ItemACThaumcraft("wandcore", true, "darklands", "coralium", "dreadlands", "omothol");
//			thaumiumU = new ItemUpgradeKit("Iron", "Thaumium").setUnlocalizedName("thaumiumu"); //setTextureName(ACIntegration.modid + ":" + "thaumiumu")
//			voidmetalU = new ItemUpgradeKit("Thaumium", "Void metal").setUnlocalizedName("voidmetalu"); //setTextureName(ACIntegration.modid + ":" + "voidmetalu")
//
//			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
//				TabACThaum.instance.addItem(thaumiumU);
//				TabACThaum.instance.addItem(voidmetalU);
//			}
//
//			GameRegistry.registerItem(wandCap, "wandcap");
//			GameRegistry.registerItem(wandCore, "wandcore");
//			GameRegistry.registerItem(thaumiumU, "thaumiumu");
//			GameRegistry.registerItem(voidmetalU, "voidmetalu");
//
//			darklandsRod = new WandRodAC("darklands", 50, new ItemStack(wandCore, 1, 0), 1, new DarklandsRodOnUpdate(), new ResourceLocation("acintegration","textures/model/wands/wand_rod_darklands.png"));
//			coraliumRod = new WandRodAC("coralium", 75, new ItemStack(wandCore, 1, 1), 3, new CoraliumRodOnUpdate(), new ResourceLocation("acintegration","textures/model/wands/wand_rod_coralium.png"));
//			dreadlandsRod = new WandRodAC("dreadlands", 100, new ItemStack(wandCore, 1, 2), 5, new DreadlandsRodOnUpdate(), new ResourceLocation("acintegration","textures/model/wands/wand_rod_dreadlands.png"));
//			omotholRod = new WandRodAC("omothol", 0, new ItemStack(wandCore, 1, 3), 7, new OmotholRodOnUpdate(), new ResourceLocation("acintegration","textures/model/wands/wand_rod_omothol.png"));
//
//			abyssalniteCap = new WandCapAC("abyssalnite", 0.95F, new ItemStack(wandCap, 1, 0), 1);
//			coraliumCap = new WandCapAC("coralium", 0.9F, new ItemStack(wandCap, 1, 1), 3);
//			dreadiumCap = new WandCapAC("dreadium", 0.85F, new ItemStack(wandCap, 1, 2), 5);
//			ethaxiumCap = new WandCapAC("ethaxium", 0.8F, new ItemStack(wandCap, 1, 3), 7);
//
//			darkWand = ItemApi.getItem("itemWandCasting", 1000);
//			((ItemWandCasting)darkWand.getItem()).setCap(darkWand, WandCap.caps.get("abyssalnite"));
//			((ItemWandCasting)darkWand.getItem()).setRod(darkWand, WandRod.rods.get("darklands"));
//			((ItemWandCasting) darkWand.getItem()).storeAllVis(darkWand, new AspectList().add(Aspect.AIR, 5000).add(Aspect.EARTH, 5000).add(Aspect.FIRE, 5000).add(Aspect.WATER, 5000).add(Aspect.ORDER, 5000).add(Aspect.ENTROPY, 5000));
//
//			corWand = ItemApi.getItem("itemWandCasting", 1001);
//			((ItemWandCasting)corWand.getItem()).setCap(corWand, WandCap.caps.get("coralium"));
//			((ItemWandCasting)corWand.getItem()).setRod(corWand, WandRod.rods.get("coralium"));
//			((ItemWandCasting) corWand.getItem()).storeAllVis(corWand, new AspectList().add(Aspect.AIR, 7500).add(Aspect.EARTH, 7500).add(Aspect.FIRE, 7500).add(Aspect.WATER, 7500).add(Aspect.ORDER, 7500).add(Aspect.ENTROPY, 7500));
//
//			dreadWand = ItemApi.getItem("itemWandCasting", 1002);
//			((ItemWandCasting)dreadWand.getItem()).setCap(dreadWand, WandCap.caps.get("dreadium"));
//			((ItemWandCasting)dreadWand.getItem()).setRod(dreadWand, WandRod.rods.get("dreadlands"));
//			((ItemWandCasting) dreadWand.getItem()).storeAllVis(dreadWand, new AspectList().add(Aspect.AIR, 10000).add(Aspect.EARTH, 10000).add(Aspect.FIRE, 10000).add(Aspect.WATER, 10000).add(Aspect.ORDER, 10000).add(Aspect.ENTROPY, 10000));
//
//			omotholWand = ItemApi.getItem("itemWandCasting", 1003);
//			((ItemWandCasting)omotholWand.getItem()).setCap(omotholWand, WandCap.caps.get("ethaxium"));
//			((ItemWandCasting)omotholWand.getItem()).setRod(omotholWand, WandRod.rods.get("omothol"));
//			((ItemWandCasting) omotholWand.getItem()).storeAllVis(omotholWand, new AspectList().add(Aspect.AIR, 0).add(Aspect.EARTH, 0).add(Aspect.FIRE, 0).add(Aspect.WATER, 0).add(Aspect.ORDER, 0).add(Aspect.ENTROPY, 0));
//
//			endWand = ItemApi.getItem("itemWandCasting", 1003);
//			((ItemWandCasting)endWand.getItem()).setCap(endWand, WandCap.caps.get("void"));
//			((ItemWandCasting)endWand.getItem()).setRod(endWand, WandRod.rods.get("omothol"));
//			((ItemWandCasting) endWand.getItem()).storeAllVis(endWand, new AspectList().add(Aspect.AIR, 0).add(Aspect.EARTH, 0).add(Aspect.FIRE, 0).add(Aspect.WATER, 0).add(Aspect.ORDER, 0).add(Aspect.ENTROPY, 0));
//
//			TabACThaum.instance.addWands();
//			TabACThaum.instance.addAllItemsAndBlocks();
//		}
	}

	@Override
	public void init(){

		//initializing aspects
//		CORALIUM = new Aspect("coralos", 0x00FFEE, new Aspect[] {Aspect.POISON, Aspect.WATER}, new ResourceLocation("acintegration", "textures/aspects/coralos.png"), 1);
//		DREAD = new Aspect("dreadia", 0xB00000, new Aspect[] {Aspect.POISON, Aspect.FIRE}, new ResourceLocation("acintegration", "textures/aspects/dreadia.png"), 1);

//		if(ACIntegration.tcItems){
//
//			GameRegistry.addRecipe(new ItemStack(thaumiumU), new Object[] {"#%", "%&", '#', Items.iron_ingot, '%', ItemApi.getItem("itemResource", 2), '&', AbyssalCraft.IronU});
//			GameRegistry.addRecipe(new ItemStack(voidmetalU), new Object[] {"#%", "%&", '#', ItemApi.getItem("itemResource", 2), '%', ItemApi.getItem("itemResource", 16), '&', thaumiumU});
//
//			ThaumcraftApi.addWarpToItem(new ItemStack(thaumiumU), 10);
//			ThaumcraftApi.addWarpToItem(new ItemStack(voidmetalU), 10);
//
//			addArmorUpgrade(ItemApi.getItem("itemHelmetThaumium", 0), ItemApi.getItem("itemChestThaumium", 0), ItemApi.getItem("itemLegsThaumium", 0), ItemApi.getItem("itemBootsThaumium", 0), thaumiumU, new ItemStack(Items.iron_helmet, 1, OreDictionary.WILDCARD_VALUE),
//					new ItemStack(Items.iron_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_boots, 1, OreDictionary.WILDCARD_VALUE));
//			addArmorUpgrade(ItemApi.getItem("itemHelmetVoid", 0), ItemApi.getItem("itemChestVoid", 0), ItemApi.getItem("itemLegsVoid", 0), ItemApi.getItem("itemBootsVoid", 0), voidmetalU, ItemApi.getItem("itemHelmetThaumium", OreDictionary.WILDCARD_VALUE),
//					ItemApi.getItem("itemChestThaumium", OreDictionary.WILDCARD_VALUE), ItemApi.getItem("itemLegsThaumium", OreDictionary.WILDCARD_VALUE), ItemApi.getItem("itemBootsThaumium", OreDictionary.WILDCARD_VALUE));
//
//			addToolUpgrade(ItemApi.getItem("itemSwordThaumium", 0), ItemApi.getItem("itemPickThaumium", 0), ItemApi.getItem("itemAxeThaumium", 0), ItemApi.getItem("itemShovelThaumium", 0), ItemApi.getItem("itemHoeThaumium", 0), thaumiumU,
//					new ItemStack(Items.iron_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_axe, 1, OreDictionary.WILDCARD_VALUE),
//					new ItemStack(Items.iron_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_hoe, 1, OreDictionary.WILDCARD_VALUE));
//			addToolUpgrade(ItemApi.getItem("itemSwordVoid", 0), ItemApi.getItem("itemPickVoid", 0), ItemApi.getItem("itemAxeVoid", 0), ItemApi.getItem("itemShovelVoid", 0), ItemApi.getItem("itemHoeVoid", 0), voidmetalU,
//					ItemApi.getItem("itemSwordThaumium", OreDictionary.WILDCARD_VALUE), ItemApi.getItem("itemPickThaumium", OreDictionary.WILDCARD_VALUE), ItemApi.getItem("itemAxeThaumium", OreDictionary.WILDCARD_VALUE),
//					ItemApi.getItem("itemShovelThaumium", OreDictionary.WILDCARD_VALUE), ItemApi.getItem("itemHoeThaumium", OreDictionary.WILDCARD_VALUE));
//
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 0), new AspectList().add(Aspect.ORDER, 3).add(Aspect.FIRE, 3).add(Aspect.AIR, 3), new Object[] {"###", "# #", '#', new ItemStack(AbyssalCraft.nugget, 1, 0)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 1), new AspectList().add(Aspect.ORDER, 4).add(Aspect.FIRE, 4).add(Aspect.AIR, 4), new Object[] {"###", "# #", '#', new ItemStack(AbyssalCraft.nugget, 1, 1)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 2), new AspectList().add(Aspect.ORDER, 5).add(Aspect.FIRE, 5).add(Aspect.AIR, 5), new Object[] {"###", "# #", '#', new ItemStack(AbyssalCraft.nugget, 1, 2)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 3), new AspectList().add(Aspect.ORDER, 6).add(Aspect.FIRE, 6).add(Aspect.AIR, 6), new Object[] {"###", "# #", '#', new ItemStack(AbyssalCraft.nugget, 1, 3)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 0), new AspectList().add(Aspect.ENTROPY, 5), new Object[] {" # ", "#  ", '#', AbyssalCraft.DLTLog});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 1), new AspectList().add(Aspect.ENTROPY, 5), new Object[] {" # ", "#  ", '#', AbyssalCraft.cstone});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 2), new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.FIRE, 5), new Object[] {" # ", "#  ", '#', AbyssalCraft.dreadlog});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 3), new AspectList().add(Aspect.ENTROPY, 5), new Object[] {" # ", "#  ", '#', AbyssalCraft.omotholstone});
//			ThaumcraftApi.addArcaneCraftingRecipe("", darkWand, new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.ORDER, 5), new Object[] {"  #", " % ", "#  ", '#', new ItemStack(wandCap, 1, 0), '%', new ItemStack(wandCore, 1, 0)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", dreadWand, new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.ORDER, 5), new Object[] {"  #", " % ", "#  ", '#', new ItemStack(wandCap, 1, 2), '%', new ItemStack(wandCore, 1, 2)});
//
//			ThaumcraftApi.addInfusionCraftingRecipe("", corWand, 20, new AspectList().add(Aspect.ENTROPY, 10).add(CORALIUM, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 1), new ItemStack[]{new ItemStack(wandCap, 1, 1), new ItemStack(wandCap, 1, 1)});
//			ThaumcraftApi.addInfusionCraftingRecipe("", omotholWand, 30, new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.SOUL, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 3), new ItemStack[]{new ItemStack(wandCap, 1, 3), new ItemStack(wandCap, 1, 3)});
//			ThaumcraftApi.addInfusionCraftingRecipe("", endWand, 30, new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.ELDRITCH, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 3), new ItemStack[]{ItemApi.getItem("itemWandCap", 7), ItemApi.getItem("itemWandCap", 7)});
//		}

		//Aspects, blocks
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Darkstone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Darkgrass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Darkstone_brick), new AspectList().add(Aspect.EARTH, 2).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Darkstone_cobble), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abyore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abydreadore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1).add(DREAD, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumore), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.CoraliumInfusedStone), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.EARTH, 1).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.AbyCorOre), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.AbyLCorOre), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.AbyPCorOre), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abystone), new AspectList().add(Aspect.EARTH, 2).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abybrick), new AspectList().add(Aspect.EARTH, 2).add(CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Abybutton), new AspectList().add(Aspect.MECHANISM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.portal), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.UNDEAD, 1).add(Aspect.TRAVEL, 4));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadportal), new AspectList().add(Aspect.DARKNESS, 4).add(DREAD, 1).add(Aspect.TRAVEL, 4));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.omotholportal), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.ELDRITCH, 1).add(Aspect.TRAVEL, 4));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadstone), new AspectList().add(Aspect.EARTH, 2).add(DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abydreadstone), new AspectList().add(Aspect.EARTH, 2));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Cwater), new AspectList().add(Aspect.WATER, 3).add(Aspect.POISON, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.anticwater), new AspectList().add(Aspect.WATER, 3).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxium), new AspectList().add(Aspect.SOUL, 2).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxiumbrick), new int[]{0}, new AspectList().add(Aspect.SOUL, 3).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxiumbrick), new int[]{1}, new AspectList().add(Aspect.SOUL, 3).add(Aspect.ELDRITCH, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.omotholstone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.cstone), new AspectList().add(Aspect.EARTH, 2).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.PSDL), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ENERGY, 2).add(DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.monolithStone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothBlock), new AspectList().add(Aspect.EARTH, 1).add(Aspect.FLESH, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothBiomass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.FLESH, 1).add(Aspect.ELDRITCH, 1).add(Aspect.LIFE, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.cthulhuStatue), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.hasturStatue), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.jzaharStatue), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.azathothStatue), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.nyarlathotepStatue), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.yogsothothStatue), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shubniggurathStatue), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.monolithStone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.energyPedestal), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.monolithPillar), new AspectList().add(Aspect.EARTH, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));

		//Aspects, items
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abyingot), new AspectList().add(Aspect.METAL, 3).add(Aspect.DARKNESS, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Cingot), new AspectList().add(Aspect.METAL, 3).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadiumingot), new AspectList().add(Aspect.METAL, 3).add(DREAD, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coralium), new AspectList().add(Aspect.CRYSTAL, 2).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster2), new AspectList().add(Aspect.CRYSTAL, 4).add(CORALIUM, 2));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster3), new AspectList().add(Aspect.CRYSTAL, 6).add(CORALIUM, 3));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster4), new AspectList().add(Aspect.CRYSTAL, 8).add(CORALIUM, 4));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster5), new AspectList().add(Aspect.CRYSTAL, 10).add(CORALIUM, 5));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster6), new AspectList().add(Aspect.CRYSTAL, 12).add(CORALIUM, 6));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster7), new AspectList().add(Aspect.CRYSTAL, 14).add(CORALIUM, 7));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster8), new AspectList().add(Aspect.CRYSTAL, 16).add(CORALIUM, 8));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster9), new AspectList().add(Aspect.CRYSTAL, 18).add(CORALIUM, 9));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxiumIngot), new AspectList().add(Aspect.METAL, 1).add(Aspect.SOUL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.cbrick), new AspectList().add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxium_brick), new AspectList().add(Aspect.FIRE, 1).add(Aspect.SOUL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shadowfragment), new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.DARKNESS, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.nitre), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coralium), new AspectList().add(Aspect.CRYSTAL, 2).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Cpearl), new AspectList().add(Aspect.CRYSTAL, 18).add(CORALIUM, 9));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Corflesh), new AspectList().add(Aspect.FLESH, 2).add(Aspect.MAN, 1).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Corbone), new AspectList().add(Aspect.FLESH, 2).add(Aspect.MAN, 1).add(CORALIUM, 1).add(Aspect.DEATH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadfragment), new AspectList().add(Aspect.FLESH, 2).add(Aspect.MAN, 1).add(DREAD, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.omotholFlesh), new AspectList().add(Aspect.FLESH, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.eldritchScale), new AspectList().add(Aspect.FLESH, 2).add(Aspect.ELDRITCH, 1).add(Aspect.WATER, 1).add(Aspect.ARMOR, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.PSDLfinder), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.SENSES, 2).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.EoA), new AspectList().add(Aspect.SENSES, 3).add(Aspect.DARKNESS, 3).add(CORALIUM, 2));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.essence, 1, 0), new AspectList().add(Aspect.MAGIC, 2).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.essence, 1, 1), new AspectList().add(Aspect.MAGIC, 2).add(DREAD, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.essence, 1, 2), new AspectList().add(Aspect.MAGIC, 2).add(Aspect.DARKNESS, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.skin, 1, 0), new AspectList().add(Aspect.FLESH, 3).add(Aspect.MAN, 2).add(CORALIUM, 2));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.skin, 1, 1), new AspectList().add(Aspect.FLESH, 3).add(Aspect.MAN, 2).add(DREAD, 2));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.skin, 1, 2), new AspectList().add(Aspect.FLESH, 3).add(Aspect.MAN, 2).add(Aspect.ELDRITCH, 2));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 0), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FLESH, 3).add(Aspect.SLIME, 3));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 1), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FLESH, 3).add(Aspect.SLIME, 3).add(CORALIUM, 3));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 2), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FLESH, 3).add(Aspect.SLIME, 3).add(DREAD, 3));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 3), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FLESH, 3).add(Aspect.SLIME, 3).add(Aspect.ELDRITCH, 3));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 4), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FLESH, 3).add(Aspect.SLIME, 3).add(Aspect.DARKNESS, 3));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.charm), new AspectList().add(Aspect.CRAFT, 2).add(Aspect.CRYSTAL, 1).add(Aspect.METAL, 2).add(Aspect.MAGIC, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.cthulhuCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2).add(Aspect.MAGIC, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.hasturCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2).add(Aspect.MAGIC, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.jzaharCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2).add(Aspect.MAGIC, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.azathothCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2).add(Aspect.MAGIC, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.nyarlathotepCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2).add(Aspect.MAGIC, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.yogsothothCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2).add(Aspect.MAGIC, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shubniggurathCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2).add(Aspect.MAGIC, 1));

		//Aspects, crystals
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 15), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 16), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 17), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 18), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 19), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 20), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 21), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 22), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 23), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 24), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(CORALIUM, 1));
//		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 15), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 16), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 17), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 18), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 19), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 20), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 21), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 22), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 23), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 24), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));

		//Aspect, entities
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.depths_ghoul), new AspectList().add(Aspect.UNDEAD, 6).add(Aspect.DEATH, 1).add(Aspect.EARTH, 2).add(CORALIUM, 1));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_pig), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.FLESH, 2));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.abyssal_zombie), new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.MAN, 1).add(Aspect.EARTH, 2).add(CORALIUM, 1));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.jzahar), new AspectList().add(Aspect.DEATH, 8).add(Aspect.DARKNESS, 10).add(Aspect.ELDRITCH, 15));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dreadguard), new AspectList().add(Aspect.UNDEAD, 5).add(Aspect.FLESH, 5).add(DREAD, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.spectral_dragon), new AspectList().add(Aspect.SOUL, 6).add(Aspect.AIR, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.asorah), new AspectList().add(Aspect.SOUL, 12).add(Aspect.AIR, 8).add(Aspect.BEAST, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_creature), new AspectList().add(Aspect.DARKNESS, 2).add(Aspect.BEAST, 1));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_monster), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.BEAST, 2));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dreadling), new AspectList().add(Aspect.UNDEAD, 3).add(DREAD, 3).add(Aspect.FLESH, 3));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dread_spawn), new AspectList().add(Aspect.UNDEAD, 3).add(DREAD, 3).add(Aspect.FLESH, 3));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_pig), new AspectList().add(Aspect.FLESH, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.skeleton_goliath), new AspectList().add(Aspect.UNDEAD, 6).add(Aspect.DEATH, 1).add(Aspect.EARTH, 2));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.spawn_of_chagaroth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FLESH, 3).add(DREAD, 3));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.fist_of_chagaroth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FLESH, 3).add(DREAD, 3));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.chagaroth), new AspectList().add(Aspect.UNDEAD, 8).add(Aspect.FLESH, 10).add(DREAD, 15));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_beast), new AspectList().add(Aspect.DARKNESS, 6).add(Aspect.BEAST, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.sacthoth), new AspectList().add(Aspect.DEATH, 10).add(Aspect.DARKNESS, 15).add(Aspect.BEAST, 8));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.remnant), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.omothol_ghoul), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.minion_of_the_gatekeeper), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.greater_dread_spawn), new AspectList().add(Aspect.UNDEAD, 3).add(DREAD, 3).add(Aspect.FLESH, 3));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.lesser_dreadbeast), new AspectList().add(Aspect.UNDEAD, 3).add(DREAD, 3).add(Aspect.FLESH, 3));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.lesser_shoggoth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.FLESH, 3).add(Aspect.SLIME, 3));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_cow), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.FLESH, 2));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_chicken), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.FLESH, 2));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_cow), new AspectList().add(Aspect.FLESH, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
//		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_chicken), new AspectList().add(Aspect.FLESH, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));

		//Infusion enchanting
//		ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", AbyssalCraft.lightPierce, 10, new AspectList().add(Aspect.LIGHT, 10), new ItemStack[]{new ItemStack(Items.glowstone_dust), new ItemStack(Items.arrow)});
//		ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", AbyssalCraft.ironWall, 5, new AspectList().add(Aspect.ARMOR, 10).add(Aspect.EARTH, 10), new ItemStack[]{new ItemStack(Blocks.iron_block), new ItemStack(Blocks.cobblestone_wall)});
//		ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", AbyssalCraft.coraliumE, 20, new AspectList().add(CORALIUM, 10).add(Aspect.ENERGY, 10), new ItemStack[]{new ItemStack(AbyssalCraft.Cingot), new ItemStack(AbyssalCraft.Corflesh)});
//		ThaumcraftApi.addInfusionEnchantmentRecipe("INFUSIONENCHANTMENT", AbyssalCraft.dreadE, 20, new AspectList().add(DREAD, 10).add(Aspect.ENERGY, 10), new ItemStack[]{new ItemStack(AbyssalCraft.dreadiumingot), new ItemStack(AbyssalCraft.dreadfragment)});
	}

	@Override
	public void postInit() {

	}

	public static String getMobName(String name){
		return "abyssalcraft." + name;
	}

	private void addArmorUpgrade(ItemStack helmet, ItemStack chestplate, ItemStack pants, ItemStack boots,
			Item upgrade,ItemStack oldh, ItemStack oldc, ItemStack oldp, ItemStack oldb){

		GameRegistry.addRecipe(helmet, new Object[] {"#", "@", '#', oldh, '@', upgrade});
		GameRegistry.addRecipe(chestplate, new Object[] {"#", "@", '#', oldc, '@', upgrade});
		GameRegistry.addRecipe(pants, new Object[] {"#", "@", '#', oldp, '@', upgrade});
		GameRegistry.addRecipe(boots, new Object[] {"#", "@", '#', oldb, '@', upgrade});
	}

	private void addToolUpgrade(ItemStack tool1, ItemStack tool2, ItemStack tool3, ItemStack tool4, ItemStack tool5,
			Item upgrade,ItemStack oldtool1, ItemStack oldtool2, ItemStack oldtool3, ItemStack oldtool4, ItemStack oldtool5){

		GameRegistry.addRecipe(tool1, new Object[] {"#", "@", '#', oldtool1, '@', upgrade});
		GameRegistry.addRecipe(tool2, new Object[] {"#", "@", '#', oldtool2, '@', upgrade});
		GameRegistry.addRecipe(tool3, new Object[] {"#", "@", '#', oldtool3, '@', upgrade});
		GameRegistry.addRecipe(tool4, new Object[] {"#", "@", '#', oldtool4, '@', upgrade});
		GameRegistry.addRecipe(tool5, new Object[] {"#", "@", '#', oldtool5, '@', upgrade});
	}
}