//refer Framework Part 2 - Design Pattern - Page Object & factory Implementation

package ChaitraAcademy.pageobjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ChaitraAcademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) //now i am calling the information webdriver driver
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //this refer to current class driver
	}
	
	
	// page factory ele,ent creation
	
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By AddToCart = By.cssSelector(".card-body button:last-child");
	By toastMessage = By.cssSelector("#toast-container");
	public  List<WebElement> getProductList() {
		WaitForElementToAppear(productsBy);
			return products;
	
}
	
	public WebElement getProductByName(String ProductName)
	
	{
		
		WebElement prod =  getProductList().stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
	}
	
	public void AddProductToCart(String ProductName) throws InterruptedException
	{
		
		WebElement prod = getProductByName(ProductName);
		Thread.sleep(3000);
		prod.findElement(AddToCart).click();
		WaitForElementToAppear(toastMessage);
		WaitForElementToDisappear(spinner);
		 
	}
	
	
}
	
	
	


