package com.shinoow.acintegration.integrations.betterquesting.tasks;

import net.minecraft.entity.player.EntityPlayer;
import betterquesting.client.gui.GuiQuesting;
import betterquesting.client.gui.misc.GuiEmbedded;
import betterquesting.quests.QuestInstance;
import betterquesting.quests.tasks.TaskBase;
import betterquesting.utils.JsonHelper;

import com.google.gson.JsonObject;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.acintegration.integrations.betterquesting.gui.GuiTaskRitual;

public class TaskRitual extends TaskBase {

	public String name;

	@Override
	public String getUnlocalisedName() {

		return "acintegration.task.ritual";
	}

	public void onRitual(EntityPlayer player, NecronomiconRitual ritual){

		if(isComplete(player.getUniqueID()))
			return;

		if(ritual.getUnlocalizedName().equals("ac.ritual." + name))
			setCompletion(player.getUniqueID(), true);
	}

	@Override
	public void writeToJson(JsonObject json){
		super.writeToJson(json);

		json.addProperty("ritualname", name);
	}

	@Override
	public void readFromJson(JsonObject json){
		super.readFromJson(json);

		name = JsonHelper.GetString(json, "ritualname", "");
	}

	@Override
	public GuiEmbedded getGui(QuestInstance quest, GuiQuesting screen, int posX, int posY, int sizeX, int sizeY)
	{
		return new GuiTaskRitual(this, screen, posX, posY, sizeX, sizeY);
	}
}