package com.shinoow.acintegration.integrations.tinkers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
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

		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			registerFluidModel(moltenAbyssalnite, "aby");
			registerFluidModel(moltenCoralium, "cor");
			registerFluidModel(moltenDreadium, "dre");
		}
	}

	@Override
	public void init() {
		ACTiConMisc.initMaterials();
	}

	@Override
	public void postInit() {

	}

	@SideOnly(Side.CLIENT)
	private void registerFluidModel(Block fluidBlock, String name) {
		Item item = Item.getItemFromBlock(fluidBlock);

		ModelBakery.registerItemVariants(item);

		final ModelResourceLocation modelResourceLocation = new ModelResourceLocation("acintegration:fluid", name);

		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition(){

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {

				return modelResourceLocation;
			}

		});

		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
				return modelResourceLocation;
			}
		});
	}
}