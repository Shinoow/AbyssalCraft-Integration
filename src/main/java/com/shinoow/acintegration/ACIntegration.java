package com.shinoow.acintegration;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.PageData;
import com.shinoow.acintegration.integrations.ee3.ACEE3;
import com.shinoow.acintegration.integrations.morph.ACMorph;
import com.shinoow.acintegration.integrations.thaumcraft.ACTC;

import cpw.mods.fml.client.event.ConfigChangedEvent;
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

@Mod(modid = ACIntegration.modid, name = ACIntegration.name, version = ACIntegration.version, dependencies = "required-after:Forge@[forgeversion,);required-after:abyssalcraft", useMetadata = false, guiFactory = "com.shinoow.acintegration.client.config.ACIGuiFactory")
public class ACIntegration {

	public static final String version = "1.0.1";
	public static final String modid = "acintegration";
	public static final String name = "AbyssalCraft Integration";

	@Metadata(ACIntegration.modid)
	public static ModMetadata metadata;

	@Instance(ACIntegration.modid)
	public static ACIntegration instance = new ACIntegration();

	public static Configuration cfg;

	public static boolean loadTC, loadMorph, loadEE3, tcItems;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		metadata = event.getModMetadata();

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
		if(Loader.isModLoaded("Morph") && loadMorph)
			AbyssalCraftAPI.registerACIntegration(new ACMorph());
		if(Loader.isModLoaded("EE3") && loadEE3){
			AbyssalCraftAPI.registerACIntegration(new ACEE3());
			ACEE3.instance.preInit();
		}
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
		String[] morphtxt = new String[]{StatCollector.translateToLocal("necro.text.integration.morph.1"),
				StatCollector.translateToLocal("necro.text.integration.morph.2")};

		PageData tc = new PageData(4, "Thaumcraft", tctxt);
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
		loadMorph = cfg.get(Configuration.CATEGORY_GENERAL, "Morph Integration", true, "Whether or not to load the Morph integration.").getBoolean();
		loadEE3 = cfg.get(Configuration.CATEGORY_GENERAL, "Equivalent Exchange 3 Integration", true, "Whether or not to load the Equivalent Exchange 3 integration.").getBoolean();
		tcItems = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Items", true, "Wheter or not to add items through the Thaumcraft integration.").getBoolean();

		if(cfg.hasChanged())
			cfg.save();
	}
}