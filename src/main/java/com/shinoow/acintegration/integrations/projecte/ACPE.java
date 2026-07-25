package com.shinoow.acintegration.integrations.projecte;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.acintegration.ACIntegration;

import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

@ACPlugin
public class ACPE implements IACPlugin {

	@Override
	public String getModName() {

		return "ProjectE";
	}

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("projecte") && ACIntegration.loadPE;
	}

	@Override
	public void init() {

		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.darkstone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_stone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.coralium_stone), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadstone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.elysian_stone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.omothol_stone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.ethaxium), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_grass), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.monolith_stone), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.darkstone_cobblestone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_cobblestone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.coralium_cobblestone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadstone_cobblestone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.elysian_cobblestone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.shoggoth_ooze), 8);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.shoggoth_biomass), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssalnite_ore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_abyssalnite_ore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreaded_abyssalnite_ore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.liquified_coralium_ore), 4096);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.nitre_ore), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_nitre_ore), 32);
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
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.ghoul_flesh), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssal_ghoul_flesh), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreaded_ghoul_flesh), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.omothol_ghoul_flesh), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shadow_ghoul_flesh), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dread_fragment), 48);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.omothol_ghoul_flesh), 64);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.eldritch_scale), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.token_of_jzahar), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.coralium_pearl), 262);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shadow_fragment), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssalnite_ingot), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.refined_coralium_ingot), 4096);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreadium_ingot), 5120);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreaded_shard_of_abyssalnite), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.liquid_coralium), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.liquid_antimatter), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_bone), 48);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_beef), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_chicken), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.rotten_anti_flesh), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_plagued_flesh), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_ghoul_flesh), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_spider_eye), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.anti_pork), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.overworld_shoggoth_flesh), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssal_shoggoth_flesh), 64);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreaded_shoggoth_flesh), 96);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.omothol_shoggoth_flesh), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shadow_shoggoth_flesh), 160);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.sulfur), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.nitre), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.life_crystal), 6144);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ACItems.liquid_coralium_bucket_stack, 800);
		ProjectEAPI.getEMCProxy().registerCustomEMC(ACItems.liquid_antimatter_bucket_stack, 800);

		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.oblivion_catalyst), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssal_wasteland_essence), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreadlands_essence), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.omothol_essence), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.skin_of_the_abyssal_wasteland), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.skin_of_the_dreadlands), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.skin_of_omothol), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssal_wasteland_necronomicon), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreadlands_necronomicon), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.omothol_necronomicon), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.abyssalnomicon), 0);
		
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.cthulhu_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.range_cthulhu_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.duration_cthulhu_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.power_cthulhu_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.hastur_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.range_hastur_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.duration_hastur_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.power_hastur_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.jzahar_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.range_jzahar_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.duration_jzahar_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.power_jzahar_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.azathoth_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.range_azathoth_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.duration_azathoth_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.power_azathoth_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.nyarlathotep_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.range_nyarlathotep_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.duration_nyarlathotep_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.power_nyarlathotep_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.yog_sothoth_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.range_yog_sothoth_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.duration_yog_sothoth_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.power_yog_sothoth_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.shub_niggurath_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.range_shub_niggurath_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.duration_shub_niggurath_charm), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.power_shub_niggurath_charm), 0);
		
		for(ItemStack statue : APIUtils.getAllStatues())
			ProjectEAPI.getEMCProxy().registerCustomEMC(statue, 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.gateway_key), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dreadlands_infused_gateway_key), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.dread_plagued_gateway_key), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.omothol_forged_gateway_key), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_infused_powerstone), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.sealing_lock), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.unlocked_sealing_lock), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACItems.eye_of_the_abyss), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.overworld_energy_pedestal), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_wasteland_energy_pedestal), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_energy_pedestal), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.omothol_energy_pedestal), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.overworld_sacrificial_altar), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_wasteland_sacrificial_altar), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_sacrificial_altar), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.omothol_sacrificial_altar), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.overworld_energy_collector), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_wasteland_energy_collector), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_energy_collector), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.omothol_energy_collector), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.overworld_energy_relay), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_wasteland_energy_relay), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_energy_relay), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.omothol_energy_relay), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.overworld_energy_container), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.abyssal_wasteland_energy_container), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.dreadlands_energy_container), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(ACBlocks.omothol_energy_container), 0);

		List<String> names = Arrays.asList(OreDictionary.getOreNames())
				.stream()
				.filter(x -> x.startsWith("crystal"))
				.collect(Collectors.toList());
		for(String name : names){
			List<ItemStack> ores = OreDictionary.getOres(name);
			for(ItemStack ore : ores)
				ProjectEAPI.getEMCProxy().registerCustomEMC(ore, 0);
		}
	}

	@Override
	public void postInit() {}
}