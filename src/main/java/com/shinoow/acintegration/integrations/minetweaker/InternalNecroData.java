package com.shinoow.acintegration.integrations.minetweaker;

import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.necronomicon.*;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.Chapter;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.Page;

import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;

@ZenClass("mods.abyssalcraft.necronomicon.internal")
public class InternalNecroData {

	@ZenMethod
	public static void addChapter(String chapteridentifier, String chaptertitle, String necrodataidentifier){
		Chapter chapter = new Chapter(chapteridentifier, chaptertitle, 0);
		ACMTMisc.ADDITIONS.add(new AddChapter(chapter, necrodataidentifier));
	}

	private static class AddChapter implements IAction
	{

		private final Chapter chapter;
		private final String identifier;

		public AddChapter(Chapter chapter, String identifier){

			this.chapter = chapter;
			this.identifier = identifier;
		}

		@Override
		public void apply() {

			AbyssalCraftAPI.getInternalNDHandler().addChapter(chapter, identifier);
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Chapter " + chapter.getIdentifier() + " to the NecroData " + identifier;
		}
	}

	@ZenMethod
	public static void removeChapter(String chapteridentifier, String necrodataidentifier){
		ACMTMisc.REMOVALS.add(new RemoveChapter(chapteridentifier, necrodataidentifier));
	}

	private static class RemoveChapter implements IAction
	{
		private final String chapteridentifier;
		private final String necrodataidentifier;

		public RemoveChapter(String chapter, String necrodata){
			chapteridentifier = chapter;
			necrodataidentifier = necrodata;
		}

		@Override
		public void apply() {

			AbyssalCraftAPI.getInternalNDHandler().removeChapter(necrodataidentifier, chapteridentifier);
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Chapter " + chapteridentifier + " from the NecroData " + necrodataidentifier;
		}
	}

	@ZenMethod
	public static void addNormalPage(int pageNum, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, "title", 0, pagetext);
		ACMTMisc.ADDITIONS.add(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addItemPage(int pageNum, IItemStack stack, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, "title", 0, ACMT.toStack(stack), pagetext);
		ACMTMisc.ADDITIONS.add(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addImagePage(int pageNum, String resourcepath, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, "title", 0, new ResourceLocation(resourcepath), pagetext);
		ACMTMisc.ADDITIONS.add(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addCraftingPage(int pageNum, IIngredient thing, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, "title", 0, new CraftingStack(ACMT.toObject(thing)), pagetext);
		ACMTMisc.ADDITIONS.add(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addCraftingPage(int pageNum, IIngredient thing, IIngredient[] stuff, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, "title", 0, new CraftingStack(ACMT.toObject(thing), ACMT.toObjects(stuff)), pagetext);
		ACMTMisc.ADDITIONS.add(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addURLPage(int pageNum, String url, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, "title", 0, url, pagetext);
		ACMTMisc.ADDITIONS.add(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	private static class AddPage implements IAction{

		private final Page page;
		private final String chapteridentifier;
		private final String necrodataidentifier;

		public AddPage(Page page, String chapteridentifier, String necrodataidentifier){
			this.page = page;
			this.chapteridentifier = chapteridentifier;
			this.necrodataidentifier = necrodataidentifier;
		}

		@Override
		public void apply() {

			AbyssalCraftAPI.getInternalNDHandler().addPage(page, necrodataidentifier, chapteridentifier);
		}

		@Override
		public String describe() {

			return "Adding Page to Necronomicon Chapter " + chapteridentifier + " in the NecroData " + necrodataidentifier;
		}
	}

	@ZenMethod
	public static void removePage(int pageNum, String chapteridentifier, String necrodataidentifier){
		ACMTMisc.REMOVALS.add(new RemovePage(pageNum, chapteridentifier, necrodataidentifier));
	}

	private static class RemovePage implements IAction{

		private final int pageNum;
		private final String chapteridentifier;
		private final String necrodataidentifier;

		public RemovePage(int pageNum, String chapteridentifier, String necrodataidentifier){
			this.pageNum = pageNum;
			this.chapteridentifier = chapteridentifier;
			this.necrodataidentifier = necrodataidentifier;
		}

		@Override
		public void apply() {

			for(INecroData d : AbyssalCraftAPI.getInternalNDHandler().getInternalNecroData(necrodataidentifier).getContainedData())
				if(d instanceof Chapter && d.getIdentifier().equals(chapteridentifier)){
					((Chapter)d).removePage(pageNum);
					return;
				}
		}

		@Override
		public String describe() {

			return "Removing Page from Necronomicon Chapter " + chapteridentifier + " in the NecroData " + necrodataidentifier;
		}
	}
}