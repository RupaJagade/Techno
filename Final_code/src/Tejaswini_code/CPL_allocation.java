package Tejaswini_code;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * 
 * @author Rupali
 */

public class CPL_allocation {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;

	@Test(dataProvider = "wordpress")
	public void open(String[] cols) throws InterruptedException {

		String f1 = "C:\\ExtentReports\\cpl_report";

		report = new ExtentReports(f1);
		System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");
		if (cols[0].contains("@"))
			driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("http://45.112.2.195:83/");

		// login Email and pasword
		driver.findElement(By.name("email")).sendKeys(cols[0]);
		driver.findElement(By.name("password")).sendKeys(cols[1]);
		Thread.sleep(2000);
		WebElement login_button = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		login_button.click();
		Thread.sleep(2000);
		System.out.println("login");
		driver.get("http://45.112.2.195:83/vendor/pending-campaign/"); // vendor side pending campaign click

		// for counter action click
		for (int i = 0; i <= 5; i++) {
			List<WebElement> camp_id = driver
					.findElements(By.xpath("//table[@id='vendor_pending_campaign_table']/tbody/tr/td[2]"));
			String id = camp_id.get(i).getAttribute("camp_Id");

			if (id.equals(cols[2])) {
				Thread.sleep(2000);
				// for click on counter action
				driver.findElement(By.xpath("//*[contains(@title,'counter')]")).click();
			}
			System.out.println("change cpl");

			// for cpl Change
			WebDriverWait wait = new WebDriverWait(driver, 30);
			// driver.findElement(By.xpath("//input[@id='id_req_cpl']")).click();

			WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/div[4]/div/div[7]/div/div/div/div[2]/form/div/div[1]/input")));
			Thread.sleep(2000);
			e.clear();
			System.out.println("click method");
			Thread.sleep(2000);
			e.sendKeys(cols[3]);
			e.sendKeys(Keys.ENTER);
			Thread.sleep(2000);

			// For Volume change
			WebDriverWait wait1 = new WebDriverWait(driver, 30);
			WebElement e1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/div[4]/div/div[7]/div/div/div/div[2]/form/div/div[2]/input")));
			e1.clear();
			e1.sendKeys(cols[4]);
			e1.sendKeys(Keys.ENTER);

			// for comments enter
			WebDriverWait wait2 = new WebDriverWait(driver, 30);
			WebElement e2 = wait2
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='id_Desc']")));
			System.out.println("comments");
			e2.sendKeys(cols[5]);

			driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

			// agree the campaign
			driver.findElement(By.xpath("//a[@href='/vendor/sendAgreeRequest/370/']")).click();

			// Reject the campaign
			driver.findElement(By.xpath("//a[@href='/vendor/remove_campaign_from_pending/370/50']  ")).click();

		}

	}

	// retrive the data from excel
	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\CPL_counter.xlsx");
		int rowCount = config.getRowCount(0);
		System.out.println(rowCount);

		int colCount = config.getColCount(0);
		System.out.println(colCount);

		Object[][] data = new Object[rowCount + 1][colCount + 1];

		for (int rows = 1; rows < rowCount; rows++) {

			for (int cols = 0; cols < colCount; cols++) {

				data[rows][cols] = config.getData(0, rows, cols);

			}
		}
		return data;
	}
}
