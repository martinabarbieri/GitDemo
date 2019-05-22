package com.modulodatitest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginModuloDati {
	public WebDriver driver;
	

	public WebDriver LoginModuloDatiNotevoli() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\Francesco\\ModuloDatiProject\\src\\test\\java\\com\\modulodatitest\\datadrivenModuloDati.properties");
		prop.load(fis);
		// <------ Driver Selection ------>
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Francesco\\Downloads\\chromedriver.exe");

		// CORS troubleshooting
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("--disable-web-security");
		// options.addArguments("--disable-gpu");
		options.addArguments("--user-data-dir=~/chromeTemp");
		options.addArguments("–allow-running-insecure-content");
		options.addArguments("start-maximized");
		options.addExtensions(new File("C:\\Users\\Francesco\\Downloads\\Allow-Control-Allow-Originv1.0.3.crx"));
		options.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		// <------ Driver Selection END ------>

		WebDriverWait wait = new WebDriverWait(driver, 10000); // Wait time for events
		// On the login compile username and password
		driver.get(prop.getProperty("url"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usr")));
		driver.findElement(By.id("usr")).sendKeys(prop.getProperty("utenza"));
		System.out.println(prop.getProperty("utenza"));
		driver.findElement(By.id("passwordInput")).sendKeys(prop.getProperty("password"));
		System.out.println(prop.getProperty("password"));
		driver.findElement(By.cssSelector("[title='Effettua il login']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[ng-click='chooseSedia(sedia)']")));
		driver.findElement(By.xpath("//*[text()='Comune di ROMA']")).click();

		// New tab opening sample
		((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");
		driver.switchTo().window(driver.getWindowHandles().toArray()[driver.getWindowHandles().size() - 1].toString());
		driver.get(prop.getProperty("urlModuloDati"));
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	public WebDriver SAccessModuleDetails() throws InterruptedException  {
		System.out.println("Dettaglio Modulo");
		driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]")).click();
		Thread.sleep(2000);
		return driver;
}

}
