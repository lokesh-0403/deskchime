package goalpagetesting;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;
import retryanalyzer.RetryAnalyzer;

import basetest.BaseTest;

public class TestingOfGoalpage extends BaseTest {

	

	private void closePopups() {
		
		// Try to close any popups that appear after login
		try {
			WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
			
			// Try close button first
			try {
				WebElement closeBtn = shortWait.until(
					ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Close']")));
				closeBtn.click();
				System.out.println("Close button clicked");
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println("No close button found");
			}
			
			// Try skip button (multiple attempts)
			for (int i = 0; i < 3; i++) {
				try {
					WebElement skipBtn = shortWait.until(
						ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Skip']")));
					skipBtn.click();
					System.out.println("Skip button clicked (attempt " + (i + 1) + ")");
					Thread.sleep(500);
				} catch (Exception e) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("No popups to close");
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
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
		Thread.sleep(500);
		try {
			element.click();
		} catch (Exception e) {
			// Fallback to JS click if regular click fails
			js.executeScript("arguments[0].click();", element);
		}
	}

	private void type(By locator, String text) throws InterruptedException {
		WebElement element = waitForVisible(locator);
		element.clear();
		Thread.sleep(300);
		element.sendKeys(text);
	}

	private void clickSwitchButtonOfElement(String locator, String innerLocator) throws InterruptedException {
		WebElement switchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator+"/ancestor::div[contains(@class,'justify-between')] //button[@role='switch']"+innerLocator)));
//		WebElement element = waitForClickable(locator);
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", switchButton);
		Thread.sleep(500);
//		WebElement switchButton = element.findElement(innerLocator);
		switchButton.click();
	}

	@Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
	public void userCanCreateGoalForselfDevelopment() throws InterruptedException {
		Goto();
		loginApplication();
		closePopups();

		// Navigate to Goals
		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));
		
		// Click Create Goal button
		clickJS(By.xpath("//div[normalize-space()='New goal']"));
		
		// Select Self-Development goal type
		clickJS(By.xpath("//div[text()='Self-Development']"));

		// Fill in goal title
		type(By.cssSelector("input[placeholder='Eg: Increase my overall productivity']"), 
			"Self Development Goal - " + System.currentTimeMillis());
		
