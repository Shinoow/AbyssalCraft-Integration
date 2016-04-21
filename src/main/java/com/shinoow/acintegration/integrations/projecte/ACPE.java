package com.shinoow.acintegration.integrations.projecte;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import moze_intel.projecte.api.ProjectEAPI;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
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

		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Darkstone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Darkstone_cobble), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.abystone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.cstone), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadstone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.abydreadstone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.omotholstone), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.ethaxium), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Darkgrass), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadgrass), 1);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.monolithStone), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shoggothBlock), 8);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shoggothBiomass), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.abyore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.abydreadore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadore), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyLCorOre), 4096);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.nitreOre), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyNitOre), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyTinOre), 256);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyCopOre), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Coraliumore), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyCorOre), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyPCorOre), 262);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyIroOre), 256);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyGolOre), 2048);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.AbyDiaOre), 8192);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.transmutator), 95606);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.materializer), 111920);

		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.cbrick), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.ethaxium_brick), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Coralium), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Corflesh), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Corbone), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadfragment), 48);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.omotholFlesh), 64);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.eldritchScale), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.cthulhuCoin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.elderCoin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.jzaharCoin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.hasturCoin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.azathothCoin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.nyarlathotepCoin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.yogsothothCoin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shubniggurathCoin), 1028);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Cpearl), 262);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shadowfragment), 16);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.abyingot), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Cingot), 4096);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadiumingot), 5120);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.abychunk), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadchunk), 6144);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Dreadshard), 3072);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Cwater), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.anticwater), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antiBone), 48);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.friedegg), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antiBeef), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antiChicken), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antiFlesh), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antiCorflesh), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antiCorbone), 80);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antiSpider_eye), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antiPork), 24);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 0), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 1), 64);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 2), 96);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 3), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shoggothFlesh, 1, 4), 160);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.sulfur), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.nitre), 32);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.tinIngot), 256);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.copperIngot), 128);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.lifeCrystal), 6144);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.Cbucket), 800);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.antibucket), 800);

		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.OC), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.essence, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.skin, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.necronomicon_cor), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.necronomicon_dre), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.necronomicon_omt), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.abyssalnomicon), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.cthulhuCharm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.hasturCharm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.jzaharCharm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.azathothCharm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.nyarlathotepCharm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.yogsothothCharm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shubniggurathCharm, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.cthulhuStatue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.hasturStatue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.jzaharStatue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.azathothStatue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.nyarlathotepStatue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.yogsothothStatue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.shubniggurathStatue), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.gatewayKey), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.gatewayKeyDL), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadKey), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.gatewayKeyJzh), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.PSDL), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadaltartop), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.dreadaltarbottom), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.EoA), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.tieredEnergyPedestal, 1, OreDictionary.WILDCARD_VALUE), 0);
		ProjectEAPI.getEMCProxy().registerCustomEMC(new ItemStack(AbyssalCraft.tieredSacrificialAltar, 1, OreDictionary.WILDCARD_VALUE), 0);

		for(String name :OreDictionary.getOreNames()){
			if(name.startsWith("crystal")){
				List<ItemStack> ores = OreDictionary.getOres(name);
				Iterator iter = ores.iterator();
				while(iter.hasNext())
					ProjectEAPI.getEMCProxy().registerCustomEMC(iter.next(), 0);
			}
		}
	}

	@Override
	public void postInit() {}
}