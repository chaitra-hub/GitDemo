package ChaitraAcademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ChaitraAcademy.pageobjects.LandingPage;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//below chrome option is used to ignore SSL certificate errors
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");
        
        String Productname = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup(); // here chromerdriver will download automatically
		WebDriver driver = new ChromeDriver(options); // Creating object to your chromedriver
		//ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.rahulshettyacademy.com/client");
		//LandingPage landingpages = new LandingPage(driver); //here we are sending driver as an argument to the class, this we can catch in the constructor which is Landingpage class
		driver.findElement(By.id("userEmail")).sendKeys("chai.nir@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  // creating webdriver wait class
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")))	;	
	List<WebElement> products = 	driver.findElements(By.cssSelector(".mb-3")); //here we are searching s generic locator to find the product section
		// stream will itirate just like for loop  
	//products.stream().filter(product-> product.getText().equals("ZARA COAT 3")); , IF IT IS DIRECT WE CN USE THIS
	
	// below is the latest trend of code. else we can use for loop also
	
	// BELOW STREAM WE USE TO FIND ALL WEBELEMENTS
WebElement prod = products.stream().filter(product-> 
product.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst().orElse(null);
prod.findElement(By.cssSelector(".card-body button:last-child")).click();
	



			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")))	;
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")))	; // .classname
		//or use below
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))))	; 
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		// now in checkout ascreen, you are going to find the product, but in cart screen, there might be multiple product in that case we use "css>>  .classname tag" etc traverse from parent to child

	// .cartSection h3 - css  i.e, .class tagname
		// //*[@class='cartSection']  xpath
		
		List<WebElement> cartproducts = 	driver.findElements(By.cssSelector(".cartSection h3")); 
	
		
		// BELOW STREAM WE USE TO FIND CONDITION MATCHES
		Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		//driver.findElement(By.xpath("[placeholder='Select Country']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
		//(//button[contains(@class,'ta-item')])[2]
		
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();


		Thread.sleep(5000);
		//below is scroll down to page where place order button is visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,1000)");
		
		 // Alternatively, you can scroll to the bottom of the page
         js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
         
         Thread.sleep(5000);
		
	//	WebElement placeorder = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Place Order']")));

        // Click on the button
		//placeorder.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		 
	
		   String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		//Assert.assertEquals(confirmmessage, " Thankyou for the order. ");
		
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
		
	}
	

}
