package Kiran_code;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
/**
 * 
 * @author Kiran Bhalerao
 *
 */
public class RFQBulkUpload{
	ExtentReports report;
	ExtentTest test;
	ExtentHtmlReporter htmlreporter;

	WebDriver driver;
 
	@Test
	    public void RFQBulkUoload1() throws InterruptedException {
		try {
			/** @param RFQ Bulk Campaign Upload
			 * @param args 
		    */
			report = new ExtentReports("C:\\ExtentReports\\AddExtVendors\\RFQBullCampaign.html", true);
//			test = report.startTest("RFQ Bulk Upload");
			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");
			driver = new ChromeDriver();
			test = report.startTest("RFQ Bulk Campaign Creation");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
//		driver.navigate().to("");
			driver.get("http://192.168.20.220:85/");
//			driver.get("http://192.168.20.103:8000/");
			driver.findElement(By.name("email")).sendKeys("rupali.jagade@trigensoft.com");
//			driver.findElement(By.name("email")).sendKeys("test@client.com");
			driver.findElement(By.name("password")).sendKeys("Tech@123#");
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.xpath("//*[@id=\"#\"]")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.xpath("//*[@id=\"CampaignList\"]/a")).click();
			Thread.sleep(3000);
			driver.navigate().to("http://192.168.20.220:85/client/rfq-campaign/");
//			driver.navigate().to("http://192.168.20.103:8000/client/rfq-campaign/");
			
		
			Thread.sleep(3000);
			// Bulk RFQ Upload
			driver.findElement(By.xpath("//*[@id=\"centertext\"]/div/button[1]")).click();

			// Browse file
			Actions action = new Actions(driver);
			WebElement w1 = driver.findElement(By.xpath("//*[@id=\"rfq_camp_form\"]/label"));

			w1.click();
			Thread.sleep(4000);
			uploadFile("F:\\Data_Files\\Template_UploadedFile\\RFQ_BulkTemplate_Upload.xlsx");
			Thread.sleep(3000);	
			// click on Upload button
			driver.findElement(By.xpath("//*[@id=\"upload_rfq_camp_file\"]")).click();

			// Click on Next Button
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[6]/div/div[4]/div[2]/button")).click();

			// Preview
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"preview_headers\"]")).click();

			// Back To Mapping
			Thread.sleep(3000);
			driver.navigate().refresh();

//		Save Campaign
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@id=\"save_headers\"]")).click();

			// Save Confirm Mapping
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div/button")).click();
			test.log(LogStatus.PASS,"Test Passed");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, e);
			System.out.println(e);
		}
		finally {
			
			report.endTest(test);
			report.flush();
		}

	}
	

	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void uploadFile(String fileLocation) {
		try {
			/** @param Upload Bulk RFQ Campaign CSV  
			 * @param args 
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


	