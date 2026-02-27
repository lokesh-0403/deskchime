package settingpagetesting;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;

public class TeamTestingPage extends BaseTest {

    // ✅ FIX #4: Made static so value persists across @Test method instances
    private static String nameValue;

    public TeamTestingPage() {
        super();
    }

    // ─── Helper: Safe JS Click ────────────────────────────────────────────────
    /**
     * Attempts a normal click first; falls back to JS click if intercepted.
     */
    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            System.out.println("Normal click failed, falling back to JS click: " + e.getMessage());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    // ─── Helper: Scroll to Center + Safe Click ────────────────────────────────
    /**
     * Scrolls element into the center of the viewport before clicking.
     * This prevents the element from being hidden behind sticky headers/footers.
     */
    private void scrollAndClick(WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        Thread.sleep(400); // Allow scroll animation to settle
        safeClick(element);
    }

    // ─── Helper: Dismiss Open Dropdowns ──────────────────────────────────────
    /**
     * Sends ESC key to close any open Ant Design dropdown/overlay
     * that might intercept clicks on other elements.
     */
    private void dismissOpenDropdowns() throws InterruptedException {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ESCAPE).perform();
            Thread.sleep(400);
        } catch (Exception e) {
            System.out.println("Dismiss dropdown attempt: " + e.getMessage());
        }
    }

    // ─── Helper: Close Popups ─────────────────────────────────────────────────
    /**
     * Handles all post-login popups and workspace selection.
     */
    private void closePopup() throws InterruptedException {

        // Attempt 1: Skip popup
        try {
            WebElement skipPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[normalize-space()='Skip']")));
            skipPopup.click();
            System.out.println("Closed skip popup");
        } catch (Exception e) {
            System.out.println("No skip popup found or already closed");
        }
        Thread.sleep(800);

        // Attempt 2: Feedback/Close button popup
        try {
            WebElement feedbackPopup = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[aria-label='Close']")));
            feedbackPopup.click();
            System.out.println("Closed feedback popup");
        } catch (Exception e) {
            System.out.println("No feedback popup found or already closed");
        }
        Thread.sleep(800);

        // Attempt 3: Second skip popup check
        try {
            WebElement skipPopup2 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[normalize-space()='Skip']")));
            skipPopup2.click();
            System.out.println("Closed second skip popup");
        } catch (Exception e) {
            System.out.println("No second skip popup found or already closed");
        }

        // Workspace selection
        WebElement workspaceElement = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-testid='org-select-input']")));
        workspaceElement.click();

        WebElement listedWorkspace = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='Yesh Tester']")));
        listedWorkspace.click();
        Thread.sleep(800);
    }

    // ─── Helper: Navigate to Teams Section ───────────────────────────────────
    /**
     * Shared navigation logic to reach the Teams section from the Settings panel.
     * Extracted to avoid duplication between test methods.
     */
    private void navigateToTeamsSection() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Check if settings sub-menu is already expanded
        try {
            WebElement settingPanel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//ul[@class='ant-menu ant-menu-sub ant-menu-inline']")));
            if (settingPanel.isDisplayed()) {
                System.out.println("Settings panel is already expanded");
            }
        } catch (Exception e) {
            // Panel not visible, click Settings to expand it
            WebElement settingSection = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='Settings']")));
            settingSection.click();
            System.out.println("Clicked Settings to expand panel");
        }
        Thread.sleep(800);

        // Click My Profile to load the settings sub-section
        WebElement myProfile = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='My Profile']")));
        myProfile.click();

        // Refresh to stabilize the page before interacting with Teams
        driver.navigate().refresh();
        Thread.sleep(1000);

        // ✅ FIX #2: Use inherited 'wait' from BaseTest — removed local re-declaration
        // Find and click Teams link
        WebElement teams = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//span[normalize-space()='Teams'])[1]")));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", teams);
        Thread.sleep(400);
        teams.click();
        Thread.sleep(800);
    }

    // ─── Test 1: Create Team ──────────────────────────────────────────────────
    @Test(priority = 1)
    public void teamCreatedAndDeletedSuccessfully() throws InterruptedException {

        Goto();
        loginApplication();
//        closePopup();
        navigateToTeamsSection();

        // Click the Create button (second instance on the page)
        WebElement create = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[normalize-space()='Create'])[2]")));
        scrollAndClick(create);

        // ── Fill in Team Name ──────────────────────────────────────────────────
        nameValue = "Designer Team";
        WebElement name = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("teamName")));
        name.clear();
        name.sendKeys(nameValue);

        // ── Fill in Description ────────────────────────────────────────────────
        WebElement description = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("teamDescription")));
        description.clear();
        description.sendKeys("This is a team for UI/UX designers");

        // ── Select Manager ────────────────────────────────────────────────────
        WebElement manager = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select a manager to add')]/ancestor::div[1]")));
        manager.click();
        Thread.sleep(800);

        WebElement managerOption = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(text(),'Lokesh Sharma')])[1]")));
        managerOption.click();
        Thread.sleep(800);

        // Dismiss manager dropdown before opening members dropdown
        dismissOpenDropdowns();

        // ── Select First Member (Zasya Tester) ────────────────────────────────
        WebElement members = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select a user to add')]/ancestor::div[1]")));
        members.click();
        Thread.sleep(800);

        WebElement memberOption2 = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(text(),'Zasya Tester')])[2]")));
        memberOption2.click();
        Thread.sleep(800);

        // ── Select Second Member (Lokesh Sharma via search) ───────────────────
        WebElement membersInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Select a user to add')]/ancestor::div[1]//input[1]")));
        membersInput.click();
        membersInput.sendKeys("Lokesh Sharma");

        // Wait for the visible (non-hidden) dropdown to appear
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'ant-select-dropdown') and not(contains(@class,'ant-select-dropdown-hidden'))]")));

        // Click the first matching option in the visible dropdown
        WebElement memberOption1 = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[contains(@class,'ant-select-dropdown') and not(contains(@class,'ant-select-dropdown-hidden'))]//div[@class='ant-select-item-option-content'])[1]")));
        memberOption1.click();
        Thread.sleep(800);

        // ✅ FIX #3: Explicitly dismiss dropdown before clicking Save
        dismissOpenDropdowns();

        // ✅ FIX #1: Scroll Save button to center + safe click to prevent interception
        WebElement submitButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Save']")));
        scrollAndClick(submitButton);
        Thread.sleep(1000);

        // ── Assert success notification ────────────────────────────────────────
        WebElement notification = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-notification-notice-message']")));
        String notificationText = notification.getText();
        System.out.println("Notification received: " + notificationText);
        Assert.assertEquals(notificationText, "Team created successfully",
            "Team creation notification did not match expected text");

        // Wait for notification to auto-dismiss
        Thread.sleep(3000);
        System.out.println("✓ Team created successfully: " + nameValue);
    }

    // ─── Test 2: Remove User from Team ───────────────────────────────────────
    @Test(priority = 2, dependsOnMethods = {"teamCreatedAndDeletedSuccessfully"})
    public void removeuserFromTeam() throws InterruptedException {

        Goto();
        loginApplication();
//        closePopup();
        navigateToTeamsSection();

        // Locate the row for the team we created in test 1
        // ✅ FIX #4: nameValue is static so it is available here
        WebElement optionButtonOfCreatedTeam = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[normalize-space()='" + nameValue + "']/ancestor::td/ancestor::tr[1]//button[@data-testid='team-action-menu-button']")));
        scrollAndClick(optionButtonOfCreatedTeam);
        Thread.sleep(600);

        // Click Edit
        WebElement editButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@data-testid='edit-team-button']")));
        editButton.click();
        Thread.sleep(800);

        // Get current members list size to target the last member's Remove button
        List<WebElement> membersList = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//div[@class='flex items-center justify-between p-4 bg-slate-50 border border-slate-200 rounded-lg hover:bg-slate-100 transition-colors']")));
        int listSize = membersList.size();
        System.out.println("Members found: " + listSize);

        Assert.assertTrue(listSize > 0, "No members found in the team to remove");

        // Click Remove on the last member in the list
        WebElement removeButtonOfMember = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[normalize-space()='Remove'])[" + listSize + "]")));
        scrollAndClick(removeButtonOfMember);
        Thread.sleep(800);

        // ✅ FIX #1 (applied here too): Scroll Update button to center before clicking
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement updateButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Update']")));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", updateButton);
        Thread.sleep(400);

        // Dismiss any dropdowns before clicking Update
        dismissOpenDropdowns();
        safeClick(updateButton);
        Thread.sleep(1000);

        // Assert success notification
        WebElement notification = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-notification-notice-message']")));
        String notificationText = notification.getText();
        System.out.println("Notification received: " + notificationText);
        Assert.assertEquals(notificationText, "Team updated successfully",
            "Team update notification did not match expected text");

        System.out.println("✓ Member removed from team successfully");
    }

    // ─── Helper: Delete Team ──────────────────────────────────────────────────
    /**
     * Deletes the team created during testing.
     * Call this from a cleanup @AfterTest method if needed.
     */
    
    
    @Test(priority = 3, dependsOnMethods = {"removeuserFromTeam"})
    public void deleteTeam() throws InterruptedException {

    	 Goto();
         loginApplication();
//         closePopup();
         navigateToTeamsSection();
    	
        WebElement optionButtonOfCreatedTeam = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='" + nameValue + "']/ancestor::td/ancestor::tr[1]//button[@data-testid='team-action-menu-button']")));
        scrollAndClick(optionButtonOfCreatedTeam);
        Thread.sleep(600);

        WebElement deleteButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@data-testid='delete-team-button']")));
        deleteButton.click();
        Thread.sleep(600);

        WebElement confirmDeleteButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Yes']")));
        confirmDeleteButton.click();

        WebElement notificationDelete = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-notification-notice-message']")));
        String notificationText = notificationDelete.getText();
        System.out.println("Delete notification: " + notificationText);
        Assert.assertEquals(notificationText, "Team deleted successfully",
            "Team delete notification did not match expected text");

        Thread.sleep(1000);
        System.out.println("✓ Team deleted successfully: " + nameValue);
    }
}