package com.shinoow.acintegration.integrations.bewitchment;

import com.bewitchment.api.BewitchmentAPI;
import com.bewitchment.api.registry.AltarUpgrade;
import com.bewitchment.common.block.tile.entity.TileEntityPlacedItem;
import com.bewitchment.registry.ModObjects;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.abyssalcraft.common.items.ItemNecronomicon;
import com.shinoow.acintegration.ACIntegration;

import net.minecraftforge.fml.common.Loader;

@ACPlugin
public class ACBW implements IACPlugin {

	@Override
	public String getModName() {

		return "bewitchment";
	}

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("bewitchment") && ACIntegration.loadBW;
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

		BewitchmentAPI.ALTAR_UPGRADES.put(s -> s.getBlockState().getBlock() == ACBlocks.statue || s.getBlockState().getBlock() == ACBlocks.decorative_statue, new AltarUpgrade(AltarUpgrade.Type.PENTACLE, 4, 0));
		BewitchmentAPI.ALTAR_UPGRADES.put(s -> s.getBlockState().getBlock() == ACBlocks.dreadlands_infused_powerstone, new AltarUpgrade(AltarUpgrade.Type.PENTACLE, 1, 0));
		BewitchmentAPI.ALTAR_UPGRADES.put(s -> s.getBlockState().getBlock() == ACBlocks.depths_ghoul_head, new AltarUpgrade(AltarUpgrade.Type.PENTACLE, 1, 0));
		BewitchmentAPI.ALTAR_UPGRADES.put(s -> s.getBlockState().getBlock() == ACBlocks.pete_head, new AltarUpgrade(AltarUpgrade.Type.PENTACLE, 2, 0));
		BewitchmentAPI.ALTAR_UPGRADES.put(s -> s.getBlockState().getBlock() == ACBlocks.mr_wilson_head, new AltarUpgrade(AltarUpgrade.Type.PENTACLE, 3, 0));
		BewitchmentAPI.ALTAR_UPGRADES.put(s -> s.getBlockState().getBlock() == ACBlocks.dr_orange_head, new AltarUpgrade(AltarUpgrade.Type.PENTACLE, 4, 0));
		BewitchmentAPI.ALTAR_UPGRADES.put(s -> s.getBlockState().getBlock() == ACBlocks.crystal_cluster || s.getBlockState().getBlock() == ACBlocks.crystal_cluster2, new AltarUpgrade(AltarUpgrade.Type.PENTACLE, 2, 0));
		BewitchmentAPI.ALTAR_UPGRADES.put(s -> s.getBlockState().getBlock() == ModObjects.placed_item && s.getTileEntity() instanceof TileEntityPlacedItem && ((TileEntityPlacedItem)s.getTileEntity()).getInventories()[0].getStackInSlot(0).getItem() instanceof ItemNecronomicon, new AltarUpgrade(AltarUpgrade.Type.PENTACLE, 4, 0));
	}

	@Override
	public void postInit() {}

}
