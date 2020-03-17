package Tejaswini_code;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
 * @author Tejaswini
 */

public class reject_reason_Dynamic {

	ExtentReports report;
	ExtentTest test;

	@Test(dataProvider = "wordpress")
	public void open(String[] cols) throws InterruptedException {
		
		try {
			report = new ExtentReports("C:\\ExtentReports\\Rectify_Reason.html", true);
			
			WebDriver driver;
			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");
			
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

			test = report.startTest("reject_reason_Dynamic");
			driver.get("http://45.112.2.195:83/");

			// login the appliacation from email
			driver.findElement(By.name("email")).sendKeys(cols[0]);

			// taking password
			driver.findElement(By.name("password")).sendKeys((cols[1]));

			// click on login button
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();

			// there is two URL i,e for RECTIFY REASON & REJECTED REASON
			String rectify = "http://45.112.2.195:83/client/Rectify-Reason-List/";
			String rejected = "http://45.112.2.195:83/client/Rejected-Reason-List/";

			// select rejected reason link
			driver.get(rejected);

			for (int i = 0; i < 10; i++) {
				List<WebElement> l = driver.findElements(By.xpath("//*[@id=\"leadtable\"]/tbody/tr['+i+']/td[2]"));

				// it store the all reason
				String ReasonList = l.get(i).getText();
				System.out.println(ReasonList);

				// compare input reason with stored reason
				if (ReasonList.equals((cols[2]))) {

					System.out.println(i++);

					Thread.sleep(3000);
					driver.findElement(By.xpath("//*[@id=\"leadtable\"]/tbody/tr[3]/td[4]/ul/li/a")).click(); // tr[1]
																												// for
																												// rectify

					for (int j = 0; j <= 2; j++) {
						List<WebElement> k = driver.findElements(
								By.xpath("//*[@id=\"leadtable\"]/tbody/tr[" + i + "]/td[4]/ul/li/ul/li['+j+']/a"));

						// It stores all action_Icon
						String action_icon = k.get(j).getText();
						System.out.println(action_icon);

						String demo = cols[3];

						// selecting action icon as per input reason
						// comparing stored action with input raeson
						if (action_icon.equals(demo)) {

							// If input reason is deleted reason
							if (demo.equals("Delete Reason")) {
								System.out.println(j++);
								Thread.sleep(3000);
								System.out.println(
										"//*[@id=\"leadtable\"]/tbody/tr[" + i + "]/td[4]/ul/li/ul/li[" + j + "]/a");
								driver.findElement(By.xpath(
										"//*[@id=\"leadtable\"]/tbody/tr[" + i + "]/td[4]/ul/li/ul/li[" + j + "]/a"))
										.click();
								Thread.sleep(3000);
								driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div[2]/button")).click();
								Thread.sleep(3000);
								driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/div/button")).click();

							}
							// If input reason is updated reason
							else if (demo.equals("Update Reason"))

							{
								System.out.println(j++);
								Thread.sleep(3000);
								System.out.println(
										"//*[@id=\"leadtable\"]/tbody/tr[" + i + "]/td[4]/ul/li/ul/li[" + j + "]/a");
								driver.findElement(By.xpath(
										"//*[@id=\"leadtable\"]/tbody/tr[" + i + "]/td[4]/ul/li/ul/li[" + j + "]/a"))
										.click();
								driver.findElement(By.xpath("//*[@id=\"lead_reason_update\"]")).sendKeys(cols[4]);
								Thread.sleep(3000);
								driver.findElement(By.xpath("//*[@id=\"leads_rejected_reason_update\"]")).click();
								driver.findElement(By.xpath("/html/body/div[6]/div/div[4]/div/button")).click();

							}
						}

					}

				}
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

	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
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
