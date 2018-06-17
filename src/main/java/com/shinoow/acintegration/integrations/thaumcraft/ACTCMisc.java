package com.shinoow.acintegration.integrations.thaumcraft;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

/**
 * This class exists to move fields from the main plugin class that can trigger a NoClassDefFoundError when the<br>
 * IntegrationHandler scans through the plugin. You don't need do to this if the plugin is directly present in<br>
 * your mod, as the classes for your stuff will be present.
 */
public class ACTCMisc {

//	public static WandCap abyssalniteCap, coraliumCap, dreadiumCap, ethaxiumCap;
//	public static WandRod darklandsRod, coraliumRod, dreadlandsRod, omotholRod;
	public static Aspect CORALIUM, DREAD;

//	public static void initWandParts(){
//		darklandsRod = new WandRodAC("darklands", 250, new ItemStack(ACTC.wandCore, 1, 0), 1, new DarklandsRodOnUpdate(), new ResourceLocation("acintegration","model/wands/wand_rod_darklands"));
//		coraliumRod = new WandRodAC("coraliumstone", 375, new ItemStack(ACTC.wandCore, 1, 1), 3, new CoraliumRodOnUpdate(), new ResourceLocation("abyssalcraft","blocks/cstone"));
//		dreadlandsRod = new WandRodAC("dreadlands", 500, new ItemStack(ACTC.wandCore, 1, 2), 5, new DreadlandsRodOnUpdate(), new ResourceLocation("acintegration","model/wands/wand_rod_dreadlands"));
//		omotholRod = new WandRodAC("omothol", 0, new ItemStack(ACTC.wandCore, 1, 3), 7, new OmotholRodOnUpdate(), new ResourceLocation("abyssalcraft","blocks/os"));
//
//		abyssalniteCap = new WandCapAC("abyssalnite", 0.95F, new ItemStack(ACTC.wandCap, 1, 0), 1);
//		coraliumCap = new WandCapAC("coralium", 0.9F, new ItemStack(ACTC.wandCap, 1, 1), 3);
//		dreadiumCap = new WandCapAC("dreadium", 0.85F, new ItemStack(ACTC.wandCap, 1, 2), 5);
//		ethaxiumCap = new WandCapAC("ethaxium", 0.8F, new ItemStack(ACTC.wandCap, 1, 3), 7);
//	}

	public static void initAspects(){
		CORALIUM = new Aspect("coralos", 0x00FFEE, new Aspect[] {Aspect.UNDEAD, Aspect.WATER}, new ResourceLocation("acintegration", "textures/aspects/coralos.png"), 1);
		DREAD = new Aspect("dreadia", 0xB00000, new Aspect[] {Aspect.UNDEAD, Aspect.FIRE}, new ResourceLocation("acintegration", "textures/aspects/dreadia.png"), 1);
	}
}