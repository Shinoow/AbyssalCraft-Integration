package com.shinoow.acintegration.client.config;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.acintegration.ACIntegration;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ACIConfigGUI extends GuiConfig {

	public ACIConfigGUI(GuiScreen parent) {
		super(parent, getConfigElements(), "acintegration", true, true, "AbyssalCraft Integration");
	}

	private static List<IConfigElement> getConfigElements(){
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.add(new DummyCategoryElement(I18n.translateToLocal("aci_general"), "aci_general", GeneralEntry.class));
		return list;
	}

	public static class GeneralEntry extends CategoryEntry{

		public GeneralEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
			super(owningScreen, owningEntryList, configElement);
		}

		@Override
		protected GuiScreen buildChildScreen(){
			return new GuiConfig(owningScreen, new ConfigElement(ACIntegration.cfg.getCategory("general")).getChildElements(), "acintegration", "general", true, true, I18n.translateToLocal("aci_general"));

		}
	}
}