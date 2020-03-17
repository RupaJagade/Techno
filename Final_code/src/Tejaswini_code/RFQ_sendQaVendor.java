package Tejaswini_code;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import Tejaswini_code.ExcelDataConfig;
/**
 * 
 * @author Rupali
 */
public class RFQ_sendQaVendor {

	WebDriver driver;

	ExtentReports report;
	ExtentTest test;

	@Test(dataProvider = "wordpress")
	public void open(String[] cols) throws InterruptedException {
		
			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.get("http://45.112.2.195:83/");
			driver.findElement(By.name("email")).sendKeys(cols[0]);
			driver.findElement(By.name("password")).sendKeys(cols[1]);
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();
			driver.get("http://45.112.2.195:83/vendor/vendor-rfq-campaign/");
            Thread.sleep(3000);
            
            //to find particular campaign
			for (int k = 0; k <= 10; k++) {
				List<WebElement> vendor = driver
						.findElements(By.xpath("//*[@id=\"vendor_pending_campaign_table\"]/tbody/tr[2]/td[1]/i"));
				String ven = vendor.get(k).getAttribute("data-camp_id");
				System.out.println("click icon");

				
				//If selected campaign id match with excel id then it click on that particular campaign 
				if (ven.equals(cols[2])) {	
					Thread.sleep(3000);
					driver.findElement(By.xpath("//i[@data-camp_id='" + ven + "']")).click();

					
					//click on edit button
					WebElement w = driver.findElement(
							By.xpath("//table[@id='vendor_table_259']/tbody/tr[1]/td[5]/div[1]/button[1]"));

					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", w);
					Thread.sleep(3000);

					//to edit the CPL value
					WebElement cpl = driver
							.findElement(By.xpath("//*[@id=\"vendor_table_259\"]/tbody/tr/td[2]/input[1]"));
					cpl.clear();                                                      //first to clear it
					cpl.sendKeys(cols[3]);

					
					//to edit volume 
					WebElement volume = driver.findElement(By.xpath("//*[@id=\"vendor_table_259\"]/tbody/tr/td[3]/input[1]"));
					volume.clear();
					volume.sendKeys(cols[4]);
					

					//to edit the monthly capacity
					WebElement mc = driver
							.findElement(By.xpath("//*[@id=\"vendor_table_259\"]/tbody/tr/td[4]/input[1]"));
					mc.clear();
					mc.sendKeys(cols[5]);

					//to click on send icon
					WebElement send=driver.findElement(By.xpath("//*[@id=\"vendor_table_259\"]/tbody/tr/td[5]/div/button[3]"));
					JavascriptExecutor js1 = (JavascriptExecutor) driver;
					js1.executeScript("arguments[0].click();", send);
					Thread.sleep(3000);
					
					//to write comment in comment box
					WebDriverWait wait = new WebDriverWait(driver, 30);
					WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("/html/body/div[5]/div/div[3]/input")));
					Thread.sleep(2000);
					e.sendKeys(cols[6]);
					e.sendKeys(Keys.ENTER);
					
					
					//to click on ok button
					driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
					driver.close();
					driver.quit();
				//	WebElement com=driver.findElement(By.xpath("/html/body/div[5]/div/div[3]/input"));
				//	JavascriptExecutor js2=(JavascriptExecutor) driver;
				//	js2.executeScript("arguments[0].click()" ,com);

				}
			}
		} 
	

	//to retrive the data fro excel
	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\send_RFQ_qoutVendor.xlsx");
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
