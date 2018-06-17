package com.shinoow.acintegration.integrations.thaumcraft;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI.ACEntities;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.acintegration.ACIntegration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;

@ACPlugin
public class ACTC implements IACPlugin {

	public static Item wandCap, wandCore, thaumiumU, voidmetalU;
	public static ItemStack darkWand, corWand, dreadWand, omotholWand, endWand;

	@Override
	public String getModName(){
		return "Thaumcraft";
	}

	public static ACTC instance = new ACTC();

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("Thaumcraft") && ACIntegration.loadTC;
	}

	@Override
	public void preInit() {

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
//			ACTCMisc.initWandParts();
//
//			darkWand = new ItemStack(ItemsTC.wand);
//			((ItemWand)darkWand.getItem()).setCap(darkWand, WandCap.caps.get("abyssalnite"));
//			((ItemWand)darkWand.getItem()).setRod(darkWand, WandRod.rods.get("darklands"));
//			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.AIR, 25000, true);
//			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.EARTH, 25000, true);
//			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.FIRE, 25000, true);
//			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.WATER, 25000, true);
//			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.ORDER, 25000, true);
//			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.ENTROPY, 25000, true);
//
//			corWand = new ItemStack(ItemsTC.wand);
//			((ItemWand)corWand.getItem()).setCap(corWand, WandCap.caps.get("coralium"));
//			((ItemWand)corWand.getItem()).setRod(corWand, WandRod.rods.get("coraliumstone"));
//			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.AIR, 37500, true);
//			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.EARTH, 37500, true);
//			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.FIRE, 37500, true);
//			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.WATER, 37500, true);
//			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.ORDER, 37500, true);
//			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.ENTROPY, 37500, true);
//
//			dreadWand = new ItemStack(ItemsTC.wand);
//			((ItemWand)dreadWand.getItem()).setCap(dreadWand, WandCap.caps.get("dreadium"));
//			((ItemWand)dreadWand.getItem()).setRod(dreadWand, WandRod.rods.get("dreadlands"));
//			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.AIR, 50000, true);
//			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.EARTH, 50000, true);
//			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.FIRE, 50000, true);
//			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.WATER, 50000, true);
//			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.ORDER, 50000, true);
//			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.ENTROPY, 50000, true);
//
//			omotholWand = new ItemStack(ItemsTC.wand);
//			((ItemWand)omotholWand.getItem()).setCap(omotholWand, WandCap.caps.get("ethaxium"));
//			((ItemWand)omotholWand.getItem()).setRod(omotholWand, WandRod.rods.get("omothol"));
//			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.AIR, 0, true);
//			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.EARTH, 0, true);
//			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.FIRE, 0, true);
//			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.WATER, 0, true);
//			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.ORDER, 0, true);
//			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.ENTROPY, 0, true);
//
//			endWand = new ItemStack(ItemsTC.wand);
//			((ItemWand)endWand.getItem()).setCap(endWand, WandCap.caps.get("void"));
//			((ItemWand)endWand.getItem()).setRod(endWand, WandRod.rods.get("omothol"));
//			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.AIR, 0, true);
//			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.EARTH, 0, true);
//			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.FIRE, 0, true);
//			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.WATER, 0, true);
//			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.ORDER, 0, true);
//			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.ENTROPY, 0, true);
//
//			TabACThaum.instance.addWands();
//			TabACThaum.instance.addAllItemsAndBlocks();
//
//			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
//				String[] caps = {"abyssalnite", "coralium", "dreadium", "ethaxium"};
//				String[] cores = {"darklands", "coralium", "dreadlands", "omothol"};
//				ResourceLocation[] capres = new ResourceLocation[caps.length];
//				ResourceLocation[] coreres = new ResourceLocation[cores.length];
//				for(int i = 0; i < 4; i++){
//					capres[i] = new ResourceLocation("acintegration", "wandcap_" + caps[i]);
//					coreres[i] = new ResourceLocation("acintegration", "wandcore_" + cores[i]);
//				}
//				ModelBakery.registerItemVariants(wandCap, capres);
//				ModelBakery.registerItemVariants(wandCore, coreres);
//			}
//		}
	}

	@Override
	public void init(){

		if(ACIntegration.tcWarp)
			MinecraftForge.EVENT_BUS.register(new ACTCEvents());
		ACTCMisc.initAspects();

//		if(ACIntegration.tcItems){

//			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
//				String[] caps = {"abyssalnite", "coralium", "dreadium", "ethaxium"};
//				String[] cores = {"darklands", "coralium", "dreadlands", "omothol"};
//				RenderItem render = Minecraft.getMinecraft().getRenderItem();
//				for(int i = 0; i < 4; i++){
//					render.getItemModelMesher().register(wandCap, i, new ModelResourceLocation("acintegration:wandcap_" + caps[i], "inventory"));
//					render.getItemModelMesher().register(wandCore, i, new ModelResourceLocation("acintegration:wandcore_" + cores[i], "inventory"));
//				}
//				render.getItemModelMesher().register(thaumiumU, 0, new ModelResourceLocation("acintegration:thaumiumu", "inventory"));
//				render.getItemModelMesher().register(voidmetalU, 0, new ModelResourceLocation("acintegration:voidmetalu", "inventory"));
//			}

//			GameRegistry.addRecipe(new ItemStack(thaumiumU), new Object[] {"#%", "%&", '#', Items.iron_ingot, '%', new ItemStack(ItemsTC.ingots, 1, 0), '&', ACItems.iron_upgrade_kit});
//			GameRegistry.addRecipe(new ItemStack(voidmetalU), new Object[] {"#%", "%&", '#', new ItemStack(ItemsTC.ingots, 1, 0), '%', new ItemStack(ItemsTC.ingots, 1, 1), '&', thaumiumU});
//
//			ThaumcraftApi.addWarpToItem(new ItemStack(thaumiumU), 10);
//			ThaumcraftApi.addWarpToItem(new ItemStack(voidmetalU), 10);

//			addArmorUpgrade(new ItemStack(ItemsTC.thaumiumHelm), new ItemStack(ItemsTC.thaumiumChest), new ItemStack(ItemsTC.thaumiumLegs), new ItemStack(ItemsTC.thaumiumBoots), thaumiumU, new ItemStack(Items.iron_helmet, 1, OreDictionary.WILDCARD_VALUE),
//					new ItemStack(Items.iron_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_boots, 1, OreDictionary.WILDCARD_VALUE));
//			addArmorUpgrade(new ItemStack(ItemsTC.voidHelm), new ItemStack(ItemsTC.voidChest), new ItemStack(ItemsTC.voidLegs), new ItemStack(ItemsTC.voidBoots), voidmetalU, new ItemStack(ItemsTC.thaumiumHelm, 1, OreDictionary.WILDCARD_VALUE),
//					new ItemStack(ItemsTC.thaumiumChest, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumLegs, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumBoots, 1, OreDictionary.WILDCARD_VALUE));
//
//			addToolUpgrade(new ItemStack(ItemsTC.thaumiumSword), new ItemStack(ItemsTC.thaumiumPick), new ItemStack(ItemsTC.thaumiumAxe), new ItemStack(ItemsTC.thaumiumShovel), new ItemStack(ItemsTC.thaumiumHoe), thaumiumU,
//					new ItemStack(Items.iron_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_axe, 1, OreDictionary.WILDCARD_VALUE),
//					new ItemStack(Items.iron_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_hoe, 1, OreDictionary.WILDCARD_VALUE));
//			addToolUpgrade(new ItemStack(ItemsTC.voidSword), new ItemStack(ItemsTC.voidPick), new ItemStack(ItemsTC.voidAxe), new ItemStack(ItemsTC.voidShovel), new ItemStack(ItemsTC.voidHoe), voidmetalU,
//					new ItemStack(ItemsTC.thaumiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumPick, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumAxe, 1, OreDictionary.WILDCARD_VALUE),
//					new ItemStack(ItemsTC.thaumiumShovel, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumHoe, OreDictionary.WILDCARD_VALUE));
//
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 0), new AspectList().add(Aspect.ORDER, 30).add(Aspect.FIRE, 30).add(Aspect.AIR, 30), new Object[] {"###", "# #", '#', new ItemStack(ACItems.ingot_nugget, 1, 0)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 1), new AspectList().add(Aspect.ORDER, 40).add(Aspect.FIRE, 40).add(Aspect.AIR, 40), new Object[] {"###", "# #", '#', new ItemStack(ACItems.ingot_nugget, 1, 1)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 2), new AspectList().add(Aspect.ORDER, 50).add(Aspect.FIRE, 50).add(Aspect.AIR, 50), new Object[] {"###", "# #", '#', new ItemStack(ACItems.ingot_nugget, 1, 2)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 3), new AspectList().add(Aspect.ORDER, 60).add(Aspect.FIRE, 60).add(Aspect.AIR, 60), new Object[] {"###", "# #", '#', new ItemStack(ACItems.ingot_nugget, 1, 3)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 0), new AspectList().add(Aspect.ENTROPY, 24), new Object[] {" # ", "#  ", '#', ACBlocks.darklands_oak_wood});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 1), new AspectList().add(Aspect.ENTROPY, 24), new Object[] {" # ", "#  ", '#', ACBlocks.coralium_stone});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 2), new AspectList().add(Aspect.ENTROPY, 24).add(Aspect.FIRE, 24), new Object[] {" # ", "#  ", '#', ACBlocks.dreadlands_log});
//			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 3), new AspectList().add(Aspect.ENTROPY, 24), new Object[] {" # ", "#  ", '#', ACBlocks.omothol_stone});
//			ThaumcraftApi.addArcaneCraftingRecipe("", darkWand, new AspectList().add(Aspect.AIR, 50).add(Aspect.EARTH, 50).add(Aspect.FIRE, 50).add(Aspect.WATER, 50).add(Aspect.ENTROPY, 50).add(Aspect.ORDER, 50), new Object[] {"  #", " % ", "#  ", '#', new ItemStack(wandCap, 1, 0), '%', new ItemStack(wandCore, 1, 0)});
//			ThaumcraftApi.addArcaneCraftingRecipe("", dreadWand, new AspectList().add(Aspect.AIR, 50).add(Aspect.EARTH, 50).add(Aspect.FIRE, 50).add(Aspect.WATER, 50).add(Aspect.ENTROPY, 50).add(Aspect.ORDER, 50), new Object[] {"  #", " % ", "#  ", '#', new ItemStack(wandCap, 1, 2), '%', new ItemStack(wandCore, 1, 2)});
//
//			ThaumcraftApi.addInfusionCraftingRecipe("", corWand, 20, new AspectList().add(Aspect.ENTROPY, 10).add(ACTCMisc.CORALIUM, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 1), new ItemStack[]{new ItemStack(wandCap, 1, 1), new ItemStack(wandCap, 1, 1)});
//			ThaumcraftApi.addInfusionCraftingRecipe("", omotholWand, 30, new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.SOUL, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 3), new ItemStack[]{new ItemStack(wandCap, 1, 3), new ItemStack(wandCap, 1, 3)});
//			ThaumcraftApi.addInfusionCraftingRecipe("", endWand, 30, new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.ELDRITCH, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 3), new ItemStack[]{new ItemStack(ItemsTC.wandCaps, 1, 6), new ItemStack(ItemsTC.wandCaps, 1, 6)});
//		}

		//Aspects, blocks
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 0), new AspectList().add(Aspect.EARTH, 2).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.darkstone_brick), new AspectList().add(Aspect.EARTH, 2).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 0), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.abyssalnite_ore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.dreadlands_abyssalnite_ore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.dreaded_abyssalnite_ore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.coralium_ore), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.coralium_infused_stone), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.abyssal_coralium_ore), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.liquified_coralium_ore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.pearlescent_coralium_ore), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 1), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.abyssal_stone_brick), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.abyssal_stone_button), new AspectList().add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.abyssal_gateway), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.UNDEAD, 1).add(Aspect.MOTION, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.dreaded_gateway), new AspectList().add(Aspect.DARKNESS, 4).add(ACTCMisc.DREAD, 1).add(Aspect.MOTION, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.omothol_gateway), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.ELDRITCH, 1).add(Aspect.MOTION, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 2), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 3), new AspectList().add(Aspect.EARTH, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.liquid_coralium), new AspectList().add(Aspect.WATER, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.liquid_antimatter), new AspectList().add(Aspect.WATER, 3).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 5), new AspectList().add(Aspect.SOUL, 2).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.ethaxium_brick), new int[]{0}, new AspectList().add(Aspect.SOUL, 3).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.ethaxium_brick), new int[]{1}, new AspectList().add(Aspect.SOUL, 3).add(Aspect.ELDRITCH, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 6), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 4), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.dreadlands_infused_powerstone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ENERGY, 2).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 7), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.shoggoth_ooze), new AspectList().add(Aspect.EARTH, 1).add(Aspect.LIFE, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.shoggoth_biomass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.LIFE, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 0), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 1), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 2), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 3), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 4), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 5), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 6), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.energy_pedestal), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.monolith_pillar), new AspectList().add(Aspect.EARTH, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.sacrificial_altar), new AspectList().add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1).add(Aspect.DEATH, 1).add(Aspect.EXCHANGE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.tiered_energy_pedestal), new int[]{0, 1, 2, 3}, new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.tiered_sacrificial_altar), new int[]{0, 1, 2, 3}, new AspectList().add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1).add(Aspect.DEATH, 1).add(Aspect.EXCHANGE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 1), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 2), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 3), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 4), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.abyssal_sand), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.fused_abyssal_sand), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.abyssal_sand_glass), new AspectList().add(Aspect.CRYSTAL, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.dreadlands_grass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.dreadlands_dirt), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.DREAD, 1));

		//Aspects, items
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.abyssalnite_ingot), new AspectList().add(Aspect.METAL, 3).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.refined_coralium_ingot), new AspectList().add(Aspect.METAL, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.dreadium_ingot), new AspectList().add(Aspect.METAL, 3).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem), new AspectList().add(Aspect.CRYSTAL, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_2), new AspectList().add(Aspect.CRYSTAL, 4).add(ACTCMisc.CORALIUM, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_3), new AspectList().add(Aspect.CRYSTAL, 6).add(ACTCMisc.CORALIUM, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_4), new AspectList().add(Aspect.CRYSTAL, 8).add(ACTCMisc.CORALIUM, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_5), new AspectList().add(Aspect.CRYSTAL, 10).add(ACTCMisc.CORALIUM, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_6), new AspectList().add(Aspect.CRYSTAL, 12).add(ACTCMisc.CORALIUM, 6));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_7), new AspectList().add(Aspect.CRYSTAL, 14).add(ACTCMisc.CORALIUM, 7));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_8), new AspectList().add(Aspect.CRYSTAL, 16).add(ACTCMisc.CORALIUM, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_9), new AspectList().add(Aspect.CRYSTAL, 18).add(ACTCMisc.CORALIUM, 9));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.ethaxium_ingot), new AspectList().add(Aspect.METAL, 1).add(Aspect.SOUL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_brick), new AspectList().add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.ethaxium_brick), new AspectList().add(Aspect.FIRE, 1).add(Aspect.SOUL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.shadow_fragment), new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.DARKNESS, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.nitre), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_pearl), new AspectList().add(Aspect.CRYSTAL, 18).add(ACTCMisc.CORALIUM, 9));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_plagued_flesh), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.coralium_plagued_flesh_on_a_bone), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.CORALIUM, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.dread_fragment), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.omothol_flesh), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.eldritch_scale), new AspectList().add(Aspect.LIFE, 2).add(Aspect.ELDRITCH, 1).add(Aspect.WATER, 1).add(Aspect.PROTECT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.powerstone_tracker), new AspectList().add(Aspect.FLIGHT, 2).add(Aspect.SENSES, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.eye_of_the_abyss), new AspectList().add(Aspect.SENSES, 3).add(Aspect.DARKNESS, 3).add(ACTCMisc.CORALIUM, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.essence, 1, 0), new AspectList().add(Aspect.SENSES, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.essence, 1, 1), new AspectList().add(Aspect.SENSES, 2).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.essence, 1, 2), new AspectList().add(Aspect.SENSES, 2).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.skin, 1, 0), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(ACTCMisc.CORALIUM, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.skin, 1, 1), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(ACTCMisc.DREAD, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.skin, 1, 2), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(Aspect.ELDRITCH, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 0), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 1), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(ACTCMisc.CORALIUM, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 2), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(ACTCMisc.DREAD, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 3), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(Aspect.ELDRITCH, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 4), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(Aspect.DARKNESS, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.ritual_charm), new AspectList().add(Aspect.CRAFT, 2).add(Aspect.CRYSTAL, 1).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.cthulhu_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.hastur_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.jzahar_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.azathoth_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.nyarlathotep_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.yog_sothoth_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.shub_niggurath_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.essence_of_the_gatekeeper), new AspectList().add(Aspect.ELDRITCH, 3).add(Aspect.DARKNESS, 3).add(Aspect.DEATH, 3).add(Aspect.ENERGY, 3));

		//Aspects, crystals
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 15), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 16), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 17), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 18), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 19), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 20), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 21), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 22), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 23), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal, 1, 24), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 15), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 16), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 17), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 18), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 19), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 20), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 21), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 22), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 23), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 24), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 15), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));

		//Aspect, entities
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.depths_ghoul), new AspectList().add(Aspect.UNDEAD, 6).add(Aspect.DEATH, 1).add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_pig), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.abyssal_zombie), new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.MAN, 1).add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.jzahar), new AspectList().add(Aspect.DEATH, 8).add(Aspect.DARKNESS, 10).add(Aspect.ELDRITCH, 15));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dreadguard), new AspectList().add(Aspect.UNDEAD, 5).add(Aspect.LIFE, 5).add(ACTCMisc.DREAD, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.spectral_dragon), new AspectList().add(Aspect.SOUL, 6).add(Aspect.AIR, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.asorah), new AspectList().add(Aspect.SOUL, 12).add(Aspect.AIR, 8).add(Aspect.BEAST, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_creature), new AspectList().add(Aspect.DARKNESS, 2).add(Aspect.BEAST, 1));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_monster), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dreadling), new AspectList().add(Aspect.UNDEAD, 3).add(ACTCMisc.DREAD, 3).add(Aspect.LIFE, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dread_spawn), new AspectList().add(Aspect.UNDEAD, 3).add(ACTCMisc.DREAD, 3).add(Aspect.LIFE, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_pig), new AspectList().add(Aspect.LIFE, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.skeleton_goliath), new AspectList().add(Aspect.UNDEAD, 6).add(Aspect.DEATH, 1).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.spawn_of_chagaroth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(ACTCMisc.DREAD, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.fist_of_chagaroth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(ACTCMisc.DREAD, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.chagaroth), new AspectList().add(Aspect.UNDEAD, 8).add(Aspect.LIFE, 10).add(ACTCMisc.DREAD, 15));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_beast), new AspectList().add(Aspect.DARKNESS, 6).add(Aspect.BEAST, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.sacthoth), new AspectList().add(Aspect.DEATH, 10).add(Aspect.DARKNESS, 15).add(Aspect.BEAST, 8));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.remnant), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.omothol_ghoul), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.minion_of_the_gatekeeper), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.greater_dread_spawn), new AspectList().add(Aspect.UNDEAD, 3).add(ACTCMisc.DREAD, 3).add(Aspect.LIFE, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.lesser_dreadbeast), new AspectList().add(Aspect.UNDEAD, 3).add(ACTCMisc.DREAD, 3).add(Aspect.LIFE, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.lesser_shoggoth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_cow), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_chicken), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_cow), new AspectList().add(Aspect.LIFE, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_chicken), new AspectList().add(Aspect.LIFE, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_sheep), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_sheep), new AspectList().add(Aspect.LIFE, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.coralium_squid), new AspectList().add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(ACTCMisc.CORALIUM, 3));
	}

	@Override
	public void postInit() {

	}

	public static String getMobName(String name){
		return "abyssalcraft." + name;
	}

