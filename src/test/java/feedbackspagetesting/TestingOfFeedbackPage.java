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
  // Reusable method to login and close popup
    private void closePopup() throws InterruptedException {
      
        try {
            WebElement feedbackPopup = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Close']")));
            feedbackPopup.click();
        } catch (Exception e) {
            System.out.println("No popup found or already closed");
        }
        Thread.sleep(5000);   
        try {
            WebElement skipPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Skip']")));
            skipPopup.click();
        } catch (Exception e) {
            System.out.println("No popup found or already closed");
        }
      
    }
    
    // Reusable method to click Feedbacks menu
    
    private void clickFeedbacksMenu() {
        WebElement feedbacksMenu = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Feedbacks']")));
        feedbacksMenu.click();
    }
    
    // Reusable method to click Templates button
    private void clickTemplatesButton() {
        WebElement templatesBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='Templates']")));
        templatesBtn.click();
    }
    private void clickRequestFeedbackButton() {
        WebElement reqFeedbackBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='Request a Feedback']")));
        reqFeedbackBtn.click();
    }
    
    // Reusable method to click action menu on template row
    private void clickTemplateActionMenu() {
        WebElement actionMenu = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[1]/div/div/main/div/div[3]/div/div/div/div/div/div/div[2]/table/tbody/tr[2]/td[4]/div/button")));
        actionMenu.click();
    }
    
    // Reusable method to scroll and click element
    private void scrollAndClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

 
    public void templateCreation() throws InterruptedException {
      	goto();
		loginApplication();
		closePopup()
        clickFeedbacksMenu();
        clickTemplatesButton();
        Thread.sleep(1000);
        // Click "Create from scratch" button
        WebElement createFromScratch = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "(//div[normalize-space()='Create From Scratch'])[2]")));
        createFromScratch.click();
        Thread.sleep(1000);

        // Add title
        WebElement titleInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("template_name")));
        titleInput.clear();
        titleInput.sendKeys("TEST");

        // Add description
        WebElement descriptionInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("template_description")));
        descriptionInput.clear();
        descriptionInput.sendKeys("test the feedback section");

        // Click next/continue button
        WebElement continueBtn = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "(//button[contains(@class,'rounded-lg')])[3]")));
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", continueBtn);

        Thread.sleep(500);
        
        continueBtn.click();
        
//    continueBtn.click();

        // Click on multiple choice selector
        WebElement multipleChoice = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(
                "span[class='ant-select-selection-item'] div[class='font-normal text-xs lg:text-sm leading-140 lg:leading-140 text-slate-600']")));
        multipleChoice.click();

        // Enter question
        WebElement questionInput = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[placeholder='E.g. What would you like to ask from a person.']")));
        questionInput.clear();
        questionInput.sendKeys("anything");

        // Enter option 1
        WebElement option1 = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='E.g. Option 1']")));
        option1.clear();
        option1.sendKeys("true");

        // Enter option 2
        WebElement option2 = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='E.g. Option 2']")));
        option2.clear();
        option2.sendKeys("false");

        // Click add question button
        WebElement addQuestionBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(
                "button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative'] div[class='flex items-center gap-2 justify-center']")));
        addQuestionBtn.click();

        // Click submit button
        WebElement submitBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Submit']")));
        submitBtn.click();
        
        WebElement goToTemplate = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Go to templates']")));
        goToTemplate.click();
        
        
        Thread.sleep(2000); // Wait to see result
        System.out.println("Template created successfully!");
    }

 public void saveAndEditTemplate() throws InterruptedException {
    	Goto();
		loginApplication();	
		closePopup();  
    	clickFeedbacksMenu();
        clickTemplatesButton();
        
        Thread.sleep(1000); // Wait for templates to load
        clickTemplateActionMenu();

        // Click edit option
        WebElement editLink = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("li[data-testid='edit-template']")));
        editLink.click();

        // Edit description
        WebElement descriptionInput = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("#template_description")));
        descriptionInput.sendKeys(Keys.ENTER);
        descriptionInput.sendKeys("Updates value");
        
        Thread.sleep(1000);

        // Click save buttons (3 times as in original)
       
            WebElement nextBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(
                		   "(//button[contains(@class,'rounded-lg')])[3]")));
            
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", nextBtn);
            Thread.sleep(500);
            nextBtn.click();
            Thread.sleep(500);
  
            WebElement nextBtn2 = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(
                    		   "//div[normalize-space()='Next']")));
                
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", nextBtn2);
                Thread.sleep(1000);
                nextBtn2.click();
                Thread.sleep(500);
        
        WebElement updateBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(
                		   "//div[normalize-space()='Update']")));
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", updateBtn);
        
        System.out.println("Template edited successfully!");
    }

