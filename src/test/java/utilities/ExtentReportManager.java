package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {
	String repName;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		repName = "Test-Report-" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		sparkReporter.config().setDocumentTitle("Opencart Automation Reports");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedgroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedgroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedgroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	// ✅ FIXED METHOD NAME
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
//	try {
//		  URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
//	  
//	  // Create the email message 
//	  ImageHtmlEmail email = new ImageHtmlEmail();
//	  email.setDataSourceResolver(new DataSourceUrlResolver(url));
//	  email.setHostName("smtp.googlemail.com"); 
//	  email.setSmtpPort(465);
//	  email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
//	  email.setSSLOnConnect(true);
//	  email.setFrom("pavanoltraining@gmail.com"); //Sender
//	  email.setSubject("Test Results");
//	  email.setMsg("Please find Attached Report....");
//	  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
//	  email.attach(url, "extent report", "please check report..."); 
//	  email.send(); // send the email 
//	  }
//	  catch(Exception e) 
//	  { 
//		  e.printStackTrace(); 
//		  }
