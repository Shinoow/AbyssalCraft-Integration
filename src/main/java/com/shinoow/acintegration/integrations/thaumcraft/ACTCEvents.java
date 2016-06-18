package com.shinoow.acintegration.integrations.thaumcraft;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.internal.EnumWarpType;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.entity.EntityUtil;
import com.shinoow.abyssalcraft.common.entity.EntityChagaroth;
import com.shinoow.abyssalcraft.common.entity.EntityDragonBoss;
import com.shinoow.abyssalcraft.common.entity.EntityDragonMinion;
import com.shinoow.abyssalcraft.common.entity.EntityDreadguard;
import com.shinoow.abyssalcraft.common.entity.EntityGatekeeperMinion;
import com.shinoow.abyssalcraft.common.entity.EntityJzahar;
import com.shinoow.abyssalcraft.common.entity.EntityLesserDreadbeast;
import com.shinoow.abyssalcraft.common.entity.EntityOmotholWarden;
import com.shinoow.abyssalcraft.common.entity.EntitySacthoth;
import com.shinoow.abyssalcraft.common.entity.EntityShadowBeast;
import com.shinoow.abyssalcraft.common.entity.EntityShadowTitan;
import com.shinoow.abyssalcraft.common.entity.EntitySkeletonGoliath;
import com.shinoow.abyssalcraft.lib.ACLib;

public class ACTCEvents {

	List<Class<? extends EntityMob>> elites = Lists.newArrayList();
	List<Class<? extends EntityMob>> bosses = Lists.newArrayList();

	int warpTimer;

	public ACTCEvents(){

		elites.add(EntityDragonMinion.class);
		elites.add(EntityDreadguard.class);
		elites.add(EntityGatekeeperMinion.class);
		elites.add(EntityLesserDreadbeast.class);
		elites.add(EntityShadowBeast.class);
		elites.add(EntitySkeletonGoliath.class);
		elites.add(EntityOmotholWarden.class);
		elites.add(EntityShadowTitan.class);

		bosses.add(EntityChagaroth.class);
		bosses.add(EntityDragonBoss.class);
		bosses.add(EntityJzahar.class);
		bosses.add(EntitySacthoth.class);
	}

	@SubscribeEvent
	public void onEntityAttack(LivingAttackEvent event){
		if(event.entity instanceof EntityLiving){
			EntityLiving mob = (EntityLiving)event.entity;

			if(event.source.getEntity() != null &&
					event.source.getEntity() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer)event.source.getEntity();

				if(EntityUtil.isEntityCoralium(mob) || EntityUtil.isEntityDread(mob) ||
						mob.getCreatureAttribute() == AbyssalCraftAPI.SHADOW ||
						mob instanceof EntitySkeletonGoliath ){
					if(isElite(mob)){
						if(player.worldObj.rand.nextInt(20) == 0)
							ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, EnumWarpType.NORMAL);
					} else if(isBoss(mob)){
						if(player.worldObj.rand.nextInt(25) == 0)
							ThaumcraftApi.internalMethods.addWarpToPlayer(player, 7, EnumWarpType.PERMANENT);
					} else {
						if(player.worldObj.rand.nextInt(15) == 0)
							ThaumcraftApi.internalMethods.addWarpToPlayer(player, 3, EnumWarpType.TEMPORARY);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;

			if(event.source.getEntity() != null &&
					event.source.getEntity() instanceof EntityLiving){
				EntityLiving mob = (EntityLiving)event.source.getEntity();

				if(EntityUtil.isEntityCoralium(mob) || EntityUtil.isEntityDread(mob) ||
						mob.getCreatureAttribute() == AbyssalCraftAPI.SHADOW ||
						mob instanceof EntitySkeletonGoliath){
					if(isElite(mob)){
						if(player.worldObj.rand.nextInt(8) == 0){
							if(player.worldObj.rand.nextBoolean())
								ThaumcraftApi.internalMethods.addWarpToPlayer(player, 7, EnumWarpType.NORMAL);
							else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 2, EnumWarpType.PERMANENT);
						}
					} else if(isBoss(mob)){
						if(player.worldObj.rand.nextInt(12) == 0){
							if(player.worldObj.rand.nextBoolean())
								ThaumcraftApi.internalMethods.addWarpToPlayer(player, 10, EnumWarpType.PERMANENT);
							else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 3, EnumWarpType.PERMANENT);
						}
					} else {
						if(player.worldObj.rand.nextInt(4) == 0){
							if(player.worldObj.rand.nextBoolean())
								ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, EnumWarpType.TEMPORARY);
							else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, EnumWarpType.TEMPORARY);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityLiving(LivingUpdateEvent event){
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;

			if(player.dimension == ACLib.abyssal_wasteland_id ||
					player.dimension == ACLib.dreadlands_id ||
					player.dimension == ACLib.omothol_id ||
					player.dimension == ACLib.dark_realm_id)
				warpTimer++;
			if(warpTimer >= 2400){
				warpTimer = player.worldObj.rand.nextInt(300);
				if(player.dimension == ACLib.abyssal_wasteland_id ||
						player.dimension == ACLib.dreadlands_id)
					if(player.worldObj.rand.nextBoolean())
						ThaumcraftApi.internalMethods.addWarpToPlayer(player, 10, EnumWarpType.NORMAL);
					else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 10, EnumWarpType.PERMANENT);
				if(player.dimension == ACLib.omothol_id ||
						player.dimension == ACLib.dark_realm_id)
					if(player.worldObj.rand.nextBoolean())
						ThaumcraftApi.internalMethods.addWarpToPlayer(player, 15, EnumWarpType.NORMAL);
					else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 15, EnumWarpType.PERMANENT);
			}
		}
	}

	//	@SubscribeEvent
	//	public void leftDarkRealm(PlayerChangedDimensionEvent event){
	//		if(event.fromDim == AbyssalCraft.configDimId4){
	//			//apply sun scorched
	//		}
	//	}

	private boolean isElite(EntityLiving entity){
		for(Class<? extends EntityMob> clazz : elites){
			return entity.getClass().getName().equals(clazz.getName());
		}
		return false;
	}

	private boolean isBoss(EntityLiving entity){
		for(Class<? extends EntityMob> clazz : bosses){
			return entity.getClass().getName().equals(clazz.getName());
		}
		return false;
	}
}