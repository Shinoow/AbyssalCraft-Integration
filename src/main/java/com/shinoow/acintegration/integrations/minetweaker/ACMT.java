package com.shinoow.acintegration.integrations.minetweaker;

import static crafttweaker.api.minecraft.CraftTweakerMC.getItemStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import com.shinoow.abyssalcraft.api.integration.ACPlugin;
import com.shinoow.abyssalcraft.api.integration.IACPlugin;
import com.shinoow.acintegration.ACIntegration;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IngredientStack;
import crafttweaker.api.oredict.IOreDictEntry;

@ACPlugin
public class ACMT implements IACPlugin {

	@Override
	public String getModName() {

		return "CraftTweaker";
	}

	public static ACMT instance = new ACMT();

	@Override
	public boolean canLoad() {

		return Loader.isModLoaded("crafttweaker") && ACIntegration.loadMT;
	}

	@Override
	public void preInit() {
		CraftTweakerAPI.registerClass(Crystallizer.class);
		CraftTweakerAPI.registerClass(Transmutator.class);
		CraftTweakerAPI.registerClass(CreationRitual.class);
		CraftTweakerAPI.registerClass(InfusionRitual.class);
		CraftTweakerAPI.registerClass(InternalNecroData.class);
		CraftTweakerAPI.registerClass(Shoggoth.class);
		CraftTweakerAPI.registerClass(EnchantmentRitual.class);
		CraftTweakerAPI.registerClass(PotionRitual.class);
		CraftTweakerAPI.registerClass(PotionAoERitual.class);
		CraftTweakerAPI.registerClass(UpgradeKit.class);
		CraftTweakerAPI.registerClass(SummonRitual.class);
		CraftTweakerAPI.registerClass(GatewayKey.class);
		CraftTweakerAPI.registerClass(Rituals.class);
		CraftTweakerAPI.registerClass(Materializer.class);
		if(Loader.isModLoaded("gamestages") && ACIntegration.loadGS)
			CraftTweakerAPI.registerClass(RitualStages.class);
	}

	@Override
	public void init() {
		ACMTMisc.ADDITIONS.forEach(CraftTweakerAPI::apply);
		ACMTMisc.REMOVALS.forEach(CraftTweakerAPI::apply);
	}

	@Override
	public void postInit() {

	}

	// Start of borrowed code from Immersive Engineering
	// https://github.com/BluSunrize/ImmersiveEngineering/blob/master/src/main/java/blusunrize/immersiveengineering/common/util/compat/minetweaker/MTHelper.java

	public static ItemStack toStack(IItemStack iStack)
	{
		return getItemStack(iStack);
	}

	public static Object toObject(IIngredient iStack)
	{
		if (iStack == null)
			return null;
		else if(iStack instanceof IOreDictEntry)
			return ((IOreDictEntry)iStack).getName();
		else if(iStack instanceof IItemStack)
			return getItemStack((IItemStack) iStack);
		else if(iStack instanceof IngredientStack)
		{
			IIngredient ingr = ReflectionHelper.getPrivateValue(IngredientStack.class, (IngredientStack)iStack, "ingredient");
			return toObject(ingr);
		}
		else
			return null;
	}
	public static Object[] toObjects(IIngredient[] iStacks)
	{
		Object[] oA = new Object[iStacks.length];
		for(int i=0; i<iStacks.length; i++)
			oA[i] = toObject(iStacks[i]);
		return oA;
	}

	// End of borrowed code
}