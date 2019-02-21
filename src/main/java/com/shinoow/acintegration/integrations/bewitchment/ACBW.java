package com.shinoow.acintegration.integrations.bewitchment;

import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.acintegration.ACIntegration;

import net.minecraftforge.common.MinecraftForge;
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
		MinecraftForge.EVENT_BUS.register(new ACBWEvents());
	}

	@Override
	public void postInit() {}

}
