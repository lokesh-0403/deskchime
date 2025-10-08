package signuppagetesting;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import loginpagetesting.ChromeOptionsConfig;

@Test
public class Signuppagetesting {
  private WebDriver driver;
    private WebDriverWait wait;

    // Initialize driver and wait
    private void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://deskchime.com/");
    }

    // Helper method for JS click
    private void clickJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // --- Successful Registration ---
//    @Test
    public void successfulregistration() {
        initDriver();

        // Wait for homepage load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Log in')]")));

        // Click Log in
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Log in')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        clickJS(loginBtn);

        // Click Sign up
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Sign Up')]")));
       
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpBtn);
        clickJS(signUpBtn);

        // Wait until signup form appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name")));

        // Fill registration details
        driver.findElement(By.id("register-form_first_name")).sendKeys("Test User");
        driver.findElement(By.id("register-form_company_name")).sendKeys("Treasor");
        driver.findElement(By.id("register-form_company_slug")).sendKeys("kratos");
        driver.findElement(By.id("register-form_email")).sendKeys("testuser2@citmo.net");
        driver.findElement(By.id("register-form_password")).sendKeys("Mmpl@2025");
        driver.findElement(By.id("register-form_confirm_password")).sendKeys("Mmpl@2025");

        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='Sign Up']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpButton);
        clickJS(signUpButton);

        // Continue with next steps after registration success
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Registration Successful!')]")));

        // Then proceed to login
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        firstName.sendKeys("testuser@citmo.net");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("Mmpl@2025");

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        clickJS(submitBtn);

        driver.quit();
    }

    // --- Empty Fields Validation ---\
//    @Test
    public void emptyfields() {
        initDriver();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Log in')]")));

        // Click Log in
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Log in')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        clickJS(loginBtn);

        // Click Sign up
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Sign Up')]")));
       
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpBtn);
      
        clickJS(signUpBtn);

        // Wait until signup form appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name")));

        // Fill registration details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name"))).sendKeys("Test User");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_password"))).sendKeys("Mmpl@2025");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_confirm_password"))).sendKeys("Mmpl@2025");
        
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[normalize-space()='Sign Up']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpButton);
        clickJS(signUpButton);
        
        String companyError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Please enter your company name!')]"))).getText();
        String subdomainError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Please enter your company subdomain!')]"))).getText();

        System.out.println(companyError);
        System.out.println(subdomainError);

        driver.close();
    }

    // --- Invalid Email Format ---
//    @Test
    public void invalidemailformat() {
        initDriver();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Log in')]")));

        // Click Log in
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Log in')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        clickJS(loginBtn);

        // Click Sign up
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Sign Up')]")));
       
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpBtn);
      
        clickJS(signUpBtn);

        // Wait until signup form appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name")));

        // Fill registration details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name"))).sendKeys("Test User");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_company_name"))).sendKeys("llo1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_company_slug"))).sendKeys("lol1");

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_email")));
        emailInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        emailInput.sendKeys(Keys.BACK_SPACE);
        emailInput.sendKeys("yeshsharma516032gmail.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_password"))).sendKeys("Hp31c3329@");

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        clickJS(submitBtn);

        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-form-item-explain-error']"))).getText();
        System.out.println(error);

        driver.close();
    }

    // --- Existing Email Address --- 
//    @Test
    public void existingemailaddress() {
        initDriver();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Log in')]")));

        // Click Log in
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Log in')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        clickJS(loginBtn);

        // Click Sign up
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Sign Up')]")));
       
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpBtn);
        clickJS(signUpBtn);

        // Wait until signup form appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name")));

        // Fill registration details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name"))).sendKeys("Test User");
      
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_company_name"))).sendKeys("lfgbdfloa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_company_slug"))).sendKeys("lgbfgbola");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_email"))).sendKeys("testuser2@citmo.net");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_password"))).sendKeys("Hp31c3329@");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_confirm_password"))).sendKeys("Hp31c3329@");

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        clickJS(submitBtn);

        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-notification-notice-message']")));
        System.out.println("Popup message: " + notification.getText());

        driver.close();
    }

    // --- Password Strength Validation ---
//    @Test
    public void passwordstrength() {
        initDriver();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Log in')]")));

        // Click Log in
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Log in')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        clickJS(loginBtn);

        // Click Sign up
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Sign Up')]")));
       
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpBtn);
        clickJS(signUpBtn);

        // Wait until signup form appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name")));

        // Fill registration details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name"))).sendKeys("Test User");
      
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_company_name"))).sendKeys("lfgbdflob");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_company_slug"))).sendKeys("lgbfgbolb");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_password"))).sendKeys("Hp31c332");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_confirm_password"))).sendKeys("Hp31c332");

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        clickJS(submitBtn);

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-form-item-explain-error']")));
        System.out.println("Popup message: " + error.getText());

        driver.close();
    }

    // --- Password Confirmation ---
//    @Test
    public void passwordconfirmation() {
        initDriver();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Log in')]")));

        // Click Log in
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Log in')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);
        clickJS(loginBtn);

        // Click Sign up
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Sign Up')]")));
       
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpBtn);
        clickJS(signUpBtn);

        // Wait until signup form appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name")));

        // Fill registration details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_first_name"))).sendKeys("Test User");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_company_name"))).sendKeys("llo2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_company_slug"))).sendKeys("lol2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_password"))).sendKeys("Hp31c3329@");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-form_confirm_password"))).sendKeys("Hp31c332");

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        clickJS(submitBtn);

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-form-item-explain-error']")));
        System.out.println("Popup message: " + error.getText());

        driver.close();
    }
}

}
