package com.shinoow.acintegration.integrations.tinkers;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.entity.EntityUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitDreadPlague extends AbstractTrait {

	public TraitDreadPlague() {
		super("dreadplague", TextFormatting.DARK_RED);
	}

	@Override
	public void afterHit(ItemStack tool,  EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {

		if(wasCritical && wasHit)
			if(!EntityUtil.isEntityDread(target))
				target.addPotionEffect(new PotionEffect(AbyssalCraftAPI.dread_plague, 100));
	}
}