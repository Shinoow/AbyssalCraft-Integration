package com.shinoow.acintegration.integrations.thaumcraft.cap;

import com.shinoow.abyssalcraft.lib.ACLib;

import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;

public class TaintTimerCapability implements ITaintTimerCapability {

	private int timer;

	public static ITaintTimerCapability getCap(EntityPlayer player) {
		return player.getCapability(TaintTimerCapabilityProvider.TAINT_TIMER, null);
	}

	@Override
	public void incrementTimerIfApplicable(EntityPlayer player) {
		if(player.dimension == ACLib.abyssal_wasteland_id ||
				player.dimension == ACLib.dreadlands_id ||
				player.dimension == ACLib.omothol_id ||
				player.dimension == ACLib.dark_realm_id)
			timer++;
	}

	@Override
	public int getTimer() {
		return timer;
	}

	@Override
	public void setTimer(int timer) {
		this.timer = timer;
	}

	@Override
	public void executeIfApplicable(EntityPlayer player) {
		if(timer >= 2400) {
			timer = player.world.rand.nextInt(300);
			if(player.dimension == ACLib.abyssal_wasteland_id ||
					player.dimension == ACLib.dreadlands_id)
				if(player.world.rand.nextBoolean())
					ThaumcraftApi.internalMethods.addWarpToPlayer(player, 10, EnumWarpType.TEMPORARY);
				else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 10, EnumWarpType.NORMAL);
			if(player.dimension == ACLib.omothol_id ||
					player.dimension == ACLib.dark_realm_id)
				if(player.world.rand.nextBoolean())
					ThaumcraftApi.internalMethods.addWarpToPlayer(player, 15, EnumWarpType.TEMPORARY);
				else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 15, EnumWarpType.NORMAL);
		}
	}

	@Override
	public void copy(ITaintTimerCapability cap) {
		timer = cap.getTimer();
	}
}
