package Tejaswini_code;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CampaignAllocation {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	ExtentHtmlReporter htmlreporter;

	@Test(dataProvider = "wordpress")
	public void open(String[] cols) throws InterruptedException {
		try {
			String f1 = "C:\\ExtentReports\\ABM_supp\\Campaign_allocation.html";

			report = new ExtentReports(f1);

			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

			test = report.startTest("Exception");
			driver.get("http://45.112.2.195:83/");
			driver.findElement(By.name("email")).sendKeys(cols[0]);
			driver.findElement(By.name("password")).sendKeys(cols[1]);
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();
			driver.get("http://45.112.2.195:83/client/pending-campaign/");

			// search particular campaign with respect to camp_id
			Thread.sleep(3000);
			for (int i = 0; i <= 10; i++) {
				List<WebElement> w1 = driver
						.findElements(By.xpath("//*[@id=\"campaign_notebook_table\"]/tbody/tr['+i+']/td[3]"));
				String s = w1.get(i).getAttribute("camp_id");
				if (s.equals(cols[2])) {
					Thread.sleep(3000);
					driver.findElement(By.xpath("//a[@href=\"/client/vendor-allocation/" + s + "\"]")).click();

					// search particular vendor with respect to lead id
					for (int j = 0; j < 5; j++) {
						List<WebElement> w2 = driver.findElements(
								By.xpath("//*[@id=\"cpl_lead_alloc_table\"]/tbody/tr['+j+']/td[6]/input"));
						String lead_id = w2.get(j).getAttribute("id");
						System.out.println(lead_id);
						System.out.println("new lead");

						// send the no of lead to vendor
						if (lead_id.equals(cols[3])) {
							driver.findElement(By.xpath("//*[@id='" + lead_id + "']")).sendKeys(cols[4]);

							WebElement w = driver.findElement(By.xpath("//*[@id=\"submit_all_data\"]"));
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", w);

							Thread.sleep(3000);
							driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div/button")).click();
						}
					}

				}
			}
		} catch (Exception e) {

			test.log(LogStatus.FAIL, e);
			System.out.println(e);

		}

		finally {

			report.endTest(test);
			report.flush();
		}

	}

	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\camp_alloction.xlsx");
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
