//package com.shinoow.acintegration.integrations.thaumcraft;
//
//import java.util.List;
//
//import net.minecraft.entity.EntityLiving;
//import net.minecraft.entity.monster.EntityMob;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraftforge.event.entity.living.LivingAttackEvent;
//import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
//import net.minecraftforge.event.entity.living.LivingHurtEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import thaumcraft.api.ThaumcraftApiHelper;
//
//import com.google.common.collect.Lists;
//import com.shinoow.abyssalcraft.AbyssalCraft;
//import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
//import com.shinoow.abyssalcraft.common.entity.EntityChagaroth;
//import com.shinoow.abyssalcraft.common.entity.EntityDragonBoss;
//import com.shinoow.abyssalcraft.common.entity.EntityDragonMinion;
//import com.shinoow.abyssalcraft.common.entity.EntityDreadguard;
//import com.shinoow.abyssalcraft.common.entity.EntityGatekeeperMinion;
//import com.shinoow.abyssalcraft.common.entity.EntityJzahar;
//import com.shinoow.abyssalcraft.common.entity.EntityLesserDreadbeast;
//import com.shinoow.abyssalcraft.common.entity.EntityOmotholWarden;
//import com.shinoow.abyssalcraft.common.entity.EntitySacthoth;
//import com.shinoow.abyssalcraft.common.entity.EntityShadowBeast;
//import com.shinoow.abyssalcraft.common.entity.EntityShadowTitan;
//import com.shinoow.abyssalcraft.common.entity.EntitySkeletonGoliath;
//import com.shinoow.abyssalcraft.common.util.EntityUtil;
//
//public class ACTCEvents {
//
//	List<Class<? extends EntityMob>> elites = Lists.newArrayList();
//	List<Class<? extends EntityMob>> bosses = Lists.newArrayList();
//
//	int warpTimer;
//
//	public ACTCEvents(){
//
//		elites.add(EntityDragonMinion.class);
//		elites.add(EntityDreadguard.class);
//		elites.add(EntityGatekeeperMinion.class);
//		elites.add(EntityLesserDreadbeast.class);
//		elites.add(EntityShadowBeast.class);
//		elites.add(EntitySkeletonGoliath.class);
//		elites.add(EntityOmotholWarden.class);
//		elites.add(EntityShadowTitan.class);
//
//		bosses.add(EntityChagaroth.class);
//		bosses.add(EntityDragonBoss.class);
//		bosses.add(EntityJzahar.class);
//		bosses.add(EntitySacthoth.class);
//	}
//
//	@SubscribeEvent
//	public void onEntityAttack(LivingAttackEvent event){
//		if(event.entity instanceof EntityLiving){
//			EntityLiving mob = (EntityLiving)event.entity;
//
//			if(event.source.getEntity() != null &&
//					event.source.getEntity() instanceof EntityPlayer){
//				EntityPlayer player = (EntityPlayer)event.source.getEntity();
//
//				if(EntityUtil.isEntityCoralium(mob) || EntityUtil.isEntityDread(mob) ||
//						mob.getCreatureAttribute() == AbyssalCraftAPI.SHADOW ||
//						mob instanceof EntitySkeletonGoliath ){
//					if(isElite(mob)){
//						if(player.worldObj.rand.nextInt(20) == 0)
//							ThaumcraftApiHelper.addStickyWarpToPlayer(player, 5);
//					} else if(isBoss(mob)){
//						if(player.worldObj.rand.nextInt(25) == 0)
//							ThaumcraftApiHelper.addWarpToPlayer(player, 7, false);
//					} else {
//						if(player.worldObj.rand.nextInt(15) == 0)
//							ThaumcraftApiHelper.addWarpToPlayer(player, 3, true);
//					}
//				}
//			}
//		}
//	}
//
//	@SubscribeEvent
//	public void onEntityHurt(LivingHurtEvent event){
//		if(event.entity instanceof EntityPlayer){
//			EntityPlayer player = (EntityPlayer)event.entity;
//
//			if(event.source.getEntity() != null &&
//					event.source.getEntity() instanceof EntityLiving){
//				EntityLiving mob = (EntityLiving)event.source.getEntity();
//
//				if(EntityUtil.isEntityCoralium(mob) || EntityUtil.isEntityDread(mob) ||
//						mob.getCreatureAttribute() == AbyssalCraftAPI.SHADOW ||
//						mob instanceof EntitySkeletonGoliath){
//					if(isElite(mob)){
//						if(player.worldObj.rand.nextInt(8) == 0){
//							if(player.worldObj.rand.nextBoolean())
//								ThaumcraftApiHelper.addStickyWarpToPlayer(player, 7);
//							else ThaumcraftApiHelper.addWarpToPlayer(player, 2, false);
//						}
//					} else if(isBoss(mob)){
//						if(player.worldObj.rand.nextInt(12) == 0){
//							if(player.worldObj.rand.nextBoolean())
//								ThaumcraftApiHelper.addWarpToPlayer(player, 10, false);
//							else ThaumcraftApiHelper.addWarpToPlayer(player, 3, false);
//						}
//					} else {
//						if(player.worldObj.rand.nextInt(4) == 0){
//							if(player.worldObj.rand.nextBoolean())
//								ThaumcraftApiHelper.addWarpToPlayer(player, 5, true);
//							else ThaumcraftApiHelper.addWarpToPlayer(player, 1, true);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	@SubscribeEvent
//	public void onEntityLiving(LivingUpdateEvent event){
//		if(event.entity instanceof EntityPlayer){
//			EntityPlayer player = (EntityPlayer)event.entity;
//
//			if(player.dimension == AbyssalCraft.configDimId1 ||
//					player.dimension == AbyssalCraft.configDimId2 ||
//					player.dimension == AbyssalCraft.configDimId3 ||
//					player.dimension == AbyssalCraft.configDimId4)
//				warpTimer++;
//			if(warpTimer >= 2400){
//				warpTimer = player.worldObj.rand.nextInt(300);
//				if(player.dimension == AbyssalCraft.configDimId1 ||
//						player.dimension == AbyssalCraft.configDimId2)
//					if(player.worldObj.rand.nextBoolean())
//						ThaumcraftApiHelper.addStickyWarpToPlayer(player, 10);
//					else ThaumcraftApiHelper.addWarpToPlayer(player, 10, true);
//				if(player.dimension == AbyssalCraft.configDimId3 ||
//						player.dimension == AbyssalCraft.configDimId4)
//					if(player.worldObj.rand.nextBoolean())
//						ThaumcraftApiHelper.addStickyWarpToPlayer(player, 15);
//					else ThaumcraftApiHelper.addWarpToPlayer(player, 15, true);
//			}
//		}
//	}
//
//	//	@SubscribeEvent
//	//	public void leftDarkRealm(PlayerChangedDimensionEvent event){
//	//		if(event.fromDim == AbyssalCraft.configDimId4){
//	//			//apply sun scorched
//	//		}
//	//	}
//
//	private boolean isElite(EntityLiving entity){
//		for(Class<? extends EntityMob> clazz : elites){
//			return entity.getClass().getName().equals(clazz.getName());
//		}
//		return false;
//	}
//
//	private boolean isBoss(EntityLiving entity){
//		for(Class<? extends EntityMob> clazz : bosses){
//			return entity.getClass().getName().equals(clazz.getName());
//		}
//		return false;
//	}
//}