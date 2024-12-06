package ChaitraAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ChaitraAcademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	
WebDriver driver;




public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //this refer to current class driver
	
}


// below we can call as page factory elements

@FindBy(css = "[placeholder='Select Country']")
WebElement country;

@FindBy(css = ".action__submit")
WebElement submit;

@FindBy(xpath = "//button[contains(@class, 'ta-item')][2]")
WebElement SelectCountry;

By results = By.cssSelector(".ta-results");



public void SelectCountry(String CountryName)
{
	Actions a = new Actions(driver);
	a.sendKeys(country,CountryName).build().perform();
	WaitForElementToAppear(results);
	SelectCountry.click();
}


public ComfirmationPage SubmitOrder()

{
	submit.click();
	return new ComfirmationPage(driver);
	
}



}