package utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class DriverExt {

	public static void SwitchNextWnd(WebDriver driver)
	{
		String mainWindow = driver.getWindowHandle();
		Set<String> handlers = driver.getWindowHandles();
		for (String handler : handlers)
		{
			System.out.println(handler);
			if (!handler.equals(mainWindow)){
				driver.switchTo().window(handler);
				System.out.println(driver.getTitle());
				break;
			}
		}
	}
	
	public static void SwitchWndById(WebDriver driver, int wndId)
	{
		String[] allwndHandles = GetWndHandles(driver);
	     driver.switchTo().window(allwndHandles[wndId]);
	}
	
	public static void SwitchToLastWnd(WebDriver driver)
	{
		String[] allwndHandles = GetWndHandles(driver);
		int id = allwndHandles.length-1;
		System.out.println("id = " +id);
		
		driver.switchTo().window(allwndHandles[id]);
		System.out.println(driver.getTitle());
		
	}
	public static void waitForNumberOfWindowsToEqual(final int numberOfWindows, WebDriver driver) throws InterruptedException {
	    new WebDriverWait(driver, 40) {
	    }.until(new ExpectedCondition<Boolean>() {
	        @Override
	        public Boolean apply(WebDriver driver) {                        
	            return (driver.getWindowHandles().size() == numberOfWindows);
	        }
	    });
	    Thread.sleep(2000);
	}
	
	public static void BackToRootWindow(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		DriverExt.SwitchToLastWnd(driver);
		wait.equals(driver.getTitle().contains("Dimensions RM"));
	}
	
	
	public static int HandlesCount(WebDriver driver)
	{
		int hadles = GetWndHandles(driver).length;
	        return hadles;
		
		
	}
	
	private static String[] GetWndHandles(WebDriver driver)
	{
		Set<String> handles = driver.getWindowHandles();
		 String[] individualHandle = new String[handles.size()];
	        Iterator<String> it = handles.iterator();
	        int i =0;
	        while(it.hasNext())
	        {
	            individualHandle[i] = (String) it.next();
	            i++;
	        }
	        return individualHandle;
	}
	
	public void checkAlert(WebDriver driver) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, 2);
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    } catch (Exception e) {
	        
	    }
	}
	
	public static void checkPageLoaded(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until( new Predicate<WebDriver>() {
            public boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        }
    );}
}