package Tejaswini_code;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
/**
 * 
 * @author Rupali
 */

public class Scripts_upload {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;

	@Test(priority = 1)
	public void open() throws InterruptedException {

		String f1 = "C:\\ExtentReports\\scripts_report";
		report = new ExtentReports(f1);
		System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");
		// if(cols[0].contains("@"))
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("http://45.112.2.195:83/");

		
		//To login the application
		driver.findElement(By.name("email")).sendKeys("test@vendor2.com");
		driver.findElement(By.name("password")).sendKeys("Tech@123#");
		Thread.sleep(2000);
		WebElement login_button = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		login_button.click();
		Thread.sleep(2000);
		System.out.println("login");
		Thread.sleep(2000);

		
		//To live vendor campaign
		driver.get("http://45.112.2.195:83/vendor/live-campaign/");

		WebElement a = driver.findElement(By.xpath("//table[@id='campaign_notebook_table']/tbody/tr[1]/td[3]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;                   //javascript excutor used for forcly click on element
		Thread.sleep(2000);
		executor.executeScript("arguments[0].click()", a);

		
		// click on action icon
		driver.findElement(By.xpath(
				"/html/body/div[4]/div/div[2]/div/div/div[3]/div[2]/div/div/section/div/div/div[1]/h5/span/div/span/i"))
				.click();

		
		// click on scripts text
		driver.findElement(By.xpath(
				"/html/body/div[4]/div/div[2]/div/div/div[3]/div[2]/div/div/section/div/div/div[1]/h5/span/div/ul/li[1]/a"))
				.click();

		
		//browze file for upload
		WebElement browzeFile = driver.findElement(By.xpath("/html/body/div[4]/div/div[6]/div/div/div/div[2]/div/form/div/center/div/input"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", browzeFile);
		Thread.sleep(2000);
		uploadFile("F:\\Data_Files\\sample.pdf");
		
		//submit file
		driver.findElement(By.xpath("//button[@id='submit_script']")).click();

	
	}
		
		public static void setClipboardData(String string) {
			// StringSelection is a class that can be used for copy and paste operations.
			StringSelection stringSelection = new StringSelection(string);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
		public static void uploadFile(String fileLocation) {
			try {
				
				/** @param Upload scripts file 
				  */
				
				Thread.sleep(3000);
				// Setting clipboard with file location
				setClipboardData(fileLocation);
				// native key strokes for CTRL, V and ENTER keys
				Robot robot = new Robot();

				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		
		
	
	}
}
