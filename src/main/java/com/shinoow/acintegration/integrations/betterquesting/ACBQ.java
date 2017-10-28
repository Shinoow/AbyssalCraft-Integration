package com.shinoow.acintegration.integrations.betterquesting;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import betterquesting.quests.tasks.TaskRegistry;

import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.acintegration.ACIntegration;
import com.shinoow.acintegration.integrations.betterquesting.tasks.*;

@ACPlugin
public class ACBQ implements IACPlugin {

	@Override
	public String getModName() {

		return "Better Questing";
	}

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("betterquesting") && ACIntegration.loadBQ;
	}

	@Override
	public void preInit() {}

	@Override
	public void init() {
		MinecraftForge.EVENT_BUS.register(new ACBQEvents());
		TaskRegistry.RegisterTask(TaskTransmute.class, new ResourceLocation("acintegration", "transmute"));
		TaskRegistry.RegisterTask(TaskCrystallize.class, new ResourceLocation("acintegration", "crystallize"));
		TaskRegistry.RegisterTask(TaskRitual.class, new ResourceLocation("acintegration", "ritual"));
		TaskRegistry.RegisterTask(TaskCreationRitual.class, new ResourceLocation("acintegration", "creationritual"));
		TaskRegistry.RegisterTask(TaskInfusionRitual.class, new ResourceLocation("acintegration", "infusionritual"));
	}

	@Override
	public void postInit() {}
}