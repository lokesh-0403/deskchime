
package applaudpagetesting;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import basetest.BaseTest;


public class Applaud extends BaseTest {

//	public Applaud() {
//		super(); // This will initialize the WebDriver in the BaseTest class
//	}

//@Test
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
                      
            // Wait and enter comment
            WebElement commentBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("comment")));
            commentBox.sendKeys("xyz");

            // Wait and click submit button
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
            submitButton.click();
            WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='ant-notification-notice-message']")));
            System.out.println("Message shown :"+ notification);
            driver.close();
	}

//	@Test
	public void useMonthFilter() throws InterruptedException {

		Goto();

		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
    		WebElement element = wait.until(
     				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='No Applauds Found']"))); 
    		 if (element.isDisplayed()) {
    			 System.out.println("No Applauds Found");
    			 } else{
    		
    		List<WebElement> dateFeilds = wait.until(
    				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("//div[contains(text(),',')]")));
    		for(WebElement dateField : dateFeilds ) {
    		String dateText = dateField.getText();
    		Assert.assertTrue(dateText.contains(monthName));
    		Thread.sleep(3000);
    		}
        }
	}

//	@Test
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
		
		softAssert("//div[text()='From ']");

	}

//	@Test
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
		
		softAssert("//div[text()='Sent ']");
	
	}
	
	public void softAssert(String locator) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		
		Thread.sleep(2000);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		
		for(int i=0;i<elements.size();i++) {
		boolean isVisible = !elements.isEmpty() && elements.get(i).isDisplayed();

		softAssert.assertTrue(isVisible, "Element should be visible");

		if (isVisible) {
		    System.out.println("Element is present ");		 
		} else {
		    System.out.println("Element not presnt"); 
		}
		System.out.println("Continuing rest of test...");
		softAssert.assertAll();
		}
	}
}
