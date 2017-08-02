package com.shinoow.acintegration.integrations.tinkers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.acintegration.ACIntegration;

@ACPlugin
public class ACTiCon implements IACPlugin {

	public static Fluid fluid_molten_abyssalnite, fluid_molten_coralium, fluid_molten_dreadium;

	public static Block moltenAbyssalnite, moltenCoralium, moltenDreadium;

	@Override
	public String getModName() {

		return "Tinkers' Construct";
	}

	public static ACTiCon instance = new ACTiCon();

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("tconstruct") && ACIntegration.loadTiCon;
	}

	@Override
	public void preInit() {

		ACTiConMisc.initFluids();

		//IMC registration
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("fluid", fluid_molten_abyssalnite.getName());
		tag.setString("ore", "Abyssalnite");
		tag.setBoolean("toolforge", true);

		FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);

		tag = new NBTTagCompound();
		tag.setString("fluid", fluid_molten_coralium.getName());
		tag.setString("ore", "LiquifiedCoralium");
		tag.setBoolean("toolforge", true);

		FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);

		tag = new NBTTagCompound();
		tag.setString("fluid", fluid_molten_dreadium.getName());
		tag.setString("ore", "Dreadium");
		tag.setBoolean("toolforge", true);

		FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void init() {
		ACTiConMisc.initMaterials();
	}

	@Override
	public void postInit() {

	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event){
		event.getRegistry().register(moltenAbyssalnite.setRegistryName(new ResourceLocation("acintegration", "moltenabyssalnite")));
		event.getRegistry().register(moltenCoralium.setRegistryName(new ResourceLocation("acintegration", "moltencoralium")));
		event.getRegistry().register(moltenDreadium.setRegistryName(new ResourceLocation("acintegration", "moltendreadium")));
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event){
		event.getRegistry().register(new ItemBlock(moltenAbyssalnite).setRegistryName(new ResourceLocation("acintegration", "moltenabyssalnite")));
		event.getRegistry().register(new ItemBlock(moltenCoralium).setRegistryName(new ResourceLocation("acintegration", "moltencoralium")));
		event.getRegistry().register(new ItemBlock(moltenDreadium).setRegistryName(new ResourceLocation("acintegration", "moltendreadium")));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event){
		registerFluidModel(moltenAbyssalnite, "aby");
		registerFluidModel(moltenCoralium, "cor");
		registerFluidModel(moltenDreadium, "dre");
	}

	@SideOnly(Side.CLIENT)
	private void registerFluidModel(Block fluidBlock, String name) {
		Item item = Item.getItemFromBlock(fluidBlock);

		ModelBakery.registerItemVariants(item);

		final ModelResourceLocation modelResourceLocation = new ModelResourceLocation("acintegration:fluid", name);

		ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
				return modelResourceLocation;
			}
		});
	}
}