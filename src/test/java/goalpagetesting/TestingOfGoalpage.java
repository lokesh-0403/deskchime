package goalpagetesting;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class TestingOfGoalpage extends BaseTest {

	private WebDriverWait wait;
	private JavascriptExecutor js;

	@BeforeTest
	public void initDriver() {
		try {
			setUp(); // Use BaseTest setup
			wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			js = (JavascriptExecutor) driver;
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage());
		}
	}

	private WebElement waitForVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	private WebElement waitForClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private void clickJS(By locator) throws InterruptedException {
		WebElement element = waitForClickable(locator);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		js.executeScript("arguments[0].click();", element);
	}

	private void type(By locator, String text) throws InterruptedException {
		WebElement element = waitForVisible(locator);
		element.clear();
		element.sendKeys(text);
	}

	private void clickSwitchButtonOfElement(By locator, By innerLocator) throws InterruptedException {
		WebElement element = waitForClickable(locator);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		WebElement switchButton = element.findElement(innerLocator);
		switchButton.click();
	}

//	@Test
	public void userCanCreateGoalForselfDevelopment() throws InterruptedException {
		Goto();
		loginApplication();
		try {
			clickJS(By.cssSelector("button[aria-label='Close']"));
		} catch (Exception e) {
			System.out.println("");
		}

		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));
		clickJS(By.xpath("//div[normalize-space()='New goal']"));
		clickJS(By.xpath("//div[text()='Self-Development']"));

		type(By.xpath("(//input[@type='text'])[1]"), "Self Goal");
		Thread.sleep(1000);
		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"),
				"When setting goals for yourself, you may choose to follow the SMART goal method...");

		clickJS(By.xpath("//button[@type='submit']"));

		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]"));
		System.out.println("Goal created successfully!");

		driver.quit();
	}

//	@Test 
	public void userCanCreateGoalForTeam() throws InterruptedException {

		Goto(); 
		loginApplication(); 
		try {
			clickJS(By.cssSelector("button[aria-label='Close']"));
		} catch (Exception e) {
			System.out.println("");
		}
		 // Go to Goals
		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));
		
		// Click Create Goal
		clickJS(By.xpath("//div[normalize-space()='New goal']"));
		//choose goals
		clickJS(By.xpath("//div[@class='py-4 px-6 space-y-4']/div[2]"));
		
		type(By.xpath("(//input[@type='text'])[1]"), "lokesh"); 
		Thread.sleep(1000); 
		clickJS(By.cssSelector("div[class='flex gap-2 items-center select-none']"));
		clickJS(By.id("member-save"));
		// Fill details 
		type(By.cssSelector("input[placeholder='Eg: Increase my overall productivity']"), "Use the SMART goal method"); 
		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"), "When setting goals for yourself, you may choose to follow the SMART goal method...");
		// Set Goal Timeline 
		clickJS(By.xpath("//p[normalize-space()='Weekly']")); 
		// Add observer 
		clickSwitchButtonOfElement(By.xpath("(//div[normalize-space()='Add Goal Observers'])[1]"), By.cssSelector(".ant-switch-handle")); 
		type(By.xpath("(//input[@type='text'])[3]"), ""); Thread.sleep(1000); 
		clickJS(By.xpath("(//div[@class='flex gap-2 items-center select-none'])[2]")); 
		clickJS(By.xpath("(//button[@id='member-save'])[2]"));
		
		
		// Submit goal 
		clickJS(By.xpath("//button[@type='submit']"));
		// Wait for confirmation 
		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]")); System.out.println("Goal created successfully!"); driver.close();
	}
	
//	@Test
	public void usersCanSeeCompletedGoals() throws InterruptedException {
		Goto();
		loginApplication();
		
		clickJS(By.xpath("//span[text()='Goals']"));
		clickJS(By.xpath("//div[@data-testid='tab-button-Completed']"));
		
		driver.close();
	}

//	@Test
	public void usersCanSeeDelayedGoals() throws InterruptedException {
		Goto();
		loginApplication();
		clickJS(By.xpath("//span[text()='Goals']"));
		clickJS(By.xpath("//div[@data-testid='tab-button-Delayed']"));
	
		driver.close();
	}

