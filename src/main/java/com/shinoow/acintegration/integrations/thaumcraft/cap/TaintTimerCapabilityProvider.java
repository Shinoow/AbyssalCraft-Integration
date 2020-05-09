package com.shinoow.acintegration.integrations.thaumcraft.cap;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class TaintTimerCapabilityProvider implements ICapabilityProvider, INBTSerializable<NBTBase> {

	@CapabilityInject(ITaintTimerCapability.class)
	public static final Capability<ITaintTimerCapability> TAINT_TIMER = null;

	private ITaintTimerCapability capability;

	public TaintTimerCapabilityProvider() {
		capability = new TaintTimerCapability();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		return capability == TAINT_TIMER;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

		if (capability == TAINT_TIMER)
			return (T) this.capability;

		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return TaintTimerCapabilityStorage.instance.writeNBT(TAINT_TIMER, capability, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		TaintTimerCapabilityStorage.instance.readNBT(TAINT_TIMER, capability, null, nbt);
	}
}