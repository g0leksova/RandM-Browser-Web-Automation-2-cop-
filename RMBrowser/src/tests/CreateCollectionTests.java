package tests;

import static org.junit.Assert.*;
import helpers.ConfirmationHelper;
import helpers.CreateCollectionHelper;
import helpers.ManageContainersHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;


import org.openqa.selenium.remote.RemoteWebDriver;

import pageObjects.*;
import pageObjects.Containers.ManageContainersPage;
import utils.*;

public class CreateCollectionTests  extends BaseTests{
	
	private String collectionName;
	private String description;
	private List<Map<String,String>> ListParams = new ArrayList<Map<String,String>>();
	Logger Log = Logger.getLogger(this.getClass().getPackage().getName());
	

	
	@Before
	public void setup() throws FileNotFoundException
	{
		ListParams = Params.readXML("conf/RMDEMO/Collections.xml");
		Map<String,String> TestParams = ListParams.get(0);
		this.collectionName = TestParams.get("collectionName");
		this.description = TestParams.get("description");
		
		LoginPage loginPage = new LoginPage(_driver);
		loginPage.loginAs(params.username, params.password, params.db, params.project);
		Log.log(Level.ALL, "test log message");
	}
	
	
	@Test
	public void CreateCollection() throws Exception
	{
		CreateCollectionHelper createColl = new CreateCollectionHelper(_driver);
		MainToolBar toolbar = new MainToolBar(_driver);
		
		toolbar.CallManageContainersWnd();
		
		ManageContainersPage manageCont = new ManageContainersPage(_driver);
		ManageContainersHelper manageH = new ManageContainersHelper(_driver);
		
		manageCont.CallNewCollectionWnd();
		createColl.CreateCollection(collectionName, description);
	
		manageH.BackToManageContainers();
	
		assertTrue(manageH.IsPresentedInManageContWnd(collectionName));   
	}
	
	
}
