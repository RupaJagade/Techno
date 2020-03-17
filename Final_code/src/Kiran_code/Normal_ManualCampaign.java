package Kiran_code;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Normal_ManualCampaign {

	WebDriver driver;

	@Test(dataProvider = "wordpress")
	public void NormalManualCamp(String[] colCount) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\selenium 64 bit\\chromedriver.exe");

		// if (colCount[0].contains("@"))
		driver = new ChromeDriver();
		driver.get("http://45.112.2.195:83/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		// get the email from excel file
		driver.findElement(By.name("email")).sendKeys(colCount[0]);

		// get the password from excel file
		driver.findElement(By.name("password")).sendKeys(colCount[1]);
		// click on login button
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div/div/div[4]/center/button")).click();
		Thread.sleep(3000);

		// click on draft campaign link
		driver.get("http://45.112.2.195:83/client/draft-campaign/");
		System.out.println("login");

		// click on manual campaign
		WebElement w = driver.findElement(By.xpath("//button[@title='Manual Campaign']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", w);
		Thread.sleep(2000);

		// click on campaign name and enter value from excel
		driver.findElement(By.xpath("//input[@id='id_name']")).sendKeys(colCount[2]);
		Thread.sleep(2000);

		// click on campaign io number and enter the value from excel
		driver.findElement(By.xpath("//input[@id=\"id_io_number\"]")).sendKeys(colCount[3]);
		Thread.sleep(2000);

		// click on campaign description and enter the value from excel
		driver.findElement(By.xpath("//textarea[@id='id_description']")).sendKeys(colCount[4]);
		Thread.sleep(2000);

		//click on campaign type and select
		WebElement type = driver.findElement(By.xpath("//select[@id='id_type']"));
		List<WebElement> options = type.findElements(By.tagName("option"));
		options.get(2).click();
		Thread.sleep(2000);

		//click on outreach method and select
		WebElement method = driver.findElement(By.xpath("//button[@title='Select Outreach Method']"));
		method.click();
		WebElement m1 = driver.findElement(By.xpath("//input[@type='checkbox' and @value='1']"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", m1);
		System.out.println("outreach method");
		

		//to enter cpl value
		driver.findElement(By.xpath("//input[@name='cpl_name']")).sendKeys(colCount[5]);
		System.out.println("cpl value");

		//to enter Target Quantity
		driver.findElement(By.xpath("//input[@id='currency-field2']  ")).sendKeys(colCount[6]);
		Thread.sleep(4000);
		WebElement s_date=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div/div/form/fieldset/div[1]/div[8]/div/div/div/div/input[2]"));
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", s_date);
		
		Thread.sleep(4000);
		System.out.println("date picker");
		
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/span[18]")).click();
		//System.out.println("selected date");
		//WebElement dateWidget = driver.findElement(your locator);
		//List<WebElement> columns=dateWidget.findElements(By.tagName("td"));

		//for (WebElement cell: columns){
		   //Select 13th Date 
		///   if (cell.getText().equals("13")){
		//      cell.findElement(By.linkText("13")).click();
		//      break;
		// }

		
		

	}

	@DataProvider(name = "wordpress")
	public Object[][] passdata() {
		ExcelDataConfig config = new ExcelDataConfig("F:\\Data_Files\\Normal_ManualCamp.xlsx");
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
