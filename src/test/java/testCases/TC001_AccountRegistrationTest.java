package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = { "Regression","Master"})
	public void verifyRegistrationPageTest() {
		try {
			logger.info("------------starting tc001_Account registration test-------");

			HomePage page = new HomePage(driver);
			page.clickMyAccount();
			logger.info("-----click myaccount link");
			page.clickRegister();
			logger.info("----clicked on register link");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			logger.info("---- providing customer details");
			regpage.setFirstName(randamString().toUpperCase());
			regpage.setLastName(randamString().toUpperCase());
			regpage.setemail(randamString() + "@gmai.com");
			regpage.setPhoneno(randomNumber());
			String password = randomAlphaNumberic();
			regpage.setpassword(password);
			regpage.setconfirmpassword(password);
			regpage.setprivacypolicy();
			regpage.continueBtn();
			logger.info("---validating expected massage---");
			String cnfmmsg = regpage.getconfirmationMsg();
			if(cnfmmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}else {
				
				logger.error("------Test case failed----");
				logger.debug("---debug logs...");
				Assert.assertTrue(false);
			}
//			Assert.assertEquals(cnfmmsg, "Your Account Has Been Created!!");
		} catch (Exception e) {
			
			Assert.fail();
		}
		logger.info("-------------finish the test case registration-----------");
	}

}
