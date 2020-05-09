package com.shinoow.acintegration.integrations.thaumcraft;

import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.acintegration.ACIntegration;
import com.shinoow.acintegration.integrations.thaumcraft.cap.ITaintTimerCapability;
import com.shinoow.acintegration.integrations.thaumcraft.cap.TaintTimerCapability;
import com.shinoow.acintegration.integrations.thaumcraft.cap.TaintTimerCapabilityStorage;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;

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

		return Loader.isModLoaded("thaumcraft") && ACIntegration.loadTC;
	}

	@Override
	public void preInit() {

		ACTCMisc.initAspects();
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
		CapabilityManager.INSTANCE.register(ITaintTimerCapability.class, TaintTimerCapabilityStorage.instance, TaintTimerCapability::new);
	}

	@Override
	public void init(){

		MinecraftForge.EVENT_BUS.register(new ACTCEvents());

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


	}

	@Override
	public void postInit() {

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