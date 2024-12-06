package ChaitraAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ChaitraAcademy.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement CartHaeder;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement OrderHeader;

	public void WaitForElementToAppear(By findBy) {
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  // creating webdriver wait class
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy))	;	
	}
	
	public void WaitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  // creating webdriver wait class
		wait.until(ExpectedConditions.visibilityOf(findBy))	;	
		}
	
	
	public void goToCartPage() {
		CartHaeder.click();
	}
	//IF IT IS BY. THEN USE FINDBY, ELSE USE WEBELEMENT LIKE BELOW
	
	public OrderPage goToOrdersPage() {
		OrderHeader.click();
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;
	}
	
	public void WaitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5)); 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))))	; 

		
	}	
}
