package org.testing.test;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTest {

	private static ChromeDriverService service;
	private WebDriver driver;

	@BeforeClass
	public static void createAndStartService() throws IOException{
		
		System.setProperty("webdriver.chrome.driver", new File("src/main/resources/chromedriver.exe").toString());
		service = new ChromeDriverService.Builder().usingDriverExecutable(new File("src/main/resources/chromedriver.exe")).usingAnyFreePort().build();
		service.start();
		
	}

	@AfterClass
	public static void stopService() {
		service.stop();
	}
	
	@Before
	public void createDriver() {
		ChromeOptions co = new ChromeOptions();
		//co.addArguments("--headless");
		driver = new RemoteWebDriver(service.getUrl(), co);
	}

	@After
	public void quitDriver() {
		driver.quit();
	}

	@Test
	public void googleIt() throws IOException, InterruptedException{
		
	    driver.get("http://www.google.com/");
	    Thread.sleep(5000);  // Let the user actually see something!
	    WebElement searchBox = driver.findElement(By.name("q"));
	    searchBox.sendKeys("ChromeDriver");
	    searchBox.submit();
	    Thread.sleep(5000);  // Let the user actually see something!
	    driver.quit();

		
	}

}