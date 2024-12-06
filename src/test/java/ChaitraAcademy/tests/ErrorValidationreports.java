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

public class ErrorValidationreports extends BaseTests {


	
		
 @Test(groups = {"ErrorHandling"})
 public void LoginErrorValidation() throws IOException, InterruptedException
		
 {
        
	 
        String Productname = "ZARA COAT 3";

     
        landingpages.loginapplication("chai.nir@gmail.com", "Test123");
		landingpages.gerErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingpages.gerErrorMessage());
		
		
		
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
