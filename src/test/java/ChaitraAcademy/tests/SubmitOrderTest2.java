package ChaitraAcademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ChaitraAcademy.pageobjects.CartPage;
import ChaitraAcademy.pageobjects.CheckoutPage;
import ChaitraAcademy.pageobjects.ComfirmationPage;
import ChaitraAcademy.pageobjects.LandingPage;
import ChaitraAcademy.pageobjects.ProductCatalogue;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest2 { 

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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	
		LandingPage landingpages = new LandingPage(driver); //here we are sending driver as an argument to the class, this we can catch in the constructor which is Landingpage class
		landingpages.goTo();
		ProductCatalogue  ProductCatalogue =landingpages.loginapplication("chai.nir@gmail.com", "Test@123");
		
		
		//ProductCatalogue  ProductCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.AddProductToCart(Productname);
		ProductCatalogue.goToCartPage();
		CartPage CartPage = new CartPage(driver);
		
		Boolean match = CartPage.VerifyProductDisplay(Productname);
		Assert.assertTrue(match);// valiations cannot go inside page object files
		CheckoutPage checkoutpage = CartPage.goToCheckout();
		
		checkoutpage.SelectCountry("India");
		
		 Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(5000);
        
		
        ComfirmationPage confirmationpage = checkoutpage.SubmitOrder();
		
		


		
		
		
		
		 
	
		   String confirmmessage = confirmationpage.getConfirmationMessage();
		//Assert.assertEquals(confirmmessage, " Thankyou for the order. ");
		
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	//	driver.close();
		
		
		
	}
	

}
