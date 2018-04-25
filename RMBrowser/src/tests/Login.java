package tests;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestCategories.Smoke;
import pageObjects.*;





public class Login extends utils.BaseTests{


	
	@Category(Smoke.class)
	@Test
	public void testLogin() throws Exception
	{
		LoginPage loginPage = new LoginPage(_driver);
		loginPage.loginAs(params.username, params.password, params.db, params.project);
		
		if (ExpectedConditions.frameToBeAvailableAndSwitchToIt("topPanel")!= null){
		_driver.switchTo().frame("topPanel");
		}
		WebElement welcomeLink = _driver.findElement(By.xpath(".//span[contains(., 'Welcome')]"));
		assertTrue(welcomeLink.isDisplayed());

	}
	/*
	@Test
	public void testFailedLogin() throws NoSuchElementException
	{
		LoginPage loginPage = new LoginPage(_driver);
		loginPage.loginAs(params.username, params.password, params.db, params.project);
		WebElement welcomeLink = _driver.findElement(By.xpath("//a[contains(., 'Welcome1')]"));
		assertTrue(welcomeLink.isDisplayed());

	}
	*/
	
}
