package ChaitraAcademy.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ChaitraAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {

	public 	WebDriver driver;
	public LandingPage landingpages;
	public WebDriver initilizeDriver() throws IOException
	
	{
		// properties class
		Properties prop = new Properties(); // just creating class for property object
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\chaitraAcandemy\\resources\\GlobalData.properties"); // right click on global properties and copy the pATH
		prop.load(fis); // fis is the object, it has file stream which is defined above
		String browsername = prop.getProperty("browser");

		if (browsername.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
ChromeOptions options = new ChromeOptions();
			
			options.addArguments("--ignore-certificate-errors");
	        options.addArguments("--allow-insecure-localhost");
	        
	      //  WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver(options); 
		
		 	
		
		}
		
		else if (browsername.equalsIgnoreCase("Firefox"))
		{
			//firefox
		}
		
		else if (browsername.equalsIgnoreCase("edge"))
		{
			//edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	
	{
		driver = initilizeDriver();
		 landingpages = new LandingPage(driver); //here we are sending driver as an argument to the class, this we can catch in the constructor which is Landingpage class
		landingpages.goTo();
		return landingpages;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
}
