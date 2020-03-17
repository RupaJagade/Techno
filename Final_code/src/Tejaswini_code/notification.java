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

public class notification {
	WebDriver driver;

	ExtentReports report;
	ExtentTest test;

	@Test (dataProvider = "wordpress")
	public void open(String[] cols) throws InterruptedException {

		String f1 = "C:\\ExtentReports\\ABM_supp\\RFQ_quotations.html";

		report = new ExtentReports(f1);

		System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("http://45.112.2.195:83/");

		driver.findElement(By.name("email")).sendKeys(cols[0]);
		driver.findElement(By.name("password")).sendKeys(cols[1]);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();

		driver.get("http://45.112.2.195:83/client/client_mail_noti/");

		//to click on particular notification
		for (int i = 0; i <= 10; i++) {
			List<WebElement> not = driver.findElements(By.xpath("//*[@id=\"noti_ajax\"]/div[1]"));
			String note = not.get(i).getText();
          System.out.println(note);
			if (note.equals(cols[2])) {
				switch (note) {
				
				//to click on campaign notification
				case "Campaign":
					Thread.sleep(4000);
					driver.findElement(By.xpath("//*[@id=\"noti_ajax\"]/div[1]")).click();
					String id = cols[3];
					switch (id) {
					case "25":
						driver.findElement(By.xpath("//*[@id=\"25\"]")).click();
						driver.findElement(By.xpath("//*[@name=\"Save\"]")).click();
						break;
					case "9":
						driver.findElement(By.xpath("//*[@id=\"9\"]")).click();
						driver.findElement(By.xpath("//*[@name=\"Save\"]")).click();
						break;
					case "10":
						driver.findElement(By.xpath("//*[@id=\"10\"]")).click();
						driver.findElement(By.xpath("//*[@name=\"Save\"]")).click();
						break;

					}

					break;

					
					//to click on Registrations notification
				case "Registrations":
					Thread.sleep(4000);
					driver.findElement(By.xpath("//*[@id=\"noti_ajax\"]/div[3]")).click();
					
					String id1 = cols[3];
					switch (id1) {
					case "12":
						driver.findElement(By.xpath("//*[@id=\"12\"]")).click();
						driver.findElement(By.xpath("//*[@name=\"Save\"]")).click();
						break;
					case "6":
						driver.findElement(By.xpath("//*[@id=\"6\"]")).click();
						driver.findElement(By.xpath("//*[@name=\"Save\"]")).click();
						break;
					case "5":
						driver.findElement(By.xpath("//*[@id=\"5\"]")).click();
						driver.findElement(By.xpath("//*[@name=\"Save\"]")).click();
						break;
					}
					break;

					
					//to click on lead notification
				case "Leads":
					Thread.sleep(4000);
					driver.findElement(By.xpath("//*[@id=\"noti_ajax\"]/div[5]")).click();
					driver.findElement(By.xpath("//*[@id=\"18\"]")).click();
					driver.findElement(By.xpath("//*[@name=\"Save\"]")).click();
					break;
					
					
					

				}

			}

		}
		

		

	}
	
	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\notification.xlsx");
		int rowCount = config.getRowCount(0);
         System.out.println(rowCount);
         
		int colCount = config.getColCount(0);
		System.out.println(colCount);
		Object[][] data = new Object[rowCount + 1][colCount + 1];

		for (int rows = 1; rows < rowCount; rows++) {

			for (int cols = 0; cols < colCount; cols++) {
				//System.out.println(colCount);

				data[rows][cols] = config.getData(0, rows, cols);

			}

		}

		return data;

	}
	
}