//    @Test(dataProvider="getData")
    public void userCanMakeUseOfPreMadeFeedbackTemplate() throws InterruptedException {
       	Goto();
		loginApplication();
		closePopup();
        clickFeedbacksMenu();
        clickTemplatesButton();
        
        Thread.sleep(1000);
        
        // Click use template option
        WebElement gotoDefaultTemplate = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='Default Template']")));
        gotoDefaultTemplate.click();

        Thread.sleep(1000); // Wait for templates to load
        clickTemplateActionMenu();

        WebElement useTemplate = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.cssSelector("li[data-testid='use-template']")));
        useTemplate.click();
        Thread.sleep(1000);
        
        // Click through steps
        
            WebElement nextBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(
                		"button[data-testid='question-next-btn']")));
                		
            nextBtn.click();
            Thread.sleep(500);
        

        // Select team      
        String teamMateName = "Yesh Sharma";
        WebElement teamSelector = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='All Team Members']")));
        teamSelector.click();

        WebElement nameInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class='ant-input']")));
        nameInput.sendKeys(teamMateName);
        
        WebElement teamOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='flex gap-2 items-center select-none']")));
        teamOption.click();
        
        Thread.sleep(2000);
        
        WebElement nextBtn1 = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Next']")));
         nextBtn1.click();
            Thread.sleep(500);
        
        // Scroll to and click final submit button
        WebElement submitBtn = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                "button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative'] div[class='flex items-center gap-2 justify-center']")));
        scrollAndClick(submitBtn);

        // Click final confirmation
        WebElement confirmText = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(
                "div[class='ant-notification-notice ant-notification-notice-success ant-notification-notice-closable']")));
      String confText = confirmText.getText();
        Assert.assertEquals(confText, "Feedback Assigned Successfully");
        
        if (confText.equals("Feedback Assigned Successfully")) {
            System.out.println("Pre-made template used successfully!");
        } else {
            System.out.println("Expected message not found! Actual: " + confText);
        }
        }

