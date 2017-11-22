package com.shinoow.acintegration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Level;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.lib.item.ItemMetadata;
import com.shinoow.acintegration.command.RitualCommand;
import com.shinoow.acintegration.integrations.tinkers.ACTiCon;

@Mod(modid = ACIntegration.modid, name = ACIntegration.name, version = ACIntegration.version, dependencies = "required-after:forge@[forgeversion,);required-after:abyssalcraft@[1.9.4.2,];after:tconstruct", useMetadata = false, guiFactory = "com.shinoow.acintegration.client.config.ACIGuiFactory",
acceptedMinecraftVersions = "[1.11.2]", updateJSON = "https://raw.githubusercontent.com/Shinoow/AbyssalCraft-Integration/master/version.json", certificateFingerprint = "cert_fingerprint")
public class ACIntegration {

	public static final String version = "1.6.2";
	public static final String modid = "acintegration";
	public static final String name = "AbyssalCraft Integration";

	@Metadata(ACIntegration.modid)
	public static ModMetadata metadata;

	@Instance(ACIntegration.modid)
	public static ACIntegration instance = new ACIntegration();

	public static Configuration cfg;

	public static boolean loadTC, loadEE3, tcItems, loadMT, loadPE, tcWarp, loadTiCon, loadBQ, loadGS;

	public static Item dust;

	public static final CreativeTabs tabItems = new CreativeTabs("acintegration"){

		@Override
		public ItemStack getTabIconItem() {

			return new ItemStack(dust);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		metadata = event.getModMetadata();
		metadata.description = metadata.description +"\n\n\u00a76Supporters: "+getSupporterList()+"\u00a7r";

		instance = this;

		cfg = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();

		dust = new ItemMetadata("dust", "abyssalnite", "coralium", "dreadium").setCreativeTab(tabItems);

		GameRegistry.register(dust.setRegistryName(new ResourceLocation("acintegration", "dust")));

		OreDictionary.registerOre("dustAbyssalnite", new ItemStack(dust, 1, 0));
		OreDictionary.registerOre("dustLiquifiedCoralium", new ItemStack(dust, 1, 1));
		OreDictionary.registerOre("dustDreadium", new ItemStack(dust, 1, 2));

		registerIntegrations();
		//		registerNecroData();

		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			String[] names = {"abyssalnite", "coralium", "dreadium"};
			ResourceLocation[] res = new ResourceLocation[names.length];
			for(int i = 0; i < names.length; i++)
				res[i] = new ResourceLocation("acintegration", "dust_" + names[i]);
			ModelBakery.registerItemVariants(dust, res);
		}
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			String[] names = {"abyssalnite", "coralium", "dreadium"};
			RenderItem render = Minecraft.getMinecraft().getRenderItem();
			for(int i = 0; i < names.length; i++)
				render.getItemModelMesher().register(dust, i, new ModelResourceLocation("acintegration:dust_" + names[i], "inventory"));
		}

