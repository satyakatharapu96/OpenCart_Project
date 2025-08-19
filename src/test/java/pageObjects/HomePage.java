package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement my_account;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']/child::li//a[text()='Register']")
	WebElement registerlink;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']/child::li//a[text()='Login']")
	WebElement loginLink;
	
 
	@FindBy(xpath = "//ul[contains(@class, 'dropdown-menu-right')]//a[text()='Login']")
	WebElement loginlink;
	
	public void clickMyAccount() {
		my_account.click();
		
	}
	public void clickRegister() {
		registerlink.click();
	}
	public void clickLogin() {
		loginlink.click();
	}
	

}