//    @Test(dataProvider="getData")
    public void userCanAskForFeedbackFromSpecificTeamMember() throws InterruptedException {
      	Goto();
		loginApplication();
		closePopup();
        clickFeedbacksMenu();
        clickRequestFeedbackButton();
        
        Thread.sleep(1000);
        
        // Click use template option
        WebElement fromTemplateCard = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='From a Template']")));
        fromTemplateCard.click();

        WebElement searchTemplate = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.cssSelector("input[type='text']")));
        searchTemplate.sendKeys("Template from scratch");
        Thread.sleep(500);
        WebElement searchedTemplate = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[data-testid='feedback-template-item-0']")));
        searchedTemplate.click();
        
        Thread.sleep(1000); // Wait for templates to load
      
        // Click through first two steps     
        WebElement nextBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(
                		"button[data-testid='question-next-btn']")));
            nextBtn.click();
            Thread.sleep(500);

        // Click "Select specific members" option
            String teamMateName1 = "Yesh Sharma";
            String teamMateName = "1";
            WebElement teamSelector = wait.until(
                    ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[normalize-space()='All Team Members']")));
            teamSelector.click();

            WebElement nameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class='ant-input']")));
            nameInput.sendKeys(teamMateName);
            Thread.sleep(2000);
            WebElement teamOption = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='flex gap-2 items-center select-none']")));
            teamOption.click();
            
            Thread.sleep(3000);
            
           
            
            WebElement freqCycle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(
            "//*[text()='Daily']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", freqCycle);
            Thread.sleep(700);
            freqCycle.click();
            
            WebElement endFeedbackDate = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(
            "//span[normalize-space()='Would you like to add an end date for the feedback?']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", endFeedbackDate);
            Thread.sleep(700);
            endFeedbackDate.click();
            
            WebElement calendar = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
            "div[class='ant-picker-input']")));
            calendar.click();
            
            //calender
    	
            WebElement yearLabel = driver.findElement(By.cssSelector("button[aria-label='Choose a year']"));
            yearLabel.click();
            
    		List<WebElement> years = driver.findElements(By.cssSelector("div[class*='ant-picker-cell-inner']"));
    	        for (WebElement y : years) {
    	            if (y.getText().equals(year)) {
    	                y.click();
    	                break;
    	            }
    	        }
 
    	
    	        List<WebElement> months = driver.findElements(By.cssSelector("div[class*='ant-picker-cell-inner']"));
    	        for (WebElement m : months) {
    	            if (m.getText().equals(month)) { 
    	                m.click();
    	                break;
    	            }
    	        }
    		
    	        List<WebElement> currentMonthDates = driver.findElements(
    	                By.cssSelector("td.ant-picker-cell-in-view:not(.ant-picker-cell-disabled) div.ant-picker-cell-inner")
    	        );

    	        Random random = new Random();
    	        WebElement randomDate = currentMonthDates.get(random.nextInt(currentMonthDates.size()));
    	        randomDate.click();
    	        
    	        Thread.sleep(2000);
    		
            WebElement nextBtn1 = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Next']")));
             nextBtn1.click();
                Thread.sleep(500);
            
            // Scroll to and click final submit button
            WebElement submitBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                    "button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative'] div[class='flex items-center gap-2 justify-center']")));
            scrollAndClick(submitBtn);

            // Click final confirmation
            WebElement confirmText = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(
                    "div[class='ant-notification-notice ant-notification-notice-success ant-notification-notice-closable']")));
          String confText = confirmText.getText();
            Assert.assertEquals(confText, "Feedback Assigned Successfully");
    }	
	// public void userCanAskForFeedbackFromWholeTeam() throws InterruptedException {

	// 	Goto();
	// 	loginApplication();
 // 	 	closePopup();
 //        clickFeedbacksMenu();
 //        clickRequestFeedbackButton();
        
 //        Thread.sleep(1000);
        
 //        // Click use template option
 //        WebElement fromTemplateCard = wait.until(
 //            ExpectedConditions.elementToBeClickable(
 //                By.xpath("//div[normalize-space()='From a Template']")));
 //        fromTemplateCard.click();

 //        WebElement searchTemplate = wait.until(
 //                ExpectedConditions.elementToBeClickable(
 //                    By.cssSelector("input[type='text']")));
 //        searchTemplate.sendKeys("Template from scratch");
 //        Thread.sleep(500);
 //        WebElement searchedTemplate = wait.until(
 //                ExpectedConditions.elementToBeClickable(
 //                    By.cssSelector("div[data-testid='feedback-template-item-0']")));
 //        searchedTemplate.click();
        
 //        Thread.sleep(1000); // Wait for templates to load
      
 //        // Click through first two steps     
 //        WebElement nextBtn = wait.until(
 //                ExpectedConditions.elementToBeClickable(By.cssSelector(
 //                		"button[data-testid='question-next-btn']")));
 //            nextBtn.click();
 //            Thread.sleep(500);

 //        // Click "All team Number" option
         
 //            WebElement selectTeam = driver.findElement(By.xpath("//span[normalize-space()='Select Team']"));
 //            selectTeam.click();
            
 //            List<WebElement> teamOption = driver.findElements(By.cssSelector("div[class*='flex justify-between items-center cursor-pointer hover:bg-blue-50 p-2']"));
        	
	//         for (WebElement teamMate : teamOption) {
	//         	teamMate.click();
	//         }
 //            Thread.sleep(3000);
            
    		
 //            WebElement nextBtn1 = wait.until(
 //                    ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Next']")));
 //             nextBtn1.click();
 //                Thread.sleep(500);
            
 //            // Scroll to and click final submit button
 //            WebElement submitBtn = wait.until(
 //                ExpectedConditions.presenceOfElementLocated(By.cssSelector(
 //                    "button[class='rounded-lg w-fit tracking-[0.5px] font-medium flex items-center gap-2 justify-center transition-all duration-200 ease-in-out disabled:cursor-default bg-teal-500 text-white hover:bg-teal-400 active:bg-teal-300 disabled:bg-zinc-400 h-10 px-3 py-2 text-sm relative'] div[class='flex items-center gap-2 justify-center']")));
 //            scrollAndClick(submitBtn);

 //            // Click final confirmation
 //            WebElement confirmText = wait.until(
 //                ExpectedConditions.elementToBeClickable(By.cssSelector(
 //                    "div[class='ant-notification-notice ant-notification-notice-success ant-notification-notice-closable']")));
 //          String confText = confirmText.getText();
 //            Assert.assertEquals(confText, "Feedback Assigned Successfully");
	// }

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
		WebElement recievedTab = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Received']")));
		recievedTab.click();
		   WebElement filterDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='All'])[2]")));
		filterDropdown.click();

		  WebElement selectOption = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[normalize-space()='Pending']")));
			selectOption.click();
			Thread.sleep(3000);

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
		WebElement recievedTab = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Received']")));
		recievedTab.click();
		   WebElement filterDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='All'])[2]")));
		filterDropdown.click();

		  WebElement selectOption = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[normalize-space()='Answered']")));
			selectOption.click();
			Thread.sleep(3000);

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
