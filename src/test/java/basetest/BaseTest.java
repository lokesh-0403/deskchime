package basetest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import loginpagetesting.ChromeOptionsConfig;
import utility.ConfigReader;

public class BaseTest {

	public String emails = "yesh@zasyasolutions.com";
	public String passwords = "Yesh255198@";

	protected WebDriver driver;
	ConfigReader config = new ConfigReader();

	@BeforeMethod  // ADD THIS ANNOTATION
	public void setUp() throws MalformedURLException {
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: config.getProperty("browser");

		if (browser == null || browser.isEmpty())
			browser = "chrome";

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = ChromeOptionsConfig.getChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");

			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			throw new IllegalArgumentException("Browser " + browser + " is not supported.");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		System.out.println("✓ WebDriver initialized successfully");
	}

	@AfterMethod  // ADD THIS ANNOTATION
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("✓ WebDriver closed successfully");
		}
	}

	public void loginApplication() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		
		
		WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/auth/login']")));
		Thread.sleep(1250);
		loginBtn.click();
		
		WebElement email = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Email address']")));
		email.sendKeys(emails);

		WebElement password = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Password']")));
		password.sendKeys(passwords);

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
	}

	public void Goto() {
		// Driver should already be initialized by @BeforeMethod, but adding safety check
		if (driver == null) {
			System.err.println("ERROR: Driver is null in Goto()! This shouldn't happen.");
			throw new IllegalStateException("WebDriver not initialized properly");
		}
		driver.manage().deleteAllCookies();
		driver.get("https://deskchime.com");
	}

	public void avoidFeedbackpopup() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement skipButton = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Skip']")));
			skipButton.click();
		} catch (Exception e) {
			System.out.println("Skip button not found — continuing...");
		}
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File destination = new File(destinationPath);
		FileUtils.copyFile(source, destination);
		return destinationPath;
	}
}