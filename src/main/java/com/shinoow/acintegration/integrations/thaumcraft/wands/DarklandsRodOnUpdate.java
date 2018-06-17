//package com.shinoow.acintegration.integrations.thaumcraft.wands;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//import thaumcraft.api.aspects.Aspect;
//import thaumcraft.api.wands.IWandRodOnUpdate;
//import thaumcraft.common.items.wands.ItemWand;
//
//import com.shinoow.abyssalcraft.api.biome.IDarklandsBiome;
//
//public class DarklandsRodOnUpdate implements IWandRodOnUpdate {
//
//	Aspect primals[] = Aspect.getPrimalAspects().toArray(new Aspect[0]);
//
//	@Override
//	public void onUpdate(ItemStack itemstack, EntityPlayer player) {
//		if(player.ticksExisted % 100 == 0)
//			if(player.worldObj.getBiomeGenForCoords(player.getPosition()) instanceof IDarklandsBiome)
//				for(int x = 0;x < primals.length;x++)
//					if(((ItemWand)itemstack.getItem()).getVis(itemstack, primals[x]) < ((ItemWand)itemstack.getItem()).getMaxVis(itemstack) / 10)
//						((ItemWand)itemstack.getItem()).addVis(itemstack, primals[x], 1, true);
//	}
//}
