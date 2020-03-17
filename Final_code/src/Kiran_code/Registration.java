package Kiran_code;

import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * @author Tejaswini
 */
public class Registration {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;

	@Test(dataProvider = "wordpress")

	public void open(String[] colCount) throws InterruptedException, AWTException {

		try {
			String f1 = "C:\\ExtentReports\\Regstration.html";

			report = new ExtentReports(f1);

			System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");

			if (colCount[0].contains("@"))
				driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			test = report.startTest("Registration)");
			//driver.get("http://192.168.20.220:85/");
			 driver.get("http://45.112.2.195:83/");

			driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/p/a/button")).click();

			//get the email fro excel
			driver.findElement(By.name("email")).sendKeys(colCount[0]);
			Thread.sleep(3000);
			
			//get the password from excel
			driver.findElement(By.name("password1")).sendKeys(colCount[1]);
			Thread.sleep(3000);
			
			//it taking the confirm password
			driver.findElement(By.name("password2")).sendKeys(colCount[2]);
			Thread.sleep(3000);
			

			//element taking User Type as Client or Vendor
			WebElement w = driver.findElement(By.xpath("//*[@id=\"id_user_type\"]"));
			List<WebElement> allElements = w.findElements(By.tagName("option"));

			for (WebElement element : allElements) {

				//click on selected User Type
				if (colCount[3].equals(element.getText())) {
					element.click();
				}
			}
			//click on submit
			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[5]/center/button")).click();

			test.log(LogStatus.PASS, "passed");

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.PASS, e);
			System.out.println(e);
		}

		finally {

			report.endTest(test);
			report.flush();
		}

	}

	//retrive data from excel file
	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\registration1.xlsx");
		/**
		 * @param config It takes input excel file
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