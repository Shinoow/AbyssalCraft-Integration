package com.shinoow.acintegration.integrations.projecte;

import java.util.Iterator;
import java.util.List;

import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.acintegration.ACIntegration;

@ACPlugin
public class ACPE implements IACPlugin {

	@Override
	public String getModName() {

		return "ProjectE";
	}

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("ProjectE") && ACIntegration.loadPE;
	}

	@Override
	public void preInit() {}

	@Override
	public void init() {

		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.darkstone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.darkstone_cobblestone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_stone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.coralium_stone), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadstone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssalnite_stone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.omothol_stone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.ethaxium), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.darklands_grass), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_grass), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.monolith_stone), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.shoggoth_ooze), 8);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.shoggoth_biomass), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssalnite_ore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_abyssalnite_ore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreaded_abyssalnite_ore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.liquified_coralium_ore), 4096);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.nitre_ore), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_nitre_ore), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_tin_ore), 256);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_copper_ore), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.coralium_ore), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_coralium_ore), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.pearlescent_coralium_ore), 262);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_iron_ore), 256);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_gold_ore), 2048);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_diamond_ore), 8192);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.transmutator_idle), 95606);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.materializer), 111920);

		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.coralium_brick), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.ethaxium_brick), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.coralium_gem), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.coralium_plagued_flesh), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.coralium_plagued_flesh_on_a_bone), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dread_fragment), 48);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.omothol_flesh), 64);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.eldritch_scale), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.cthulhu_engraved_coin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.elder_engraved_coin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.jzahar_engraved_coin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.hastur_engraved_coin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.azathoth_engraved_coin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.nyarlathotep_engraved_coin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.yog_sothoth_engraved_coin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shub_niggurath_engraved_coin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.coralium_pearl), 262);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shadow_fragment), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssalnite_ingot), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.refined_coralium_ingot), 4096);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreadium_ingot), 5120);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.chunk_of_abyssalnite), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreaded_chunk_of_abyssalnite), 6144);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreaded_shard_of_abyssalnite), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.liquid_coralium), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.liquid_antimatter), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_bone), 48);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.fried_egg), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_beef), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_chicken), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.rotten_anti_flesh), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_plagued_flesh), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_plagued_flesh_on_a_bone), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_spider_eye), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_pork), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shoggoth_flesh, 1, 0), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shoggoth_flesh, 1, 1), 64);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shoggoth_flesh, 1, 2), 96);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shoggoth_flesh, 1, 3), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shoggoth_flesh, 1, 4), 160);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.sulfur), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.nitre), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.tin_ingot), 256);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.copper_ingot), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.life_crystal), 6144);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ACItems.liquid_coralium_bucket_stack, 800);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ACItems.liquid_antimatter_bucket_stack, 800);

		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.oblivion_catalyst), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.essence, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.skin, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssal_wasteland_necronomicon), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreadlands_necronomicon), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.omothol_necronomicon), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssalnomicon), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.cthulhu_charm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.hastur_charm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.jzahar_charm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.azathoth_charm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.nyarlathotep_charm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.yog_sothoth_charm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shub_niggurath_charm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.cthulhu_statue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.hastur_statue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.jzahar_statue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.azathoth_statue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.nyarlathotep_statue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.yog_sothoth_statue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.shub_niggurath_statue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.gateway_key), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreaded_gateway_key), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dread_plagued_gateway_key), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.rlyehian_gateway_key), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_infused_powerstone), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.chagaroth_altar_top), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.chagaroth_altar_bottom), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.eye_of_the_abyss), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.tiered_energy_pedestal, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.tiered_sacrificial_altar, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.tiered_energy_collector, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.overworld_energy_relay), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_wasteland_energy_relay), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_energy_relay), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.omothol_energy_relay), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.tiered_energy_container, 1, OreDictionary.WILDCARD_VALUE), 0);

		for(String name :OreDictionary.getOreNames())
			if(name.startsWith("crystal")){
				List<ItemStack> ores = OreDictionary.getOres(name);
				Iterator iter = ores.iterator();
				while(iter.hasNext())
					ProjectEAPI.getEMCProxy().registerCustomEMC(iter.next(), 0);
			}
	}

	@Override
	public void postInit() {}
}