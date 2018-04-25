package utils;

import static org.junit.Assert.assertTrue;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import helpers.Types.CreateDocOptions;

public class WebElementsExt {

	public WebElementsExt()
	{
	
	}
	
	public static void setCheckBoxValue(WebElement checker, boolean value)
	{
		if (checker.isSelected())
		{
			if (!value)
			{
				checker.click();
			}
		}
		else 
		{
			if (value)
			{
				checker.click();
			}
		}
	}
	
	public static void SelectRadioButtonOnClassReport(List<WebElement> listOfChoise, String toSelect) throws Exception
	{
		String value;
		if (toSelect=="Script") 		{
			value = "1";
		} else if (toSelect=="Filter")		{
			value = "0";
		}
		else {
			throw new Exception ("Filter type ='"+toSelect+"' do not supported");
		}
		SelectRadioButton(listOfChoise, toSelect, value);
		
	}
	public static void SelectDocumentsOptions(List<WebElement> listOfChoise, CreateDocOptions option) throws Exception
	{
		String value;
		switch(option) {
			case Blank:
				value = "2";
				break;
			case ChaptersRequirements:
				value = "0";
				break;
			case ChaptersOnly:
				value = "1";
				break;
			case AsChild:
				value = "5";
				break;
			default:
				value = "0";
				break;
				
		}
		
		SelectRadioButton(listOfChoise, option, value);
		
	}

	
	
	public static void SelectRadioButton(List<WebElement> listOfChoise, String toSelect, String value ) throws Exception
	{
	
		for (WebElement typeToSelect : listOfChoise)
		{
			
			if(typeToSelect.getAttribute("value").equals(value))
			{
				if(!typeToSelect.isSelected())
					typeToSelect.click();
	      
				assertTrue(typeToSelect.isSelected());
				break;
			}
		}
	}
	public static void SelectRadioButton(List<WebElement> listOfChoise, CreateDocOptions toSelect, String value ) throws Exception
	{
	
		for (WebElement typeToSelect : listOfChoise)
		{
			
			if(typeToSelect.getAttribute("value").equals(value))
			{
				if(!typeToSelect.isSelected())
					typeToSelect.click();
	      
				assertTrue(typeToSelect.isSelected());
				break;
			}
		}
	}
	
	
	
	public static boolean CheckElementIsPresented(WebDriver driver, By byValue)
	{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		boolean exists;
		if (driver.findElements(byValue).size() != 0)
		{
			exists = true;
		}
		else
		{
			exists = false;
		}
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);   
		
		return exists; 
	}
	
	public static void actionsClick(WebDriver driver, WebElement element){
	    Actions builder = new Actions(driver);
	    builder.moveToElement(element).click(element).perform();
	}
	
	
	
	public static void selectCategory(WebDriver driver, WebElement categoryControl, String categoryName)
	{
		categoryControl.click();
		driver.findElement(By.xpath("//td[contains(text(), \""+categoryName+"\")]")).click();;
		//TBD
	}
}
