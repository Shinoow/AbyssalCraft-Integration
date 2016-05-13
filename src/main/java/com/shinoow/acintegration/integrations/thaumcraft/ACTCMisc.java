package com.shinoow.acintegration.integrations.thaumcraft;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.item.ItemUpgradeKit;
import com.shinoow.acintegration.ACIntegration;
import com.shinoow.acintegration.integrations.thaumcraft.creativetabs.TabACThaum;
import com.shinoow.acintegration.integrations.thaumcraft.items.ItemACThaumcraft;
import com.shinoow.acintegration.integrations.thaumcraft.wands.CoraliumRodOnUpdate;
import com.shinoow.acintegration.integrations.thaumcraft.wands.DarklandsRodOnUpdate;
import com.shinoow.acintegration.integrations.thaumcraft.wands.DreadlandsRodOnUpdate;
import com.shinoow.acintegration.integrations.thaumcraft.wands.OmotholRodOnUpdate;
import com.shinoow.acintegration.integrations.thaumcraft.wands.WandCapAC;
import com.shinoow.acintegration.integrations.thaumcraft.wands.WandRodAC;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;

/**
 * This class exists to move fields from the main plugin class that can trigger a NoClassDefFoundError when the<br>
 * IntegrationHandler scans through the plugin. You don't need do to this if the plugin is directly present in<br>
 * your mod, as the classes for your stuff will be present.
 */
public class ACTCMisc {

	public static WandCap abyssalniteCap, coraliumCap, dreadiumCap, ethaxiumCap;
	public static WandRod darklandsRod, coraliumRod, dreadlandsRod, omotholRod;
	public static Aspect CORALIUM, DREAD;

	public static void initWandParts(){
		darklandsRod = new WandRodAC("darklands", 50, new ItemStack(ACTC.wandCore, 1, 0), 1, new DarklandsRodOnUpdate(), new ResourceLocation("acintegration","textures/model/wands/wand_rod_darklands.png"));
		coraliumRod = new WandRodAC("coralium", 75, new ItemStack(ACTC.wandCore, 1, 1), 3, new CoraliumRodOnUpdate(), new ResourceLocation("acintegration","textures/model/wands/wand_rod_coralium.png"));
		dreadlandsRod = new WandRodAC("dreadlands", 100, new ItemStack(ACTC.wandCore, 1, 2), 5, new DreadlandsRodOnUpdate(), new ResourceLocation("acintegration","textures/model/wands/wand_rod_dreadlands.png"));
		omotholRod = new WandRodAC("omothol", 0, new ItemStack(ACTC.wandCore, 1, 3), 7, new OmotholRodOnUpdate(), new ResourceLocation("acintegration","textures/model/wands/wand_rod_omothol.png"));

		abyssalniteCap = new WandCapAC("abyssalnite", 0.95F, new ItemStack(ACTC.wandCap, 1, 0), 1);
		coraliumCap = new WandCapAC("coralium", 0.9F, new ItemStack(ACTC.wandCap, 1, 1), 3);
		dreadiumCap = new WandCapAC("dreadium", 0.85F, new ItemStack(ACTC.wandCap, 1, 2), 5);
		ethaxiumCap = new WandCapAC("ethaxium", 0.8F, new ItemStack(ACTC.wandCap, 1, 3), 7);
	}

	public static void initAspects(){

		CORALIUM = new Aspect("coralos", 0x00FFEE, new Aspect[] {Aspect.POISON, Aspect.WATER}, new ResourceLocation("acintegration", "textures/aspects/coralos.png"), 1);
		DREAD = new Aspect("dreadia", 0xB00000, new Aspect[] {Aspect.POISON, Aspect.FIRE}, new ResourceLocation("acintegration", "textures/aspects/dreadia.png"), 1);

	}
}