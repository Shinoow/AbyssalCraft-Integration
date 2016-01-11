package com.shinoow.acintegration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.logging.log4j.Level;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.PageData;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.PageData.PageType;
import com.shinoow.acintegration.integrations.ee3.ACEE3;
import com.shinoow.acintegration.integrations.minetweaker.ACMT;
import com.shinoow.acintegration.integrations.projecte.ACPE;
import com.shinoow.acintegration.integrations.thaumcraft.ACTC;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = ACIntegration.modid, name = ACIntegration.name, version = ACIntegration.version, dependencies = "required-after:Forge@[forgeversion,);required-after:abyssalcraft@[1.9.0,];after:Thaumcraft", useMetadata = false, guiFactory = "com.shinoow.acintegration.client.config.ACIGuiFactory")
public class ACIntegration {

	public static final String version = "1.3.5";
	public static final String modid = "acintegration";
	public static final String name = "AbyssalCraft Integration";

	@Metadata(ACIntegration.modid)
	public static ModMetadata metadata;

	@Instance(ACIntegration.modid)
	public static ACIntegration instance = new ACIntegration();

	public static Configuration cfg;

	public static boolean loadTC, loadEE3, tcItems, loadMT, loadPE, tcWarp;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		metadata = event.getModMetadata();
		metadata.description = metadata.description +"\n\n\u00a76Supporters: "+getSupporterList()+"\u00a7r";

		instance = this;

		cfg = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();

		registerIntegrations();
		registerNecroData();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	private void registerIntegrations(){
		if(Loader.isModLoaded("Thaumcraft") && loadTC){
			AbyssalCraftAPI.registerACIntegration(new ACTC());
			ACTC.instance.preInit();
		}
		if(Loader.isModLoaded("EE3") && loadEE3){
			AbyssalCraftAPI.registerACIntegration(new ACEE3());
			ACEE3.instance.preInit();
		}
		if(Loader.isModLoaded("MineTweaker3") && loadMT)
			AbyssalCraftAPI.registerACIntegration(new ACMT());
		if(Loader.isModLoaded("ProjectE") && loadPE)
			AbyssalCraftAPI.registerACIntegration(new ACPE());
	}

	private void registerNecroData(){
		String[] tctxt = new String[]{StatCollector.translateToLocal("necro.text.integration.tc.1"),
				StatCollector.translateToLocal("necro.text.integration.tc.2"),
				StatCollector.translateToLocal("necro.text.integration.tc.3"),
				StatCollector.translateToLocal("necro.text.integration.tc.4"),
				StatCollector.translateToLocal("necro.text.integration.tc.5"),
				StatCollector.translateToLocal("necro.text.integration.tc.6"),
				StatCollector.translateToLocal("necro.text.integration.tc.7"),
				StatCollector.translateToLocal("necro.text.integration.tc.8")};
		ResourceLocation[] tcres = new ResourceLocation[]{new ResourceLocation("acintegration", "textures/necronomicon/tc1.png"),
				new ResourceLocation("acintegration", "textures/necronomicon/tc2.png"),
				new ResourceLocation("acintegration", "textures/necronomicon/tc3.png"), null};
		String[] morphtxt = new String[]{StatCollector.translateToLocal("necro.text.integration.morph")};

		PageData tc = new PageData(4, "Thaumcraft", tcres, tctxt);
		PageData morph = new PageData(1, "Morph", morphtxt);

		NecroData data = new NecroData(name, StatCollector.translateToLocal("necro.text.integration"), tc, morph);

		AbyssalCraftAPI.registerNecronomiconData(data, 0);
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.modID.equals("acintegration"))
			syncConfig();
	}

	private static void syncConfig(){

		loadTC = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Integration", true, "Whether or not to load the Thaumcraft integration. Disabling this will override any other part of said integration.").getBoolean();
		loadEE3 = cfg.get(Configuration.CATEGORY_GENERAL, "Equivalent Exchange 3 Integration", true, "Whether or not to load the Equivalent Exchange 3 integration.").getBoolean();
		tcItems = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Items", true, "Wheter or not to add items through the Thaumcraft integration.").getBoolean();
		loadMT = cfg.get(Configuration.CATEGORY_GENERAL, "MineTweaker 3 Integration", true, "Whether or not to load the MineTweaker 3 integration.").getBoolean();
		loadPE = cfg.get(Configuration.CATEGORY_GENERAL, "ProjectE Integration", true, "Whether or not to load the ProjectE integration").getBoolean();
		tcWarp = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Warp", true, "Toggles wheter or not to gain additional warp from attacking/being attacked by AbyssalCraft mobs and being inside AbyssalCraft dimensions").getBoolean();

		if(cfg.hasChanged())
			cfg.save();
	}

	private String getSupporterList(){
		BufferedReader nameFile;
		String names = "";
		try {
			nameFile = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/Shinoow/AbyssalCraft/master/supporters.txt").openStream()));

			names = nameFile.readLine();
			nameFile.close();

		} catch (IOException e) {
			FMLLog.log("AbyssalCraft Integration", Level.ERROR, "Failed to fetch supporter list, using local version!");
			names = "Enfalas, Saice Shoop";
		}

		return names;
	}
}