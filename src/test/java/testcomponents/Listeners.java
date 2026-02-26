package testcomponents;

import java.lang.reflect.Field;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import extentReports.ExtentManager;
import utils.ScreenshotUtils;

public class Listeners implements ITestListener {
    private static ExtentReports extent = ExtentManager.getReporter();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("=================================================");
        System.out.println("Test Suite Started: " + context.getName());
        System.out.println("=================================================");
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String testName = result.getTestClass().getRealClass().getSimpleName() 
                         + " :: " + result.getMethod().getMethodName();
        
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
        
        test.assignCategory(result.getTestClass().getRealClass().getSimpleName());
        test.info("Test Started: " + result.getMethod().getMethodName());
        
        System.out.println("▶ Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, 
            MarkupHelper.createLabel("Test PASSED", ExtentColor.GREEN));
        System.out.println("✓ Test Passed: " + result.getMethod().getMethodName());
        extentTest.remove();
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, 
            MarkupHelper.createLabel("Test FAILED", ExtentColor.RED));
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = getDriver(result);
        
        if (driver != null) {
            try {
                String screenshotPath = ScreenshotUtils.takeScreenshot(
                    driver, 
                    result.getMethod().getMethodName()
                );
                
                if (screenshotPath != null) {
                    extentTest.get().addScreenCaptureFromPath(
                        screenshotPath, 
                        "Failure Screenshot"
                    );
                    System.out.println("✓ Screenshot captured and attached to report");
                }
            } catch (Exception e) {
                extentTest.get().log(Status.WARNING, 
                    "Could not capture screenshot: " + e.getMessage());
                System.err.println("✗ Screenshot capture failed: " + e.getMessage());
            }
        } else {
            extentTest.get().log(Status.WARNING, 
                "Driver instance not found, screenshot not captured");
            System.err.println("✗ Driver is null for test: " + result.getMethod().getMethodName());
        }

        System.out.println("✗ Test Failed: " + result.getMethod().getMethodName());
        extentTest.remove();
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ExtentTest test = extent.createTest(
            result.getTestClass().getRealClass().getSimpleName() 
            + " :: " + result.getMethod().getMethodName()
        );
        extentTest.set(test);
        extentTest.get().log(Status.SKIP, 
            MarkupHelper.createLabel("Test SKIPPED", ExtentColor.ORANGE));
        extentTest.get().skip(result.getThrowable());
        
        System.out.println("⊘ Test Skipped: " + result.getMethod().getMethodName());
        extentTest.remove();
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("=================================================");
        System.out.println("Test Suite Finished: " + context.getName());
        System.out.println("Total Tests: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("=================================================");
        
        // Flush the report
        ExtentManager.flush();
    }

    /**
     * Get WebDriver from test instance using reflection
     */
    private WebDriver getDriver(ITestResult result) {
        Object testInstance = result.getInstance();
        WebDriver driver = null;

        try {
            // Try getting driver from superclass (BaseTest)
            Class<?> superclass = testInstance.getClass().getSuperclass();
            if (superclass != null) {
                Field field = superclass.getDeclaredField("driver");
                field.setAccessible(true);
                driver = (WebDriver) field.get(testInstance);
                return driver;
            }
        } catch (Exception e) {
            // Try current class
            try {
                Field field = testInstance.getClass().getDeclaredField("driver");
                field.setAccessible(true);
                driver = (WebDriver) field.get(testInstance);
                return driver;
            } catch (Exception ex) {
                System.err.println("Could not access driver field: " + ex.getMessage());
            }
        }

        return driver;
    }
}