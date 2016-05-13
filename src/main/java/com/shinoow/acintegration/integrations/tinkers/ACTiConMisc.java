package com.shinoow.acintegration.integrations.tinkers;

import com.shinoow.abyssalcraft.api.item.ACItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
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
import static com.shinoow.acintegration.integrations.tinkers.ACTiCon.*;

/**
 * This class exists to move fields from the main plugin class that can trigger a NoClassDefFoundError when the<br>
 * IntegrationHandler scans through the plugin. You don't need do to this if the plugin is directly present in<br>
 * your mod, as the classes for your stuff will be present.
 */
public class ACTiConMisc {

	public static Material abyssalnite, coralium, dreadium;
	
	public static final AbstractTrait dread_plague = new TraitDreadPlague();
	public static final AbstractTrait coralium_plague = new TraitCoraliumPlague();
	public static final AbstractTrait dread_purity = new TraitDreadPurity();
	
	public static void initFluids(){
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

		registerBlock(moltenAbyssalnite, "moltenabyssalnite");
		registerBlock(moltenCoralium, "moltencoralium");
		registerBlock(moltenDreadium, "moltendreadium");
	}
	
	public static void initMaterials(){
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

	private static void registerItem(Item item, String name){
		GameRegistry.register(item.setRegistryName(new ResourceLocation("acintegration", name)));
	}

	private static void registerBlock(Block block, String name){
		registerBlock(block, new ItemBlock(block), name);
	}

	private static void registerBlock(Block block, ItemBlock item, String name){
		GameRegistry.register(block.setRegistryName(new ResourceLocation("acintegration", name)));
		registerItem(item, name);
	}
}