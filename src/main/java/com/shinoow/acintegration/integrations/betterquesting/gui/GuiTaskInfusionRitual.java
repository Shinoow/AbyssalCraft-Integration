package com.shinoow.acintegration.integrations.betterquesting.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import betterquesting.client.gui.GuiQuesting;
import betterquesting.client.gui.misc.GuiEmbedded;
import betterquesting.client.themes.ThemeRegistry;
import betterquesting.quests.QuestInstance;
import betterquesting.utils.BigItemStack;
import betterquesting.utils.RenderUtils;

import com.shinoow.acintegration.integrations.betterquesting.tasks.TaskInfusionRitual;

public class GuiTaskInfusionRitual extends GuiEmbedded {

	TaskInfusionRitual task;
	QuestInstance quest;

	public GuiTaskInfusionRitual(QuestInstance quest, TaskInfusionRitual task, GuiQuesting screen, int posX, int posY,
			int sizeX, int sizeY) {
		super(screen, posX, posY, sizeX, sizeY);
		this.quest = quest;
		this.task = task;
	}

	@Override
	public void drawGui(int mx, int my, float partialTick) {
		BigItemStack dispStack = task.output.copy();
		BigItemStack offerStack = task.offering.copy();
		int progress = quest == null || !quest.globalQuest? task.GetUserProgress(screen.mc.thePlayer.getUniqueID()) : task.GetGlobalProgress();

		if(dispStack.getBaseStack() != null && offerStack.getBaseStack() != null){
			screen.mc.renderEngine.bindTexture(ThemeRegistry.curTheme().guiTexture());
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			screen.drawTexturedModalRect(posX + sizeX/2 - 9 + 20, posY + sizeY/2 - 18, 0, 48, 18, 18);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			RenderUtils.RenderItemStack(screen.mc, dispStack.getBaseStack(), posX + sizeX/2 - 8 + 20, posY + sizeY/2 - 17, "");

			screen.mc.renderEngine.bindTexture(ThemeRegistry.curTheme().guiTexture());
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			screen.drawTexturedModalRect(posX + sizeX/2 - 9 + 20, posY + sizeY/2 - 18 - 35, 0, 48, 18, 18);
			GL11.glEnable(GL11.GL_DEPTH_TEST);

			ItemStack temp = offerStack.getBaseStack();

			if(offerStack.oreDict != null)
			{
				List<ItemStack> ores = OreDictionary.getOres(offerStack.oreDict);

				if(ores != null && ores.size() > 0)
				{
					temp = ores.get((int)(Minecraft.getSystemTime()/16000)%ores.size()).copy();
					temp.setItemDamage((int)(Minecraft.getSystemTime()/1000)%16);
					temp.setTagCompound(offerStack.GetTagCompound());
				}
			}

			RenderUtils.RenderItemStack(screen.mc, temp, posX + sizeX/2 - 8 + 20, posY + sizeY/2 - 17 - 35, "");

			String txt = progress + "/" + dispStack.stackSize;
			screen.mc.fontRenderer.drawString(txt, posX + sizeX/2 - screen.mc.fontRenderer.getStringWidth(txt)/2 + 20, posY + sizeY/2 + 2, ThemeRegistry.curTheme().textColor().getRGB());
			screen.mc.fontRenderer.drawString("Infuse the following through a Infusion Ritual:", posX + sizeX/2 - screen.mc.fontRenderer.getStringWidth("Infuse the following through a Infusion Ritual:")/2 + 20, posY + sizeY/2 - 65, ThemeRegistry.curTheme().textColor().getRGB());
			screen.mc.fontRenderer.drawString("To create the following:", posX + sizeX/2 - screen.mc.fontRenderer.getStringWidth("To create the following:")/2 + 20, posY + sizeY/2 - 30, ThemeRegistry.curTheme().textColor().getRGB());

			if(mx >= posX + sizeX/2 - 8 + 20 && mx < posX + sizeX/2 + 8 + 20 && my >= posY + sizeY/2 - 17 && my < posY + sizeY/2 - 1)
				screen.DrawTooltip(dispStack.getBaseStack().getTooltip(screen.mc.thePlayer, screen.mc.gameSettings.advancedItemTooltips), mx, my);

			if(mx >= posX + sizeX/2 - 8 + 20 && mx < posX + sizeX/2 + 8 + 20 && my >= posY + sizeY/2 - 17 - 35 && my < posY + sizeY/2 - 1 - 35)
				screen.DrawTooltip(offerStack.getBaseStack().getTooltip(screen.mc.thePlayer, screen.mc.gameSettings.advancedItemTooltips), mx, my);
		}
	}
}