package loginpagetesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import basetest.BaseTest;

@Test
public class Testingofloginpage extends BaseTest {

	private WebDriverWait wait;
	public Testingofloginpage() {
		super(); // This will initialize the WebDriver in the BaseTest class
	}
	@FindBy(css = "input[placeholder='Email address']")
	WebElement useremail;

	@FindBy(css = "input[placeholder='Password']")
	WebElement passwordEle;

	@FindBy(css = "button[type='submit']")
	WebElement submit;

	   
    @BeforeMethod
    public void initWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }
    
	public void login(){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		Goto();
		WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/auth/login']")));
		login.click();
	}
		
	public void validcredendetials() throws InterruptedException {

		Goto();
		loginApplication();
	}

	public void invalidcredentials1() throws InterruptedException {
		login();
		PageFactory.initElements(driver, this);
		useremail.sendKeys("yesh@zasyasolution.com");
		passwordEle.sendKeys("Yesh255198@");
		submit.click();
		
		WebElement w1 = driver.findElement(By.cssSelector(".ant-notification-notice-message"));
		if (w1.isDisplayed()) {
			String dialogText = w1.getText();
			System.out.println("Popup message: " + dialogText);

		}

	}

	
	public void invalidcredentials2() throws InterruptedException {
		login();
		PageFactory.initElements(driver, this);
		useremail.sendKeys("yesh@zasyasolutions.com");
		passwordEle.sendKeys("Yesh2551981@");
		submit.click();
		WebElement w1 = driver.findElement(By.cssSelector(".ant-notification-notice-message"));

		if (w1.isDisplayed()) {
			String dialogText = w1.getText();
			System.out.println("Popup message: " + dialogText);
		}
	}

	public void emptyusernamefield() throws InterruptedException {
	login();
		PageFactory.initElements(driver, this);
		passwordEle.sendKeys("Yesh2551981@");
		submit.click();
		Thread.sleep(1000);
		WebElement w1 = driver.findElement(By.cssSelector(".ant-form-item-explain-error"));

		if (w1.isDisplayed()) {
			String dialogText = w1.getText();
			System.out.println("Popup message: " + dialogText);
		}
	}

	public void emptypasswordfield() throws InterruptedException {
	login();
		PageFactory.initElements(driver, this);
		useremail.sendKeys("yesh@zasyasolutions.com");		
		submit.click();
		Thread.sleep(1000);
		WebElement w1 = driver.findElement(By.cssSelector(".ant-form-item-explain-error"));

		if (w1.isDisplayed()) {
			String dialogText = w1.getText();
			System.out.println("Popup message: " + dialogText);
		}
	}


	public void passwordresetlink() throws InterruptedException {
		login();


		driver.findElement(By.xpath("(//div[normalize-space()='Forgot password ?'])[2]")).click();
		driver.findElement(By.cssSelector("input[data-testid='forgot-email-input']"))
				.sendKeys("yeshsharma516032@gmail.com");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("button[data-testid='forgot-sent-btn']")).click();
		
		WebElement dialog = driver.findElement(By.xpath(
				"//div[@class='ant-notification-notice-message']"));
		if (dialog.isDisplayed()) {
			String dialogText = dialog.getText();
			System.out.println("Popup message: " + dialogText);

		}
		
	}

	public void userlogout() throws InterruptedException {
		Goto();
		loginApplication();


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-testid='logout-btn']")));
		element.click();

	}
}
