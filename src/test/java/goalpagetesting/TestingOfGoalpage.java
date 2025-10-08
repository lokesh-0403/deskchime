package goalpagetesting;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class TestingOfGoalpage extends BaseTest {

	public TestingOfGoalpage() {
		super(); // This will initialize the WebDriver in the BaseTest class
	}

	private WebDriverWait wait;
	private WebDriver driver;
	private JavascriptExecutor js;

	private void initDriver() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
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
		Thread.sleep(800);
		js.executeScript("arguments[0].click();", element);
	}

	private void type(By locator, String text) throws InterruptedException {
		WebElement element = waitForVisible(locator);
		element.clear();
		element.sendKeys(text);
	}

	private void clickSwitchButtonOfElement(By locator, By LoactorA) throws InterruptedException {
		WebElement element = waitForClickable(locator);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(800);
		WebElement switchButton = element.findElement(LoactorA);
		switchButton.click();
	}
@Test
	public void userCanCreateGoalForselfDevelopment() throws InterruptedException {
		Goto();
		loginApplication();
		clickJS(By.cssSelector("button[aria-label='Close']"));

		// Go to Goals
		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));

		// Click Create Goal
		clickJS(By.xpath("//div[normalize-space()='New goal']"));

		// Choose Your Goal
		clickJS(By.xpath("//div[text()='Self-Development']"));
		
		// Select Team Mate
		type(By.xpath("(//input[@type='text'])[1]"), "lokesh");
		Thread.sleep(1000);
		clickJS(By.cssSelector("div[class='flex gap-2 items-center select-none']"));
		clickJS(By.id("member-save"));

		// Fill details
		type(By.cssSelector("input[placeholder='Eg: Increase my overall productivity']"), "Use the SMART goal method");

		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"),
				"When setting goals for yourself, you may choose to follow the SMART goal method...");
	
		// Submit goal
		clickJS(By.xpath("//button[@type='submit']"));

		// Wait for confirmation
		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]"));
		System.out.println("Goal created successfully!");

		driver.close();
	}

@Test
	public void userCanCreateGoalForTeam() throws InterruptedException {

		Goto();
		loginApplication();
		clickJS(By.cssSelector("button[aria-label='Close']"));

		// Go to Goals
		clickJS(By.xpath("//span[@class='ant-menu-title-content'][normalize-space()='Goals']"));

		// Click Create Goal
		clickJS(By.xpath("//div[normalize-space()='New goal']"));

		// Choose Your Goal
		clickJS(By.xpath("//div[@class='py-4 px-6 space-y-4']/div[2]"));

		// Select Team Mate
		type(By.xpath("(//input[@type='text'])[1]"), "lokesh");
		Thread.sleep(1000);
		clickJS(By.cssSelector("div[class='flex gap-2 items-center select-none']"));
		clickJS(By.id("member-save"));

		// Fill details
		type(By.cssSelector("input[placeholder='Eg: Increase my overall productivity']"), "Use the SMART goal method");

		type(By.xpath("//div[contains(@class,'tiptap ProseMirror ')]"),
				"When setting goals for yourself, you may choose to follow the SMART goal method...");

		// Set Goal Timeline
		clickJS(By.xpath("//p[normalize-space()='Weekly']"));

		// Add observer

		clickSwitchButtonOfElement(By.xpath("(//div[normalize-space()='Add Goal Observers'])[1]"),
				By.cssSelector(".ant-switch-handle"));

		type(By.xpath("(//input[@type='text'])[3]"), "");
		Thread.sleep(1000);
		clickJS(By.xpath("(//div[@class='flex gap-2 items-center select-none'])[2]"));

		clickJS(By.xpath("(//button[@id='member-save'])[2]"));

		// Schedule FollowUp
		clickSwitchButtonOfElement(By.xpath("(//div[normalize-space()='Schedule Follow-Up for Goal'])[1]"),
				By.cssSelector(".ant-switch-handle"));

		type(By.xpath("(//input[@type='search'])[3]"), "Weekly");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[title='Weekly'] div[class='ant-select-item-option-content']"))).click();
		Thread.sleep(1000);
		type(By.xpath("(//input[@type='search'])[4]"), "12:00 PM");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"div[class='ant-select-item ant-select-item-option ant-select-item-option-active'] div[class='ant-select-item-option-content']")))
				.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='ant-select-selector'])[5]")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[title='30 min'] div[class='ant-select-item-option-content']"))).click();

		// Submit goal
		clickJS(By.xpath("//button[@type='submit']"));

		// Wait for confirmation
		waitForVisible(By.xpath("//div[contains(@class,'ant-notification-notice-success')]"));
		System.out.println("Goal created successfully!");

		driver.close();
	}
@Test
	public void usersCanSeeCompletedGoals() throws InterruptedException {

		Goto();
		loginApplication();


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Goals']")));
		element.click();
		WebElement element1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='tab-button-Completed']")));
		element1.click();

		driver.close();

	}

	public void usersCanSeeDelayedGoals() throws InterruptedException {

		Goto();
		loginApplication();



		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Goals']")));
		element.click();
		WebElement element1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='tab-button-Delayed']")));
		element1.click();

		driver.close();

	}
