package tests;

import static org.junit.Assert.assertTrue;
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
import org.junit.experimental.categories.Category;

import TestCategories.Smoke;
import pageObjects.*;
import pageObjects.Reports.ClassReport;
import utils.BaseTests;
import utils.DriverExt;
import utils.Params;
import utils.WebElementsExt;

public class CreateClassReport extends BaseTests{
	//private List<Map<String,String>> ListParams = new ArrayList<Map<String,String>>();
	Logger Log = Logger.getLogger(this.getClass().getPackage().getName());
	

	
	@Before
	public void setup() throws FileNotFoundException
	{
		//ListParams = Params.readXML("conf/RMDEMO/Collections.xml");
		//Map<String,String> TestParams = ListParams.get(0);
		
		LoginPage loginPage = new LoginPage(_driver);
		loginPage.loginAs(params.username, params.password, params.db, params.project);
		Log.log(Level.ALL, "test log message");
	}
	
	@Category(Smoke.class)
	@Test
	public void CreateClassReportInRootCategory() throws Exception
	{
		String className = "Marketing_Requirements";
		String reportName = "CreateClassReportInRootCategory";
		String description = "Description";
		String reportType = "Script";
		String category = "RMDEMO";
		
		ClassReport rep = new ClassReport(_driver);
		MainToolBar tlBar = new MainToolBar(_driver);
		
		tlBar.CallClassReportWindow();
		rep.SetGeneralSettings(className, reportName,description,reportType, category);
		rep.SaveAndRun();
		//DriverExt.BackToRootWindow(_driver);
		rep.isPresentedReportName(reportName);
	}
	@Category(Smoke.class)
	@Test
	public void CreateScriptClassReportInSubCategory() throws Exception
	{
		String className = "Marketing_Requirements";
		String reportName = "CreateScriptClassReportInSubCategory";
		String description = "Description";
		String reportType = "Script";
		String category = "Data";

		ClassReport rep = new ClassReport(_driver);
		MainToolBar tlBar = new MainToolBar(_driver);
		tlBar.CallClassReportWindow();
		rep.SetGeneralSettings(className, reportName, description, reportType, category);
		
		rep.Switchtab("Display Options");
		rep.Options.SetAttributeToDisplay("<Collections>");
		rep.Options.SetAttributeToDisplay("<Documents>");
		rep.SaveAndRun();
		//DriverExt.BackToRootWindow(_driver);
		//rep.BackToRootWindow();
		rep.isPresentedReportName(reportName);
	}
	@Category(Smoke.class)
	@Test
	public void CreateFilterClassReportInSubCategory() throws Exception
	{
		
		String className = "Marketing_Requirements";
		String reportName = "CreateFilterClassReportInSubCategory";
		String description = "Description";
		String reportType = "Filter";
		String category = "Availability";
		
		
		ClassReport rep = new ClassReport(_driver);
		MainToolBar tlBar = new MainToolBar(_driver);
		tlBar.CallClassReportWindow();
		
		rep.SetGeneralSettings(className, reportName, description, reportType, category);
		rep.Switchtab("Display Options");
		rep.Options.SetAttributeToDisplay("<Collections>");
		rep.Options.SetAttributeToDisplay("<Documents>");
		rep.SaveAndRun();
		
		//rep.BackToRootWindow();
		rep.isPresentedReportName(reportName);
	}
	@Category(Smoke.class)
	@Test
	public void CreateFilterClassReportInRootCategory() throws Exception
	{
		String className = "Marketing_Requirements";
		String reportName = "CreateFilterClassReportInRootCategory";
		String description = "Description";
		String reportType = "Filter";
		String category = "RMDEMO";

		ClassReport rep = new ClassReport(_driver);
		MainToolBar tlBar = new MainToolBar(_driver);
		tlBar.CallClassReportWindow();
		rep.SetGeneralSettings(className, reportName, description, reportType, category);
		rep.Switchtab("Display Options");
		rep.Options.SetAttributeToDisplay("Object ID");
		rep.Options.SetAttributeToDisplay("Current Status");
		rep.SaveAndRun();
		//DriverExt.BackToRootWindow(_driver);
		rep.isPresentedReportName(reportName);
	}


}
