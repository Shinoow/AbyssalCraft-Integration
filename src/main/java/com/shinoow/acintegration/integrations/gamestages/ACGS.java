package com.shinoow.acintegration.integrations.gamestages;

import java.util.Map;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.google.common.collect.Maps;
import com.shinoow.abyssalcraft.api.event.ACEvents.RitualEvent;
import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.acintegration.ACIntegration;

@ACPlugin
public class ACGS implements IACPlugin {

	public static final Map<String, String> RITUAL_MAP = Maps.newHashMap();

	@Override
	public String getModName() {

		return "Game Stages";
	}

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("gamestages") && ACIntegration.loadGS;
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void postInit() {

	}

	@SubscribeEvent
	public void onRitual(RitualEvent.Pre event){

		String requiredStage = RITUAL_MAP.get(event.getRitual().getUnlocalizedName().substring(10));

		if(requiredStage != null && !requiredStage.isEmpty())
			if(!GameStageHelper.getPlayerData(event.getEntityPlayer()).hasStage(requiredStage)){
				event.setCanceled(true);
				if(event.getWorld().isRemote)
					event.getEntityPlayer().sendMessage(new TextComponentString("You cannot perform this ritual yet."));
			}
	}
}
