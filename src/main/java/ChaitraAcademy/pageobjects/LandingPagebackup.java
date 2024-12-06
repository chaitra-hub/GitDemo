//refer Framework Part 2 - Design Pattern - Page Object & factory Implementation

package ChaitraAcademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ChaitraAcademy.AbstractComponents.AbstractComponent;

public class LandingPagebackup extends AbstractComponent {
	
	WebDriver driver;

	public LandingPagebackup(WebDriver driver) //now i am calling the information webdriver driver
	{
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //this refer to current class driver
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));  // we are defining all the page objects here
	
	// page factory - we can reduce the syntax, instead of above we use pagefactory , so above one is commented
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement PasswordEle;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement submit;
	
	//now action
	
	public void loginapplication(String email, String password)
	{
		userEmail.sendKeys(email);
		PasswordEle.sendKeys(password);
		submit.click();
	}
	
	public void goTo()
	
	{
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	
	
	
}
