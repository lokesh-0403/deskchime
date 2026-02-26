package followupspagetesting;


import java.time.Duration;
import java.time.LocalTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.BaseTest;
import retryanalyzer.RetryAnalyzer;

public class Followuppage extends BaseTest {

	public Followuppage() {
		super(); // This will initialize the WebDriver in the BaseTest class
	}

	
	  private void closePopup() throws InterruptedException {
	      
    	  try {
              WebElement skipPopup = wait.until(
                  ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Skip']")));
              skipPopup.click();
          } catch (Exception e) {
              System.out.println("No popup found or already closed");
          }
    	  Thread.sleep(1000);
        try {
            WebElement feedbackPopup = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Close']")));
            feedbackPopup.click();
        } catch (Exception e) {
            System.out.println("No popup found or already closed");
        }
        Thread.sleep(1000);   
        try {
            WebElement skipPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Skip']")));
            skipPopup.click();
        } catch (Exception e) {
            System.out.println("No popup found or already closed");
        }
        
        WebElement workspaceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='org-select-input']")));
        workspaceElement.click();
        WebElement listedWorkspace = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Yesh Tester']")));
        listedWorkspace.click();
        Thread.sleep(1000);
    }
	
//	public void followupCreatedAndDeleted() throws InterruptedException {
//
//		Goto();
//		loginApplication();
//
//
//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
//
//		WebElement element = wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Follow Ups']")));
//
//		element.click();
//
//		WebElement element1 = wait.until(ExpectedConditions
//				.visibilityOfElementLocated(By.cssSelector("a[data-testid='create-follow-up-button']")));
//
//		element1.click();
//
//		WebElement element2 = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Casual')]")));
//		element2.click();
//
//		WebElement element3 = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#meeting_meeting_title")));
//
//		element3.sendKeys("followup functionality");
//
//		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
//				By.cssSelector("div[class='tiptap ProseMirror text-sm min-h-[80px] p-3 max-h-80 overflow-y-auto']")));
//		element4.sendKeys("user interface");
//		WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(
//				By.cssSelector("button[type='submit'] div[class='flex items-center gap-2 justify-center']")));
//		element5.click();
//
//		WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meeting_meeting_at")));
//		element6.click();
//
//		WebElement element7 = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-picker-today-btn")));
//		element7.click();
//		Thread.sleep(2000);
//		// Get the current local time
//
//		LocalTime currentTime = LocalTime.now();
//		// Extract hour and minute components
//		int hour = currentTime.getHour();
//		int minute = currentTime.getMinute();
//		System.out.println(hour);
//		System.out.println(minute);
//
//		if (hour == 10 && minute >= 1 && minute <= 29) {
//
//			driver.findElement(By.xpath("//div[text()='11:00 PM']")).click();
//
//		} else if (hour == 10 && minute >= 30 && minute <= 59) {
//			driver.findElement(By.xpath("//div[text()='11:30 PM']")).click();
//
//		} else if (hour == 11 && minute >= 1 && minute < 29) {
//			driver.findElement(By.xpath("//div[text()='12:00 PM']")).click();
//
//		} else if (hour == 11 && minute >= 30 && minute < 59) {
//
//			driver.findElement(By.xpath("//div[text()='12:30 PM']")).click();
//
//		} else if (hour == 12 && minute >= 1 && minute < 29) {
//
//			driver.findElement(By.xpath("//div[text()='01:00 PM']")).click();
//		} else if (hour == 12 && minute >= 30 && minute < 59) {
//			driver.findElement(By.xpath("//div[text()='01:30 PM']")).click();
//		} else if (hour == 13 && minute >= 1 && minute < 29) {
//
//			driver.findElement(By.xpath("//div[text()='02:00 PM']")).click();
//
//		} else if (hour == 13 && minute >= 30 && minute < 59) {
//
//			driver.findElement(By.xpath("//div[text()='02:50 PM']")).click();
//		} else if (hour == 14 && minute >= 1 && minute < 29) {
//			driver.findElement(By.xpath("//div[text()='03:00 PM']")).click();
//
//		} else if (hour == 14 && minute >= 30 && minute < 59) {
//			driver.findElement(By.xpath("//div[text()='03:30 PM']")).click();
//
//		} else if (hour == 15 && minute >= 1 && minute < 29) {
//			driver.findElement(By.xpath("//div[text()='04:00 PM']")).click();
//		} else if (hour == 15 && minute >= 30 && minute < 59) {
//			driver.findElement(By.xpath("//div[text()='04:30 PM']")).click();
//
//		} else if (hour == 16 && minute >= 1 && minute < 29) {
//			driver.findElement(By.xpath("//div[text()='05:00 PM']")).click();
//
//		} else if (hour == 16 && minute >= 30 && minute < 59) {
//
//			driver.findElement(By.xpath("//div[text()='05:30 PM']")).click();
//		} else if (hour == 17 && minute >= 1 && minute < 29) {
//			driver.findElement(By.xpath("//div[text()='06:00 PM']")).click();
//		} else if (hour == 17 && minute >= 30 && minute < 59) {
//			driver.findElement(By.xpath("//div[text()='06:30 PM']")).click();
//		} else if (hour == 18 && minute >= 1 && minute < 29) {
//			driver.findElement(By.xpath("//div[text()='07:00 PM']")).click();
//
//		} else if (hour == 18 && minute >= 30 && minute < 59) {
//			driver.findElement(By.xpath("//div[text()='07:30 PM']")).click();
//
//		}
//
//		//// body/div[6]/div[1]/div[2]/div[1]/div[2]/button[1]/span[1]/span[1]//*[name()='svg']
//
//		driver.findElement(By.cssSelector("button[type='submit'] div[class='flex items-center gap-2 justify-center']"))
//				.click();
//		Thread.sleep(2000);
//		WebElement element8 = wait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='memberList-3']")));
//		element8.click();
//
//		WebElement element9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
//				"div[class='relative space-y-2'] button[type='button'] div[class='flex items-center gap-2 justify-center']")));
//		element9.click();
//		WebElement element10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
//				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative'] div[class='flex items-center gap-2 justify-center']")));
//		element10.click();
//
//		WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meeting-save")));
//		element11.click();
//
//		System.out.println("meeting created successfully");
//
//		WebElement element12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
//				"button[class='tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default text-teal-700 border-2 hover:border-teal-700 hover:text-teal-700 active:bg-teal-500 active:text-white disabled:border-zinc-400 disabled:text-zinc-400 px-3 py-2 text-sm rounded-full leading-none ant-dropdown-trigger border-slate-300 w-9 h-9 md:w-9 md:h-9 relative']")));
//		element12.click();
//
//		WebElement element13 = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(.,'Delete')]")));
//		element13.click();
//		WebElement element14 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Yes']")));
//		element14.click();
//		System.out.println("meeting deleted sccessfully");
//	}

@Test(retryAnalyzer=RetryAnalyzer.class) 
	public void meetingWithoutSpecifyDateAndTime() throws InterruptedException {

		Goto();
		loginApplication();
		closePopup();


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Follow Ups']")));

		element.click();

		WebElement element1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='create-follow-up-button']")));