		GameRegistry.addSmelting(new ItemStack(dust, 1, 0), new ItemStack(ACItems.abyssalnite_ingot), 3F);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 1), new ItemStack(ACItems.refined_coralium_ingot), 3F);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 2), new ItemStack(ACItems.dreadium_ingot), 3F);

		AbyssalCraftAPI.addSingleCrystallization(new ItemStack(dust, 1, 0), new ItemStack(ACItems.crystal_shard, 4, 12), 0.1F);
		AbyssalCraftAPI.addSingleCrystallization(new ItemStack(dust, 1, 1), new ItemStack(ACItems.crystal_shard, 4, 13), 0.1F);
		AbyssalCraftAPI.addSingleCrystallization(new ItemStack(dust, 1, 2), new ItemStack(ACItems.crystal_shard, 4, 14), 0.1F);

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
		//		if(Loader.isModLoaded("Thaumcraft") && loadTC)
		//						ACTC.instance.preInit();
		//		if(Loader.isModLoaded("EE3") && loadEE3)
		//			ACEE3.instance.preInit();
		if(ACTiCon.instance.canLoad())
			ACTiCon.instance.preInit();
	}

	//	private void registerNecroData(){
	//		String[] tctxt = new String[]{I18n.translateToLocal("necro.text.integration.tc.1"),
	//				I18n.translateToLocal("necro.text.integration.tc.2"),
	//				I18n.translateToLocal("necro.text.integration.tc.3"),
	//				I18n.translateToLocal("necro.text.integration.tc.4"),
	//				I18n.translateToLocal("necro.text.integration.tc.5"),
	//				I18n.translateToLocal("necro.text.integration.tc.6"),
	//				I18n.translateToLocal("necro.text.integration.tc.7"),
	//				I18n.translateToLocal("necro.text.integration.tc.8")};
	//		ResourceLocation[] tcres = new ResourceLocation[]{new ResourceLocation("acintegration", "textures/necronomicon/tc1.png"),
	//				new ResourceLocation("acintegration", "textures/necronomicon/tc2.png"),
	//				new ResourceLocation("acintegration", "textures/necronomicon/tc3.png"), null};
	//
	//		Chapter tc = new Chapter("thaumcraft", "Thaumcraft", new Page[]{
	//				new Page(1, tcres[0], tctxt[0]), new Page(2, tctxt[1]), new Page(3, tcres[1], tctxt[2]),
	//				new Page(4, tctxt[3]), new Page(5, tcres[2], tctxt[4]), new Page(5, tctxt[5]),
	//				new Page(7, tctxt[6]), new Page(8, tctxt[7])
	//		});
	//				Chapter morph = new Chapter("morph", "Morph", new Page[]{
	//						new Page(1, I18n.translateToLocal("necro.text.integration.morph"))
	//				});
	//
	//		NecroData data = new NecroData(modid, name, I18n.translateToLocal("necro.text.integration"), tc);
	//
	//				AbyssalCraftAPI.registerNecronomiconData(data, 0);
	//	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.getModID().equals("acintegration"))
			syncConfig();
	}

	private static void syncConfig(){

		//		loadTC = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Integration", true, "Whether or not to load the Thaumcraft integration. Disabling this will override any other part of said integration.").getBoolean();
		//		loadEE3 = cfg.get(Configuration.CATEGORY_GENERAL, "Equivalent Exchange 3 Integration", true, "Whether or not to load the Equivalent Exchange 3 integration.").getBoolean();
		//		tcItems = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Items", true, "Wheter or not to add items through the Thaumcraft integration.").getBoolean();
		loadMT = cfg.get(Configuration.CATEGORY_GENERAL, "MineTweaker Integration", true, "Whether or not to load the MineTweaker integration.").getBoolean();
		loadPE = cfg.get(Configuration.CATEGORY_GENERAL, "ProjectE Integration", true, "Whether or not to load the ProjectE integration").getBoolean();
		//		tcWarp = cfg.get(Configuration.CATEGORY_GENERAL, "Thaumcraft Warp", true, "Toggles wheter or not to gain additional warp from attacking/being attacked by AbyssalCraft mobs and being inside AbyssalCraft dimensions").getBoolean();
		loadTiCon = cfg.get(Configuration.CATEGORY_GENERAL, "Tinkers' Construct", true, "Whether or not to load the Tinkers' Construct integration.").getBoolean();
		//		loadBQ = cfg.get(Configuration.CATEGORY_GENERAL, "Better Questing", true, "Whether or not to load the Better Questing integration.").getBoolean();
		loadGS = cfg.get(Configuration.CATEGORY_GENERAL, "Game Stages", true, "Whether or not to load the Game Stages integration.").getBoolean();

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
			names = "Enfalas, Saice Shoop, Minecreatr, Kendoshii";
		}

		return names;
	}
}