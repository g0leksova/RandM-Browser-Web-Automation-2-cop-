package utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;







import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



import pageObjects.LoginPage;

public abstract class BaseTests {
	protected static final String SCREEN_SHOTS_RESULTS_PATH = "c:\\temp\\SCREENSHOTS\\";;


	protected static WebDriver _driver;
	
	
	public static Params params = new Params();

    private static String rmLoginPage;
         

    private static String driverName =
                System.getProperty(
                        "test.driver",
                        "org.openqa.selenium.ie.InternetExplorerDriver");
    
    public enum Browsers {CHROME, FIREFOX, IE }
  
    @Before
    public static void setUp() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	
    	System.out.print("before class is running on the base class");
    	rmLoginPage = params.serverUrl;
    	Browsers browser = Browsers.FIREFOX;
        	
         switch (browser) {
         case CHROME:
        	 System.setProperty("webdriver.chrome.driver", "c:\\RM_AutoTests\\drivers\\chromedriver.exe");
             _driver = new ChromeDriver();
             break;

         case FIREFOX:             
        	 System.setProperty("webdriver.firefox.marionette", "c:\\RM_AutoTests\\drivers\\geckodriver.exe");
        	 System.out.println("webdriver.firefox.marionette"+ "c:\\RM_AutoTests\\drivers\\geckodriver.exe");
        	 _driver = new FirefoxDriver();
             break;

         case IE:
        	 System.setProperty("webdriver.ie.driver", "c:\\RM_AutoTests\\drivers\\IEDriverServer.exe");
             _driver = new InternetExplorerDriver();
             break;

         default:
             _driver = new FirefoxDriver();
             break;

         }                
    	_driver.manage().window().maximize();
		_driver.get(rmLoginPage);
		_driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		LoginPage loginPage = new LoginPage(_driver);
		loginPage.loginAs(params.username, params.password, params.db, params.project);

   
    }
    
    public static WebDriver getDriver(){
    	
    	return _driver;
    }

   
    
    @Rule
    public TestRule testWatcher = new TestWatcher() {
    
    	    private long startTime;
    	    private long endTime;
    	@Override
        public void starting(Description desc) {
            Log.info("Launching browser...");
        }

        
        @Override
        public void failed(Throwable e, Description d) {
        	String methodName=d.getMethodName().toString().trim();
        	Log.info(d.getMethodName()+"Failed!"+" "+e.getMessage());
        	
        	Log.info("Creating screenshot...");
            File scrFile = ((TakesScreenshot) BaseTests.getDriver()).getScreenshotAs(OutputType.FILE);
             File outputFile = new File(SCREEN_SHOTS_RESULTS_PATH, methodName+".png");
            Log.info(methodName + " screenshot created.");
            try {
                       	FileUtils.copyFile(scrFile, outputFile);
            } catch (IOException ioe) {
                Log.error("Error copying screenshot after exception.", ioe);
            }
        }
        
        @Override        
          protected void finished(Description desc) {        	
        	  Log.info("Suite completed!");
        	   _driver.close();
               Log.info("driver closed");
               
               _driver.quit();
               Log.info("driver quited");
        }
     
      
        
    //    String methodName=result.getName().toString().trim();
    ///	takeScreenShot(methodName,BaseTests.getDriver(),result.getStartMillis());
    };
    public void takeScreenShot(String methodName, WebDriver driver) {
    	//get the driver
    	//driver=TestBase.getDriver();
    	
        
    	/*File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with test method name 
            try {
				FileUtils.copyFile(scrFile, new File(filePath+methodName+time+".png"));
				Reporter.log("<br> <img src=c:\\temp\\screenshots\\"+methodName+time+".png/> <br>");
				Reporter.log("\n***Placed screen shot in "+filePath+methodName+time+".png ***");
			//	System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}*/
    }
    
    
}
