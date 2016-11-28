package com.shinoow.acintegration.integrations.betterquesting.gui;

import org.lwjgl.opengl.GL11;

import betterquesting.client.gui.GuiQuesting;
import betterquesting.client.gui.misc.GuiEmbedded;
import betterquesting.client.themes.ThemeRegistry;
import betterquesting.quests.QuestInstance;
import betterquesting.utils.BigItemStack;
import betterquesting.utils.RenderUtils;

import com.shinoow.acintegration.integrations.betterquesting.tasks.TaskCreationRitual;

public class GuiTaskCreationRitual extends GuiEmbedded {

	TaskCreationRitual task;
	QuestInstance quest;

	public GuiTaskCreationRitual(QuestInstance quest, TaskCreationRitual task, GuiQuesting screen, int posX, int posY,
			int sizeX, int sizeY) {
		super(screen, posX, posY, sizeX, sizeY);
		this.quest = quest;
		this.task = task;
	}

	@Override
	public void drawGui(int mx, int my, float partialTick) {
		BigItemStack dispStack = task.output.copy();
		int progress = quest == null || !quest.globalQuest? task.GetUserProgress(screen.mc.thePlayer.getUniqueID()) : task.GetGlobalProgress();

		if(dispStack.getBaseStack() != null){
			screen.mc.renderEngine.bindTexture(ThemeRegistry.curTheme().guiTexture());
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			screen.drawTexturedModalRect(posX + sizeX/2 - 9 + 20, posY + sizeY/2 - 18, 0, 48, 18, 18);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			RenderUtils.RenderItemStack(screen.mc, dispStack.getBaseStack(), posX + sizeX/2 - 8 + 20, posY + sizeY/2 - 17, "");
			String txt = progress + "/" + dispStack.stackSize;
			screen.mc.fontRenderer.drawString(txt, posX + sizeX/2 - screen.mc.fontRenderer.getStringWidth(txt)/2 + 20, posY + sizeY/2 + 2, ThemeRegistry.curTheme().textColor().getRGB());

			screen.mc.fontRenderer.drawString("Create the following through a Creation Ritual:", posX + sizeX/2 - screen.mc.fontRenderer.getStringWidth("Creation the following through a Creation Ritual:")/2 + 20, posY + sizeY/2 - 30, ThemeRegistry.curTheme().textColor().getRGB());

			if(mx >= posX + sizeX/2 - 8 + 20 && mx < posX + sizeX/2 + 8 + 20 && my >= posY + sizeY/2 - 17 && my < posY + sizeY/2 - 1)
				screen.DrawTooltip(dispStack.getBaseStack().getTooltip(screen.mc.thePlayer, screen.mc.gameSettings.advancedItemTooltips), mx, my);
		}
	}
}