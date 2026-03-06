package utils;

import java.util.HashMap;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeOptionsConfig {

    public static ChromeOptions getChromeOptions(String downloadPath) {

	
	       ChromeOptions options = new ChromeOptions();

	        options.setBinary(
	            "/Applications/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing"
	        );

	        // Headless settings
	        options.addArguments("--headless=new");
	        options.addArguments("--disable-gpu");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--window-size=1920,1080");
	        options.addArguments("--start-maximized");
	        options.addArguments("--remote-allow-origins=*");
	        options.addArguments("--disable-extensions");
	        options.addArguments("--disable-notifications");
	        options.addArguments("--blink-settings=imagesEnabled=false"); 
	        options.addArguments("--disable-infobars"); 
	        options.addArguments("--force-device-scale-factor=1");

	        // Download preferences (IMPORTANT)
	        HashMap<String, Object> prefs = new HashMap<>();
	        prefs.put("download.default_directory", downloadPath);
	        prefs.put("download.prompt_for_download", false);
	        prefs.put("download.directory_upgrade", true);
	        prefs.put("safebrowsing.enabled", true);

	        options.setExperimentalOption("prefs", prefs);
	        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
	        options.setAcceptInsecureCerts(true);

	        return options;
	    }
}
