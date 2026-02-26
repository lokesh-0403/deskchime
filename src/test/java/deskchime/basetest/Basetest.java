package deskchime.basetest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import loginpagetesting.ChromeOptionsConfig;

public class Basetest {
	
	public void initializeDriver() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis =new FileInputStream("/deskchime2.managementproject/src/main/java/deskchime/resources/GlobalData.properties");
		prop.load(fis);
		 String browserName =prop.getProperty("browser");
		 if(browserName.equals("Chrome")) {
			 
			

		 }
		 else if (browserName.equals("edge")){
			 WebDriver driver=new EdgeDriver();


		}
	}
}