@Test
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
@Test
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
@Test
	public void usersCanUpdateStatus() throws InterruptedException {

		Goto();
		loginApplication();


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Goals']")));
		element.click();
		WebElement element1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-testid='goal-menu-btn-0']")));
		element1.click();
		WebElement element2 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-testid='update-menu-btn-0']")));
		element2.click();

		WebElement element3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='On Track']")));
		element3.click();

		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[title='Completed'] div[class='ant-select-item-option-content']")));
		element4.click();

		WebElement element5 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@data-placeholder='Comment about the status']")));
		element5.sendKeys("Ok");
		WebElement element6 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Update']")));
		element6.click();
		driver.close();
	}

	public void userCanSetUpFollowupForGoal() throws InterruptedException, IOException {

		Goto();
		loginApplication();



		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Goals']")));
		element.click();
		WebElement element1 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid='goal-create-btn']")));
		element1.click();
		WebElement element2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='goal-team']")));
		element2.click();
		LocalDateTime currentDateTime = LocalDateTime.now();
		String Title = "testing of user can set up followup for goal"+ currentDateTime;
		WebElement element3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("goals_goals_headers_0_goal_title")));
		element3.sendKeys(Title);

		WebElement element4 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='is-empty is-editor-empty']")));
		element4.sendKeys("testcase");

		Actions action = new Actions(driver);
		for (int i = 0; i < 3; i++) {

			action.sendKeys(Keys.PAGE_DOWN).perform();
			Thread.sleep(2000);
		}

		WebElement selectTeam = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[data-testid='tab-button-Select Team']")));

		selectTeam.click();
		WebElement FrontendTeam = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='frontend']")));
		FrontendTeam.click();

		WebElement Firstmember = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='memberList-0']")));
		Firstmember.click();

		WebElement element6 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-testid='goal-submit-btn']")));
		element6.click();

		WebElement element7 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[normalize-space()='"+Title+"']")));
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(element7);
		actions1.perform();
		element7.click();

		WebElement element8 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Add a Follow up')]")));
		element8.click();

		WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm border-2 border-teal-500 disabled:border-gray-400 relative'] div[class='flex items-center gap-2 justify-center']")));
		element9.click();

		WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("agenda-next")));
		element10.click();

		WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meeting_meeting_at")));
		element11.click();

		WebElement element12 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-picker-today-btn")));
		element12.click();
		Thread.sleep(3000);
		// Get the current local time

		LocalTime currentTime = LocalTime.now();
		// Extract hour and minute components
		int hour = currentTime.getHour();
		int minute = currentTime.getMinute();
		System.out.println(hour);
		System.out.println(minute);
		// Print the hour and minute

		if (hour == 10 && minute >= 1 && minute <= 29) {

			driver.findElement(By.xpath("//div[text()='11:00 PM']")).click();

		} else if (hour == 10 && minute >= 30 && minute <= 59) {
			driver.findElement(By.xpath("//div[text()='11:30 PM']")).click();

		} else if (hour == 11 && minute >= 1 && minute < 29) {
			driver.findElement(By.xpath("//div[text()='12:00 PM']")).click();

		} else if (hour == 11 && minute >= 30 && minute < 59) {

			driver.findElement(By.xpath("//div[text()='12:30 PM']")).click();

		} else if (hour == 12 && minute >= 1 && minute < 29) {

			driver.findElement(By.xpath("//div[text()='01:00 PM']")).click();
		} else if (hour == 12 && minute >= 30 && minute < 59) {
			driver.findElement(By.xpath("//div[text()='01:30 PM']")).click();
		} else if (hour == 13 && minute >= 1 && minute < 29) {

			driver.findElement(By.xpath("//div[text()='02:00 PM']")).click();

		} else if (hour == 13 && minute >= 30 && minute < 59) {

			driver.findElement(By.xpath("//div[text()='02:50 PM']")).click();
		} else if (hour == 14 && minute >= 1 && minute < 29) {
			driver.findElement(By.xpath("//div[text()='03:00 PM']")).click();

		} else if (hour == 14 && minute >= 30 && minute < 59) {
			driver.findElement(By.xpath("//div[text()='03:30 PM']")).click();

		} else if (hour == 15 && minute >= 1 && minute < 29) {
			driver.findElement(By.xpath("//div[text()='04:00 PM']")).click();
		} else if (hour == 15 && minute >= 30 && minute < 59) {
			driver.findElement(By.xpath("//div[text()='04:30 PM']")).click();

		} else if (hour == 16 && minute >= 1 && minute < 29) {
			driver.findElement(By.xpath("//div[text()='05:00 PM']")).click();

		} else if (hour == 16 && minute >= 30 && minute < 59) {

			driver.findElement(By.xpath("//div[text()='05:30 PM']")).click();
		} else if (hour == 17 && minute >= 1 && minute < 29) {
			driver.findElement(By.xpath("//div[text()='06:00 PM']")).click();
		} else if (hour == 17 && minute >= 30 && minute < 59) {
			driver.findElement(By.xpath("//div[text()='06:30 PM']")).click();
		} else if (hour == 18 && minute >= 1 && minute < 29) {
			driver.findElement(By.xpath("//div[text()='07:00 PM']")).click();

		} else if (hour == 18 && minute >= 30 && minute < 59) {
			driver.findElement(By.xpath("//div[text()='07:30 PM']")).click();

		}

		driver.findElement(By.cssSelector("button[type='submit'] div[class='flex items-center gap-2 justify-center']"))
				.click();

		WebElement element13 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"div[class='relative space-y-2'] button[type='button'] div[class='flex items-center gap-2 justify-center']")));
		element13.click();

		WebElement element14 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative'] div[class='flex items-center gap-2 justify-center']")));
		element14.click();

		WebElement element15 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meeting-save")));
		element15.click();
	}

}
