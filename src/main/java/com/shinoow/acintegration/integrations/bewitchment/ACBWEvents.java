package com.shinoow.acintegration.integrations.bewitchment;

import com.bewitchment.api.event.AltarModifierCollectionEvent;
import com.bewitchment.common.block.ModBlocks;
import com.bewitchment.common.tile.tiles.TileEntityPlacedItem;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.common.items.ItemNecronomicon;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ACBWEvents {

	@SubscribeEvent
	public static void setModifiers(AltarModifierCollectionEvent evt) {
		Block b = evt.getState().getBlock();
		if(b == ACBlocks.statue || b == ACBlocks.decorative_statue) {
			evt.multiplier = 1;
			return;
		}
		if(b == ACBlocks.dreadlands_infused_powerstone) {
			evt.multiplier = 0.5;
			return;
		}
		if(b == ACBlocks.depths_ghoul_head) {
			evt.multiplier = 0.05;
			return;
		}
		if(b == ACBlocks.pete_head) {
			evt.multiplier = 0.1;
			return;
		}
		if(b == ACBlocks.mr_wilson_head) {
			evt.multiplier = 0.15;
			return;
		}
		if(b == ACBlocks.dr_orange_head) {
			evt.multiplier = 0.2;
			return;
		}
		if(b == ACBlocks.crystal_cluster || b == ACBlocks.crystal_cluster2) {
			evt.multiplier = 0.1;
			return;
		}
		if(b == ModBlocks.placed_item && ((TileEntityPlacedItem) evt.getWorld().getTileEntity(evt.getPos())).getItem().getItem() instanceof ItemNecronomicon) {
			evt.multiplier = 1;
			return;
		}
	}
}
