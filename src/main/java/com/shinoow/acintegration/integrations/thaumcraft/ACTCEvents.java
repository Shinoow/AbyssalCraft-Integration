package com.shinoow.acintegration.integrations.thaumcraft;

import java.util.List;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.AbyssalCraftAPI.ACEntities;
import com.shinoow.abyssalcraft.api.block.ACBlocks;
import com.shinoow.abyssalcraft.api.entity.EntityUtil;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.common.entity.*;
import com.shinoow.abyssalcraft.common.entity.demon.EntityEvilAnimal;
import com.shinoow.acintegration.ACIntegration;
import com.shinoow.acintegration.integrations.thaumcraft.cap.ITaintTimerCapability;
import com.shinoow.acintegration.integrations.thaumcraft.cap.TaintTimerCapability;
import com.shinoow.acintegration.integrations.thaumcraft.cap.TaintTimerCapabilityProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;

public class ACTCEvents {

	List<Class<? extends EntityMob>> elites = Lists.newArrayList();

	public ACTCEvents(){

		elites.add(EntityDragonMinion.class);
		elites.add(EntityDreadguard.class);
		elites.add(EntityGatekeeperMinion.class);
		elites.add(EntityLesserDreadbeast.class);
		elites.add(EntityShadowBeast.class);
		elites.add(EntitySkeletonGoliath.class);
		elites.add(EntityOmotholWarden.class);
		elites.add(EntityShadowTitan.class);
		elites.add(EntityShubOffspring.class);
	}

