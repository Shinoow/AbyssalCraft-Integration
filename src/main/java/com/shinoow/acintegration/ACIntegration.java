package com.shinoow.acintegration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.logging.log4j.Level;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.lib.item.ItemACBasic;
import com.shinoow.acintegration.command.RitualCommand;
import com.shinoow.acintegration.integrations.minetweaker.ACMT;
import com.shinoow.acintegration.integrations.thaumcraft.ACTC;
import com.shinoow.acintegration.integrations.tinkers.ACTiCon;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = ACIntegration.modid, name = ACIntegration.name, version = ACIntegration.version, dependencies = "required-after:forge@[forgeversion,);required-after:abyssalcraft@[2.0.0,];after:tconstruct", useMetadata = false, guiFactory = "com.shinoow.acintegration.client.config.ACIGuiFactory",
acceptedMinecraftVersions = "[1.12.2]", updateJSON = "https://raw.githubusercontent.com/Shinoow/AbyssalCraft-Integration/master/version.json", certificateFingerprint = "cert_fingerprint")
public class ACIntegration {

	public static final String version = "aci_version";
	public static final String modid = "acintegration";
	public static final String name = "AbyssalCraft Integration";

	@Metadata(ACIntegration.modid)
	public static ModMetadata metadata;

	@Instance(ACIntegration.modid)
	public static ACIntegration instance = new ACIntegration();

	public static Configuration cfg;

	public static boolean loadTC, loadEE3, tcItems, loadMT, loadPE, tcWarpDims, tcWarpMobs, tcWarpPE, loadTiCon, loadBQ, loadGS, loadBW;

	public static Item dust_abyssalnite, dust_coralium, dust_dreadium;

	public static final CreativeTabs tabItems = new CreativeTabs("acintegration"){

		@Override
		public ItemStack createIcon() {

			return new ItemStack(dust_abyssalnite);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		metadata = event.getModMetadata();
		getSupporterList();

		MinecraftForge.EVENT_BUS.register(this);

		cfg = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();

		dust_abyssalnite = new ItemACBasic("dust_abyssalnite").setCreativeTab(tabItems);
		dust_coralium = new ItemACBasic("dust_coralium").setCreativeTab(tabItems);
		dust_dreadium = new ItemACBasic("dust_dreadium").setCreativeTab(tabItems);

		registerIntegrations();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {

		GameRegistry.addSmelting(new ItemStack(dust_abyssalnite), new ItemStack(ACItems.abyssalnite_ingot), 3F);
		GameRegistry.addSmelting(new ItemStack(dust_coralium), new ItemStack(ACItems.refined_coralium_ingot), 3F);
		GameRegistry.addSmelting(new ItemStack(dust_dreadium), new ItemStack(ACItems.dreadium_ingot), 3F);

		AbyssalCraftAPI.addSingleCrystallization(new ItemStack(dust_abyssalnite), new ItemStack(ACItems.crystal_shard_abyssalnite, 4), 0.1F);
		AbyssalCraftAPI.addSingleCrystallization(new ItemStack(dust_coralium), new ItemStack(ACItems.crystal_shard_coralium, 4), 0.1F);
		AbyssalCraftAPI.addSingleCrystallization(new ItemStack(dust_dreadium), new ItemStack(ACItems.crystal_shard_dreadium, 4), 0.1F);

		if(FMLCommonHandler.instance().getEffectiveSide().isClient())
			ClientCommandHandler.instance.registerCommand(new RitualCommand());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		FMLLog.log("AbyssalCraft Integration", Level.WARN, "Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
	}

	private void registerIntegrations(){
		if(ACTiCon.instance.canLoad())
			ACTiCon.instance.preInit();
		if(ACMT.instance.canLoad())
			ACMT.instance.preInit();
		if(ACTC.instance.canLoad())
			ACTC.instance.preInit();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.getModID().equals("acintegration"))
			syncConfig();
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event){
		event.getRegistry().register(dust_abyssalnite.setRegistryName(new ResourceLocation("acintegration", "dust_abyssalnite")));
		event.getRegistry().register(dust_coralium.setRegistryName(new ResourceLocation("acintegration", "dust_coralium")));
		event.getRegistry().register(dust_dreadium.setRegistryName(new ResourceLocation("acintegration", "dust_dreadium")));

		OreDictionary.registerOre("dustAbyssalnite", new ItemStack(dust_abyssalnite));
		OreDictionary.registerOre("dustLiquifiedCoralium", new ItemStack(dust_coralium));
		OreDictionary.registerOre("dustDreadium", new ItemStack(dust_dreadium));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event){
		registerItemRender(dust_abyssalnite);
		registerItemRender(dust_coralium);
		registerItemRender(dust_dreadium);
	}

	@SideOnly(Side.CLIENT)
	private void registerItemRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	private static void syncConfig(){

		loadTC = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Integration", true, "Whether or not to load the Thaumcraft integration. Disabling this will override any other part of said integration.").getBoolean();
		//		loadEE3 = cfg.get(Configuration.CATEGORY_GENERAL, "Equivalent Exchange 3 Integration", true, "Whether or not to load the Equivalent Exchange 3 integration.").getBoolean();
		//		tcItems = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Items", true, "Wheter or not to add items through the Thaumcraft integration.").getBoolean();
		loadMT = cfg.get(Configuration.CATEGORY_GENERAL, "MineTweaker Integration", true, "Whether or not to load the MineTweaker integration.").getBoolean();
		loadPE = cfg.get(Configuration.CATEGORY_GENERAL, "ProjectE Integration", true, "Whether or not to load the ProjectE integration").getBoolean();
		tcWarpDims = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Warp: Dimensions", true, "Toggles wheter or not to gain additional warp from being inside AbyssalCraft dimensions").getBoolean();
		tcWarpMobs = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Warp: Mobs", true, "Toggles wheter or not to gain additional warp from attacking/being attacked by AbyssalCraft mobs").getBoolean();
		tcWarpPE = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Warp: PE", true, "Toggles whether or not you gain additional warp through failing rituals and Disruptions").getBoolean();
		loadTiCon = cfg.get(Configuration.CATEGORY_GENERAL, "Tinkers' Construct", true, "Whether or not to load the Tinkers' Construct integration.").getBoolean();
		//		loadBQ = cfg.get(Configuration.CATEGORY_GENERAL, "Better Questing", true, "Whether or not to load the Better Questing integration.").getBoolean();
		loadGS = cfg.get(Configuration.CATEGORY_GENERAL, "Game Stages", true, "Whether or not to load the Game Stages integration.").getBoolean();
		loadBW = cfg.get(Configuration.CATEGORY_GENERAL, "Bewitchment", true, "Whether or not to load the Bewitchment integration.").getBoolean();

		if(cfg.hasChanged())
			cfg.save();
	}

	private void getSupporterList(){
		new Thread("AbyssalCraft Integration Get Supporters") {
			public void run() {
				BufferedReader nameFile;
				String names = "";
				try {
					nameFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/Shinoow/AbyssalCraft/master/supporters.txt").openStream()));

					names = nameFile.readLine();
					nameFile.close();

				} catch (IOException e) {
					FMLLog.log("AbyssalCraft Integration", Level.ERROR, "Failed to fetch supporter list, using local version!");
					names = "Jenni Mort, Simon.R.K";
				}

				metadata.description += String.format("\n\n\u00a76Supporters: %s\u00a7r", names);
			}
		}.start();
	}
}