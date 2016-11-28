package com.shinoow.acintegration.integrations.minetweaker;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.necronomicon.CraftingStack;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.Chapter;
import com.shinoow.abyssalcraft.api.necronomicon.NecroData.Page;

@ZenClass("mods.abyssalcraft.necronomicon.internal")
public class InternalNecroData {

	@ZenMethod
	public static void addChapter(String chapteridentifier, String chaptertitle, String necrodataidentifier){
		Chapter chapter = new Chapter(chapteridentifier, I18n.translateToLocal(chaptertitle));
		MineTweakerAPI.apply(new AddChapter(chapter, necrodataidentifier));
	}

	private static class AddChapter implements IUndoableAction
	{

		private final Chapter chapter;
		private final String identifier;
		private Chapter oldChapter;

		public AddChapter(Chapter chapter, String identifier){

			this.chapter = chapter;
			this.identifier = identifier;
		}

		@Override
		public void apply() {

			for(Chapter chap : AbyssalCraftAPI.getInternalNDHandler().getInternalNecroData(identifier).getChapters())
				if(chap.getIdentifier().equals(chapter.getIdentifier())){
					oldChapter = chap;
					break;
				}

			AbyssalCraftAPI.getInternalNDHandler().addChapter(chapter, identifier);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Chapter " + chapter.getIdentifier() + " to the NecroData " + identifier;
		}

		@Override
		public String describeUndo() {

			return "Removing Necronomicon Chapter " + chapter.getIdentifier() + " from the NecroData " + identifier;
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(oldChapter != null)
				AbyssalCraftAPI.getInternalNDHandler().addChapter(oldChapter, identifier);
			else AbyssalCraftAPI.getInternalNDHandler().removeChapter(identifier, chapter.getIdentifier());
		}
	}

	@ZenMethod
	public static void removeChapter(String chapteridentifier, String necrodataidentifier){
		MineTweakerAPI.apply(new RemoveChapter(chapteridentifier, necrodataidentifier));
	}

	private static class RemoveChapter implements IUndoableAction
	{
		Chapter removedChapter;
		private final String chapteridentifier;
		private final String necrodataidentifier;

		public RemoveChapter(String chapter, String necrodata){
			chapteridentifier = chapter;
			necrodataidentifier = necrodata;
		}

		@Override
		public void apply() {

			for(Chapter chap : AbyssalCraftAPI.getInternalNDHandler().getInternalNecroData(necrodataidentifier).getChapters())
				if(chap.getIdentifier().equals(chapteridentifier)){
					removedChapter = chap;
					AbyssalCraftAPI.getInternalNDHandler().removeChapter(necrodataidentifier, chapteridentifier);
					return;
				}
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Chapter " + chapteridentifier + " from the NecroData " + necrodataidentifier;
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Necronomicon Chapter " + chapteridentifier + " to the NecroData " + necrodataidentifier;
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removedChapter != null)
				AbyssalCraftAPI.getInternalNDHandler().addChapter(removedChapter, necrodataidentifier);
		}
	}

	@ZenMethod
	public static void addNormalPage(int pageNum, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, I18n.translateToLocal(pagetext));
		MineTweakerAPI.apply(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addItemPage(int pageNum, IItemStack stack, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, ACMT.toStack(stack), I18n.translateToLocal(pagetext));
		MineTweakerAPI.apply(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addImagePage(int pageNum, String resourcepath, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, new ResourceLocation(resourcepath), I18n.translateToLocal(pagetext));
		MineTweakerAPI.apply(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addCraftingPage(int pageNum, IIngredient thing, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, new CraftingStack(ACMT.toObject(thing)), I18n.translateToLocal(pagetext));
		MineTweakerAPI.apply(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addCraftingPage(int pageNum, IIngredient thing, IIngredient[] stuff, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, new CraftingStack(ACMT.toObject(thing), ACMT.toObjects(stuff)), I18n.translateToLocal(pagetext));
		MineTweakerAPI.apply(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	@ZenMethod
	public static void addURLPage(int pageNum, String url, String pagetext, String chapteridentifier, String necrodataidentifier){
		Page page = new Page(pageNum, url, I18n.translateToLocal(pagetext));
		MineTweakerAPI.apply(new AddPage(page, chapteridentifier, necrodataidentifier));
	}

	private static class AddPage implements IUndoableAction{

		private final Page page;
		private final String chapteridentifier;
		private final String necrodataidentifier;
		private Page oldPage;

		public AddPage(Page page, String chapteridentifier, String necrodataidentifier){
			this.page = page;
			this.chapteridentifier = chapteridentifier;
			this.necrodataidentifier = necrodataidentifier;
		}

		@Override
		public void apply() {

			for(Chapter chap : AbyssalCraftAPI.getInternalNDHandler().getInternalNecroData(necrodataidentifier).getChapters())
				if(chap.getIdentifier().equals(chapteridentifier)){
					oldPage = chap.getPage(page.getPageNumber());
					break;
				}

			AbyssalCraftAPI.getInternalNDHandler().addPage(page, necrodataidentifier, chapteridentifier);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Page to Necronomicon Chapter " + chapteridentifier + " in the NecroData " + necrodataidentifier;
		}

		@Override
		public String describeUndo() {

			return "Removing Page from Necronomicon Chapter " + chapteridentifier + " in the NecroData " + necrodataidentifier;
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(oldPage != null)
				AbyssalCraftAPI.getInternalNDHandler().addPage(oldPage, necrodataidentifier, chapteridentifier);
			else AbyssalCraftAPI.getInternalNDHandler().removePage(page.getPageNumber(), necrodataidentifier, chapteridentifier);
		}
	}

	@ZenMethod
	public static void removePage(int pageNum, String chapteridentifier, String necrodataidentifier){
		MineTweakerAPI.apply(new RemovePage(pageNum, chapteridentifier, necrodataidentifier));
	}

	private static class RemovePage implements IUndoableAction{

		Page removedPage;
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

			for(Chapter chap : AbyssalCraftAPI.getInternalNDHandler().getInternalNecroData(necrodataidentifier).getChapters())
				if(chap.getIdentifier().equals(chapteridentifier)){
					removedPage = chap.getPage(pageNum);
					if(removedPage != null)
						chap.removePage(pageNum);
					return;
				}
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing Page from Necronomicon Chapter " + chapteridentifier + " in the NecroData " + necrodataidentifier;
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Page to Necronomicon Chapter " + chapteridentifier + " in the NecroData " + necrodataidentifier;
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removedPage != null)
				AbyssalCraftAPI.getInternalNDHandler().addPage(removedPage, necrodataidentifier, chapteridentifier);
		}
	}
}