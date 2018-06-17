package com.shinoow.acintegration.integrations.betterquesting.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import betterquesting.client.gui.GuiQuesting;
import betterquesting.client.gui.misc.GuiEmbedded;
import betterquesting.client.themes.ThemeRegistry;
import betterquesting.quests.QuestInstance;
import betterquesting.utils.BigItemStack;
import betterquesting.utils.RenderUtils;

import com.shinoow.acintegration.integrations.betterquesting.tasks.TaskACMachine;

public class GuiTaskACMachine extends GuiEmbedded {

	TaskACMachine task;
	QuestInstance quest;

	public GuiTaskACMachine(QuestInstance quest, TaskACMachine task, GuiQuesting screen, int posX, int posY, int sizeX,
			int sizeY) {
		super(screen, posX, posY, sizeX, sizeY);
		this.quest = quest;
		this.task = task;
	}

	@Override
	public void drawGui(int mx, int my, float partialTick) {

		BigItemStack dispStack = task.output.copy();
		int progress = quest == null || !quest.globalQuest? task.GetUserProgress(screen.mc.player.getUniqueID()) : task.GetGlobalProgress();

		if(dispStack.getBaseStack() != null){


			screen.mc.renderEngine.bindTexture(ThemeRegistry.curTheme().guiTexture());
			GlStateManager.disableDepth();
			screen.drawTexturedModalRect(posX + sizeX/2 - 9 + 20, posY + sizeY/2 - 18, 0, 48, 18, 18);
			GlStateManager.enableDepth();
			RenderUtils.RenderItemStack(screen.mc, dispStack.getBaseStack(), posX + sizeX/2 - 8 + 20, posY + sizeY/2 - 17, "");

			if(!task.inputs.isEmpty()){
				if(task.inputs.size() == 1){

					screen.mc.renderEngine.bindTexture(ThemeRegistry.curTheme().guiTexture());
					GlStateManager.disableDepth();
					screen.drawTexturedModalRect(posX + sizeX/2 - 9 - 40, posY + sizeY/2 - 18, 0, 48, 18, 18);

					BigItemStack stack = task.inputs.get(0).copy();

					ItemStack temp = stack.getBaseStack();

					if(stack.oreDict != null)
					{
						List<ItemStack> ores = OreDictionary.getOres(stack.oreDict);

						if(ores != null && ores.size() > 0)
						{
							temp = ores.get((int)(Minecraft.getSystemTime()/16000)%ores.size()).copy();
							temp.setItemDamage((int)(Minecraft.getSystemTime()/1000)%16);
							temp.setTagCompound(stack.GetTagCompound());
						}
					}

					GlStateManager.enableDepth();
					RenderUtils.RenderItemStack(screen.mc, temp, posX + sizeX/2 - 8 - 40, posY + sizeY/2 - 17, "");

					String txt = progress + "/" + dispStack.stackSize;
					screen.mc.fontRendererObj.drawString(txt, posX + sizeX/2 - screen.mc.fontRendererObj.getStringWidth(txt)/2 + 20, posY + sizeY/2 + 2, ThemeRegistry.curTheme().textColor().getRGB());
					screen.mc.fontRendererObj.drawString("->", posX + sizeX/2 - screen.mc.fontRendererObj.getStringWidth("->")/2 - 10, posY + sizeY/2 - 17 + 2, ThemeRegistry.curTheme().textColor().getRGB());

					if(mx >= posX + sizeX/2 - 8 - 40 && mx < posX + sizeX/2 + 8 - 40 && my >= posY + sizeY/2 - 17 && my < posY + sizeY/2 - 1)
						screen.DrawTooltip(temp.getTooltip(screen.mc.player, screen.mc.gameSettings.advancedItemTooltips), mx, my);
				} else {
					int max = task.inputs.size();
					if(max > 5)
						max = 5;
					for(int i = 0; i < max; i++){
						if(i >= 5) break;
						screen.mc.renderEngine.bindTexture(ThemeRegistry.curTheme().guiTexture());
						GlStateManager.disableDepth();
						screen.drawTexturedModalRect(posX + sizeX/2 - 9 - 40, posY + sizeY/2 - 18 + i*19 - max * 8, 0, 48, 18, 18);

						BigItemStack stack = task.inputs.get(i).copy();

						ItemStack temp = stack.getBaseStack();

						if(stack.oreDict != null)
						{
							List<ItemStack> ores = OreDictionary.getOres(stack.oreDict);

							if(ores != null && ores.size() > 0)
							{
								temp = ores.get((int)(Minecraft.getSystemTime()/16000)%ores.size()).copy();
								temp.setItemDamage((int)(Minecraft.getSystemTime()/1000)%16);
								temp.setTagCompound(stack.GetTagCompound());
							}
						}

						GlStateManager.enableDepth();
						RenderUtils.RenderItemStack(screen.mc, temp, posX + sizeX/2 - 8 - 40, posY + sizeY/2 - 17 + i*19 - max * 8, "");

						if(i == max - 1){
							String txt = progress + "/" + dispStack.stackSize;
							screen.mc.fontRendererObj.drawString(txt, posX + sizeX/2 - screen.mc.fontRendererObj.getStringWidth(txt)/2 + 20, posY + sizeY/2 + 2, ThemeRegistry.curTheme().textColor().getRGB());
							screen.mc.fontRendererObj.drawString("->", posX + sizeX/2 - screen.mc.fontRendererObj.getStringWidth("->")/2 - 10, posY + sizeY/2 - 17 + 2, ThemeRegistry.curTheme().textColor().getRGB());
						}

						if(mx >= posX + sizeX/2 - 8 - 40 && mx < posX + sizeX/2 + 8 - 40 && my >= posY + sizeY/2 - 17 + i*19 - max * 8 && my < posY + sizeY/2 - 1 + i*19 - max * 8)
							screen.DrawTooltip(temp.getTooltip(screen.mc.player, screen.mc.gameSettings.advancedItemTooltips), mx, my);
					}
				}
			} else {
				String txt = progress + "/" + dispStack.stackSize;
				screen.mc.fontRendererObj.drawString(txt, posX + sizeX/2 - screen.mc.fontRendererObj.getStringWidth(txt)/2 + 20, posY + sizeY/2 + 2, ThemeRegistry.curTheme().textColor().getRGB());
			}

			if(mx >= posX + sizeX/2 - 8 + 20 && mx < posX + sizeX/2 + 8 + 20 && my >= posY + sizeY/2 - 17 && my < posY + sizeY/2 - 1)
				screen.DrawTooltip(dispStack.getBaseStack().getTooltip(screen.mc.player, screen.mc.gameSettings.advancedItemTooltips), mx, my);
		}
	}
}