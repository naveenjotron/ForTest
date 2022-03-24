package test.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {
	
	WebDriver driver;
	String driverPath1;
	String driverPath2;
	public static Properties prop = null;
	public void testbase() {
	    try {
        	//Below line creates an object of Properties called 'prop'
            prop = new Properties();
          //Below line creates an object of FileInputStream called 'ip'. 
          //Give the path of the properties file which you have created
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources"
                    + "/config.properties");
            //Below line of code will load the property file
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@Parameters("browser")
	@BeforeTest
	public void onSetup() {
	    testbase(); // for loading the configurations
	   // String browserName = prop.getProperty("browser");
	    String browserName = prop.getProperty("browser");
	    driverPath1=System.getProperty("user.dir")+"\\CommonFiles\\chromedriver.exe";
	    driverPath2=System.getProperty("user.dir")+"\\CommonFiles\\geckodriver.exe";

	    if (browserName.equals("chrome")) {
	    	System.setProperty("webdriver.chrome.driver", driverPath1);
	        driver = new ChromeDriver();

	    }
	    else if (browserName.equals("firefox")) {
	    	//geckodriver
	    	System.setProperty("webdriver.gecko.driver", driverPath2);
	        driver = new FirefoxDriver();
	    }
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(prop.getProperty("url"));
	    driver.manage().window().maximize();   
	}
	
	@AfterTest
	public void quitBrowser() throws IOException, InterruptedException {
		Thread.sleep(5000);
	    driver.quit();
	}
}
