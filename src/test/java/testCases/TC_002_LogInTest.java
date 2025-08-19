package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_002_LogInTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})
	public void verifyLoginPage() {
		logger.info("---starting the login test case");
		try {
			HomePage hmpage = new HomePage(driver);
			hmpage.clickMyAccount();
			hmpage.clickLogin();

			LoginPage page = new LoginPage(driver);
			page.setEmail(prop.getProperty("email"));
			page.setpassword(prop.getProperty("password"));
			page.loginbtn();

			MyAccountPage mypage = new MyAccountPage(driver);
			boolean isexists = mypage.isMyAccountPageExists();
			Assert.assertEquals(isexists, true, "Login is Failed");
		} catch (Exception e) {
			Assert.fail();
		}

	}

}
