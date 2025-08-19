package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	WebElement msgheading;
	
	
	@FindBy(xpath = "//div[@class='list-group']/a[text()='Logout']")
	WebElement logout;
	
	public boolean isMyAccountPageExists() {
		try {
		return (msgheading.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
	
	public void logoutclick() {
		logout.click();
	}
	

}
