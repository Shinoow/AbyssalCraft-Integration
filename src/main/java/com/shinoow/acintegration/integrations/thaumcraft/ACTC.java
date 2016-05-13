package com.shinoow.acintegration.integrations.thaumcraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWand;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI.ACEntities;
import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.abyssalcraft.api.item.ItemUpgradeKit;
import com.shinoow.acintegration.ACIntegration;
import com.shinoow.acintegration.integrations.thaumcraft.creativetabs.TabACThaum;
import com.shinoow.acintegration.integrations.thaumcraft.items.ItemACThaumcraft;

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

		if(ACIntegration.tcWarp)
			MinecraftForge.EVENT_BUS.register(new ACTCEvents());

		if(ACIntegration.tcItems){

//			//did it like this solely for disabling everything item-related if something toggles it off
			TabACThaum.instance = new TabACThaum();

			wandCap = new ItemACThaumcraft("wandcap", true, "abyssalnite", "coralium", "dreadium", "ethaxium");
			wandCore = new ItemACThaumcraft("wandcore", true, "darklands", "coralium", "dreadlands", "omothol");
			thaumiumU = new ItemUpgradeKit("Iron", "Thaumium").setUnlocalizedName("thaumiumu"); //setTextureName(ACIntegration.modid + ":" + "thaumiumu")
			voidmetalU = new ItemUpgradeKit("Thaumium", "Void metal").setUnlocalizedName("voidmetalu"); //setTextureName(ACIntegration.modid + ":" + "voidmetalu")

			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
				TabACThaum.instance.addItem(thaumiumU);
				TabACThaum.instance.addItem(voidmetalU);
			}

			GameRegistry.registerItem(wandCap, "wandcap");
			GameRegistry.registerItem(wandCore, "wandcore");
			GameRegistry.registerItem(thaumiumU, "thaumiumu");
			GameRegistry.registerItem(voidmetalU, "voidmetalu");

			ACTCMisc.initWandParts();

			darkWand = new ItemStack(ItemsTC.wand);
			((ItemWand)darkWand.getItem()).setCap(darkWand, WandCap.caps.get("abyssalnite"));
			((ItemWand)darkWand.getItem()).setRod(darkWand, WandRod.rods.get("darklands"));
			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.AIR, 5000, true);
			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.FIRE, 5000, true);
			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.WATER, 5000, true);
			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.ORDER, 5000, true);
			((ItemWand) darkWand.getItem()).addVis(darkWand, Aspect.ENTROPY, 5000, true);

			corWand = new ItemStack(ItemsTC.wand);
			((ItemWand)corWand.getItem()).setCap(corWand, WandCap.caps.get("coralium"));
			((ItemWand)corWand.getItem()).setRod(corWand, WandRod.rods.get("coraliumstone"));
			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.AIR, 7500, true);
			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.FIRE, 7500, true);
			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.WATER, 7500, true);
			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.ORDER, 7500, true);
			((ItemWand) corWand.getItem()).addVis(corWand, Aspect.ENTROPY, 7500, true);

			dreadWand = new ItemStack(ItemsTC.wand);
			((ItemWand)dreadWand.getItem()).setCap(dreadWand, WandCap.caps.get("dreadium"));
			((ItemWand)dreadWand.getItem()).setRod(dreadWand, WandRod.rods.get("dreadlands"));
			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.AIR, 10000, true);
			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.FIRE, 10000, true);
			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.WATER, 10000, true);
			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.ORDER, 10000, true);
			((ItemWand) dreadWand.getItem()).addVis(dreadWand, Aspect.ENTROPY, 10000, true);

			omotholWand = new ItemStack(ItemsTC.wand);
			((ItemWand)omotholWand.getItem()).setCap(omotholWand, WandCap.caps.get("ethaxium"));
			((ItemWand)omotholWand.getItem()).setRod(omotholWand, WandRod.rods.get("omothol"));
			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.AIR, 0, true);
			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.FIRE, 0, true);
			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.WATER, 0, true);
			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.ORDER, 0, true);
			((ItemWand) omotholWand.getItem()).addVis(omotholWand, Aspect.ENTROPY, 0, true);

			endWand = new ItemStack(ItemsTC.wand);
			((ItemWand)endWand.getItem()).setCap(endWand, WandCap.caps.get("void"));
			((ItemWand)endWand.getItem()).setRod(endWand, WandRod.rods.get("omothol"));
			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.AIR, 0, true);
			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.FIRE, 0, true);
			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.WATER, 0, true);
			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.ORDER, 0, true);
			((ItemWand) endWand.getItem()).addVis(endWand, Aspect.ENTROPY, 0, true);

			TabACThaum.instance.addWands();
			TabACThaum.instance.addAllItemsAndBlocks();
			
			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
				String[] caps = {"abyssalnite", "coralium", "dreadium", "ethaxium"};
				String[] cores = {"darklands", "coralium", "dreadlands", "omothol"};
				ResourceLocation[] capres = new ResourceLocation[caps.length];
				ResourceLocation[] coreres = new ResourceLocation[cores.length];
				for(int i = 0; i < 4; i++){
					capres[i] = new ResourceLocation("acintegration", "wandcap_" + caps[i]);
					coreres[i] = new ResourceLocation("acintegration", "wandcore_" + cores[i]);
				}
				ModelBakery.registerItemVariants(wandCap, capres);
				ModelBakery.registerItemVariants(wandCore, coreres);
			}
		}
	}

	@Override
	public void init(){

		ACTCMisc.initAspects();

		if(ACIntegration.tcItems){

			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
				String[] caps = {"abyssalnite", "coralium", "dreadium", "ethaxium"};
				String[] cores = {"darklands", "coralium", "dreadlands", "omothol"};
				RenderItem render = Minecraft.getMinecraft().getRenderItem();
				for(int i = 0; i < 4; i++){
					render.getItemModelMesher().register(wandCap, i, new ModelResourceLocation("acintegration:wandcap_" + caps[i], "inventory"));
					render.getItemModelMesher().register(wandCore, i, new ModelResourceLocation("acintegration:wandcore_" + cores[i], "inventory"));
				}
				render.getItemModelMesher().register(thaumiumU, 0, new ModelResourceLocation("acintegration:thaumiumu", "inventory"));
				render.getItemModelMesher().register(voidmetalU, 0, new ModelResourceLocation("acintegration:voidmetalu", "inventory"));
			}

			GameRegistry.addRecipe(new ItemStack(thaumiumU), new Object[] {"#%", "%&", '#', Items.iron_ingot, '%', new ItemStack(ItemsTC.ingots, 1, 0), '&', AbyssalCraft.IronU});
			GameRegistry.addRecipe(new ItemStack(voidmetalU), new Object[] {"#%", "%&", '#', new ItemStack(ItemsTC.ingots, 1, 0), '%', new ItemStack(ItemsTC.ingots, 1, 1), '&', thaumiumU});

			ThaumcraftApi.addWarpToItem(new ItemStack(thaumiumU), 10);
			ThaumcraftApi.addWarpToItem(new ItemStack(voidmetalU), 10);

			addArmorUpgrade(new ItemStack(ItemsTC.thaumiumHelm), new ItemStack(ItemsTC.thaumiumChest), new ItemStack(ItemsTC.thaumiumLegs), new ItemStack(ItemsTC.thaumiumBoots), thaumiumU, new ItemStack(Items.iron_helmet, 1, OreDictionary.WILDCARD_VALUE),
					new ItemStack(Items.iron_chestplate, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_leggings, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_boots, 1, OreDictionary.WILDCARD_VALUE));
			addArmorUpgrade(new ItemStack(ItemsTC.voidHelm), new ItemStack(ItemsTC.voidChest), new ItemStack(ItemsTC.voidLegs), new ItemStack(ItemsTC.voidBoots), voidmetalU, new ItemStack(ItemsTC.thaumiumHelm, 1, OreDictionary.WILDCARD_VALUE),
					new ItemStack(ItemsTC.thaumiumChest, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumLegs, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumBoots, 1, OreDictionary.WILDCARD_VALUE));

			addToolUpgrade(new ItemStack(ItemsTC.thaumiumSword), new ItemStack(ItemsTC.thaumiumPick), new ItemStack(ItemsTC.thaumiumAxe), new ItemStack(ItemsTC.thaumiumShovel), new ItemStack(ItemsTC.thaumiumHoe), thaumiumU,
					new ItemStack(Items.iron_sword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_pickaxe, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_axe, 1, OreDictionary.WILDCARD_VALUE),
					new ItemStack(Items.iron_shovel, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.iron_hoe, 1, OreDictionary.WILDCARD_VALUE));
			addToolUpgrade(new ItemStack(ItemsTC.voidSword), new ItemStack(ItemsTC.voidPick), new ItemStack(ItemsTC.voidAxe), new ItemStack(ItemsTC.voidShovel), new ItemStack(ItemsTC.voidHoe), voidmetalU,
					new ItemStack(ItemsTC.thaumiumSword, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumPick, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumAxe, 1, OreDictionary.WILDCARD_VALUE),
					new ItemStack(ItemsTC.thaumiumShovel, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemsTC.thaumiumHoe, OreDictionary.WILDCARD_VALUE));

			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 0), new AspectList().add(Aspect.ORDER, 3).add(Aspect.FIRE, 3).add(Aspect.AIR, 3), new Object[] {"###", "# #", '#', new ItemStack(AbyssalCraft.nugget, 1, 0)});
			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 1), new AspectList().add(Aspect.ORDER, 4).add(Aspect.FIRE, 4).add(Aspect.AIR, 4), new Object[] {"###", "# #", '#', new ItemStack(AbyssalCraft.nugget, 1, 1)});
			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 2), new AspectList().add(Aspect.ORDER, 5).add(Aspect.FIRE, 5).add(Aspect.AIR, 5), new Object[] {"###", "# #", '#', new ItemStack(AbyssalCraft.nugget, 1, 2)});
			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCap, 1, 3), new AspectList().add(Aspect.ORDER, 6).add(Aspect.FIRE, 6).add(Aspect.AIR, 6), new Object[] {"###", "# #", '#', new ItemStack(AbyssalCraft.nugget, 1, 3)});
			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 0), new AspectList().add(Aspect.ENTROPY, 5), new Object[] {" # ", "#  ", '#', AbyssalCraft.DLTLog});
			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 1), new AspectList().add(Aspect.ENTROPY, 5), new Object[] {" # ", "#  ", '#', AbyssalCraft.cstone});
			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 2), new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.FIRE, 5), new Object[] {" # ", "#  ", '#', AbyssalCraft.dreadlog});
			ThaumcraftApi.addArcaneCraftingRecipe("", new ItemStack(wandCore, 1, 3), new AspectList().add(Aspect.ENTROPY, 5), new Object[] {" # ", "#  ", '#', AbyssalCraft.omotholstone});
			ThaumcraftApi.addArcaneCraftingRecipe("", darkWand, new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.ORDER, 5), new Object[] {"  #", " % ", "#  ", '#', new ItemStack(wandCap, 1, 0), '%', new ItemStack(wandCore, 1, 0)});
			ThaumcraftApi.addArcaneCraftingRecipe("", dreadWand, new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.ORDER, 5), new Object[] {"  #", " % ", "#  ", '#', new ItemStack(wandCap, 1, 2), '%', new ItemStack(wandCore, 1, 2)});

			ThaumcraftApi.addInfusionCraftingRecipe("", corWand, 20, new AspectList().add(Aspect.ENTROPY, 10).add(ACTCMisc.CORALIUM, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 1), new ItemStack[]{new ItemStack(wandCap, 1, 1), new ItemStack(wandCap, 1, 1)});
			ThaumcraftApi.addInfusionCraftingRecipe("", omotholWand, 30, new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.SOUL, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 3), new ItemStack[]{new ItemStack(wandCap, 1, 3), new ItemStack(wandCap, 1, 3)});
			ThaumcraftApi.addInfusionCraftingRecipe("", endWand, 30, new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.ELDRITCH, 10).add(Aspect.DARKNESS, 10), new ItemStack(wandCore, 1, 3), new ItemStack[]{new ItemStack(ItemsTC.wandCaps, 1, 6), new ItemStack(ItemsTC.wandCaps, 1, 6)});
		}

		//Aspects, blocks
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Darkstone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Darkgrass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Darkstone_brick), new AspectList().add(Aspect.EARTH, 2).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Darkstone_cobble), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abyore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abydreadore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumore), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.CoraliumInfusedStone), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.AbyCorOre), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.AbyLCorOre), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.AbyPCorOre), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abystone), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abybrick), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Abybutton), new AspectList().add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.portal), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.UNDEAD, 1).add(Aspect.MOTION, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadportal), new AspectList().add(Aspect.DARKNESS, 4).add(ACTCMisc.DREAD, 1).add(Aspect.MOTION, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.omotholportal), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.ELDRITCH, 1).add(Aspect.MOTION, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadstone), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abydreadstone), new AspectList().add(Aspect.EARTH, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Cwater), new AspectList().add(Aspect.WATER, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.anticwater), new AspectList().add(Aspect.WATER, 3).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxium), new AspectList().add(Aspect.SOUL, 2).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxiumbrick), new int[]{0}, new AspectList().add(Aspect.SOUL, 3).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxiumbrick), new int[]{1}, new AspectList().add(Aspect.SOUL, 3).add(Aspect.ELDRITCH, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.omotholstone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.cstone), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.PSDL), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ENERGY, 2).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.monolithStone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothBlock), new AspectList().add(Aspect.EARTH, 1).add(Aspect.LIFE, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothBiomass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.LIFE, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.cthulhuStatue), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.hasturStatue), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.jzaharStatue), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.azathothStatue), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.nyarlathotepStatue), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.yogsothothStatue), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shubniggurathStatue), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.monolithStone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.energyPedestal), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.monolithPillar), new AspectList().add(Aspect.EARTH, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.sacrificialAltar), new AspectList().add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1).add(Aspect.DEATH, 1).add(Aspect.EXCHANGE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.tieredEnergyPedestal), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.tieredSacrificialAltar), new AspectList().add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1).add(Aspect.DEATH, 1).add(Aspect.EXCHANGE, 1));

		//Aspects, items
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.abyingot), new AspectList().add(Aspect.METAL, 3).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Cingot), new AspectList().add(Aspect.METAL, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadiumingot), new AspectList().add(Aspect.METAL, 3).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coralium), new AspectList().add(Aspect.CRYSTAL, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster2), new AspectList().add(Aspect.CRYSTAL, 4).add(ACTCMisc.CORALIUM, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster3), new AspectList().add(Aspect.CRYSTAL, 6).add(ACTCMisc.CORALIUM, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster4), new AspectList().add(Aspect.CRYSTAL, 8).add(ACTCMisc.CORALIUM, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster5), new AspectList().add(Aspect.CRYSTAL, 10).add(ACTCMisc.CORALIUM, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster6), new AspectList().add(Aspect.CRYSTAL, 12).add(ACTCMisc.CORALIUM, 6));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster7), new AspectList().add(Aspect.CRYSTAL, 14).add(ACTCMisc.CORALIUM, 7));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster8), new AspectList().add(Aspect.CRYSTAL, 16).add(ACTCMisc.CORALIUM, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coraliumcluster9), new AspectList().add(Aspect.CRYSTAL, 18).add(ACTCMisc.CORALIUM, 9));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxiumIngot), new AspectList().add(Aspect.METAL, 1).add(Aspect.SOUL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.cbrick), new AspectList().add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.ethaxium_brick), new AspectList().add(Aspect.FIRE, 1).add(Aspect.SOUL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shadowfragment), new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.DARKNESS, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.nitre), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Coralium), new AspectList().add(Aspect.CRYSTAL, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Cpearl), new AspectList().add(Aspect.CRYSTAL, 18).add(ACTCMisc.CORALIUM, 9));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Corflesh), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.Corbone), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.CORALIUM, 1).add(Aspect.DEATH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.dreadfragment), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.omotholFlesh), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.eldritchScale), new AspectList().add(Aspect.LIFE, 2).add(Aspect.ELDRITCH, 1).add(Aspect.WATER, 1).add(Aspect.PROTECT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.PSDLfinder), new AspectList().add(Aspect.FLIGHT, 2).add(Aspect.SENSES, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.EoA), new AspectList().add(Aspect.SENSES, 3).add(Aspect.DARKNESS, 3).add(ACTCMisc.CORALIUM, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.essence, 1, 0), new AspectList().add(Aspect.SENSES, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.essence, 1, 1), new AspectList().add(Aspect.SENSES, 2).add(ACTCMisc.DREAD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.essence, 1, 2), new AspectList().add(Aspect.SENSES, 2).add(Aspect.DARKNESS, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.skin, 1, 0), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(ACTCMisc.CORALIUM, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.skin, 1, 1), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(ACTCMisc.DREAD, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.skin, 1, 2), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(Aspect.ELDRITCH, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 0), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 1), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(ACTCMisc.CORALIUM, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 2), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(ACTCMisc.DREAD, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 3), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(Aspect.ELDRITCH, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 4), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(Aspect.DARKNESS, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.charm), new AspectList().add(Aspect.CRAFT, 2).add(Aspect.CRYSTAL, 1).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.cthulhuCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.hasturCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.jzaharCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.azathothCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.nyarlathotepCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.yogsothothCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.shubniggurathCharm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.gatekeeperEssence), new AspectList().add(Aspect.ELDRITCH, 3).add(Aspect.DARKNESS, 3).add(Aspect.DEATH, 3).add(Aspect.ENERGY, 3));

		//Aspects, crystals
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystal, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.DREAD, 1));
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
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(AbyssalCraft.crystalShard, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.DREAD, 1));
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