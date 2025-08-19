package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
	public void verify_loginDDT(String email, String pwd, String expected) {
		logger.info("*** starting tc_003 loginddt********");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login
			LoginPage page = new LoginPage(driver);
			page.setEmail(email);
			page.setpassword(pwd);
			page.loginbtn();

			// myAccount
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean target = myacc.isMyAccountPageExists();

			if (expected.equalsIgnoreCase("Valid")) {
				if (target == true) {
					myacc.logoutclick();
					Assert.assertTrue(true);

				}

			} else {
				Assert.assertTrue(false);
			}
			if (expected.equalsIgnoreCase("Invalid")) {
				if (target == true) {
					myacc.logoutclick();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("finished tc_003 ddt");

	}
}