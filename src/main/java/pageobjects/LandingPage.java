package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Page Factory
public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//span[contains(text(),'My Account')]")
	WebElement myAccount;
	
	@FindBy(xpath="//a[contains(text(),'Login')]")
	WebElement loginButton;
	
	
	public WebElement myAccount() {
		return myAccount;
		
	}
	
	public WebElement loginButton() {
		return loginButton;
	}
	

}
