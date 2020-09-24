package ACN;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ListAllIndustries {

	WebDriver driver;

	@BeforeMethod
	public void setup(){
	
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get("https://www.accenture.com/in-en");

                      }
	@Test(priority = 1)
	public void acnLogoTest()
	{
		boolean flag = false;
		flag = driver.findElement(By.xpath("(//a[@data-analytics-link-name='accenture logo'])[1]")).isDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test(priority = 2)
	public void ACNTitleTest() {
		System.out.println("running title test...");
		System.out.println(driver.getTitle());
		assertEquals(driver.getTitle(), "This is a wrong title");
	}
	

	@Test(priority = 3)
	public void getAllIndustries() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@data-target='#primaryLink3_Industries']/span")).click();
		Thread.sleep(5000);
		List<WebElement> industryList = driver.findElements(By.xpath("//ul[@aria-label='Industries']/li"));
		industryList.forEach(ele -> System.out.println(ele.getText()));
		assertEquals(industryList.size(), 19);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
}
