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

//@Test
	public void userCreatedAndDeletedSuccessfully() throws InterruptedException {

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
		Actions action1=new Actions(driver);
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='My Profile']")));
		action1.moveToElement(element1);
		action1.perform();
		element1.click();

		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentwindowId = it.next();
		String childwindowid = it.next();
		driver.switchTo().window(childwindowid);
		driver.get("https://temp-mail.org/en/");
		WebElement element3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='click-to-copy']")));
		element3.click();

		driver.switchTo().window(parentwindowId);
		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative'] div[class='flex items-center gap-2 justify-center']")));
		element4.click();

		WebElement element5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name")));
		element5.sendKeys("ankit joseph");



		Actions actions = new Actions(driver);
		WebElement ele = driver.findElement(By.id("email"));
		actions.moveToElement(ele).click().keyDown(Keys.COMMAND).sendKeys(Keys.chord("v")).build().perform();

		WebElement element6 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='ant-select-selection-overflow']")));
		element6.click();

		WebElement element7 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[title='qa']")));
		element7.click();

		WebElement element8 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='ant-select-selection-overflow']")));
		element8.click();

		WebElement element9 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='select-user-role']")));

		element9.click();

		WebElement element10 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[title='Manager']")));
		element10.click();

		WebElement element11 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
		element11.click();

		WebElement element12 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='user-list-menu-button']")));
		element12.click();

		WebElement element13 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-testid='delete-user-button']")));
		element13.click();

		WebElement element14 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Yes']")));
		element14.click();

		driver.close();
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