		element1.click();
		WebElement element2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Casual')]")));
		element2.click();

		WebElement element3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#meeting_meeting_title")));

		element3.sendKeys("followup functionality");

		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[class='tiptap ProseMirror text-sm min-h-[80px] p-3 max-h-80 overflow-y-auto']")));
		element4.sendKeys("user interface");
		WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("button[type='submit'] div[class='flex items-center gap-2 justify-center']")));
		element5.click();

		WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meeting_meeting_at")));
		element6.click();

		WebElement element7 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Close']")));
		element7.click();

		WebElement element8 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Next')]")));
		element8.click();
		WebElement element9 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-notification-notice-message")));

		if (element9.isDisplayed()) {
			String dialogText = element9.getText();
			System.out.println("Popup message: " + dialogText);

		}
		System.out.println("meeting is not created please specify date and time");

	}

@Test(retryAnalyzer=RetryAnalyzer.class) 
	public void followupCreatedAndDeleted1() throws InterruptedException {

		Goto();
		loginApplication();
		closePopup();


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Follow Ups']")));

		element.click();

		WebElement element1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='create-follow-up-button']")));

		element1.click();

		WebElement element2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Casual')]")));
		element2.click();

		WebElement element3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#meeting_meeting_title")));
		String titleName ="followup functionality";
		element3.sendKeys(titleName);

		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[class='tiptap ProseMirror text-sm min-h-[80px] p-3 max-h-80 overflow-y-auto']")));
		element4.sendKeys("user interface");

		WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("button[type='submit'] div[class='flex items-center gap-2 justify-center']")));
		element5.click();

		WebElement element6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meeting_meeting_at")));
		element6.click();

		WebElement date = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Today']")));
		date.click();
		Thread.sleep(2000);

		LocalTime currentTime = LocalTime.now();
		int hour = currentTime.getHour();
		int minute = currentTime.getMinute();

		// Calculate the target time slot
		LocalTime targetTime = currentTime.plusMinutes((60 - minute) % 30).plusMinutes(30);
		int targetHour = targetTime.getHour();
		int targetMinute = targetTime.getMinute();

		// Format the target time to match the time slots on the page
		String timeSlot = String.format("%02d:%02d %s", (targetHour % 12 == 0) ? 12 : targetHour % 12, targetMinute,
				(targetHour < 12) ? "AM" : "PM");
		
		WebElement duration = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='ant-select-selection-item'])[2]")));
		duration.click();
		// Click the target time slot
		WebElement timeslot = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='30 min']")));
		timeslot.click();

		WebElement time= wait.
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='bg-white border border-slate-300 px-4 py-2 rounded-md hover:bg-gray-200 cursor-pointer'])[1]")));
		time.click();
		
		driver.findElement(By.cssSelector("button[type='submit'] div[class='flex items-center gap-2 justify-center']"))
				.click();
		Thread.sleep(2000);

		WebElement selectTeamMembers = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[normalize-space()='Lokesh Sharma'])[2]")));
		selectTeamMembers.click();

		WebElement inviteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"div[class='relative space-y-2'] button[type='button'] div[class='flex items-center gap-2 justify-center']")));
		inviteButton.click();

		WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
				"time-next")));
		nextButton.click();

		WebElement element11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meeting-save")));
		element11.click();

		System.out.println("meeting created successfully");

		WebElement element12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[normalize-space()='"+titleName+"']/ancestor::a/ancestor::div[1]//button[@class='tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default text-teal-700 border-2 hover:border-teal-700 hover:text-teal-700 active:bg-teal-500 active:text-white disabled:border-zinc-400 disabled:text-zinc-400 px-3 py-2 text-sm rounded-full leading-none ant-dropdown-trigger border-slate-300 w-9 h-9 md:w-9 md:h-9 relative']")));
		element12.click();
		
		WebElement delete = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Delete']")));

		delete.click();

		WebElement confirmation = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Yes']")));

		confirmation.click();


		System.out.println("meeting deleted successfully");
	}

}