package utils;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            // Create screenshots directory if it doesn't exist
            String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots";
            File directory = new File(screenshotDir);
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Created screenshots directory: " + screenshotDir);
            }
            
            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            
            // Create unique filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String path = screenshotDir + "/" + fileName;
            
            // Copy file
            FileUtils.copyFile(src, new File(path));
            System.out.println("Screenshot saved: " + path);
            
            // Return relative path for ExtentReports
            return "screenshots/" + fileName;
            
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}