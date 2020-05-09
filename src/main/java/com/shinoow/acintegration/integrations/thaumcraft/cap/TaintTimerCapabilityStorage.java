package com.shinoow.acintegration.integrations.thaumcraft.cap;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class TaintTimerCapabilityStorage implements IStorage<ITaintTimerCapability> {

	public static IStorage<ITaintTimerCapability> instance = new TaintTimerCapabilityStorage();

	@Override
	public NBTBase writeNBT(Capability<ITaintTimerCapability> capability, ITaintTimerCapability instance, EnumFacing side) {
		NBTTagCompound properties = new NBTTagCompound();

		properties.setInteger("TaintTimer", instance.getTimer());

		return properties;
	}

	@Override
	public void readNBT(Capability<ITaintTimerCapability> capability, ITaintTimerCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound properties = (NBTTagCompound)nbt;

		instance.setTimer(properties.getInteger("TaintTimer"));
	}
}