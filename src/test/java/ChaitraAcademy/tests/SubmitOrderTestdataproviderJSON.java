package ChaitraAcademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ChaitraAcademy.TestComponents.BaseTests;
import ChaitraAcademy.TestComponents.BaseTestsJSON;
import ChaitraAcademy.pageobjects.CartPage;
import ChaitraAcademy.pageobjects.CheckoutPage;
import ChaitraAcademy.pageobjects.ComfirmationPage;
import ChaitraAcademy.pageobjects.LandingPage;
import ChaitraAcademy.pageobjects.OrderPage;
import ChaitraAcademy.pageobjects.ProductCatalogue;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SubmitOrderTestdataproviderJSON extends BaseTestsJSON {

	String Productname = "ZARA COAT 3";
	
		
 @Test(dataProvider="getData", groups = {"Purchase"})
 public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException
		
 { 
        
        

       // LandingPage landingpages =   launchApplication();
		ProductCatalogue  ProductCatalogue =landingpages.loginapplication(input.get("email"), input.get("password"));
		
		//ProductCatalogue  ProductCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.AddProductToCart(input.get("Productname"));
		ProductCatalogue.goToCartPage();
		CartPage CartPage = new CartPage(driver);
		
		Boolean match = CartPage.VerifyProductDisplay(input.get("Productname"));
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
 
 
 @DataProvider
 public Object [][] getData() throws IOException
 
 {
	
	 
	List <HashMap<String, String>> data =  getJsonDataToMap((System.getProperty("user.dir")+"\\src\\test\\java\\ChaitraAcademy\\data\\PurchaseOrder.json"));
	 return new Object [][] {{data.get(0)}, {data.get(1)}};
 }

}
