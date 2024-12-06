package ChaitraAcademy.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ChaitraAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestsbackup {

	public 	WebDriver driver;
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
	
	public LandingPage launchApplication() throws IOException
	
	{
		driver = initilizeDriver();
		LandingPage landingpages = new LandingPage(driver); //here we are sending driver as an argument to the class, this we can catch in the constructor which is Landingpage class
		landingpages.goTo();
		return landingpages;
	}
	
}
