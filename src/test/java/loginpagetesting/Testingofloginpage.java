package loginpagetesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basetest.BaseTest;

@Test
public class Testingofloginpage extends BaseTest {

	public Testingofloginpage() {
		super(); // This will initialize the WebDriver in the BaseTest class
	}
	@FindBy(css = "input[placeholder='Email address']")
	WebElement useremail;

	@FindBy(css = "input[placeholder='Password']")
	WebElement passwordEle;

	@FindBy(css = "button[type='submit']")
	WebElement submit;
	
//	@Test(dataProvider = "getData")
//	public void validcredendetials(HashMap<String, String> input) throws InterruptedException {
//		WebDriver driver = new ChromeDriver(ChromeOptionsConfig.getChromeOptions());
//
//		driver.manage().window().maximize();
//
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		Login log = new Login(driver);
//
//		log.Goto();
//		log.avoidFeedbackpopup();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement element = wait
//				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid='login-btn']")));
//		element.click();
//
//		log.loginApplication(input.get("email"), input.get("password"));
//	}
//
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "yeshsharma516032@gmail.com");
//		map.put("password", "Yesh1234");
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "yesh@zasyasolutions.com");
//		map1.put("password", "Yesh255198@");
//		return new Object[][] { { map }, { map1 } };
//
//	}

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
		WebElement w1 = driver.findElement(By.cssSelector(".ant-notification-notice-message"));

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
		WebElement w1 = driver.findElement(By.cssSelector(".ant-notification-notice-message"));

		if (w1.isDisplayed()) {
			String dialogText = w1.getText();
			System.out.println("Popup message: " + dialogText);
		}
	}

	public void passwordresetlink() throws InterruptedException {
		Goto();
		loginApplication();


		driver.findElement(By.xpath("//div[text()='Forgot password ?']")).click();
		driver.findElement(By.cssSelector("input[data-testid='forgot-email-input']"))
				.sendKeys("yeshsharma516032@gmail.com");
		driver.findElement(By.cssSelector("button[data-testid='forgot-sent-btn']")).click();
		WebElement dialog = driver.findElement(By.xpath(
				"//div[@class='ant-notification-notice ant-notification-notice-success css-3mqfnx ant-notification-notice-closable']"));
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