	@SubscribeEvent
	public void onEntityAttack(LivingAttackEvent event){
		if(ACIntegration.tcWarpMobs)
			if(event.getEntity() instanceof EntityLiving){
				EntityLiving mob = (EntityLiving)event.getEntity();

				if(event.getSource().getTrueSource() instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer)event.getSource().getTrueSource();

					if(EntityUtil.isEntityCoralium(mob) || EntityUtil.isEntityDread(mob) ||
							mob.getCreatureAttribute() == AbyssalCraftAPI.SHADOW ||
							mob instanceof EntitySkeletonGoliath || mob instanceof EntityShubOffspring ||
							mob instanceof EntityEvilAnimal)
						if(isElite(mob)){
							if(player.world.rand.nextInt(20) == 0)
								ThaumcraftApi.internalMethods.addWarpToPlayer(player, 3, EnumWarpType.TEMPORARY);
						} else if(!mob.isNonBoss()){
							if(player.world.rand.nextInt(25) == 0)
								ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, EnumWarpType.NORMAL);
						} else if(player.world.rand.nextInt(15) == 0)
							ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, EnumWarpType.TEMPORARY);
				}
			}
	}

	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event){
		if(ACIntegration.tcWarpMobs)
			if(event.getEntity() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer)event.getEntity();

				if(event.getSource().getTrueSource() instanceof EntityLiving){
					EntityLiving mob = (EntityLiving)event.getSource().getTrueSource();

					if(EntityUtil.isEntityCoralium(mob) || EntityUtil.isEntityDread(mob) ||
							mob.getCreatureAttribute() == AbyssalCraftAPI.SHADOW ||
							mob instanceof EntitySkeletonGoliath || mob instanceof EntityShubOffspring ||
							mob instanceof EntityEvilAnimal)
						if(isElite(mob)){
							if(player.world.rand.nextInt(8) == 0)
								if(player.world.rand.nextBoolean())
									ThaumcraftApi.internalMethods.addWarpToPlayer(player, 7, EnumWarpType.TEMPORARY);
								else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 2, EnumWarpType.NORMAL);
						} else if(!mob.isNonBoss()){
							if(player.world.rand.nextInt(12) == 0)
								if(player.world.rand.nextBoolean())
									ThaumcraftApi.internalMethods.addWarpToPlayer(player, 10, EnumWarpType.TEMPORARY);
								else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 3, EnumWarpType.NORMAL);
						} else if(player.world.rand.nextInt(4) == 0)
							if(player.world.rand.nextBoolean())
								ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, EnumWarpType.TEMPORARY);
							else ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, EnumWarpType.TEMPORARY);
				}
			}
	}

	@SubscribeEvent
	public void onEntityLiving(LivingUpdateEvent event){
		if(ACIntegration.tcWarpDims)
			if(event.getEntity() instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer)event.getEntity();
				ITaintTimerCapability cap = TaintTimerCapability.getCap(player);

				cap.incrementTimerIfApplicable(player);
				cap.executeIfApplicable(player);
			}
	}

	@SubscribeEvent
	public void registerAspects(AspectRegistryEvent event) {
		//Aspects, blocks
		event.register.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 0), new AspectList().add(Aspect.EARTH, 2).add(Aspect.DARKNESS, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.darkstone_brick), new AspectList().add(Aspect.EARTH, 2).add(Aspect.DARKNESS, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 0), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(Aspect.DARKNESS, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.abyssalnite_ore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.dreadlands_abyssalnite_ore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.dreaded_abyssalnite_ore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(Aspect.DARKNESS, 1).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.coralium_ore), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.coralium_infused_stone), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.abyssal_coralium_ore), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.liquified_coralium_ore), new AspectList().add(Aspect.METAL, 3).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.pearlescent_coralium_ore), new AspectList().add(Aspect.CRYSTAL, 2).add(Aspect.EARTH, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 1), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.abyssal_stone_brick), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.abyssal_stone_button), new AspectList().add(Aspect.MECHANISM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.abyssal_gateway), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.UNDEAD, 1).add(Aspect.MOTION, 4));
		event.register.registerObjectTag(new ItemStack(ACBlocks.dreaded_gateway), new AspectList().add(Aspect.DARKNESS, 4).add(ACTCMisc.DREAD, 1).add(Aspect.MOTION, 4));
		event.register.registerObjectTag(new ItemStack(ACBlocks.omothol_gateway), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.ELDRITCH, 1).add(Aspect.MOTION, 4));
		event.register.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 2), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 3), new AspectList().add(Aspect.EARTH, 2));
		event.register.registerObjectTag(new ItemStack(ACBlocks.liquid_coralium), new AspectList().add(Aspect.WATER, 3).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.liquid_antimatter), new AspectList().add(Aspect.WATER, 3).add(Aspect.VOID, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 5), new AspectList().add(Aspect.SOUL, 2).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.ethaxium_brick, 1, 0), new AspectList().add(Aspect.SOUL, 3).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.ethaxium_brick, 1, 1), new AspectList().add(Aspect.SOUL, 3).add(Aspect.ELDRITCH, 2));
		event.register.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 6), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 4), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.dreadlands_infused_powerstone), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ENERGY, 2).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.stone, 1, 7), new AspectList().add(Aspect.EARTH, 2).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.shoggoth_ooze), new AspectList().add(Aspect.EARTH, 1).add(Aspect.LIFE, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.shoggoth_biomass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.LIFE, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 0), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 1), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 2), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 3), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 4), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 5), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.statue, 1, 6), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.energy_pedestal), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.monolith_pillar), new AspectList().add(Aspect.EARTH, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.sacrificial_altar), new AspectList().add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1).add(Aspect.DEATH, 1).add(Aspect.EXCHANGE, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.tiered_energy_pedestal, 1, OreDictionary.WILDCARD_VALUE), new AspectList().add(Aspect.ENERGY, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.tiered_sacrificial_altar, 1, OreDictionary.WILDCARD_VALUE), new AspectList().add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1).add(Aspect.DEATH, 1).add(Aspect.EXCHANGE, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 1), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 2), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 3), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.cobblestone, 1, 4), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.abyssal_sand), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.fused_abyssal_sand), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.abyssal_sand_glass), new AspectList().add(Aspect.CRYSTAL, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.dreadlands_grass), new AspectList().add(Aspect.EARTH, 1).add(Aspect.PLANT, 1).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.dreadlands_dirt), new AspectList().add(Aspect.EARTH, 2).add(ACTCMisc.DREAD, 1));

		//Aspects, items
		event.register.registerObjectTag(new ItemStack(ACItems.abyssalnite_ingot), new AspectList().add(Aspect.METAL, 3).add(Aspect.DARKNESS, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.refined_coralium_ingot), new AspectList().add(Aspect.METAL, 3).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.dreadium_ingot), new AspectList().add(Aspect.METAL, 3).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem), new AspectList().add(Aspect.CRYSTAL, 2).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_2), new AspectList().add(Aspect.CRYSTAL, 4).add(ACTCMisc.CORALIUM, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_3), new AspectList().add(Aspect.CRYSTAL, 6).add(ACTCMisc.CORALIUM, 3));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_4), new AspectList().add(Aspect.CRYSTAL, 8).add(ACTCMisc.CORALIUM, 4));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_5), new AspectList().add(Aspect.CRYSTAL, 10).add(ACTCMisc.CORALIUM, 5));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_6), new AspectList().add(Aspect.CRYSTAL, 12).add(ACTCMisc.CORALIUM, 6));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_7), new AspectList().add(Aspect.CRYSTAL, 14).add(ACTCMisc.CORALIUM, 7));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_8), new AspectList().add(Aspect.CRYSTAL, 16).add(ACTCMisc.CORALIUM, 8));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_gem_cluster_9), new AspectList().add(Aspect.CRYSTAL, 18).add(ACTCMisc.CORALIUM, 9));
		event.register.registerObjectTag(new ItemStack(ACItems.ethaxium_ingot), new AspectList().add(Aspect.METAL, 1).add(Aspect.SOUL, 3));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_brick), new AspectList().add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.ethaxium_brick), new AspectList().add(Aspect.FIRE, 1).add(Aspect.SOUL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.shadow_fragment), new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.DARKNESS, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.nitre), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_pearl), new AspectList().add(Aspect.CRYSTAL, 18).add(ACTCMisc.CORALIUM, 9));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_plagued_flesh), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.coralium_plagued_flesh_on_a_bone), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.CORALIUM, 1).add(Aspect.DEATH, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.dread_fragment), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.omothol_flesh), new AspectList().add(Aspect.LIFE, 2).add(Aspect.MAN, 1).add(Aspect.ELDRITCH, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.eldritch_scale), new AspectList().add(Aspect.LIFE, 2).add(Aspect.ELDRITCH, 1).add(Aspect.WATER, 1).add(Aspect.PROTECT, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.powerstone_tracker), new AspectList().add(Aspect.FLIGHT, 2).add(Aspect.SENSES, 2).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.eye_of_the_abyss), new AspectList().add(Aspect.SENSES, 3).add(Aspect.DARKNESS, 3).add(ACTCMisc.CORALIUM, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.essence, 1, 0), new AspectList().add(Aspect.SENSES, 2).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.essence, 1, 1), new AspectList().add(Aspect.SENSES, 2).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.essence, 1, 2), new AspectList().add(Aspect.SENSES, 2).add(Aspect.DARKNESS, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.skin, 1, 0), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(ACTCMisc.CORALIUM, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.skin, 1, 1), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(ACTCMisc.DREAD, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.skin, 1, 2), new AspectList().add(Aspect.LIFE, 3).add(Aspect.MAN, 2).add(Aspect.ELDRITCH, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 0), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3));
		event.register.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 1), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(ACTCMisc.CORALIUM, 3));
		event.register.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 2), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(ACTCMisc.DREAD, 3));
		event.register.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 3), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(Aspect.ELDRITCH, 3));
		event.register.registerObjectTag(new ItemStack(ACItems.shoggoth_flesh, 1, 4), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(Aspect.DARKNESS, 3));
		event.register.registerObjectTag(new ItemStack(ACItems.ritual_charm), new AspectList().add(Aspect.CRAFT, 2).add(Aspect.CRYSTAL, 1).add(Aspect.METAL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.cthulhu_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.hastur_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.jzahar_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.azathoth_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.nyarlathotep_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.yog_sothoth_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.shub_niggurath_charm), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.SOUL, 3).add(Aspect.METAL, 2));
		event.register.registerObjectTag(new ItemStack(ACItems.essence_of_the_gatekeeper), new AspectList().add(Aspect.ELDRITCH, 3).add(Aspect.DARKNESS, 3).add(Aspect.DEATH, 3).add(Aspect.ENERGY, 3));

		//Aspects, crystals
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.LIFE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 15), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 16), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 17), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 18), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 19), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 20), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 21), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 22), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 23), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal, 1, 24), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.LIFE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 15), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 16), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 17), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 18), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 19), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 20), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 21), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 22), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 23), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACItems.crystal_shard, 1, 24), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.AIR, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.LIFE, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 9), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.COLD, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 10), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 11), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 12), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 13), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.CORALIUM, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 14), new AspectList().add(Aspect.CRYSTAL, 3).add(ACTCMisc.DREAD, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster, 1, 15), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.FIRE, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 0), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 1), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 2), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 3), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 4), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 5), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 6), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 7), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));
		event.register.registerObjectTag(new ItemStack(ACBlocks.crystal_cluster2, 1, 8), new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.METAL, 1));

		//Aspect, entities
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.depths_ghoul), new AspectList().add(Aspect.UNDEAD, 6).add(Aspect.DEATH, 1).add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_pig), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.abyssal_zombie), new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.MAN, 1).add(Aspect.EARTH, 2).add(ACTCMisc.CORALIUM, 1));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.jzahar), new AspectList().add(Aspect.DEATH, 8).add(Aspect.DARKNESS, 10).add(Aspect.ELDRITCH, 15));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dreadguard), new AspectList().add(Aspect.UNDEAD, 5).add(Aspect.LIFE, 5).add(ACTCMisc.DREAD, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.spectral_dragon), new AspectList().add(Aspect.SOUL, 6).add(Aspect.AIR, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.asorah), new AspectList().add(Aspect.SOUL, 12).add(Aspect.AIR, 8).add(Aspect.BEAST, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_creature), new AspectList().add(Aspect.DARKNESS, 2).add(Aspect.BEAST, 1));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_monster), new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dreadling), new AspectList().add(Aspect.UNDEAD, 3).add(ACTCMisc.DREAD, 3).add(Aspect.LIFE, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.dread_spawn), new AspectList().add(Aspect.UNDEAD, 3).add(ACTCMisc.DREAD, 3).add(Aspect.LIFE, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_pig), new AspectList().add(Aspect.LIFE, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.skeleton_goliath), new AspectList().add(Aspect.UNDEAD, 6).add(Aspect.DEATH, 1).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.spawn_of_chagaroth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(ACTCMisc.DREAD, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.fist_of_chagaroth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(ACTCMisc.DREAD, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.chagaroth), new AspectList().add(Aspect.UNDEAD, 8).add(Aspect.LIFE, 10).add(ACTCMisc.DREAD, 15));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.shadow_beast), new AspectList().add(Aspect.DARKNESS, 6).add(Aspect.BEAST, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.sacthoth), new AspectList().add(Aspect.DEATH, 10).add(Aspect.DARKNESS, 15).add(Aspect.BEAST, 8));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.remnant), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.omothol_ghoul), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.minion_of_the_gatekeeper), new AspectList().add(Aspect.DEATH, 5).add(Aspect.DARKNESS, 5).add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.greater_dread_spawn), new AspectList().add(Aspect.UNDEAD, 3).add(ACTCMisc.DREAD, 3).add(Aspect.LIFE, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.lesser_dreadbeast), new AspectList().add(Aspect.UNDEAD, 3).add(ACTCMisc.DREAD, 3).add(Aspect.LIFE, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.lesser_shoggoth), new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.LIFE, 3).add(Aspect.WATER, 3));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_cow), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_chicken), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_cow), new AspectList().add(Aspect.LIFE, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_chicken), new AspectList().add(Aspect.LIFE, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.evil_sheep), new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2).add(Aspect.LIFE, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.demon_sheep), new AspectList().add(Aspect.LIFE, 3).add(Aspect.FIRE, 3).add(Aspect.BEAST, 2));
		ThaumcraftApi.registerEntityTag(getMobName(ACEntities.coralium_squid), new AspectList().add(Aspect.LIFE, 3).add(Aspect.WATER, 3).add(ACTCMisc.CORALIUM, 3));
		ThaumcraftApi.registerEntityTag(getMobName("shuboffspring"), new AspectList().add(Aspect.PLANT, 3).add(Aspect.DARKNESS, 3).add(Aspect.BEAST, 2));
	}

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event){
		if(event.getObject() instanceof EntityPlayer)
			event.addCapability(new ResourceLocation("acintegration", "tainttimer"), new TaintTimerCapabilityProvider());
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
		if(event.isWasDeath())
			TaintTimerCapability.getCap(event.getEntityPlayer()).copy(TaintTimerCapability.getCap(event.getOriginal()));
	}

	public static String getMobName(String name){
		return "abyssalcraft." + name;
	}

	//	@SubscribeEvent
	//	public void leftDarkRealm(PlayerChangedDimensionEvent event){
	//		if(event.fromDim == AbyssalCraft.configDimId4){
	//			//apply sun scorched
	//		}
	//	}

	private boolean isElite(EntityLiving entity){
		return elites.stream().anyMatch(c -> entity.getClass().getName().equals(c.getName()));
	}
}