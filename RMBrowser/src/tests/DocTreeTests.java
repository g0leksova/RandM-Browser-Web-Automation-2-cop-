package tests;

import helpers.GeneralFunc;













import java.io.FileNotFoundException;



import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestCategories.Documents;
import pageObjects.LoginPage;
import pageObjects.Documents.AddChapterPage;
import pageObjects.Documents.DocumentsPage;
import pageObjects.HomeView.HomePage;
import utils.BaseTests;
import utils.Log;




public class DocTreeTests extends BaseTests{
	

	
	@Before
	public void setup() throws FileNotFoundException
	{
		
		LoginPage loginPage = new LoginPage(_driver);
		loginPage.loginAs(params.username, params.password, params.db, params.project);
		GeneralFunc.GoToHomeView(_driver);
		
		HomePage home = new HomePage(_driver);
		home.SelectDocumentsTab();
		home.CategoryTree.SelectCategory("RMDEMO");
		home.DocumentsTab.OpenDocumentByDoubleClick("ePhoto Requirements");
		
		
	}

	
	
	
	
	//TEST034076
	@Test
	public void ExpandAllCollapseAllTree() throws InterruptedException{
		Log.startTestCase("TEST034076 - Expand all/ Collapse All for the document");
		DocumentsPage docPage = new DocumentsPage(_driver);
		docPage.docTreeToolBar.ClickExpandAllChapters();
		docPage.docTree.isChaptersAreExpanded();
		docPage.docTreeToolBar.ClickCollapseAllChapters();
		docPage.docTree.isChaptersAreCollapsed();
	}
	//TEST023829
	@Test
	public void AddChapter() throws InterruptedException{
		
		Log.info("**************Test case TEST023829 started***************");
		DocumentsPage docPage = new DocumentsPage(_driver);
		docPage.docTree.selectDocumentRootChapter();
		
		docPage.docTreeToolBar.ClickNewChapter();
		
		AddChapterPage addChapterPage =  new AddChapterPage(_driver);
		
		addChapterPage.setTitle("Test chapter in root").setDescription("some descr").ok();
		docPage.docTree.isChapterExist("Test chapter in root7");
		docPage.docTree.isChapterShownInContentPane("Test chapter in root");
		
				
	}
	
	@Test
	public void AddChapterAsSubChapter() throws InterruptedException{
		DocumentsPage docPage = new DocumentsPage(_driver);
		docPage.docTree.selectChapterByName("Requirements");
		
		docPage.docTreeToolBar.ClickNewChapter();
		
		AddChapterPage addChapterPage =  new AddChapterPage(_driver);
		
		addChapterPage.setTitle("Test chapter as sub chapter").setDescription("some descr").setAddAsSubChapter().ok();
		docPage.docTree.isChapterExist("Test chapter as sub chapter");
		
				
	}
	
	@Test
	public void DeleteChapter() throws InterruptedException{
		
		DocumentsPage docPage = new DocumentsPage(_driver);
		docPage.docTreeToolBar.ClickExpandAllChapters();
		docPage.docTree.selectChapterByName("Test chapter as sub chapter");
		
		docPage.docTreeToolBar.ClickDeleteChapter();
		docPage.docTree.isChapterNotExist("Test chapter as sub chapter");
		
	}
	
	
	
	
	
	
}
