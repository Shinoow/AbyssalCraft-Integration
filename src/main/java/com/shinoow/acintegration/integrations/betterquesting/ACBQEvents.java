package com.shinoow.acintegration.integrations.betterquesting;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import betterquesting.quests.QuestDatabase;
import betterquesting.quests.QuestInstance;
import betterquesting.quests.tasks.TaskBase;

import com.shinoow.abyssalcraft.api.event.ACEvents.*;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconCreationRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconInfusionRitual;
import com.shinoow.acintegration.integrations.betterquesting.tasks.*;

public class ACBQEvents {

	@SubscribeEvent
	public void onTransmuted(ItemTransmutedEvent event){

		if(event.getEntityPlayer().worldObj.isRemote)
			return;

		for(Entry<TaskACMachine,QuestInstance> set : getMachineTasks(event.getEntityPlayer().getUniqueID()).entrySet()){
			if(!(set.getKey() instanceof TaskTransmute)) continue;

			((TaskTransmute) set.getKey()).onItemTransmuted(set.getValue(), event.getEntityPlayer(), event.getTransmutedStack());
		}
	}

	@SubscribeEvent
	public void onCrystallized(ItemCrystallizedEvent event){

		if(event.getEntityPlayer().worldObj.isRemote)
			return;

		for(Entry<TaskACMachine,QuestInstance> set : getMachineTasks(event.getEntityPlayer().getUniqueID()).entrySet()){
			if(!(set.getKey() instanceof TaskCrystallize)) continue;

			((TaskCrystallize) set.getKey()).onItemCrystallized(set.getValue(), event.getEntityPlayer(), event.getCrystallizedStack());
		}
	}

	@SubscribeEvent
	public void onRitualComplete(RitualEvent.Post event){

		if(event.getEntityPlayer().worldObj.isRemote)
			return;

		for(TaskBase task : QuestDatabase.getActiveTasks(event.getEntityPlayer().getUniqueID())){
			if(!(task instanceof TaskRitual)) continue;

			((TaskRitual) task).onRitual(event.getEntityPlayer(), event.getRitual());
		}

		if(event.getRitual() instanceof NecronomiconInfusionRitual)
			for(Entry<TaskCreationRitualBase,QuestInstance> set : getRitualTasks(event.getEntityPlayer().getUniqueID()).entrySet()){
				if(!(set.getKey() instanceof TaskInfusionRitual)) continue;

				((TaskInfusionRitual) set.getKey()).onInfusionRitual(set.getValue(), event.getEntityPlayer(), (NecronomiconInfusionRitual)event.getRitual());
			}
		else if(event.getRitual() instanceof NecronomiconCreationRitual)
			for(Entry<TaskCreationRitualBase,QuestInstance> set : getRitualTasks(event.getEntityPlayer().getUniqueID()).entrySet()){
				if(!(set.getKey() instanceof TaskCreationRitual)) continue;

				((TaskCreationRitual) set.getKey()).onCreationRitual(set.getValue(), event.getEntityPlayer(), (NecronomiconCreationRitual)event.getRitual());
			}
	}

	HashMap<TaskACMachine, QuestInstance> getMachineTasks(UUID uuid)
	{
		HashMap<TaskACMachine, QuestInstance> map = new HashMap<TaskACMachine, QuestInstance>();

		for(QuestInstance quest : QuestDatabase.getActiveQuests(uuid))
			for(TaskBase task : quest.tasks)
				if(task instanceof TaskACMachine && !task.isComplete(uuid))
					map.put((TaskACMachine)task, quest);

		return map;
	}

	HashMap<TaskCreationRitualBase, QuestInstance> getRitualTasks(UUID uuid)
	{
		HashMap<TaskCreationRitualBase, QuestInstance> map = new HashMap<TaskCreationRitualBase, QuestInstance>();

		for(QuestInstance quest : QuestDatabase.getActiveQuests(uuid))
			for(TaskBase task : quest.tasks)
				if(task instanceof TaskCreationRitualBase && !task.isComplete(uuid))
					map.put((TaskCreationRitualBase)task, quest);

		return map;
	}
}