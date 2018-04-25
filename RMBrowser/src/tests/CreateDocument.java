package tests;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestCategories.Documents;
import pageObjects.LoginPage;
import pageObjects.MainToolBar;
import pageObjects.Documents.DocumentsPage;
import pageObjects.Documents.NewDocumentPage;
import pageObjects.Documents.pageElements.DocumentTree;
import utils.BaseTests;
import utils.Log;
import helpers.Types.CreateDocOptions;


public class CreateDocument extends BaseTests{
		

		
		@Before
		public void setup() throws FileNotFoundException
		{
			
			LoginPage loginPage = new LoginPage(_driver);
			loginPage.loginAs(params.username, params.password, params.db, params.project);
		}
		
		//Create document with ECP off, Update to tip off, publishTitle off, Master off Based on Chapters and Requirements
		@Category(Documents.class)
		//@Test
		public void CreateSampleDocument() throws Exception
		{
			Log.startTestCase(" Test case:  Create document based on chapters and requirements");
			String docName = "Test Document2";
			String description = "Test Description";
			Boolean isECP = false;
			Boolean isPublish = false;
			Boolean isUpdateToTip = false;
			CreateDocOptions option = CreateDocOptions.ChaptersRequirements;
			String baseDocumentName = "ePhoto Requirements";
			
			
			NewDocumentPage doc = new NewDocumentPage(_driver);
			MainToolBar tlBar = new MainToolBar(_driver);
			
			tlBar.CallNewDocumentPage();
			doc.SetDocumentName(docName)
				.SetDocumentDescription(description)
				.SetIsECP(isECP)
				.SetIsPublish(isPublish)
				.SetIsUpdateToTip(isUpdateToTip)
				.SetCreateOptions(option)
				.SelectBaseDocument(baseDocumentName)
				.SaveDocument();
			DocumentsPage docPage = new DocumentsPage(_driver);
			docPage.isPresentedDocName(docName);
			
		}

		//Create document ECP controlled, Update to tip on, publishTitle on, Master off, Based on Chapters and Requirements
			@Category(Documents.class)
		//	@Test
			public void CreateDocumentECPUpdateToTip() throws Exception
			{
				Log.startTestCase(" Test case:  Create ECP controlled document");
				
				String docName = "ECP Document3";
				String description = "eew";
				Boolean isECP = true;
				Boolean isPublish = true;
				Boolean isUpdateToTip = true;
				CreateDocOptions option = CreateDocOptions.ChaptersRequirements;
				String baseDocumentName = "ePhoto Requirements";
					
					
				NewDocumentPage doc = new NewDocumentPage(_driver);
				MainToolBar tlBar = new MainToolBar(_driver);
				tlBar.CallNewDocumentPage();
				doc.SetDocumentName(docName)
					.SetDocumentDescription(description)
					.SetIsECP(isECP)
					.SetIsPublish(isPublish)
					.SetIsUpdateToTip(isUpdateToTip)
					.SetCreateOptions(option)
					.SelectBaseDocument(baseDocumentName)
					.SaveDocument();
				
				DocumentsPage docPage = new DocumentsPage(_driver);
				docPage.isPresentedDocName(docName);
					
			}	
			
			
			//TEST023163 Create Doc with 'Blank' Create Option
			@Category(Documents.class)
			//@Test
			public void CreateBlankDocument() throws Exception
			{
				Log.startTestCase("TEST023163 Create Doc with 'Blank' Create Option");
				String docName = "Blank document";
				String description = "document created with BLANK option";
				String foreword ="foreword was added to these document";
				CreateDocOptions option = CreateDocOptions.Blank;
					
					
				NewDocumentPage doc = new NewDocumentPage(_driver);
				MainToolBar tlBar = new MainToolBar(_driver);
				tlBar.CallNewDocumentPage();
				doc.SetDocumentName(docName)
					.SetDocumentDescription(description)
					.SetCreateOptions(option)
					.setForeword(foreword)
					.SaveDocument();
				
				
				DocumentsPage docPage = new DocumentsPage(_driver);
				docPage.isPresentedDocName(docName)
		               .isDocForewordShown(foreword);
				
					
			}	
			
			//TEST023167 Create Doc with 'Chapters Only' Create Option
			@Category(Documents.class)
			@Test
			public void CreateChaptersOnlyDocument() throws Exception
			{
				Log.startTestCase("TEST023167 Create Doc with 'Chapters Only' Create Option");
				String docName = "Chapters only document";
				String description = "document created with chapters only";
				CreateDocOptions option = CreateDocOptions.ChaptersOnly;
				String baseDocumentName = "ePhoto Requirements";	
					
				NewDocumentPage doc = new NewDocumentPage(_driver);
				MainToolBar tlBar = new MainToolBar(_driver);
				tlBar.CallNewDocumentPage();
				doc.SetDocumentName(docName)
					.SetDocumentDescription(description)
					.SetCreateOptions(option)
					.SelectBaseDocument(baseDocumentName)
					.isForewordDisabled()
					.SaveDocument();
				
				
				DocumentsPage docPage = new DocumentsPage(_driver);
				docPage.isPresentedDocName(docName);
		        DocumentTree docTree = new DocumentTree(_driver);  
		        docTree.isDocumentHaveNoRequirements();
					
			}	
}
