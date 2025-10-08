
package applaudpagetesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.BaseTest;


public class Applaud extends BaseTest {

//	public Applaud() {
//		super(); // This will initialize the WebDriver in the BaseTest class
//	}

@Test
	public void sendApplaud() throws InterruptedException {

		Goto();

		loginApplication();

		// avoidFeedbackpopup();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		avoidFeedbackpopup();

		 WebElement applaudTab = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Applaud']")));
            applaudTab.click();
            			
            // Wait and click on Create button
            WebElement createButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='applaud-create-button']")));
            createButton.click();

            // Wait and select member
            WebElement memberInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_id")));
            memberInput.click();
            memberInput.sendKeys("Lokesh Sharma");
            WebElement memberOption = wait.until(
            	    ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Lokesh Sharma']")));
            	memberOption.click();
        
            // Wait and select category from dropdown
            WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='select-category']")));
            dropdownElement.click();
            Thread.sleep(2000);
           	WebElement categoryName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Initiator']")));
            categoryName.click();
            dropdownElement.click();
            
			WebElement summarizer = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@title='The one who links, restates, concludes, and summarizes.']")));
			summarizer.click();
			categoriesField.click();

		
            // Wait and enter comment
            WebElement commentBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("comment")));
            commentBox.sendKeys("xyz");

            // Wait and click submit button
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
            submitButton.click();
	
	}

	public void useMonthFilter() throws InterruptedException {

		Goto();

		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(55));
		avoidFeedbackpopup();

		WebElement applaudbutton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Applaud']")));
		applaudbutton.click();

		avoidFeedbackpopup();
		 	WebElement selectMonth = wait.until(
    				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select month']")));
    		selectMonth.click();
    		List<WebElement> currentMonth = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='ant-picker-cell-inner']")));
    		  Random random = new Random();
  	        WebElement month = currentMonth.get(random.nextInt(currentMonth.size()));
    		month.click();
    		String monthName = month.getText();
    		List<WebElement> dateFeilds = wait.until(
    				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("//div[contains(text(),',')]")));
    		for(WebElement dateField : dateFeilds ) {
    		String dateText = dateField.getText();
    		Assert.assertTrue(dateText.contains(monthName));
    		Thread.sleep(3000);
        }
	}

	public void seeReceivedApplauds() throws InterruptedException {

		Goto();

		loginApplication();	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		avoidFeedbackpopup();

		WebElement applaudbutton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Applaud']")));
		applaudbutton.click();
		avoidFeedbackpopup();

		WebElement RecievedButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Received']")));
		RecievedButton.click();

	}

	public void seeSentApplauds() throws InterruptedException {

		Goto();

		loginApplication();

		avoidFeedbackpopup();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement applaudButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Applaud']")));
		applaudButton.click();

		avoidFeedbackpopup();

		WebElement SentApplaudButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sent']")));

		SentApplaudButton.click();

	}
}
