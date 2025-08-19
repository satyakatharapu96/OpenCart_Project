package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	WebDriver driver;

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "firstname")
	WebElement firstname;
	@FindBy(name = "lastname")
	WebElement lastname;
	@FindBy(id ="input-email")
	WebElement txtemail;
	@FindBy(id = "input-telephone")
	WebElement phno;
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confirmpassowrd;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement agree;
	@FindBy(xpath = "//input[@class='btn btn-primary']")
	WebElement continuebtn;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgconfirm;
	
	public void setFirstName(String fname) {
		firstname.sendKeys(fname);
	}
	public void setLastName(String name) {
		lastname.sendKeys(name);
	}
	public void setemail(String email) {
		txtemail.sendKeys(email);
	}
	public void setPhoneno(String phonenum) {
		phno.sendKeys(phonenum);
	}
	public void setpassword(String pwd) {
		password.sendKeys(pwd);
	}
	public void setconfirmpassword(String pwd) {
		confirmpassowrd.sendKeys(pwd);
	}
	public void setprivacypolicy() {
		agree.click();
	}
	public void continueBtn() {
		continuebtn.click();
	}
	public String getconfirmationMsg() {
		try {
		return(msgconfirm.getText());
		}catch(Exception e) {
			return e.getMessage();
		}
	}

}
