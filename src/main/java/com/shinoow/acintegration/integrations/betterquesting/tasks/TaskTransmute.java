package com.shinoow.acintegration.integrations.betterquesting.tasks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import betterquesting.quests.QuestInstance;
import betterquesting.utils.ItemComparison;

public class TaskTransmute extends TaskACMachine {

	@Override
	public String getUnlocalisedName() {

		return "acintegration.task.transmute";
	}

	public void onItemTransmuted(QuestInstance quest, EntityPlayer player, ItemStack stack){

		if(isComplete(player.getUniqueID()))
			return;

		int progress = quest == null || !quest.globalQuest? GetPartyProgress(player.getUniqueID()) : GetGlobalProgress();
		
		if(ItemComparison.StackMatch(output.getBaseStack(), stack, !ignoreNBT, partialMatch) || ItemComparison.OreDictionaryMatch(output.oreDict, output.GetTagCompound(), stack, !ignoreNBT, partialMatch))
			SetUserProgress(player.getUniqueID(), progress + 1);
	}
}