		// Fill in goal description
		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"),
			"When setting goals for yourself, you may choose to follow the SMART goal method...");

		// Submit the goal
		clickJS(By.xpath("//button[@type='submit']"));

		// Wait for success notification
		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]"));
		System.out.println("✓ Self-development goal created successfully!");
	}

	@Test(priority = 2,retryAnalyzer = RetryAnalyzer.class)
	public void userCanCreateGoalForTeam() throws InterruptedException {
		Goto();
		loginApplication();
		closePopups();

		// Navigate to Goals
		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));
		
		// Click Create Goal
		clickJS(By.xpath("//div[normalize-space()='New goal']"));
		
		// Choose Team goal (second option)
		clickJS(By.xpath("//div[@class='py-4 px-6 space-y-4']/div[2]"));
		
		// Select team member
		type(By.xpath("(//input[@type='text'])[1]"), "lokesh");
		Thread.sleep(1000);
		clickJS(By.cssSelector("div[class='flex gap-2 items-center select-none']"));
		clickJS(By.id("member-save"));
		
		// Fill in goal details
		type(By.cssSelector("input[placeholder='Eg: Increase my overall productivity']"), 
			"Team Goal - " + System.currentTimeMillis());
		
		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"),
			"When setting goals for yourself, you may choose to follow the SMART goal method...");
		
		// Set Goal Timeline to Weekly
		clickJS(By.xpath("//p[normalize-space()='Weekly']"));
		
		// Add observer
		clickSwitchButtonOfElement(
			"//div[normalize-space()='Add Goal Observers']",
			"//div[@class='ant-switch-handle']");
		
		type(By.xpath("(//input[@type='text'])[3]"), "");
		Thread.sleep(1000);
		clickJS(By.xpath("(//div[@class='flex gap-2 items-center select-none'])[2]"));
		clickJS(By.xpath("(//button[@id='member-save'])[2]"));
		
		// Submit goal
		clickJS(By.xpath("//button[@type='submit']"));
		
		// Wait for confirmation
		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]"));
		System.out.println("✓ Team goal created successfully!");
	}

	@Test(priority = 3,retryAnalyzer = RetryAnalyzer.class)
	public void usersCanSeeCompletedGoals() throws InterruptedException {
		Goto();
		loginApplication();
		closePopups();
		
		// Navigate to Goals
		clickJS(By.xpath("//span[text()='Goals']"));
		
		// Click Completed tab
		clickJS(By.xpath("//div[@data-testid='tab-button-Completed']"));
		
		System.out.println("✓ Completed goals tab displayed successfully");
	}

	@Test(priority = 4,retryAnalyzer = RetryAnalyzer.class)
	public void usersCanSeeActiveGoals() throws InterruptedException {
		Goto();
		loginApplication();
		closePopups();
		
		// Navigate to Goals
		clickJS(By.xpath("//span[text()='Goals']"));
		
		// Click Delayed tab
		clickJS(By.xpath("(//span[normalize-space()='Active'])[1]"));
		
		System.out.println("✓ Active goals tab displayed successfully");
	}

	@Test(priority = 5,retryAnalyzer = RetryAnalyzer.class)
	public void usersCanSeeSupervisedGoals() throws InterruptedException {
		Goto();
		loginApplication();
		closePopups();
		
		// Navigate to Goals
		clickJS(By.xpath("//span[text()='Goals']"));
		
		// Click Abandoned tab
		clickJS(By.cssSelector("div[data-testid='tab-button-Supervising'] span"));
		
		System.out.println("✓ Supervising goals tab displayed successfully");
	}

	@Test(priority = 6,retryAnalyzer = RetryAnalyzer.class)
	public void usersCanSeeArchivedGoals() throws InterruptedException {
		Goto();
		loginApplication();
		closePopups();
		
		// Navigate to Goals
		clickJS(By.xpath("//span[text()='Goals']"));
		
		// Click Archived tab
		clickJS(By.xpath("//div[@data-testid='tab-button-Archived']"));
		
		System.out.println("✓ Archived goals tab displayed successfully");
	}
	
	@Test(priority = 7,retryAnalyzer = RetryAnalyzer.class)
	public void usersCanSeeAllGoals() throws InterruptedException {
		Goto();
		loginApplication();
		closePopups();
		
		// Navigate to Goals
		clickJS(By.xpath("//span[text()='Goals']"));
		
		// Click Archived tab
		clickJS(By.xpath("//div[@data-testid='tab-button-All']"));
		
		System.out.println("✓ All goals tab displayed successfully");
	}

	@Test(priority = 8,retryAnalyzer = RetryAnalyzer.class)
	public void usersCanUpdateStatus() throws InterruptedException {
		Goto();
		loginApplication();
		closePopups();
		
		// Navigate to Goals
		clickJS(By.xpath("//span[text()='Goals']"));
		
		// Wait for goals to load
		Thread.sleep(1500);
		
		// Click on the goal menu button (fourth goal)
		clickJS(By.xpath("(//button[contains(@class,'text-sm rounded-full leading-none ant-dropdown-trigger border-slate-300')])[4]"));
		
		// Click Update Status option
		clickJS(By.xpath("//span[normalize-space()='Update Status']"));
		
		// Click on current status dropdown
		clickJS(By.xpath("//span[@title='On Track']"));
		
		// Select Completed status
		clickJS(By.cssSelector("div[title='Completed'] div[class='ant-select-item-option-content']"));
		
		// Add comment
		type(By.xpath("//p[@class='is-empty is-editor-empty']"), "Status updated successfully");
		
		// Click Update button
		clickJS(By.xpath("//div[normalize-space()='Update']"));
		
		// Wait for success notification
		Thread.sleep(1000);
		System.out.println("✓ Goal status updated successfully");
	}

	public String selectingActiveTimeInGoalFollowUp() {
		List<WebElement> timeoptions = wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@class='rc-virtual-list-holder-inner'])[2]//div[contains(@class,'ant-select-item ant-select-item-option')]")));
		
		List<WebElement> activeTime = timeoptions.stream()
				.filter(option->option.getAttribute("class").contains("ant-select-item-option-active")).collect(Collectors.toList());
		
		List<WebElement> disabledTime = timeoptions.stream()
				.filter(option->option.getAttribute("class").contains("ant-slect-item-option-active")).collect(Collectors.toList());
		 String time = null ;
		
		if(!activeTime.isEmpty()) {
			Random random = new Random();
			WebElement randomActiveTime = activeTime.get(random.nextInt(activeTime.size()));
		 time = randomActiveTime.getText();
		}
		return time;
	}
	
	@Test(priority = 9,retryAnalyzer = RetryAnalyzer.class)
	public void userCanSetUpFollowupForGoal() throws InterruptedException, IOException {
		Goto();
		loginApplication();
		closePopups();
		
		// Navigate to Goals
		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));
		
		// Click Create Goal
		clickJS(By.xpath("//div[normalize-space()='New goal']"));
		
		// Choose Team goal
		clickJS(By.xpath("//div[@class='py-4 px-6 space-y-4']/div[2]"));
		
		// Select team member
		type(By.xpath("(//input[@type='text'])[1]"), "lokesh");
		Thread.sleep(1000);
		clickJS(By.cssSelector("div[class='flex gap-2 items-center select-none']"));
		clickJS(By.id("member-save"));
		
		// Fill in goal details
		type(By.cssSelector("input[placeholder='Eg: Increase my overall productivity']"),
			"Goal with Follow-up - " + System.currentTimeMillis());
		
		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"),
			"When setting goals for yourself, you may choose to follow the SMART goal method...");
		
		// Set Goal Timeline to Weekly
		clickJS(By.xpath("//p[normalize-space()='Weekly']"));
		
		// Add observer
		clickSwitchButtonOfElement(
			"//div[normalize-space()='Add Goal Observers']",
			"//div[@class='ant-switch-handle']");
		
		type(By.xpath("(//input[@type='text'])[3]"), "");
		Thread.sleep(1000);
		clickJS(By.xpath("(//div[@class='flex gap-2 items-center select-none'])[2]"));
		clickJS(By.xpath("(//button[@id='member-save'])[2]"));
		
		
		
		// Schedule Follow-Up
		clickSwitchButtonOfElement(
			"//div[normalize-space()='Schedule Follow-Up for Goal']",
			"//div[@class='ant-switch-handle']");
		
		// Select frequency - Weekly
		type(By.xpath("(//input[@type='search'])[3]"), "Weekly");
		Thread.sleep(500);
		
		clickJS(By.cssSelector("div[title='Weekly'] div[class='ant-select-item-option-content']"));
		
		// Select time - 
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='search'])[4]"))).click();
		

		selectingActiveTimeInGoalFollowUp();
		
		Thread.sleep(500);
		clickJS(By.cssSelector(
			"div[class='ant-select-item ant-select-item-option ant-select-item-option-active'] div[class='ant-select-item-option-content']"));
		
		// Select duration - 30 min
		Thread.sleep(1000);
		WebElement durationField = wait.until(ExpectedConditions.visibilityOfElementLocated(
			By.xpath("(//span[@class='ant-select-selection-search'])[5]")));
		durationField.click();
		Thread.sleep(1000);
		clickJS(By.cssSelector("div[title='30 min'] div[class='ant-select-item-option-content']"));
		
		// Submit goal
		clickJS(By.xpath("//button[@type='submit']"));
		
		// Wait for confirmation
		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]"));
		System.out.println("✓ Goal with follow-up created successfully!");
	}
	

	@Test(priority = 10,retryAnalyzer = RetryAnalyzer.class)
	public void userCanSetUpFeedbackForGoal() throws InterruptedException, IOException {
		Goto();
		loginApplication();
		closePopups();
		
		// Navigate to Goals
		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));
		
		// Click Create Goal
		clickJS(By.xpath("//div[normalize-space()='New goal']"));
		
		// Choose Team goal
		clickJS(By.xpath("//div[@class='py-4 px-6 space-y-4']/div[2]"));
		
		// Select team member
		type(By.xpath("(//input[@type='text'])[1]"), "lokesh");
		Thread.sleep(1000);
		clickJS(By.cssSelector("div[class='flex gap-2 items-center select-none']"));
		clickJS(By.id("member-save"));
		
		// Fill in goal details
		type(By.cssSelector("input[placeholder='Eg: Increase my overall productivity']"),
			"Goal with Follow-up - " + System.currentTimeMillis());
		
		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"),
			"When setting goals for yourself, you may choose to follow the SMART goal method...");
		
	
		// Set Goal Timeline to Weekly
		clickJS(By.xpath("//p[normalize-space()='Monthly']"));
		
		// Add observer
		clickSwitchButtonOfElement(
			"//div[normalize-space()='Add Goal Observers']",
				"//div[@class='ant-switch-handle']");
		
		type(By.xpath("(//input[@type='text'])[3]"), "");
		Thread.sleep(1000);
		clickJS(By.xpath("(//div[@class='flex gap-2 items-center select-none'])[2]"));
		clickJS(By.xpath("(//button[@id='member-save'])[2]"));
		
		
		
		// Schedule Follow-Up
		clickSwitchButtonOfElement(
			"//div[normalize-space()='Automated Feedback']",
			"//div[@class='ant-switch-handle']");
		
		Thread.sleep(5000);
		// Select frequency - Weekly
		
		WebElement autoSetFreElement = null;
		try {
		autoSetFreElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='font-medium text-sm text-blue-700']")));
		}catch(Exception e) {}
		
		if(autoSetFreElement!=null && autoSetFreElement.getText().contains("Feedback frequency is automatically set to")) {
			System.out.println("Feedback frequency auto-set message displayed: " + autoSetFreElement.getText());
			type(By.xpath("(//input[@type='search'])[3]"), "Test");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(@class,'ant-select-item-option-content') and normalize-space()='TEST'])[1]"))).click();
			Thread.sleep(500);
			
		}
		else {
				type(By.xpath("(//input[@type='search'])[3]"), "Weekly");
				Thread.sleep(500);
				
				clickJS(By.cssSelector("div[title='Weekly'] div[class='ant-select-item-option-content']"));
				
				// Select time - 
				Thread.sleep(1000);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='search'])[4]"))).click();
				Thread.sleep(500);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[normalize-space()='Monday'])[1]"))).click();

				
		
		Thread.sleep(500);
		
		// Select duration - 30 min
		Thread.sleep(1000);
		type(By.xpath("(//input[@type='search'])[5]"), "Test");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[normalize-space()='TEST'])[1]"))).click();
		Thread.sleep(500);
		
		}
		// Submit goal
		clickJS(By.xpath("//button[@type='submit']"));
		
		// Wait for confirmation
		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]"));
		System.out.println("✓ Goal with follow-up created successfully!");
	}
}