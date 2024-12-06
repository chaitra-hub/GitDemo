package ChaitraAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ChaitraAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestsJSONMaven {

	public 	WebDriver driver;
	public LandingPage landingpages;
	public WebDriver initilizeDriver() throws IOException
	
	{
		// properties class
		Properties prop = new Properties(); // just creating class for property object
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\chaitraAcandemy\\resources\\GlobalData.properties"); // right click on global properties and copy the pATH
		prop.load(fis); // fis is the object, it has file stream which is defined above
		
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//prop.getProperty("browser");

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
			System.getProperty("webdriver.gecko.driver", "C://Tools//selenium//geckodriver-v0.35.0//geckodriver");
			driver  =new FirefoxDriver();
		}
		
		else if (browsername.equalsIgnoreCase("edge"))
		{
			//edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	}
	
	
	
	 public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	 {
		 TakesScreenshot ts = (TakesScreenshot)driver;
		 File Source =  ts.getScreenshotAs(OutputType.FILE);
		// File file  =new File("(System.getProperty(\"user.dir\") + //Reports//" + testCaseName + ".png");
		 File file  =new File(System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png");
		 FileUtils.copyFile(Source, file);
		 return System.getProperty("user.dir") + "//Reports//" + testCaseName + ".png";
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
