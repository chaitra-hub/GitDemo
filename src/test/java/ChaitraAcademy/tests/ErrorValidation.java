package ChaitraAcademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ChaitraAcademy.TestComponents.BaseTests;
import ChaitraAcademy.pageobjects.CartPage;
import ChaitraAcademy.pageobjects.CheckoutPage;
import ChaitraAcademy.pageobjects.ComfirmationPage;
import ChaitraAcademy.pageobjects.ProductCatalogue;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import ChaitraAcademy.TestComponents.Retry;

public class ErrorValidation extends BaseTests {


	
		
 @Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
 public void LoginErrorValidation() throws IOException, InterruptedException
		
 {
        
        String Productname = "ZARA COAT 3";

       // LandingPage landingpages =   launchApplication();
	//	ProductCatalogue  ProductCatalogue =landingpages.loginapplication("chai.nir@gmail.com", "Test123");
        landingpages.loginapplication("chai.nir@gmail.com", "Test123");
		landingpages.gerErrorMessage();
		Assert.assertEquals("Incorrect mail or password.", landingpages.gerErrorMessage());
		
		
		
	}
	
 @Test
 public void ProductErrorValidation() throws IOException, InterruptedException
		
 {
        
        String Productname = "ZARA COAT 3";
        ProductCatalogue  ProductCatalogue =landingpages.loginapplication("chai.nir1@mailinator.com", "Test@123");
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.AddProductToCart(Productname);
		ProductCatalogue.goToCartPage();
		CartPage CartPage = new CartPage(driver);
		Boolean match = CartPage.VerifyProductDisplay(Productname);
		Assert.assertTrue(match);// valiations cannot go inside page object files
		
	
		
	}
	

}
