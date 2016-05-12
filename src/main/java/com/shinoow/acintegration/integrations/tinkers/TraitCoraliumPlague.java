package com.shinoow.acintegration.integrations.tinkers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI.ACPotions;
import com.shinoow.abyssalcraft.common.util.EntityUtil;

public class TraitCoraliumPlague extends AbstractTrait {

	public TraitCoraliumPlague() {
		super("coraliumplague", EnumChatFormatting.AQUA);
	}

	@Override
	public void afterHit(ItemStack tool,  EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {

		if(wasCritical && wasHit)
			if(!EntityUtil.isEntityCoralium(target))
				target.addPotionEffect(new PotionEffect(ACPotions.Coralium_plague.id, 100));
	}
}