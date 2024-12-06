package ChaitraAcademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ChaitraAcademy.TestComponents.BaseTests;
import ChaitraAcademy.pageobjects.CartPage;
import ChaitraAcademy.pageobjects.CheckoutPage;
import ChaitraAcademy.pageobjects.ComfirmationPage;
import ChaitraAcademy.pageobjects.LandingPage;
import ChaitraAcademy.pageobjects.OrderPage;
import ChaitraAcademy.pageobjects.ProductCatalogue;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SubmitOrderTestTestdataprosample extends BaseTests {

	String Productname = "ZARA COAT 3";
	
		
 @Test  
 public void SubmitOrder() throws IOException, InterruptedException
		
 {
        
        

       // LandingPage landingpages =   launchApplication();
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
	
 @Test(dependsOnMethods = {"SubmitOrder"})
 public void OrderHistoryTest()
 {
	 ProductCatalogue  ProductCatalogue =landingpages.loginapplication("chai.nir@gmail.com", "Test@123");
	 //ProductCatalogue.goToOrdersPage();
	 
	OrderPage ordersPage = ProductCatalogue.goToOrdersPage();
	//ordersPage.VerifyOrderDisplay(Productname);
	Assert.assertTrue(ordersPage.VerifyOrderDisplay(Productname));
 }

}
