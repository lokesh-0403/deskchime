package extentReports ;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    
    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            // Ensure directory exists
            String reportDir = System.getProperty("user.dir") + "/test-output";
            File directory = new File(reportDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            String reportPath = reportDir + "/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            
            // Configure report
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Deskchime Test Results");
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Application", "Deskchime");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
            extent.setSystemInfo("Browser", System.getProperty("browser", "chrome"));
            
            System.out.println("✓ ExtentReports initialized at: " + reportPath);
        }
        return extent;
    }
    
    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
            System.out.println("✓ ExtentReports flushed successfully");
        }
    }
}