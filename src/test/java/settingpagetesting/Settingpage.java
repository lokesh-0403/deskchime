package settingpagetesting;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import retryanalyzer.RetryAnalyzer;
import basetest.BaseTest;


public class Settingpage extends BaseTest {

	public Settingpage() {
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


	@Test
	public void userCreatedAndDeletedSuccessfully1() throws InterruptedException {

		Goto();
		loginApplication();
//		closePopup();
		WebElement settingPanel =  wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='ant-menu ant-menu-sub ant-menu-inline']")));
		if(settingPanel.isDisplayed()) {
			System.out.println("Setting panel is displayed");
		}else{
		WebElement settingSection = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Settings']")));
		settingSection.click();
		}
		Thread.sleep(1000);
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='My Profile']")));
		
		element1.click();
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement manageUser = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Manage Users']")));
	
		 js.executeScript("arguments[0].scrollIntoView(true);", manageUser);
		manageUser.click();
		WebElement create = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Create'])[2]")));
		create.click();
		
		//create user data
		String nameValue = "ankit joseph";
		WebElement name = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name")));
		name.sendKeys(nameValue);
		WebElement email = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		email.sendKeys("ankitjoseph@maulana.com");
		
		tagsInput();
		WebElement roleDropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='select-user-role']")));
		roleDropdown.click();
		WebElement roleOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Member')]")));
		roleOption.click();
		WebElement teamDropdown = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ant-select ant-select-lg ant-select-outlined ant-select-in-form-item select-tag tag-select-box ant-select-single ant-select-show-arrow']//div[@class='ant-select-selector']")));
		teamDropdown.click();	
		
		WebElement teamOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'testing team')]")));
		teamOption.click();
		WebElement submitButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
		submitButton.click();
		WebElement notification = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ant-notification-notice-message']")));
		Assert.assertEquals(notification.getText(),"Member Saved Successfully");
//		notification.findElement(By.xpath("//span[@aria-label='Close']")).click();
		Thread.sleep(4000);
		
		//Delete user
		
		 WebElement optionBuuttonOfCreatedUser = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='"+nameValue+"']/ancestor::td/ancestor::tr[1]//button[@data-testid='user-list-menu-button']")));
		 
		 optionBuuttonOfCreatedUser.click();
		 
		 WebElement deleteButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-testid='delete-user-button']")));
		 deleteButton.click();
		 WebElement confirmDeleteButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Yes']")));
		 confirmDeleteButton.click();
		 
		 WebElement notificationDelete = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ant-notification-notice-message']")));
		
		 Assert.assertEquals(notificationDelete.getText(), "Member Deleted Successfully.");
		 Thread.sleep(1000);
	}
	
	
	
	
	public void tagsInput() {
		WebElement tagField = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ant-select-selection-overflow']")));
		tagField.click();
		WebElement tagInput = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='tags']")));
		tagInput.sendKeys("qa");
		WebElement tagOption = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='ant-select-item-option-content'])[1]")));
		String optionText = tagOption.getText();
		tagOption.click();
		WebElement presentTag = null;
		try {
		presentTag =  wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[normalize-space()='"+optionText+"'])[1]")));
		}catch(Exception e) {
			System.out.println("tags are not present");
		}
		
		if(presentTag.isDisplayed()) {
			System.out.println("tags are present");
			presentTag.click();
			}else {
				System.out.println("tags are not present");
				tagOption.click();
			}
		
	}
	
	@Test
	public void testingOfSettingPage() throws InterruptedException {
		Goto();
		loginApplication();

		WebElement settingPanel =  wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='ant-menu ant-menu-sub ant-menu-inline']")));
		if(settingPanel.isDisplayed()) {
			System.out.println("Setting panel is displayed");
		}else{
		WebElement settingSection = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Settings']")));
		settingSection.click();
		}
		Thread.sleep(1000);
		Actions action1=new Actions(driver);
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='My Profile']")));
		action1.moveToElement(element1);
		action1.perform();
		element1.click();
		Thread.sleep(2000);
		

	}

}
