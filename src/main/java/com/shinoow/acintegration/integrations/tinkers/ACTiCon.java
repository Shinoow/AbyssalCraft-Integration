package com.shinoow.acintegration.integrations.tinkers;

import static slimeknights.tconstruct.library.utils.HarvestLevels.IRON;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.GameRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockTinkerFluid;

import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.acintegration.ACIntegration;

@ACPlugin
public class ACTiCon implements IACPlugin {

	public static Fluid fluid_molten_abyssalnite, fluid_molten_coralium, fluid_molten_dreadium;

	public static Block moltenAbyssalnite, moltenCoralium, moltenDreadium;

	public static Material abyssalnite, coralium, dreadium;
	
	public static final AbstractTrait dread_plague = new TraitDreadPlague();
	public static final AbstractTrait coralium_plague = new TraitCoraliumPlague();
	public static final AbstractTrait dread_purity = new TraitDreadPurity();

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
		fluid_molten_abyssalnite = new FluidMolten("moltenAbyssalnite", 0x4a1c89);
		fluid_molten_abyssalnite.setTemperature(700);
		FluidRegistry.registerFluid(fluid_molten_abyssalnite);
		FluidRegistry.addBucketForFluid(fluid_molten_abyssalnite);
		fluid_molten_coralium = new FluidMolten("moltenRefinedCoralium", 0x169265);
		fluid_molten_coralium.setTemperature(800);
		FluidRegistry.registerFluid(fluid_molten_coralium);
		FluidRegistry.addBucketForFluid(fluid_molten_coralium);
		fluid_molten_dreadium = new FluidMolten("moltenDreadium", 0x880101);
		fluid_molten_dreadium.setTemperature(900);
		FluidRegistry.registerFluid(fluid_molten_dreadium);
		FluidRegistry.addBucketForFluid(fluid_molten_dreadium);

		moltenAbyssalnite = new BlockTinkerFluid(fluid_molten_abyssalnite, net.minecraft.block.material.Material.lava);
		moltenCoralium = new BlockTinkerFluid(fluid_molten_coralium, net.minecraft.block.material.Material.lava);
		moltenDreadium = new BlockTinkerFluid(fluid_molten_dreadium, net.minecraft.block.material.Material.lava);

		GameRegistry.registerBlock(moltenAbyssalnite, "moltenabyssalnite");
		GameRegistry.registerBlock(moltenCoralium, "moltencoralium");
		GameRegistry.registerBlock(moltenDreadium, "moltendreadium");

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

		if(FMLCommonHandler.instance().getSide().isClient()){
			registerFluidModel(moltenAbyssalnite, "aby");
			registerFluidModel(moltenCoralium, "cor");
			registerFluidModel(moltenDreadium, "dre");
		}
	}

	@Override
	public void init() {
		abyssalnite = new Material("abyssalnite", 0x4a1c89);
		abyssalnite.setFluid(fluid_molten_abyssalnite);
		abyssalnite.addItem("ingotAbyssalnite", 1, Material.VALUE_Ingot);
		abyssalnite.setCraftable(true);
		abyssalnite.setRepresentativeItem(new ItemStack(ACItems.abyssalnite_ingot));
		abyssalnite.addTrait(dread_purity);

		coralium = new Material("refined_coralium", 0x169265);
		coralium.setFluid(fluid_molten_coralium);
		coralium.addItem("ingotLiquifiedCoralium", 1, Material.VALUE_Ingot);
		coralium.setCraftable(true);
		coralium.setRepresentativeItem(new ItemStack(ACItems.refined_coralium_ingot));
		coralium.addTrait(coralium_plague);

		dreadium = new Material("dreadium", 0x880101);
		dreadium.setFluid(fluid_molten_dreadium);
		dreadium.addItem("ingotDreadium", 1, Material.VALUE_Ingot);
		dreadium.setCraftable(true);
		dreadium.setRepresentativeItem(new ItemStack(ACItems.dreadium_ingot));
		dreadium.addTrait(dread_plague);

		TinkerRegistry.addMaterial(abyssalnite);
		TinkerRegistry.addMaterial(coralium);
		TinkerRegistry.addMaterial(dreadium);

		TinkerRegistry.addMaterialStats(abyssalnite,
				new HeadMaterialStats(630, 10.00f, 6.00f, 4),
				new HandleMaterialStats(0.90f, 60),
                new ExtraMaterialStats(100));
		TinkerRegistry.addMaterialStats(coralium,
				new HeadMaterialStats(900, 12.00f, 7.00f, 5),
				new HandleMaterialStats(0.90f, 60),
                new ExtraMaterialStats(100));
		TinkerRegistry.addMaterialStats(dreadium,
				new HeadMaterialStats(1150, 14.00f, 8.00f, 6),
				new HandleMaterialStats(0.90f, 60),
                new ExtraMaterialStats(100));
		
		TinkerSmeltery.registerToolpartMeltingCasting(abyssalnite);
		TinkerSmeltery.registerToolpartMeltingCasting(coralium);
		TinkerSmeltery.registerToolpartMeltingCasting(dreadium);
	}

	@Override
	public void postInit() {

	}

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