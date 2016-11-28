package com.shinoow.acintegration.client.config;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.acintegration.ACIntegration;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigElement;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;

public class ACIConfigGUI extends GuiConfig {

	public ACIConfigGUI(GuiScreen parent) {
		super(parent, getConfigElements(), "acintegration", true, true, "AbyssalCraft Integration");
	}

	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(new DummyCategoryElement<Object>(StatCollector.translateToLocal("aci_general"), "aci_general", GeneralEntry.class));
		return list;
	}

	public static class GeneralEntry extends CategoryEntry{

		public GeneralEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement<?> configElement) {
			super(owningScreen, owningEntryList, configElement);
		}

		@Override
		protected GuiScreen buildChildScreen(){
			return new GuiConfig(owningScreen, new ConfigElement<Object>(ACIntegration.cfg.getCategory("general")).getChildElements(), "acintegration", "general", true, true, StatCollector.translateToLocal("aci_general"));

		}
	}
}