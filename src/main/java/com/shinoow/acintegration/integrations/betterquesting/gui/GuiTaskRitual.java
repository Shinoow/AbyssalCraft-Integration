package com.shinoow.acintegration.integrations.betterquesting.gui;

import net.minecraft.util.StatCollector;
import betterquesting.client.gui.GuiQuesting;
import betterquesting.client.gui.misc.GuiEmbedded;
import betterquesting.client.themes.ThemeRegistry;

import com.shinoow.acintegration.integrations.betterquesting.tasks.TaskRitual;

public class GuiTaskRitual extends GuiEmbedded {

	TaskRitual task;
	
	public GuiTaskRitual(TaskRitual task, GuiQuesting screen, int posX, int posY, int sizeX,
			int sizeY) {
		super(screen, posX, posY, sizeX, sizeY);
		this.task = task;
	}

	@Override
	public void drawGui(int mx, int my, float partialTick) {

		screen.mc.fontRenderer.drawString("Ritual to perform:", posX + sizeX/2 - screen.mc.fontRenderer.getStringWidth("Ritual to Perform:")/2 - 10, (posY + sizeY/2 - 17) + 2, ThemeRegistry.curTheme().textColor().getRGB());
		screen.mc.fontRenderer.drawString(StatCollector.translateToLocal("ac.ritual." + task.name), posX + sizeX/2 - screen.mc.fontRenderer.getStringWidth(StatCollector.translateToLocal("ac.ritual." + task.name))/2 - 10, (posY + sizeY/2 - 17) + 10, ThemeRegistry.curTheme().textColor().getRGB());
	}
}