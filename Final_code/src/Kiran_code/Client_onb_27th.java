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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Kiran Bhalerao
 *
 */
public class Client_onb_27th {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;

	@Test(dataProvider = "wordpress")
	public void open(String conCount[]) throws InterruptedException {
		try {

			report = new ExtentReports("C:\\ExtentReports\\clientonboaridng.html", true);

			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			test = report.startTest("ClientOnboarding");
			driver.get("http://45.112.2.195:83/");

			// login the client with email
			driver.findElement(By.name("email")).sendKeys(conCount[0]);
			Thread.sleep(2000);

			// login with pasword
			driver.findElement(By.name("password")).sendKeys(conCount[1]);
			Thread.sleep(2000);

			// click on login button
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();

			Thread.sleep(4000);

			// naviagte to client onboarding url
			driver.navigate().to("http://45.112.2.195:83/client/client-Bording/");

			Thread.sleep(3000);

			// clear the company name field
			driver.findElement(By.name("user_name")).clear();
			// send the cpmpany name fro excel
			driver.findElement(By.id("company_name")).sendKeys(conCount[2]);
			driver.findElement(By.name("contact")).clear();
			// send the phone no from excel
			driver.findElement(By.id("phone")).sendKeys(conCount[3]);
			driver.findElement(By.name("website")).clear();
			// send website from excel
			driver.findElement(By.id("website")).sendKeys(conCount[4]);
			driver.findElement(By.name("address_line1")).clear();
			// send the address from excel
			driver.findElement(By.name("address_line1")).sendKeys(conCount[6]);
			Thread.sleep(3000);

			// scroll down
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");

			// Select Country
			driver.findElement(By.xpath("//*[@id=\"select2-country-container\"]")).click();
			WebElement w1 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
			w1.sendKeys(conCount[8]);
			w1.sendKeys(Keys.ENTER);

			// Select States
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"select2-state-container\"]")).click();
			WebElement w2 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
			w2.sendKeys(conCount[9]);
			w2.sendKeys(Keys.ENTER);

			// Select City
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"city_error\"]/span/span[1]/span")).click();
			WebElement w3 = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
			w3.sendKeys(conCount[10]);
			w3.sendKeys(Keys.ENTER);

			// Enter Zip code
			driver.findElement(By.id("zip_code")).clear();
			driver.findElement(By.id("zip_code")).sendKeys(conCount[11]);
			Thread.sleep(3000);

			// click on next buttons
			driver.findElement(By.xpath("//*[@id=\"onboradingform\"]/div[3]/ul/li[2]/a")).click();
			Thread.sleep(4000);

			// fill business card info
			driver.findElement(By.name("primary_name")).clear();
			// send primary name from excel
			driver.findElement(By.name("primary_name")).sendKeys(conCount[12]);
			driver.findElement(By.name("primary_directdial")).clear();
			// send directdail no from excel
			driver.findElement(By.name("primary_directdial")).sendKeys(conCount[13]);
			driver.findElement(By.name("primary_designation")).clear();
			// send primary designation from excel
			driver.findElement(By.name("primary_designation")).sendKeys(conCount[14]);
			driver.findElement(By.name("primary_email")).clear();
			// send primary mail from excel
			driver.findElement(By.name("primary_email")).sendKeys(conCount[15]);
			Thread.sleep(4000);
			// click on next button
			driver.findElement(By.xpath("//*[@id=\"onboradingform\"]/div[3]/ul/li[2]/a")).click();

			// finish
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"onboradingform\"]/div[3]/ul/li[3]/a")).click();
			test.log(LogStatus.PASS, "Test Passed");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, e);
		}

	}

	// Retrive the data from excel file
	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		// give excel file path to retrive the data from excel
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\ClientOnBoarding1.xlsx");
		int rowsCount = config.getRowCount(0);
		int colsCount = config.getColCount(0);

		Object[][] data = new Object[rowsCount + 1][colsCount + 1];
		for (int rows = 1; rows <= rowsCount; rows++) {
			for (int cols = 0; cols < colsCount; cols++) {

				try {

					data[rows][cols] = config.getData(0, rows, cols);

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}

		return data;
	}
}

/*
 * send alternative no from excel
 * driver.findElement(By.id("alternate_number")).clear();
 * driver.findElement(By.id("alternate_number")).sendKeys(conCount[5]);}driver.
 * {for secondary contact details}
 * findElement(By.name("secondary_name")).clear();
 * driver.findElement(By.name("secondary_name")).sendKeys(conCount[16]);
 * driver.findElement(By.name("secondary_directdial")).clear();
 * driver.findElement(By.name("secondary_directdial")).sendKeys(conCount[17]);
 * driver.findElement(By.name("secondary_designation")).clear();
 * driver.findElement(By.name("secondary_designation")).sendKeys(conCount[18]);
 * driver.findElement(By.name("secondary_email")).clear();
 * driver.findElement(By.name("secondary_email")).sendKeys(conCount[19]);
 */