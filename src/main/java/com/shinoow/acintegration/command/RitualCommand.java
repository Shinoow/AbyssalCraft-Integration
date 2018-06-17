package com.shinoow.acintegration.command;

import java.io.*;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.ritual.*;
import com.shinoow.abyssalcraft.lib.util.IHiddenRitual;

public class RitualCommand extends CommandBase {

	@Override
	public String getName() {

		return "acritual";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return "/acritual chat | file";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender,
			String[] args) throws CommandException {

		if(args.length == 1){
			if(args[0].equals("chat")){
				StringBuilder sb = new StringBuilder();

				List<NecronomiconRitual> rituals = RitualRegistry.instance().getRituals();

				for(int i = 0; i < rituals.size(); i++)
					if(!(rituals.get(i) instanceof IHiddenRitual)){
						sb.append(rituals.get(i).getUnlocalizedName().substring(10));
						if(i != rituals.size() - 1)
							sb.append(" | ");
					}

				sender.sendMessage(new TextComponentString(sb.toString()));
				return;
			} else if(args[0].equals("file")){

				StringBuilder sb = new StringBuilder();

				sb.append("#######################################################");
				sb.append("\n#               AbyssalCraft Integration              #");
				sb.append("\n#-----------------------------------------------------#");
				sb.append("\n# Ritual list dump (with extra information)            #");
				sb.append("\n########################################################");
				sb.append("\n");
				sb.append("\n");
				sb.append("\n");
				sb.append("\nClass | Unlocalized Name | Name | Book Type | Dimension | PE | Sacrifice | Item Sacrifice | NBT Sensitive | Input | Output");
				sb.append("\n--------------------------------------------------------------------------------------------------------------------------");
				sb.append("\n");
				for(NecronomiconRitual r : RitualRegistry.instance().getRituals())
					sb.append(getRitualData(r));

				try{
					File f = new File(Minecraft.getMinecraft().mcDataDir, "acritual.txt");

					if(!f.exists())
						f.createNewFile();

					FileWriter fw = new FileWriter(f.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(sb.toString());
					bw.close();
					sender.sendMessage(new TextComponentString("Done!"));
					return;
				} catch(IOException e){
					e.printStackTrace();
				}
			} else {
				sender.sendMessage(new TextComponentString(getUsage(sender)));
				return;
			}
		} else {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
			return;
		}
	}

	private String getRitualData(NecronomiconRitual ritual){
		if(ritual instanceof IHiddenRitual) return "";

		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(ritual.getClass().getSimpleName());
		sb.append(" | ");
		sb.append(ritual.getUnlocalizedName().substring(10));
		sb.append(" | ");
		sb.append(ritual.getLocalizedName());
		sb.append(" | ");
		sb.append(ritual.getBookType());
		sb.append(" | ");
		sb.append(ritual.getDimension());
		sb.append(" | ");
		sb.append(ritual.getReqEnergy());
		sb.append(" | ");
		sb.append(ritual.requiresSacrifice());
		sb.append(" | ");
		sb.append(ritual.requiresItemSacrifice());
		sb.append(" | ");
		sb.append(ritual.isNBTSensitive());
		sb.append(" | ");
		sb.append(ritual.getSacrifice() != null ? APIUtils.convertToStack(ritual.getSacrifice()).toString() : "None");
		sb.append(" | ");
		sb.append(getOutput(ritual));
		sb.append("\n");
		return sb.toString();
	}

	private String getOutput(NecronomiconRitual ritual){
		if(ritual instanceof NecronomiconCreationRitual)
			return ((NecronomiconCreationRitual) ritual).getItem().toString();
		if(ritual instanceof NecronomiconEnchantmentRitual)
			return ((NecronomiconEnchantmentRitual) ritual).getEnchantment().enchantmentobj.getName();
		if(ritual instanceof NecronomiconPotionAoERitual)
			return ((NecronomiconPotionAoERitual) ritual).getPotionEffect().getName();
		if(ritual instanceof NecronomiconPotionRitual)
			return ((NecronomiconPotionRitual) ritual).getPotionEffect().getName();
		if(ritual instanceof NecronomiconSummonRitual)
			return ((NecronomiconSummonRitual) ritual).getEntity().getSimpleName();
		return "Unknown";
	}
}
