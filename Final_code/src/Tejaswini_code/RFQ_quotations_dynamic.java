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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
/**
 * 
 * @author Rupali
 */
public class RFQ_quotations_dynamic {

	WebDriver driver;

	ExtentReports report;
	ExtentTest test;
//	ExtentHtmlReporter htmlreporter;

	@Test(dataProvider = "wordpress")
	public void open(String[] cols) throws InterruptedException {
		try {
			String f1 = "C:\\ExtentReports\\ABM_supp\\RFQ_quotations.html";

			report = new ExtentReports(f1);

			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.get("http://45.112.2.195:83/");
			test = report.startTest("ABM_Supp)");

			driver.findElement(By.name("email")).sendKeys(cols[0]);
			Thread.sleep(2000);
			driver.findElement(By.name("password")).sendKeys(cols[1]);
			Thread.sleep(2000);
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();
			driver.get("http://45.112.2.195:83/client/rfq-campaign/");
			Thread.sleep(3000);

			try {

				// find particular campaign from list
				for (int i = 0; i <= 10; i++) {
					List<WebElement> clients = driver
							.findElements(By.xpath("//*[@id=\"RFQ_campaign_table\"]/tbody/tr[1]/td[1]/i[2]"));

					String campaign_id = clients.get(i).getAttribute("data-id");

					if (campaign_id.equals(cols[2])) {
						driver.findElement(By.xpath("//i[@data-id='" + campaign_id + "']")).click();
						Thread.sleep(2000);

						// to find paricular vendor from list
						for (int j = 0; j <= 5; j++) {
							// table[@id='vendor_table_130']/tbody/tr/td/input
							List<WebElement> w = driver
									.findElements(By.xpath("//*[@id=\"vendor_table_213\"]/tbody/tr[3]/td[1]/input[1]"));
							String vendor_id = w.get(j).getAttribute("data-vendor_id");

							// to click on vendor checkbox
							if (vendor_id.equals(cols[3])) {
								Thread.sleep(3000);

								driver.findElement(By.xpath("//*[@data-vendor_id='" + vendor_id + "']")).click();
								Thread.sleep(3000);

								// click on send button
								WebElement ww = driver.findElement(By.xpath(
										"//*[@id=\"RFQ_campaign_table\"]/tbody/tr[1]/td[1]/div[3]/div[3]/div[3]/div[2]/button"));

								JavascriptExecutor js = (JavascriptExecutor) driver;
								js.executeScript("arguments[0].click();", ww);
								

								//to click on download the qutations
								driver.findElement(By.xpath("//table[@id='RFQ_campaign_table']/tbody/tr[1]/td[4]/ul/li[2]/a/i[1]")).click();
							}
						}
					}

				}
			} catch (Exception e) {

				e.printStackTrace();
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
	
	
	//to retrive data from excel

	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\send_RFQ_quotes.xlsx");  //to give excel file path to retrive data 
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