//	private void addArmorUpgrade(ItemStack helmet, ItemStack chestplate, ItemStack pants, ItemStack boots,
//			Item upgrade,ItemStack oldh, ItemStack oldc, ItemStack oldp, ItemStack oldb){
//
//		GameRegistry.addRecipe(helmet, new Object[] {"#", "@", '#', oldh, '@', upgrade});
//		GameRegistry.addRecipe(chestplate, new Object[] {"#", "@", '#', oldc, '@', upgrade});
//		GameRegistry.addRecipe(pants, new Object[] {"#", "@", '#', oldp, '@', upgrade});
//		GameRegistry.addRecipe(boots, new Object[] {"#", "@", '#', oldb, '@', upgrade});
//	}
//
//	private void addToolUpgrade(ItemStack tool1, ItemStack tool2, ItemStack tool3, ItemStack tool4, ItemStack tool5,
//			Item upgrade,ItemStack oldtool1, ItemStack oldtool2, ItemStack oldtool3, ItemStack oldtool4, ItemStack oldtool5){
//
//		GameRegistry.addRecipe(tool1, new Object[] {"#", "@", '#', oldtool1, '@', upgrade});
//		GameRegistry.addRecipe(tool2, new Object[] {"#", "@", '#', oldtool2, '@', upgrade});
//		GameRegistry.addRecipe(tool3, new Object[] {"#", "@", '#', oldtool3, '@', upgrade});
//		GameRegistry.addRecipe(tool4, new Object[] {"#", "@", '#', oldtool4, '@', upgrade});
//		GameRegistry.addRecipe(tool5, new Object[] {"#", "@", '#', oldtool5, '@', upgrade});
//	}
}