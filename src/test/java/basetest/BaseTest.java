package basetest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.netty.handler.timeout.TimeoutException;
import loginpagetesting.ChromeOptionsConfig;
import utility.ConfigReader;

public class BaseTest {

	public String emails = "yesh@zasyasolutions.com";
	public String passwords = "Yesh255198@";
	
	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	ConfigReader config = new ConfigReader();

	@BeforeMethod
	public void setUp() throws Exception {
		  System.out.println("=== SETUP STARTED ===");
		  try {
			Thread.sleep(1000);
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: config.getProperty("browser");

		System.out.println("Browser: " + browser);
		// Initialize the driver based on the browser value
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = ChromeOptionsConfig.getChromeOptions();
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
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		// Initialize WebDriverWait
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js = (JavascriptExecutor) driver;
		 System.out.println("✓ Driver initialized successfully");
		    } catch (Exception e) {
		        System.err.println("✗ Failed to initialize driver: " + e.getMessage());
		        e.printStackTrace();
		        throw e;
		    }
		 System.out.println("=== SETUP COMPLETED ===");
	}

	@AfterMethod
	public void tearDown() {
		  System.out.println("=== TEARDOWN STARTED ===");
		if (driver != null) {
			driver.quit();
			System.out.println("Driver quit successfully");
		    System.out.println("=== TEARDOWN COMPLETED ===");
		}
	}

	public void loginApplication() throws InterruptedException {
		Thread.sleep(1000);
		
		WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/auth/login']")));
		login.click();
		
		WebElement email = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Email address']")));
		email.sendKeys(emails);

		WebElement password = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Password']")));
		password.sendKeys(passwords);
		
		WebElement submitbutton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
		submitbutton.click();
		
		// Wait for successful login
		Thread.sleep(2000);
	}

	public void Goto() {
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		long startTime = System.currentTimeMillis();
		driver.get("https://dev.deskchime.com");
		long loadTime = System.currentTimeMillis() - startTime;
		System.out.println("Page load time: " + loadTime + "ms");
		
		  WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(40));  // ← Increased timeout
		    try {
		        longWait.until(webDriver -> 
		            ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
		        );
		    } catch (TimeoutException e) {
		        System.out.println("⚠ Page readyState check timed out, continuing anyway...");
		        // Continue execution even if page isn't "complete"
		    }
	}

	public void closeAllPopups() {
		// Try to close skip button (multiple attempts)
		for (int i = 0; i < 3; i++) {
			try {
				WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
				WebElement skipButton = shortWait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Skip']")));
				skipButton.click();
				System.out.println("Skip button clicked (attempt " + (i + 1) + ")");
				Thread.sleep(500);
			} catch (Exception e) {
				// Skip button not found, continue
			}
		}
		
		// Try to close feedback popup
		try {
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
			WebElement closeButton = shortWait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Close']")));
			closeButton.click();
			System.out.println("Close button clicked");
			Thread.sleep(500);
		} catch (Exception e) {
			System.out.println("No close button found");
		}
		
		// Final check for any remaining skip buttons
		try {
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
			WebElement skipButton = shortWait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Skip']")));
			skipButton.click();
			System.out.println("Final skip button clicked");
		} catch (Exception e) {
			System.out.println("All popups handled");
		}
	}
	
	public void avoidFeedbackpopup() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement skipButton = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Skip']")));

			skipButton.click();

		} catch (Exception e) {

			System.out.println("skip button is not found");

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