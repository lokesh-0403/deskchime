package feedbackspagetesting;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import basetest.BaseTest;

	@Test()
	
	public class TestingOfFeedbackPage extends BaseTest {

	private WebDriverWait wait;
	public String year =  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
	public String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM"));
	public TestingOfFeedbackPage() {
		super(); // This will initialize the WebDriver in the BaseTest class
	}
  // Reusable method to login and close popup
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
    
    @BeforeMethod
    public void initWait() {
        // Driver is already initialized by BaseTest's @BeforeMethod
        // Just verify it's not null
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized! Check BaseTest setup.");
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("✓ Wait initialized in test class");
    }


    @Test       //Passed
    public void templateCreation() throws InterruptedException {
      	Goto();
		loginApplication();
		closePopup();
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

    @Test    //Passed
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
        updateBtn.click();
        System.out.println("Template edited successfully!");
    }


   @Test   //Passed
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
        String teamMateName = "Lokesh";
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

    @Test  // passed
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
        searchTemplate.sendKeys("Template form scratch");
        Thread.sleep(1000);
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
            String teamMateName = "Lokesh Sharma";
          
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
    
 //   @Test
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

    @Test // passed
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
		Thread.sleep(2000);
	}

   @Test //fixed
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
		Thread.sleep(1000);
		  WebElement selectOption = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='ant-select-item-option-content'][normalize-space()='Pending']")));
			selectOption.click();
			Thread.sleep(3000);

	}

   @Test // passed
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
		Thread.sleep(1000);
		  WebElement selectOption = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[normalize-space()='Answered']")));
			selectOption.click();
			Thread.sleep(3000);

	}


    @Test // passed
	public void userCanAskForFeedbackByExternalLink() throws InterruptedException {
        Actions action = new Actions(driver);
		Goto();
		loginApplication();
		Actions actions = new Actions(driver);
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
	
		
		WebElement feedbackScratchNextbtn = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Next']")));
		
		

		actions.moveToElement(feedbackScratchNextbtn);
		actions.perform();
		feedbackScratchNextbtn.click();
		
		System.out.println(">> This is the after question page ");	
		
		   String teamMateName = "Lokesh Sharma";
	          
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
	
		WebElement question = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                		"button[data-testid='question-next-btn']")));
		actions.moveToElement(question);
		actions.perform();
		question.click();

		System.out.println(">> This is the after Selecting the member page ");	
	try {	
		WebElement Toaster = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ant-notification-notice-description")));
		String ToasterToast= Toaster.getText();
		System.out.println(">>, Message - " + ToasterToast);
	}catch(Exception e) {}

	WebElement SubmitButton = wait.until(ExpectedConditions
			.visibilityOfElementLocated(By.cssSelector("button[data-testid='question-next-btn']")));
	actions.moveToElement(SubmitButton);
	actions.perform();
	SubmitButton.click();
	Thread.sleep(3000);
	WebElement successRoaster = wait.until(ExpectedConditions
			.visibilityOfElementLocated(By.cssSelector("div[class='ant-notification-notice-message']")));
	String successMessage = successRoaster.getText();
	System.out.println(successMessage);
	Assert.assertEquals(successMessage,"Feedback Assigned Successfully");
	
	WebElement createdTab = wait.until(ExpectedConditions
			.visibilityOfElementLocated(By.xpath("(//div[normalize-space()='Created'])[1]")));
	createdTab.click();
	
	WebElement recentlyCreatedFeedback = wait.until(ExpectedConditions
			.visibilityOfElementLocated(By.xpath("(//a[contains(@class,'text-slate-900') and contains(@class,'font-semibold')])[1]")));
	recentlyCreatedFeedback.click();
	
	
		WebElement inviteMembers = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Invite members']")));
		actions.moveToElement(inviteMembers);
		actions.perform();
		inviteMembers.click();
		
		
		
		  // Step 1: Locate and interact with the INPUT field directly (not the span)
	    WebElement emailInput = wait.until(ExpectedConditions
	            .elementToBeClickable(By.cssSelector("input[id='externalEmails']")));
	    
	    // Clear and type email
	    emailInput.click();
	    emailInput.clear();
	    emailInput.sendKeys("testerzasya@gmail.com");
	    
	    Thread.sleep(1000); // Wait for dropdown to appear
	    
	    // Step 2: Wait for dropdown to be visible
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.cssSelector("div.ant-select-dropdown")));
	    
	    // Step 3: Click the dropdown option using JavaScript for reliability
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement dropdownOption = wait.until(ExpectedConditions
	            .presenceOfElementLocated(By.xpath(
	                    "//div[contains(@class, 'ant-select-item-option-content') and text()='testerzasya@gmail.com']")));
	    
	    js.executeScript("arguments[0].click();", dropdownOption);
	    
	    Thread.sleep(1000);
	    WebElement searchButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("span[class='anticon anticon-search ant-select-suffix']")));
	    action.moveToElement(searchButton).click().build().perform();
	    // Step 4: Wait for dropdown to close
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(
	            By.cssSelector("div.rc-virtual-list")));
	    
	    // Step 5: Verify the email was added by checking the tag/chip element
	    try {
	        WebElement emailTag = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//span[contains(@class, 'ant-select-selection-item') and contains(., 'testerzasya@gmail.com')]")));
	        System.out.println("✓ Email successfully added: " + emailTag.getText());
	    } catch (Exception e) {
	        System.out.println("✗ Email tag not found - may need to retry");
	    }
	
	   
	    // Step 6: Click Send Invite button
	    WebElement sendInvite = wait.until(ExpectedConditions
	            .elementToBeClickable(By.xpath("//button[.//div[normalize-space()='Send invite']]")));
	    
	    js.executeScript("arguments[0].scrollIntoView(true);", sendInvite);
	    Thread.sleep(500);
	    
	    // Use both JS click and regular click for maximum reliability
	    try {
	        sendInvite.click();
	    } catch (Exception e) {
	        js.executeScript("arguments[0].click();", sendInvite);
	    }
	    
	    // Step 7: Verify success message
	    try {
	        WebElement endSuccessMessage = wait.until(ExpectedConditions
	                .visibilityOfElementLocated(By.cssSelector("div.ant-notification-notice-message")));
	        System.out.println("✓ Success: " + endSuccessMessage.getText());
	    } catch (Exception e) {
	        System.out.println("✗ No success message found");
	    }
	    
	    Thread.sleep(2000);
	}
}
