package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public Logger logger; // log4j
	public static WebDriver driver;
	Properties prop;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "Datadriven" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String browser) throws IOException {
		// loading config.properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		prop = new Properties();
		prop.load(file);

		logger = LogManager.getLogger(this.getClass()); // it will take the class name dynamically

		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);

			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching os");
				return;
			}

			// browser
			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		}

		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name..");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(prop.getProperty("url")); // reading url from properties file.
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	public String randamString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;

	}

	@SuppressWarnings("deprecation")
	public String randomNumber() {
		String generatenumber = RandomStringUtils.randomNumeric(10);
		return generatenumber;

	}

	@SuppressWarnings("deprecation")
	public String randomAlphaNumberic() {
		String generatedstring = RandomStringUtils.randomNumeric(10);
		String generatenumber = RandomStringUtils.randomNumeric(10);
		return (generatedstring + "@" + generatenumber);
	}

	public String captureScreenshot(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetFilePath;
	}

}
