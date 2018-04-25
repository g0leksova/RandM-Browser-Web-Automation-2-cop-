package tests.Requirements;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import TestCategories.Smoke;
import pageObjects.*;
import pageObjects.Reports.ClassReport;
import pageObjects.Requirements.NewProductProjectRequirementPage;
import pageObjects.Requirements.NewRequirementPage;
import pageObjects.Requirements.RequirementPage;
import utils.BaseTests;
import utils.DriverExt;
import utils.Params;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class NewRequirementTestsPP extends BaseTests{
	Logger Log = Logger.getLogger(this.getClass().getPackage().getName());
	
/*	@BeforeClass
	public static void prerequisites()
	{
		//ListParams = Params.readXML("conf/RMDEMO/Collections.xml");
		//Map<String,String> TestParams = ListParams.get(0);
		
//		Params.readFile1(new File("conf/RMDEMO/Requirements.txt"));
	//	LoginPage loginPage = new LoginPage(_driver);
	//	loginPage.loginAs(params.username, params.password, params.db, params.project);
		//Log.log(Level.ALL, "test log message");
		LoginPage loginPage = new LoginPage(_driver);
		loginPage.loginAs("ephoto", "rtm", "RM", "RMDEMO");
	
		System.out.print("before class executed");
	}
*/
/*	@Before
	public void setup() throws IOException
	{
		//ListParams = Params.readXML("conf/RMDEMO/Collections.xml");
		//Map<String,String> TestParams = ListParams.get(0);
		
//		Params.readFile1(new File("conf/RMDEMO/Requirements.txt"));
	//	LoginPage loginPage = new LoginPage(_driver);
	//	loginPage.loginAs(params.username, params.password, params.db, params.project);
		Log.log(Level.ALL, "test log message");
	}
	*/
	
	@Category(Smoke.class)
	@Test
	@FileParameters("conf/RMDEMO/RequirementsProductProject.csv")
	public void CreateProductA(String className, String title, String descr, String attrName, String attrValue) throws Exception
	{
		NewRequirementPage req = new NewRequirementPage(_driver);
		NewProductProjectRequirementPage reqPr = new NewProductProjectRequirementPage(_driver);
		
		MainToolBar tlBar = new MainToolBar(_driver);
		
		tlBar.CallNewRequirement();
		req.selectClass(className);
		//req.selectCategory(category);
		//req.setTitle("Product A");
		req.setAttributeValue("NAME", title);
		req.setProdDescription(descr);
		req.setAttributeValue(attrName, attrValue);
		if (title.contains("Project"))
		{
			reqPr.assignAllProducts();
		}
		req.save();
		
		DriverExt.checkPageLoaded(_driver);
		RequirementPage.checkClassIsShownInTheTitle();
	}
	
	
	
	
	
}