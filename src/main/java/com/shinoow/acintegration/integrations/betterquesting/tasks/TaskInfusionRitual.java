package com.shinoow.acintegration.integrations.betterquesting.tasks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import betterquesting.client.gui.GuiQuesting;
import betterquesting.client.gui.misc.GuiEmbedded;
import betterquesting.quests.QuestInstance;
import betterquesting.utils.*;

import com.google.gson.JsonObject;
import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconInfusionRitual;
import com.shinoow.acintegration.integrations.betterquesting.gui.GuiTaskInfusionRitual;

public class TaskInfusionRitual extends TaskCreationRitualBase {

	public BigItemStack offering;

	@Override
	public String getUnlocalisedName() {

		return "acintegration.task.infusionritual";
	}

	public void onInfusionRitual(QuestInstance quest, EntityPlayer player, NecronomiconInfusionRitual ritual){

		if(isComplete(player.getUniqueID()))
			return;

		int progress = quest == null || !quest.globalQuest? GetPartyProgress(player.getUniqueID()) : GetGlobalProgress();

		ItemStack stack = APIUtils.convertToStack(ritual.getSacrifice());

		if(ItemComparison.StackMatch(offering.getBaseStack(), stack, !ignoreNBT, partialMatch) || ItemComparison.OreDictionaryMatch(offering.oreDict, offering.GetTagCompound(), stack, !ignoreNBT, partialMatch))
			if(ItemComparison.StackMatch(output.getBaseStack(), ritual.getItem(), !ignoreNBT, partialMatch) || ItemComparison.OreDictionaryMatch(output.oreDict, output.GetTagCompound(), ritual.getItem(), !ignoreNBT, partialMatch))
				SetUserProgress(player.getUniqueID(), progress + 1);
	}

	@Override
	public void writeToJson(JsonObject json){
		super.writeToJson(json);

		if(offering != null)
			json.add("offering", NBTConverter.NBTtoJSON_Compound(offering.writeToNBT(new NBTTagCompound()), new JsonObject()));
	}

	@Override
	public void readFromJson(JsonObject json){
		super.readFromJson(json);

		offering = JsonHelper.JsonToItemStack(JsonHelper.GetObject(json, "offering"));
	}

	@Override
	public GuiEmbedded getGui(QuestInstance quest, GuiQuesting screen, int posX, int posY, int sizeX, int sizeY)
	{
		return new GuiTaskInfusionRitual(quest, this, screen, posX, posY, sizeX, sizeY);
	}
}