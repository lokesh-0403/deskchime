package followupspagetesting;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class Followup1on1 extends BaseTest {

	public Followup1on1() {
		super(); // This will initialize the WebDriver in the BaseTest class
	}

	@Test()
	public void create1on1() throws InterruptedException {

		Goto();
		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement button1on1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("1-on-1s")));

		button1on1.click();

		WebElement createFollowUp = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='create-follow-up-button']")));

		createFollowUp.click();

		WebElement selectTeam = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='qa']")));

		selectTeam.click();

		WebElement selectMate = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@class='flex gap-2 items-center md:space-x-3'])[1]")));

		selectMate.click();

		WebElement selectDate = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select date']")));

		selectDate.click();

		WebElement todayButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-picker-now-btn")));
		todayButton.click();
		Thread.sleep(1000);

		WebElement SelectTime = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='ant-select-selection-search-input'])[3]")));
		SelectTime.click();
		
		List<WebElement> timeList =  wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='ant-select-item-option-content']")));
		
		for(int i=0; i<timeList.size();i++) {
		try {
		WebElement nextTime = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='ant-select-item-option-content'])["+i+"]")));
		nextTime.click();
		}
		catch(Exception e) {}
	
		break;
		
	}
//		LocalTime currentTime = LocalTime.now();
//		int hour = currentTime.getHour();
//		int minute = currentTime.getMinute();
//		System.out.println(hour);
//		Thread.sleep(2000);
//		// Convert to 12-hour format
//
//		// Adjust your if-else statements based on the 12-hour format
//		if (hour == 10 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='11:00 AM']")).click();
//
//		} else if (hour == 10 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='11:30 AM']")).click();
//
//		} else if (hour == 11 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='12:00 PM']")).click();
//
//		} else if (hour == 11 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='12:30 PM']")).click();
//
//		} else if (hour == 12 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='01:00 PM']")).click();
//
//		} else if (hour == 12 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='01:30 PM']")).click();
//
//		} else if (hour == 13 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='02:00 PM']")).click();
//
//		} else if (hour == 13 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='02:30 PM']")).click();
//
//		} else if (hour == 14 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='03:00 PM']")).click();
//
//		} else if (hour == 14 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='03:30 PM']")).click();
//
//		} else if (hour == 15 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='04:00 PM']")).click();
//
//		} else if (hour == 15 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='04:30 PM']")).click();
//
//		} else if (hour == 16 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='05:00 PM']")).click();
//
//		} else if (hour == 16 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='05:30 PM']")).click();
//
//		} else if (hour == 17 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='06:00 PM']")).click();
//
//		} else if (hour == 17 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='06:30 PM']")).click();
//
//		} else if (hour == 18 && minute >= 1 && minute <= 29) {
//			driver.findElement(By.xpath("//div[@title='07:00 PM']")).click();
//
//		} else if (hour == 18 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[@title='07:30 PM']")).click();
//
//		}

		WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='tiptap ProseMirror text-sm min-h-[80px] p-3 max-h-80 overflow-y-auto']")));

		Actions action = new Actions(driver);
		action.moveToElement(description);
		description.sendKeys("" + "Subject: Warning Regarding Unnecessary Leave\n" + "\n" 
				+ "\n"
				+ "This is to formally address your recent absences on [specific dates]. It has been noted that these leaves were not supported by adequate documentation or justification.\n"
				+ "\n"
				+ "Please be reminded that frequent, unjustified absences can affect team productivity and are against company policy. We expect you to provide proper documentation for future absences and to adhere to our leave procedures.\n"
				+ "\n"
				+ "Continued failure to comply may result in further disciplinary action. If you have any concerns, please discuss them with [HR/Supervisor].\n"
				+ "\n" + "Thank you for your attention to this matter.\n" + "\n" + "Sincerely,\n" + "[Your]\n"
				+ "[Your Position]");

		WebElement nextButton = driver.findElement(By.cssSelector(
				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(nextButton).perform();
		nextButton.click();

//

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative']")));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();

		WebElement deleteOptionsButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@data-testid='followupList']//div[2]//div[9]//button[2]")));

		deleteOptionsButton.click();

		WebElement delete = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='py-[5px] px-3']")));

		delete.click();

		WebElement confirmation = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Yes']")));

		confirmation.click();

	}

	@Test
	public void create1on1WithoutSpecifyDateandtime() throws InterruptedException {

		Goto();
		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		WebElement button1on1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("1-on-1s")));

		button1on1.click();

		WebElement createFollowUp = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='create-follow-up-button']")));

		createFollowUp.click();

		WebElement FrontendTeam = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='qa']")));

		FrontendTeam.click();

		WebElement selectMate = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@class='flex gap-2 items-center md:space-x-3'])[1]")));

		selectMate.click();

		WebElement selectDate = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select date']")));

		selectDate.click();

		WebElement TodayButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-picker-now-btn")));
		TodayButton.click();

		WebElement nextButton = driver.findElement(By.cssSelector(
				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(nextButton).perform();
		nextButton.click();

		WebElement errorMessageElement = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[@class='text-red-600 text-sm my-2 font-medium']")));

		String errorText = errorMessageElement.getText();
		Assert.assertEquals(errorText, "Please select date and time");

	}

}
