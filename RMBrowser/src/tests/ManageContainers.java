package tests;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import TestCategories.Smoke;
import pageObjects.*;


public class ManageContainers extends utils.BaseTests{
	
	private String baseUrl;
	
	@Before
	public void setup() throws FileNotFoundException
	{
		baseUrl = params.serverUrl; 
		_driver.get(baseUrl);
		_driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		LoginPage loginPage = new LoginPage(_driver);
		loginPage.loginAs(params.username, params.password, params.db, params.project);
		
	}
	
	@Category(Smoke.class)
	@Test
	public void testOpenManageCategory() throws Exception
	{
		MainToolBar toolbar = new MainToolBar(_driver);
		toolbar.ClickOnContainersBtn();
		
	}
	
}
