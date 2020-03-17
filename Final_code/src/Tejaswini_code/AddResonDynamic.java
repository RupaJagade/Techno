package Tejaswini_code;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Tejaswini
 */

public class AddResonDynamic extends reject_reason_Dynamic {

	ExtentReports report;
	ExtentTest test;

	@Test(dataProvider = "wordpress")
	public void open(String[] cols) throws InterruptedException {

		try {
			report = new ExtentReports("C:\\ExtentReports\\Add_Reason.html", true);

			WebDriver driver;
			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			test = report.startTest("Add_Reson_Dynamic");
			driver.get("http://45.112.2.195:83/");

			// login with email
			driver.findElement(By.name("email")).sendKeys(cols[0]);

			// get the password
			driver.findElement(By.name("password")).sendKeys(cols[1]);

			// click on login button
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();

			// there are two URL i,e RECTIFY REASON & REJECTED REASON
			String rectify = "http://45.112.2.195:83/client/Rectify-Reason-List/";
			String rejected = "http://45.112.2.195:83/client/Rejected-Reason-List/";

			// selected REJECTED URL
			driver.get(rejected);

			// compaired with rectify URL
			if (driver.getCurrentUrl().contains("/client/Rectify-Reason-List/")) {

				// to click on rectify add reason
				Thread.sleep(3000);
				driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div/div/div[1]/div/div[2]/button[2]"))
						.click();
				// To add reson form stored reason in excel
				driver.findElement(By.xpath("//*[@id=\"lead_reason\"]")).sendKeys(cols[2]);

				// to click on submit
				driver.findElement(By.xpath("//*[@id=\"leads_rectify_reason\"]")).click();
				driver.findElement(By.xpath("/html/body/div[6]/div/div[4]/div/button")).click();

				reject_reason_Dynamic r = new reject_reason_Dynamic();
				r.open(cols);

			}

			// compaired URL with rejected URL
			else

			{

				// to click on reject add reason
				Thread.sleep(3000);
				// driver.findElement(By.xpath("//button[1]//i[@class='fa fa-plus']")).click();
				driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div/div[1]/div[1]/div/div[2]/button"))
						.click();

				// to add rejected reason from stored reason in excel
				driver.findElement(By.xpath("//*[@id=\"lead_reason\"]")).sendKeys(cols[2]);

				// to click on submit button
				driver.findElement(By.xpath("//*[@id=\"leads_rejected_reason\"]")).click();
				driver.findElement(By.xpath("/html/body/div[6]/div/div[4]/div/button")).click();

				reject_reason_Dynamic r = new reject_reason_Dynamic();
				r.open(cols);

			}
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

	// retrive the data from excel file
	@DataProvider(name = "wordpress")
	public Object[][] passdata() {

		// give the stored excel file path
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\AddReason.xlsx");
		/**
		 * @param It takes input excel file
		 */
		int rowCount = config.getRowCount(0);

		int colCount = config.getColCount(0);

		Object[][] data = new Object[rowCount + 1][colCount + 1];

		for (int rows = 1; rows < rowCount; rows++) {

			for (int cols = 0; cols < colCount; cols++) {

				data[rows][cols] = config.getData(0, rows, cols);

			}

		}

		return data;

	}
}