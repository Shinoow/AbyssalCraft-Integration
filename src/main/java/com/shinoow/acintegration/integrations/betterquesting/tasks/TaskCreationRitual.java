//package com.shinoow.acintegration.integrations.betterquesting.tasks;
//
//import net.minecraft.entity.player.EntityPlayer;
//import betterquesting.client.gui.GuiQuesting;
//import betterquesting.client.gui.misc.GuiEmbedded;
//import betterquesting.quests.QuestInstance;
//import betterquesting.utils.ItemComparison;
//
//import com.shinoow.abyssalcraft.api.ritual.NecronomiconCreationRitual;
//import com.shinoow.acintegration.integrations.betterquesting.gui.GuiTaskCreationRitual;
//
//public class TaskCreationRitual extends TaskCreationRitualBase {
//
//
//	@Override
//	public String getUnlocalisedName() {
//
//		return "acintegration.task.creationritual";
//	}
//
//	public void onCreationRitual(QuestInstance quest, EntityPlayer player, NecronomiconCreationRitual ritual){
//
//		if(isComplete(player.getUniqueID()))
//			return;
//
//		int progress = quest == null || !quest.globalQuest? GetPartyProgress(player.getUniqueID()) : GetGlobalProgress();
//
//		if(ItemComparison.StackMatch(output.getBaseStack(), ritual.getItem(), !ignoreNBT, partialMatch) || ItemComparison.OreDictionaryMatch(output.oreDict, output.GetTagCompound(), ritual.getItem(), !ignoreNBT, partialMatch))
//			SetUserProgress(player.getUniqueID(), progress + 1);
//	}
//
//	@Override
//	public GuiEmbedded getGui(QuestInstance quest, GuiQuesting screen, int posX, int posY, int sizeX, int sizeY)
//	{
//		return new GuiTaskCreationRitual(quest, this, screen, posX, posY, sizeX, sizeY);
//	}
//}