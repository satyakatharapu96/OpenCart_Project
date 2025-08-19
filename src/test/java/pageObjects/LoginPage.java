package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailadd;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	WebElement loginbtn;

	public void setEmail(String email) {
		emailadd.sendKeys(email);
	}

	public void setpassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void loginbtn() {
		loginbtn.click();
	}
}
