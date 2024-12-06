package ChaitraAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ChaitraAcademy.AbstractComponents.AbstractComponent;

public class ComfirmationPage  extends AbstractComponent {

	
	WebDriver driver;




	public ComfirmationPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this); //this refer to current class driver
		
	}
	
	@FindBy(css = ".hero-primary")
	WebElement ConfirmationMessage;
	
	public String getConfirmationMessage()
	{
		return ConfirmationMessage.getText();
	}
}
