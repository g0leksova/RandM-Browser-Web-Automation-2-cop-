package tests;

import helpers.GeneralFunc;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestCategories.Documents;
import pageObjects.LoginPage;
import pageObjects.Documents.DocumentsPage;
import pageObjects.HomeView.HomePage;
import utils.BaseTests;
import utils.Log;

public class OpenDocument extends BaseTests{
				

		
		@Before
		public void setup() throws FileNotFoundException
		{
			
			LoginPage loginPage = new LoginPage(_driver);
			loginPage.loginAs(params.username, params.password, params.db, params.project);
			
		}
	
		@Category(Documents.class)
		@Test
		public void OpenDocument() throws Exception
		{
			Log.startTestCase("TEST016309 - Open document in RM Browser");
			String docName = "ePhoto Requirements";
			
			Log.info("document name " + docName);
			HomePage home = new HomePage(_driver);
			
			GeneralFunc.GoToHomeView(_driver);
			home.CategoryTree.SelectCategory("RMDEMO");
			home.SelectDocumentsTab();
			
			home.DocumentsTab.OpenDocumentByAction(docName);
			
			DocumentsPage docPage = new DocumentsPage(_driver);
			docPage.isPresentedDocName(docName);
			_driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Log.info("Test passed!");
		}
}

