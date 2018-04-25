package tests.Requirements;





import java.util.logging.Logger;



import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;




import TestCategories.Smoke;
import pageObjects.*;
import pageObjects.Requirements.NewRequirementPage;
import pageObjects.Requirements.RequirementPage;
import utils.BaseTests;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class NewRequirementTests extends BaseTests{
	Logger Log = Logger.getLogger(this.getClass().getPackage().getName());
	


	@Category(Smoke.class)
	@FileParameters("conf/RMDEMO/MRKTRequirementsinProductProject.csv")
	@Test
	public void CreateMRKTRequirement(String className, String category, String title, String descr, String attrListName,
									String attrListValue, String attrListName2, String attrListValue2) throws Exception
	{
		NewRequirementPage req = new NewRequirementPage(_driver);
		MainToolBar tlBar = new MainToolBar(_driver);
		
		tlBar.CallNewRequirement();
		req.selectClass(className);
		req.selectCategory(category);
		req.setTitle(title);
		req.setDescription(descr);
		req.setListAttributeValue(attrListName, attrListValue);
		req.setListAttributeValue(attrListName2, attrListValue2);
		
		//req.selectUserValue("Manager", "EPHOTO");
		req.save();
		RequirementPage.checkClassIsShownInTheTitle();
	
	}
	@Category(Smoke.class)
	@FileParameters("conf/RMDEMO/PRODRequirementsinProductProject.csv")
	@Test
	public void CreatePRODRequirement(String className, String category, String title, String descr, String attrListName,
									String attrListValue, String attrListName2, String attrListValue2, String userAttrName, String userAttrValue) throws Exception
	{
		NewRequirementPage req = new NewRequirementPage(_driver);
		MainToolBar tlBar = new MainToolBar(_driver);
		
		tlBar.CallNewRequirement();
		req.selectClass(className);
		req.selectCategory(category);
		req.setTitle(title);
		req.setDescription(descr);
		req.setListAttributeValue(attrListName, attrListValue);
		req.setListAttributeValue(attrListName2, attrListValue2);
		
		req.selectUserValue(userAttrName, userAttrValue);
		req.save();
		
		RequirementPage.checkClassIsShownInTheTitle();
	
	}
	
	
	
	
	
	
}