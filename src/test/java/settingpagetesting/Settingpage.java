package settingpagetesting;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.BaseTest;


public class Settingpage extends BaseTest {

	public Settingpage() {
		super(); // This will initialize the WebDriver in the BaseTest class
	}


@Test
	public void userCreatedAndDeletedSuccessfully() throws InterruptedException {

		Goto();
		loginApplication();



		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Settings']")));
		element.click();
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
	public void testingOfSettingPage() throws InterruptedException {
		Goto();
		loginApplication();



		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Settings']")));
		element.click();
		Actions action1=new Actions(driver);
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='My Profile']")));
		action1.moveToElement(element1);
		action1.perform();
		element1.click();
		driver.close();

	}

}