//	@Test
	public void usersCanSeeAbandonedGoals() throws InterruptedException {
		Goto();
		loginApplication();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Goals']")));
		element.click();
		
		WebElement element1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='tab-button-Abandoned']")));
		element1.click();
		driver.close();
	}

//	@Test
	public void usersCanSeeArchivedGoals() throws InterruptedException {
		Goto();
		loginApplication();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Goals']")));
		element.click();
		
		WebElement element1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='tab-button-Archived']")));
		element1.click();
		driver.close();
	}

//	@Test
	public void usersCanUpdateStatus() throws InterruptedException {
		Goto();
		loginApplication();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Goals']")));
		element.click();
		WebElement element1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(@class,'text-sm rounded-full leading-none ant-dropdown-trigger border-slate-300')])[4]")));
		element1.click();
		WebElement element2 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Update Status']")));
		element2.click();
		WebElement element3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='On Track']")));
		element3.click();
		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[title='Completed'] div[class='ant-select-item-option-content']")));
		element4.click();
		type(By.xpath("//p[@class='is-empty is-editor-empty']"),"ok Updated status");
//		WebElement element5 = wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.xpath("//p[@data-placeholder='Comment about the status']")));
//		element5.sendKeys("Ok");
		WebElement element6 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Update']")));
		element6.click();
		driver.close();
	}

	@Test
	public void userCanSetUpFollowupForGoal() throws InterruptedException, IOException {
		Goto(); 
		loginApplication(); 
		try {
			clickJS(By.cssSelector("button[aria-label='Close']"));
		} catch (Exception e) {
			System.out.println("");
		}
		 // Go to Goals
		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));
		
		// Click Create Goal
		clickJS(By.xpath("//div[normalize-space()='New goal']"));
		//choose goals
		clickJS(By.xpath("//div[@class='py-4 px-6 space-y-4']/div[2]"));
		
		type(By.xpath("(//input[@type='text'])[1]"), "lokesh"); 
		Thread.sleep(1000); 
		clickJS(By.cssSelector("div[class='flex gap-2 items-center select-none']"));
		clickJS(By.id("member-save"));
		// Fill details 
		type(By.cssSelector("input[placeholder='Eg: Increase my overall productivity']"), "Use the SMART goal method"); 
		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"), "When setting goals for yourself, you may choose to follow the SMART goal method...");
		// Set Goal Timeline 
		clickJS(By.xpath("//p[normalize-space()='Weekly']")); 
		
		// Add observer 
		clickSwitchButtonOfElement(By.xpath("(//div[normalize-space()='Add Goal Observers'])[1]"), By.cssSelector(".ant-switch-handle")); 
		type(By.xpath("(//input[@type='text'])[3]"), ""); Thread.sleep(1000); 
		clickJS(By.xpath("(//div[@class='flex gap-2 items-center select-none'])[2]")); 
		clickJS(By.xpath("(//button[@id='member-save'])[2]"));
		
		// Schedule FollowUp 
		clickSwitchButtonOfElement(By.xpath("(//div[normalize-space()='Schedule Follow-Up for Goal'])[1]"), By.cssSelector(".ant-switch-handle")); 
			type(By.xpath("(//input[@type='search'])[3]"), "Weekly"); 
			clickJS( By.cssSelector("div[title='Weekly'] div[class='ant-select-item-option-content']")); 
		Thread.sleep(1000); 
		type(By.xpath("(//input[@type='search'])[4]"), "12:30 PM"); 
		clickJS(By.cssSelector( "div[class='ant-select-item ant-select-item-option ant-select-item-option-active'] div[class='ant-select-item-option-content']")); 
		Thread.sleep(2000); 
		WebElement duration =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='ant-select-selection-search'])[5]"))); 
		duration.click();
		Thread.sleep(1000);
		clickJS( By.cssSelector("div[title='30 min'] div[class='ant-select-item-option-content']"));
		// Submit goal 
		clickJS(By.xpath("//button[@type='submit']"));
		// Wait for confirmation 
		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]")); System.out.println("Goal created successfully!"); driver.close();
	
	}
	
}
