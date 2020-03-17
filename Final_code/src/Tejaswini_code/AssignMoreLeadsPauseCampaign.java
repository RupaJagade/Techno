package Tejaswini_code;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AssignMoreLeadsPauseCampaign {

	WebDriver driver;

	ExtentReports report;
	ExtentTest test;
	ExtentHtmlReporter htmlreporter;

	@Test(dataProvider = "wordpress")
	public void open(String[] cols) throws InterruptedException {

		try {
			String f1 = "C:\\ExtentReports\\ABM_supp\\Assign_more_leads.html";

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
			driver.get("http://45.112.2.195:83/client/live-campaign/");
			
			

			//search particular live campaign with respect to camp_id
			for (int i = 0; i <= 10; i++) {
				List<WebElement> camp_id = driver
						.findElements(By.xpath("//*[@id=\"campaign_notebook_table\"]/tbody/tr['+i+']/td[2]"));
				String id = camp_id.get(i).getAttribute("camp_id");

				if (id.equals(cols[2])) {

					Thread.sleep(3000);
					driver.findElement(By.xpath("//a[@href=\"/client/campaigndesc/" + id + "\"]")).click();

					//to search vandor 
					for (int k = 0; k <= 5; k++) {
						List<WebElement> ven1 = driver.findElements(
								By.xpath("//*[@id=\"client_vendor_campaign_datatable\"]/tbody/tr[1]/td[1]")); //vendor name
						String ven_names = ven1.get(k).getText();
						System.out.println(ven_names);
						System.out.println("manage");

						if (ven_names.contains(cols[3])) {
							Thread.sleep(3000);
							driver.findElement(By.xpath(
									"//*[@id=\"client_vendor_campaign_datatable\"]/tbody/tr[1]/td[7]/ul/li/a")) //dropdown ink
									.click();
							
							for (int j = 0; j <= 6; j++) {
								List<WebElement> l = driver.findElements(By.xpath(
										"//*[@id=\"client_vendor_campaign_datatable\"]/tbody/tr[1]/td[7]/ul/li/ul/li/a"));// assign more leads
								
								String list = l.get(j).getText();
								System.out.println(list);
								String demo = cols[4];

								if (demo.equals(list)) {

									switch (list) {
									
									//For pause option
									case "Pause":
										Thread.sleep(3000);
										driver.findElement(By.partialLinkText(demo)).click();
										driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/input"))
												.sendKeys("Paused demo");
										Thread.sleep(3000);

										driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div[2]/button"))
												.click();
										break;

										//for pull outs leads
									case "Pull Out Leads":
										Thread.sleep(3000);
										driver.findElement(By.partialLinkText(demo)).click();
										driver.findElement(By.xpath("//*[@id=\"lead\"]")).sendKeys(cols[5]);
										Thread.sleep(3000);
										driver.findElement(By.xpath("//*[@id=\"save_leads_action\"]")).click();

										
										//for cancle assign lead
									case "Cancel Assigned Leads":
										Thread.sleep(3000);
										driver.findElement(By.partialLinkText(demo)).click();
										driver.findElement(By.xpath("/html/body/div[5]/div/div[4]/div[2]/button"))
												.click();

										//for assign more leads
									case "Assign More Leads":
										Thread.sleep(3000);
										driver.findElement(By.partialLinkText(demo)).click();

										driver.findElement(By.xpath("//*[@id=\"cpl_val\"]")).sendKeys(cols[6]);
										driver.findElement(By.xpath("//*[@id=\"lead\"]")).sendKeys(cols[7]);
										driver.findElement(By.xpath("//*[@id=\"save_leads_action\"]")).click();

									}

								}

							}
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
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\Assign_more.xlsx");
		int rowCount = config.getRowCount(0);

		int colCount = config.getColCount(0);

		Object[][] data = new Object[rowCount + 1][colCount + 1];

		for (int rows = 1; rows < rowCount; rows++) {

			for (int cols = 0; cols < colCount; cols++) {
				// System.out.println(colCount);

				data[rows][cols] = config.getData(0, rows, cols);

			}

		}

		return data;

	}
}
