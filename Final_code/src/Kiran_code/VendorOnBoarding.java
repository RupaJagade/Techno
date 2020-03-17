package Kiran_code;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElements;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
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
public class VendorOnBoarding {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	ExtentHtmlReporter htmlreporter;

	@Test(dataProvider = "wordpress")
	public void open(String conConunt[]) throws InterruptedException {

		try {
			report = new ExtentReports("C:\\ExtentReports\\Newfolder\\VendorOnboarding.html", true);
			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			test = report.startTest("Vendor OnBoarding");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.get("http://45.112.2.195:83/");

			// login the vendor from email from excel
			driver.findElement(By.name("email")).sendKeys(conConunt[0]);
			// login with password
			driver.findElement(By.name("password")).sendKeys(conConunt[1]);
			// click onlogin button
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();
			Thread.sleep(4000);

			// to navigate the vendor onboarding URL
			driver.navigate().to("http://45.112.2.195:83/vendor/vendor-Bording/");

			// Fill company info
			// send the company name from excel
			driver.findElement(By.name("user_name")).clear();
			driver.findElement(By.name("user_name")).sendKeys(conConunt[2]);
			driver.findElement(By.name("contact")).clear();
			// send phone no from excel
			driver.findElement(By.name("contact")).sendKeys(conConunt[3]);
			driver.findElement(By.name("website")).clear();
			// send website from excel
			driver.findElement(By.name("website")).sendKeys(conConunt[4]);
			driver.findElement(By.name("address_line1")).clear();
			// send addres line from excel
			driver.findElement(By.name("address_line1")).sendKeys(conConunt[5]);
			driver.findElement(By.name("zip_code")).clear();
			// to send zip code from excel
			driver.findElement(By.name("zip_code")).sendKeys(conConunt[7]);

			Thread.sleep(5000);
			// select country
			driver.findElement(By.xpath("//*[@id=\"country_error\"]/span[2]/span[1]/span")).click();
			WebElement w1 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
			w1.sendKeys(conConunt[8]);
			w1.sendKeys(Keys.ENTER);

			// Select State
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"select2-state-container\"]")).click();
			WebElement w2 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
			w2.sendKeys(conConunt[9]);
			w2.sendKeys(Keys.ENTER);

			// Select City
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"select2-cities-container\"]")).click();
			WebElement w3 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
			w3.sendKeys(conConunt[10]);
			w3.sendKeys(Keys.ENTER);

			// Vendor type
			Thread.sleep(4000);
			WebElement w6 = driver.findElement(By.xpath("//*[@id=\"vendor_error\"]/div/span/span[1]/span/ul/li/input"));
			Thread.sleep(3000);
			w6.click();
			w6.sendKeys(conConunt[11]);
			w6.sendKeys(Keys.ENTER);

			// Select Speciality
			Thread.sleep(3000);
			WebElement w5 = driver
					.findElement(By.xpath("//*[@id=\"speciality_error\"]/div/span/span[1]/span/ul/li/input"));
			w5.click();
			w5.sendKeys(conConunt[12]);
			w5.sendKeys(Keys.ENTER);

			// Click on Industry
			Thread.sleep(3000);
			WebElement w4 = driver
					.findElement(By.xpath("//*[@id=\"industry_error\"]/div/span/span[1]/span/ul/li/input"));
			w4.click();
			w4.sendKeys(conConunt[13]);
			Thread.sleep(2000);
			w4.sendKeys(Keys.ENTER);

			// click on Next button
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"onboradingform\"]/div[3]/ul/li[2]/a")).click();

			// Business Card info
			Thread.sleep(4000);

			driver.findElement(By.name("primary_name")).clear();
			driver.findElement(By.name("primary_name")).sendKeys(conConunt[14]);
			driver.findElement(By.name("primary_directdial")).clear();
			driver.findElement(By.name("primary_directdial")).sendKeys(conConunt[15]);
			driver.findElement(By.name("primary_designation")).clear();
			driver.findElement(By.name("primary_designation")).sendKeys(conConunt[16]);
			driver.findElement(By.name("primary_email")).clear();
			driver.findElement(By.name("primary_email")).sendKeys(conConunt[17]);

			// click on Next button on business card info
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"onboradingform\"]/div[3]/ul/li[2]/a")).click();

			// click on next button on data assement page
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"onboradingform\"]/div[3]/ul/li[2]/a"));
		} finally {
			report.endTest(test);
			report.flush();
		}
	}

	// Retrive the data from excel
	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\VendorOnbBoarding2.xlsx");
		int rowcount = config.getRowCount(0);

		int colsCount = config.getColCount(0);

		Object[][] data = new Object[rowcount + 1][colsCount + 1];
		for (int row = 1; row <= rowcount; row++) {
			for (int cols = 0; cols < colsCount; cols++) {
				try {
					data[row][cols] = config.getData(0, row, cols);

				} catch (Exception e) {

					e.printStackTrace();
				}
			}

		}
		return data;
	}

}

/**
 * {For secondary contact information}
 * driver.findElement(By.name("secondary_name")).clear();
 * driver.findElement(By.name("secondary_name")).sendKeys(conConunt[18]);
 * driver.findElement(By.name("secondary_directdial")).clear();
 * driver.findElement(By.name("secondary_directdial")).sendKeys(conConunt[19]);
 * driver.findElement(By.name("secondary_designation")).clear();
 * driver.findElement(By.name("secondary_designation")).sendKeys(conConunt[20]);
 * driver.findElement(By.name("secondary_email")).clear();
 * driver.findElement(By.name("secondary_email")).sendKeys(conConunt[21]);
 */
