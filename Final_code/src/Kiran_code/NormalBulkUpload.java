package Kiran_code;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author kiran Bhalerao
 *
 */
public class NormalBulkUpload {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	ExtentHtmlReporter htmlreporter;

	@Test(dataProvider = "wordpress")
	public void NormalBulkUoload(String conCount[]) throws InterruptedException {
		try {
			/**
			 * @param First Login to client
			 * @param args  To Upload Normal Bulk Campaign using Template
			 */
			report = new ExtentReports("C:\\ExtentReports\\NormaCampaignTest\\NormalBulkCamp3.html", true);
			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");

			driver = new ChromeDriver();
			test = report.startTest("NormalBulkUploadCampaign");
			driver.get("http://45.112.2.195:83/");

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

			driver.findElement(By.name("email")).sendKeys("rsg@rediff.com");

			driver.findElement(By.name("password")).sendKeys("Tech@123#");
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();

			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"campaign\"]/a/span")).click();
			Thread.sleep(3000);

			// Bulk upload from Draft Campaign
			Thread.sleep(3000);

			// Bulk Upload from Pending Campaign
			driver.findElement(By.xpath("//*[@id=\"PendingCampaign\"]/a")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[@title='Bulk Campaign']")).click();

			// Download sample Bulk File
//		driver.findElement(By.xpath("//*[@id=\"view_bulk_modal\"]/div/div/div/div[1]/center/p[1]/a")).click();

			// Browse file
//		Thread.sleep(4000);
			Actions ac = new Actions(driver);
			WebElement w = driver.findElement(By.xpath("//*[@id=\"bulk_camp_form\"]/label"));

			w.click();
			Thread.sleep(3000);

			uploadFile("F:\\Data_Files\\Template_UploadedFile\\Normal_BulkUpload_template.xlsx");
			Thread.sleep(3000);
			// Click on Upload button
			driver.findElement(By.id("upload_camp_file")).click();
//			driver.findElement(By.xpath("//*[@id=\"upload_camp_file\"]")).click();

			// click on OK Button
			Thread.sleep(2000);
			driver.switchTo().activeElement();

			// Click on Next to mapping
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[23]/div/div[4]/div[2]/button")).click();

			// Preview
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"preview_headers\"]")).click();

			// Back To Mapping
//				driver.findElement(By.xpath("//*[@id=\"back\"]")).click();
			Thread.sleep(2000);
			driver.navigate().refresh();

//				Save Campaign
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"save_headers\"]")).click();

			// Save Confirm Mapping
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div[2]/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div/button")).click();
			test.log(LogStatus.PASS, "Test Passed");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, e);
			System.out.println(e);
		} finally {
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
			/**
			 * @param To   Upload Bulk Upload CSV file
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

	//retrive the data from excel file
	@DataProvider(name = "wordpress")
	public Object[] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\Normal_campaign.xlsx");
		int rows = config.getRowCount(0);

		Object[][] data = new Object[rows][2];
		for (int i = 0; i <= rows; i++) {
			try {
				data[i][0] = config.getData(0, i, 0);
				//data[i][1] = config.getData(0, i, 1);
					//data[i][2] = config.getData(0, i, 2);
					//data[i][3] = config.getData(0, i, 3);

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return data;

	}
}
