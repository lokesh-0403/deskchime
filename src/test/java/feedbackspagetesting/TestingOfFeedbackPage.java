package feedbackspagetesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import basetest.BaseTest;
@Test
public class TestingOfFeedbackPage extends BaseTest {

	public TestingOfFeedbackPage() {
		super(); // This will initialize the WebDriver in the BaseTest class
	}
@Test
	public void templateCreation() throws InterruptedException {

		Goto();
		loginApplication();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Feedbacks']")));
		element.click();
		avoidFeedbackpopup();
		// templates
		WebElement element2 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='feedback-templates-btn']")));
		element2.click();

		// create from scratch
		WebElement element3 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-testid='create-template']")));
		element3.click();
		   Faker faker = new Faker();

	        // Generate random name
	        String name = faker.name().fullName();
		// add title

		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("template_name")));
		element4.sendKeys(name);
		// add description
		driver.findElement(By.id("template_description")).sendKeys("test the feebacksection");

		// click

		driver.findElement(By.cssSelector(
				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative']"))
				.click();
		// click on multiple choice
		driver.findElement(By.cssSelector(
				"span[class='ant-select-selection-item'] div[class='font-normal text-xs lg:text-sm leading-140 lg:leading-140 text-slate-600']"))
				.click();

		driver.findElement(By.cssSelector("input[placeholder='E.g. What would you like to ask from a person.']"))
				.sendKeys("anything");

		driver.findElement(By.xpath("//input[@placeholder='E.g. Option 1']")).sendKeys("true");
		driver.findElement(By.xpath("//input[@placeholder='E.g. Option 2']")).sendKeys("false");
		driver.findElement(By.cssSelector(
				"button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative'] div[class='flex items-center gap-2 justify-center']"))
				.click();
		WebElement element5 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Submit']")));
		element5.click();

	}

	public void userCanMaKEUseOfPreMadeFeedbackTemplate() throws InterruptedException {

		Goto();
		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Feedbacks']")));
		element.click();
		avoidFeedbackpopup();
		WebElement element1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='feedback-templates-btn']")));
		element1.click();
		WebElement element11 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Default Template']")));
		element11.click();

		WebElement element12 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[class='flex flex-1 justify-center']")));
		element12.click();

		WebElement element13 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Use Template']")));
		element13.click();

		WebElement element2 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@data-testid='feedback-scratch-next-btn']")));

		element2.click();

		Actions actions1 = new Actions(driver);
		WebElement element21 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='question-next-btn']")));

		actions1.moveToElement(element21);
		actions1.scrollToElement(element21);

		actions1.perform();
		element21.click();

		WebElement element3 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='memberList-0']")));
		element3.click();
		Thread.sleep(2000);

		WebElement element4 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-permission-next-btn']")));
		actions1.moveToElement(element4);
		actions1.perform();
		element4.click();

		WebElement element5 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-preview-submit-btn']")));

		actions1.moveToElement(element5);
		actions1.perform();
		element5.click();

	}

	public void userCanAskForFeedbackFromSpecificTeamMember() throws InterruptedException {

		Goto();
		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Feedbacks']")));
		element.click();
		avoidFeedbackpopup();
		WebElement element1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='feedback-templates-btn']")));
		element1.click();

		WebElement element11 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Default Template']")));
		element11.click();

		WebElement element12 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[class='flex flex-1 justify-center']")));
		element12.click();

		WebElement element13 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Use Template']")));
		element13.click();

		Actions actions1 = new Actions(driver);
		WebElement element2 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@data-testid='feedback-scratch-next-btn']")));

		actions1.moveToElement(element2);
		actions1.perform();
		element2.click();
		WebElement element21 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='question-next-btn']")));
		actions1.moveToElement(element21);
		actions1.perform();
		element21.click();

		WebElement element3 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[data-testid='tab-button-Select Team']")));

		element3.click();
		WebElement element33 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='frontend']")));
		element33.click();

		WebElement element4 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='memberList-0']")));
		element4.click();

		WebElement element5 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-permission-next-btn']")));
		actions1.moveToElement(element5);
		actions1.perform();
		element5.click();

		WebElement element6 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-preview-submit-btn']")));
		actions1.moveToElement(element6);
		actions1.perform();
		element6.click();

	}

	public void userCanAskForFeedbackFromWholeTeam() throws InterruptedException {

		Goto();
		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Feedbacks']")));
		element.click();
		avoidFeedbackpopup();
		WebElement element1 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='feedback-templates-btn']")));
		element1.click();

		WebElement element11 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='feedback-templates-btn']")));
		element11.click();

		WebElement element12 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Default Template']")));
		element12.click();

		WebElement element13 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[class='flex flex-1 justify-center']")));
		element13.click();

		WebElement element14 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Use Template']")));
		element14.click();

		WebElement element2 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@data-testid='feedback-scratch-next-btn']")));
		element2.click();
		Actions actions1 = new Actions(driver);
		WebElement element21 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='question-next-btn']")));
		actions1.moveToElement(element21);
		actions1.perform();
		element21.click();

		WebElement element3 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[data-testid='tab-button-Select Team']")));

		element3.click();
		WebElement element33 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='frontend']")));
		element33.click();

		WebElement element4 = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='memberList-0']")));
		element4.click();

		// WebElement element44 = wait.until(
		// ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='memberList-1']")));
		// element44.click();

		WebElement element5 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-permission-next-btn']")));
		actions1.moveToElement(element5);
		actions1.perform();
		element5.click();

		WebElement element6 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-preview-submit-btn']")));
		actions1.moveToElement(element6);
		actions1.perform();
		element6.click();

	}

	public void usersCanSeeAllTheFeedbackTheyHaveReceived() throws InterruptedException {

		Goto();
		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Feedbacks']")));
		element.click();
		avoidFeedbackpopup();
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Received']")));
		element1.click();

	}

	public void userCanShortAndFilterFeedbackByPending() throws InterruptedException {

		Goto();
		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Feedbacks']")));
		element.click();
		avoidFeedbackpopup();
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Received']")));
		element1.click();
		WebElement element3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='All']")));
		element3.click();
		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[title='Pending'] div[class='ant-select-item-option-content']")));
		element4.click();

	}

	public void userCanShortAndFilterFeedbackByAnswered() throws InterruptedException {

		Goto();
		loginApplication();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Feedbacks']")));
		element.click();
		avoidFeedbackpopup();
		WebElement element1 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Received']")));
		element1.click();
		WebElement element3 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='All']")));
		element3.click();
		WebElement element4 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[title='Answered'] div[class='ant-select-item-option-content']")));
		element4.click();

	}


	public void userCanAskForFeedbackByExternalLink() throws InterruptedException {

		Goto();
		loginApplication();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		avoidFeedbackpopup();
		WebElement feedbackButton = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Feedbacks']")));
		feedbackButton.click();
		avoidFeedbackpopup();
		WebElement feedbackTemplate = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("a[data-testid='feedback-templates-btn']")));
		feedbackTemplate.click();

		WebElement DefaultButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Default Template']")));
		DefaultButton.click();

		WebElement element12 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[class='flex flex-1 justify-center']")));
		element12.click();

		WebElement useTemplate = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Use Template']")));
		useTemplate.click();

		Actions actions1 = new Actions(driver);
		WebElement feedbackScratchNextbtn = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@data-testid='feedback-scratch-next-btn']")));

		actions1.moveToElement(feedbackScratchNextbtn);
		actions1.perform();
		feedbackScratchNextbtn.click();
		WebElement question = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='question-next-btn']")));
		actions1.moveToElement(question);
		actions1.perform();
		question.click();


		WebElement externalFeedback = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-outside-btn']")));
		actions1.moveToElement(externalFeedback);
		actions1.perform();
		externalFeedback.click();

		WebElement element9 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[data-testid='feedback-emails-input']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='yeshsharma516032@gmail.com';", element9);

		// Alternatively, use Actions to send keys
		Actions actions2 = new Actions(driver);
		actions2.moveToElement(element9).click().sendKeys("yeshsharma516032@gmail.com").perform();

		// Debugging: Print out the input field value
		System.out.println("Input field value: " + element9.getAttribute("value"));
		Actions actions = new Actions(driver);

		// Move the mouse to the specific coordinates and perform click
		actions.moveByOffset(0, 500).click().perform();
		externalFeedback.click();


		WebElement addEmail = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-emails-add-btn']")));
		addEmail.click();
		WebElement nextButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-permission-next-btn']")));
		actions1.moveToElement(nextButton);
		actions1.perform();
		nextButton.click();

		WebElement SubmitButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("button[data-testid='feedback-preview-submit-btn']")));
		actions1.moveToElement(SubmitButton);
		actions1.perform();
		SubmitButton.click();

	}
}