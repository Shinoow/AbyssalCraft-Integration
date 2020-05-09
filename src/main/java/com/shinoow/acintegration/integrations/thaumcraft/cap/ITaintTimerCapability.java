package com.shinoow.acintegration.integrations.thaumcraft.cap;

import net.minecraft.entity.player.EntityPlayer;

public interface ITaintTimerCapability {

	public void incrementTimerIfApplicable(EntityPlayer player);

	public int getTimer();

	public void setTimer(int timer);

	public void executeIfApplicable(EntityPlayer player);

	public void copy(ITaintTimerCapability cap);
}
