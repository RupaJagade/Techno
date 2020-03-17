package Kiran_code;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
public class UserAccessClientGrpAccess {

	WebDriver driver;
	ExtentTest test;
	ExtentReports report;

	@Test(dataProvider = "wordpress")
	public void open(String conCount[]) throws InterruptedException {

		try {
			/**
			 * @param Login to client Add Group,Group access,Save Access
			 * @param args
			 */
			report = new ExtentReports("C:\\ExtentReports\\UserAccess\\UserAccessClientGrpAccess4.html");
			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");

			if (conCount[0].contains("@"))
				driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			test = report.startTest("User Access-CreateGroup-CreateUSer");
			driver.get("http://45.112.2.195:83/");
			// Login to client Add Group,Group access,Save Access
			driver.findElement(By.name("email")).sendKeys(conCount[0]);
			driver.findElement(By.name("password")).sendKeys(conCount[1]);
			// to click on next buttons
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();
			Thread.sleep(4000);

			// Access Control
			// go to acess control
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"AccessControl\"]/a/span")).click();

			// click on manage users
			Thread.sleep(3000);
			WebElement manageusers = driver.findElement(By.xpath("//*[@id=\"ManageUsers\"]/a"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", manageusers);

			// Add Group
			Thread.sleep(2000);

			// click on add group
			driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[1]/div[2]/span/button[2]")).click();
			// driver.findElement(By.xpath("//button[2]//i[@class='fa fa-plus']"));
			driver.switchTo().activeElement();

			// send group name from excel
			driver.findElement(By.name("group")).sendKeys(conCount[2]);

			// click on add group
			driver.findElement(By.id("add_group")).click();
			driver.switchTo().activeElement();
			Thread.sleep(3000);

			// Group Access
			/**
			 * @param Group Access
			 * @param args
			 */
			Thread.sleep(3000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(0, 250);");

			// select all group acess
			Thread.sleep(2000);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Explore Vendors", "//*[@id=\"34\"]/div/label");
			map.put("RFQ Campaigns", "//*[@id=\"76\"]/div/label");
			map.put("Campaign", "//*[@id=\"12\"]/div/label");
			map.put("Reason List", "//*[@id=\"39\"]/div/label");
			map.put("Access Control", "//*[@id=\"71\"]/div/label");
			map.put("Support", "//*[@id=\"43\"]/div/label");
			map.put("Settings", "//*[@id=\"81\"]/div/label");
			map.put("Reports", "//*[@id=\"84\"]/div/label");

			String[] intVendors = conCount[3].split(",");

			int vendors_int = intVendors.length;

			String[] Keys = map.keySet().toArray(new String[map.size()]);

			int s = Keys.length;

			for (int i = 0; i < s; i++) {
				String ss = Keys[i];

				for (int j = 0; j < vendors_int; j++) {
					if (ss.equals(intVendors[j])) {
						driver.findElement(By.xpath(map.get(intVendors[j]))).click();
					}
				}
			}

			// Save access
			/**
			 * @param Save Access
			 * @param args
			 */
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"grp_access_form\"]/div[2]/button[1]")).click();

			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div/button")).click();

			String title = driver.getTitle();

			System.out.println(title);
			if (driver.getTitle().equals("Techconnectr - B2B Lead Generation Marketplace")) {

				// Assert.assertEquals("Only Testing",title);
				Thread.sleep(3000);

				test.log(LogStatus.PASS, title, "Title Matched");
				// ExtentTest logger=report.createTest("login test");
			} else {
				test.log(LogStatus.FAIL, "Title Not matched");
			}

			// driver.getPageSource().contains("Users and Groups");

			// Create User
			/**
			 * @param To   Create new User
			 * @param args
			 */
			Thread.sleep(3000);
			JavascriptExecutor jse1 = (JavascriptExecutor) driver;
			jse1.executeScript("scroll(250, 0);");

			WebElement w2 = driver
					.findElement(By.xpath("/html/body/div[4]/div/div[2]/div/div/div[1]/div[2]/span/button[3]"));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", w2);
			driver.findElement(By.name("user_name")).sendKeys(conCount[4]);
			driver.findElement(By.name("email")).sendKeys(conCount[5]);
			driver.findElement(By.name("password1")).sendKeys(conCount[6]);
			driver.findElement(By.name("password2")).sendKeys(conCount[7]);

			Thread.sleep(3000);
			Select s1 = new Select(driver.findElement(By.xpath("//*[@id=\"add_user\"]/div[1]/div[5]/div/select")));
			s1.selectByVisibleText(conCount[8]);

			// Submit
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"add_user\"]/div[2]/button[1]")).click();
			test.log(LogStatus.PASS, "Test Passed");

			// driver.close();
		} catch (Exception e) {

			System.out.println("exception");
			e.printStackTrace();
			test.log(LogStatus.FAIL, e);
			System.out.println(e.getMessage());
			// System.out.println(e);
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshotFile, new File("C:\\ExtentReports\\user_Accessnew\\one.png"));
				String image = test.addScreenCapture("C:\\ExtentReports\\img.jpg");
				test.log(LogStatus.FAIL, "from try block", image);
				System.out.println("take screen shot");
			} catch (IOException e1) {
				e1.printStackTrace();
				test.log(LogStatus.FAIL, "screen shot capture");

			}

		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, e);
			System.out.println("Assertion Error");
		}
		System.out.println("end");

		// finally {
		/**
		 * @param To   Generate HTML Report
		 * @param args
		 */
		report.endTest(test);
		report.flush();
	}
	// }

	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\User_Acess.xlsx");
		int rowCount = config.getRowCount(0);

		int colCount = config.getColCount(0);

		Object[][] data = new Object[rowCount + 1][colCount + 1];

		for (int rows = 0; rows < rowCount; rows++) {

			for (int cols = 0; cols < colCount; cols++) {
				// System.out.println(colCount);

				data[rows][cols] = config.getData(0, rows, cols);

			}

		}
		return data;
	}

}
