package base;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Verify;

public class BaseTestT {

	//public static WebDriver driver;
	public static String testingUrl = "http://s3.amazonaws.com/istreet-assets/bHFRMn4JHQnwP7QcqCer7w/fortinet-qa-testsite.html#";
	public static Properties or = new Properties();
	
	
	public ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	
	@Parameters({"browser"})
	@BeforeMethod()
	public void setup(String browser) throws Exception {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\or.properties");
		or.load(fis);
		
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver.set(new ChromeDriver());
		}
		else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Users\\tanay\\Downloads\\Softwares\\edgedriver_win64\\msedgedriver.exe");
			driver.set(new EdgeDriver());
		}
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get().get(testingUrl);
		waitForElem(or.getProperty("frm_addtask"));
	}
	
	
	@AfterMethod()
	public void teardown() {
		driver.get().quit();
	}
	
	
	public void click(String elem) {
		driver.get().findElement(By.xpath(elem)).click();
	}
	
	public void type(String elem, String txt) {
		driver.get().findElement(By.xpath(elem)).sendKeys(txt);
	}
	
	public void clear(String elem) {
		driver.get().findElement(By.xpath(elem)).clear();
	}
	
	public void check(String elem) {
		Actions action =new Actions(driver.get());
		action.moveToElement(driver.get().findElement(By.xpath(elem))).click().build().perform();
	}
	
	public String getAttr(String elem, String attr) {
		return driver.get().findElement(By.xpath(elem)).getCssValue(attr);
	}
	
	
	public String getText(String elem) {
		return driver.get().findElement(By.xpath(elem)).getText();
	}
	
	public void select(String elem, String val) {
		Select year = new Select(driver.get().findElement(By.xpath(elem)));
		year.selectByVisibleText(val);
		
	}
	public void selectDate(String elem, String d) throws Exception {
		/*
//		 * Argument Date format is mm/dd/yyyy
		 */
		String day = d.split("/")[1];
		int month = Integer.parseInt(d.split("/")[0]);
		String year = d.split("/")[2];
		
		select(elem, year);
		
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date date = originalFormat.parse(d);
		DateFormat targetFormat = new SimpleDateFormat("MMMM");
		String monthName = targetFormat.format(date).toUpperCase();  
		
		
		
		DateFormat originalFormat2 = new SimpleDateFormat("MMMM", Locale.ENGLISH);
		Date date2 = originalFormat2.parse(getText(or.getProperty("lbl_month")));
		DateFormat targetFormat2 = new SimpleDateFormat("MM");
		int cal_month = Integer.parseInt(targetFormat2.format(date2));
		
		
		
		while(cal_month != month) {
	
			if(cal_month < month) {
				click(or.getProperty("lnk_nxmonth"));
				cal_month++;
			}
			else if(cal_month > month) {
				click(or.getProperty("lnk_premonth"));
				cal_month--;
			}
		
		}
		//Thread.sleep(10000);
		
		if(getText(or.getProperty("lbl_month")).toUpperCase().equals(monthName)) {
			List<WebElement> datePickerDays = driver.get().findElements(By.xpath(or.getProperty("td_days")));
			datePickerDays.stream().filter(e->e.getText().equals(day)).findFirst().get().click();
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	


	private Object upper(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isElementVisible(String elem) {
		if (driver.get().findElement(By.xpath(elem)).isDisplayed()) return true;
		else return false;
	}
	
	public void waitForElem(String elem) {
		WebDriverWait wait = new WebDriverWait(driver.get(), 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elem)));
		
	}
 
}
