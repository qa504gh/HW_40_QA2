package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Firefox_40_QA2 {

	static Properties p = new Properties();

	static WebDriver driver;

	public static void main(String[] cla) throws InterruptedException, FileNotFoundException, IOException {
		// 	Logger class used to set the log level. setLevel(Level.OFF)>>> turning all logs OFF.
		Logger.getLogger("").setLevel(Level.OFF);
		p.load(new FileInputStream("./input.properties"));
		
		System.out.println("Browser: " + p.getProperty("browserF"));
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		
		FirefoxOptions option = new FirefoxOptions();
		option.addArguments("--disable-notifications");
		driver = new FirefoxDriver();
		
		//--- Test Start time
		final long start = System.currentTimeMillis();
		System.out.println("Test Start Time: " + start);
		Thread.sleep(1000);
		
		String timeStampST = new SimpleDateFormat("MM.dd.YYYY: HH.mm.ss").format(new Date(start));
		System.out.println("Test Strated: " + timeStampST);

		Thread.sleep(2000);
		driver.get(p.getProperty("url"));
		System.out.println("Check 1 - Opening in browser selected URL");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Title: " + driver.getTitle());
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("id_email")))).sendKeys(p.getProperty("fb_account"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(System.getenv("fb_password"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("u_0_b"))).click();
		System.out.println("Check 2 - Login Page");
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("xpath_timeline")))).click();
		System.out.println("Check 3 - Selecting button Timeline/Profile Image");
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("xpath_friends")))).click();
		System.out.println("Check 4 - Selecting button Friends");
		
		Thread.sleep(500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("xpath_num_friends")))).click();
		System.out.println("Check 5 - Selecting button Friends Count");
		
		Thread.sleep(500);	
		//String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div/div[1]/div/div/div[1]/div/div/div/div/div/a[3]/div[1]/span/span[2]"))).getText();
		String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("xpath_num_friends")))).getText();
		System.out.println("You have " + friends + " friends");
		
		Thread.sleep(500);	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("xpath_settings")))).click();
		System.out.println("Check 6 - Selecting button Account/Settings");
		
		
		Thread.sleep(2000);	
		String exitBTN = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("xpath_logout")))).getText();
		System.out.println("Check 7 - getText: Friends Count");
		System.out.println("exitBTN: " + exitBTN + " <- menu text");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(p.getProperty("xpath_logout")))).click();
		System.out.println("Check 8 - Exit btn");
		 
		Thread.sleep(1000);
		//-----------------------------------------------------------
		final long finish = System.currentTimeMillis();
		Thread.sleep(1000);
		//-----------------------------------------------------------
		
		driver.quit();
		System.out.println("Check 9 - Closing browser window");
		
		//-----------------------------------------------------------
		System.out.println("Test End Time: " + finish/1000.0);
	
		String timeStamp = new SimpleDateFormat("MM.dd.YYYY: HH.mm.ss").format(new Date(finish));
		System.out.println("Test Ended: " + timeStamp);
        
		System.out.println("Response time: " + (finish - start) / 1000.0 + " seconds");
		//-----------------------------------------------------------
		
	}//
}//
