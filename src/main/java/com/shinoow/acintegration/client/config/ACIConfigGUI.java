package com.shinoow.acintegration.client.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

import com.shinoow.acintegration.ACIntegration;

public class ACIConfigGUI extends GuiConfig {

	public ACIConfigGUI(GuiScreen parent) {
		super(parent, getConfigElements(), "acintegration", true, true, "AbyssalCraft Integration");
	}

	@SuppressWarnings("rawtypes")
	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(new DummyCategoryElement(StatCollector.translateToLocal("aci_general"), "aci_general", GeneralEntry.class));
		return list;
	}

	public static class GeneralEntry extends CategoryEntry{

		public GeneralEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}

		@Override
		protected GuiScreen buildChildScreen(){
			return new GuiConfig(owningScreen, new ConfigElement(ACIntegration.cfg.getCategory("general")).getChildElements(), "acintegration", "general", true, true, StatCollector.translateToLocal("aci_general"));

		}
	}
}