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
import retryanalyzer.RetryAnalyzer;

import basetest.BaseTest;

public class Followup1on1 extends BaseTest {

	public Followup1on1() {
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
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void create1on1() throws InterruptedException {

		Goto();
		loginApplication();
		closePopup();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement button1on1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("1-on-1s")));

		button1on1.click();

		WebElement createFollowUp = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='create-follow-up-button']")));

		createFollowUp.click();

		WebElement selectTeam = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='developer']")));

		selectTeam.click();

		WebElement selectMate = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Lokesh Sharma')]")));

		selectMate.click();
		String mateName = selectMate.getText();
		System.out.println(">> Mate Name : "+mateName);
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
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='ant-select-item-option-content']")));
		WebElement nextTime = timeList.get(1);	
		System.out.println(">>>"+nextTime.getText());
		nextTime.click();
		
//		for(int i=0; i<timeList.size();i++) {
//		try {
//		WebElement nextTime = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='ant-select-item-option-content'])["+i+"]")));
//		nextTime.click();
//		}
//		catch(Exception e) {}
//	
//		break;	
//	}
		
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

		Thread.sleep(1000);
		WebElement continueButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(normalize-space(),'Continue')]")));
		continueButton.click();

		WebElement continueButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"(//button[contains(normalize-space(),'Continue')])[2]")));
		continueButton2.click();

		WebElement optionsButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[normalize-space()='"+mateName+"']/ancestor::a/ancestor::div[1]//button[@class='tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default text-teal-700 border-2 hover:border-teal-700 hover:text-teal-700 active:bg-teal-500 active:text-white disabled:border-zinc-400 disabled:text-zinc-400 px-3 py-2 text-sm rounded-full leading-none ant-dropdown-trigger border-slate-300 w-9 h-9 md:w-9 md:h-9 relative']")));

		optionsButton.click();

		WebElement delete = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Delete']")));

		delete.click();

		WebElement confirmation = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Yes']")));

		confirmation.click();

	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void create1on1WithoutSpecifyDateandtime() throws InterruptedException {

		Goto();
		loginApplication();
		closePopup();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		WebElement button1on1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("1-on-1s")));

		button1on1.click();

		WebElement createFollowUp = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='create-follow-up-button']")));

		createFollowUp.click();

		WebElement selectTeam = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='developer']")));

		selectTeam.click();

		WebElement selectMate = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Lokesh Sharma')]")));

		selectMate.click();
		String mateName = selectMate.getText();
		System.out.println(">> Mate Name : "+mateName);
		WebElement selectDate = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select date']")));

		selectDate.click();

		WebElement todayButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-picker-now-btn")));
		todayButton.click();
		Thread.sleep(1000);

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
