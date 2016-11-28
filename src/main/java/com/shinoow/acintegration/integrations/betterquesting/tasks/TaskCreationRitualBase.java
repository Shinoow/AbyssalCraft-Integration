package com.shinoow.acintegration.integrations.betterquesting.tasks;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shinoow.abyssalcraft.common.util.ACLogger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import betterquesting.party.PartyInstance;
import betterquesting.party.PartyManager;
import betterquesting.party.PartyInstance.PartyMember;
import betterquesting.quests.QuestDatabase;
import betterquesting.quests.QuestInstance;
import betterquesting.quests.tasks.TaskBase;
import betterquesting.quests.tasks.advanced.IProgressionTask;
import betterquesting.utils.BigItemStack;
import betterquesting.utils.JsonHelper;
import betterquesting.utils.NBTConverter;

public abstract class TaskCreationRitualBase extends TaskBase implements IProgressionTask<Integer> {

	public BigItemStack output;
	boolean partialMatch = true;
	boolean ignoreNBT = false;
	public HashMap<UUID, Integer> userProgress = new HashMap<UUID, Integer>();

	@Override
	public void Update(QuestInstance quest, EntityPlayer player)
	{
		if(player.ticksExisted%200 == 0 && !QuestDatabase.editMode)
			Detect(quest, player);
	}

	@Override
	public void Detect(QuestInstance quest, EntityPlayer player)
	{
		if(isComplete(player.getUniqueID()))
			return;

		int progress = quest == null || !quest.globalQuest? GetPartyProgress(player.getUniqueID()) : GetGlobalProgress();

		if(progress >= output.stackSize)
			setCompletion(player.getUniqueID(), true);
	}

	@Override
	public void writeToJson(JsonObject json){
		super.writeToJson(json);

		json.addProperty("partialMatch", partialMatch);
		json.addProperty("ignoreNBT", ignoreNBT);

		if(output != null)
			json.add("output", NBTConverter.NBTtoJSON_Compound(output.writeToNBT(new NBTTagCompound()), new JsonObject()));

		JsonArray progArray = new JsonArray();
		for(Entry<UUID,Integer> entry : userProgress.entrySet())
		{
			JsonObject pJson = new JsonObject();
			pJson.addProperty("uuid", entry.getKey().toString());
			pJson.addProperty("value", entry.getValue());
			progArray.add(pJson);
		}
		json.add("userProgress", progArray);
	}

	@Override
	public void readFromJson(JsonObject json){
		super.readFromJson(json);

		partialMatch = JsonHelper.GetBoolean(json, "partialMatch", true);
		ignoreNBT = JsonHelper.GetBoolean(json, "ignoreNBT", false);

		output = JsonHelper.JsonToItemStack(JsonHelper.GetObject(json, "output"));

		userProgress = new HashMap<UUID,Integer>();
		for(JsonElement entry : JsonHelper.GetArray(json, "userProgress"))
		{
			if(entry == null || !entry.isJsonObject())
				continue;

			UUID uuid;
			try
			{
				uuid = UUID.fromString(JsonHelper.GetString(entry.getAsJsonObject(), "uuid", ""));
			} catch(Exception e)
			{
				ACLogger.log(Level.ERROR, "Unable to load user progress for task", e);
				continue;
			}

			userProgress.put(uuid, JsonHelper.GetNumber(entry.getAsJsonObject(), "value", 0).intValue());
		}
	}

	@Override
	public void ResetProgress(UUID uuid)
	{
		super.ResetProgress(uuid);
		userProgress.remove(uuid);
	}

	@Override
	public void ResetAllProgress()
	{
		super.ResetAllProgress();
		userProgress = new HashMap<UUID, Integer>();
	}

	@Override
	public float GetParticipation(UUID uuid)
	{
		if(output == null || output.stackSize <= 0)
			return 1F;

		return GetUserProgress(uuid) / (float)output.stackSize;
	}

	@Override
	public void SetUserProgress(UUID uuid, Integer progress)
	{
		userProgress.put(uuid, progress);
	}

	@Override
	public Integer GetUserProgress(UUID uuid)
	{
		Integer i = userProgress.get(uuid);
		return i == null? 0 : i;
	}

	@Override
	public Integer GetPartyProgress(UUID uuid)
	{
		int total = 0;

		PartyInstance party = PartyManager.GetParty(uuid);

		if(party == null)
			return GetUserProgress(uuid);
		else
			for(PartyMember mem : party.GetMembers())
			{
				if(mem != null && mem.GetPrivilege() <= 0)
					continue;

				total += GetUserProgress(mem.userID);
			}

		return total;
	}

	@Override
	public Integer GetGlobalProgress()
	{
		int total = 0;

		for(Integer i : userProgress.values())
			total += i == null? 0 : i;

		return total;
	}
}