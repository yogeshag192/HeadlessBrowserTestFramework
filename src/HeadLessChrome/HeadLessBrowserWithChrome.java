package HeadLessChrome;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
//import org.openqa.selenium.chrome.ChromeDriver;


public class HeadLessBrowserWithChrome {
	
	static String CHROME_DRIVER_PATH = null;
	static WebDriver driver = null;

	
	@Test
	public static void setup() throws IOException, InterruptedException {
		
		
		CHROME_DRIVER_PATH = System.getProperty("user.dir") + System.getProperty("file.separator") + "chromedriver.exe";
		System.out.println("Setting up Headless Chrome Driver.");
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		
		 // Add options to Google Chrome. The window-size is important for responsive sites
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		//options.addArguments("window-size=1200x600");
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.google.co.in");
		
		Assert.assertTrue(driver.findElement(By.name("q")).isDisplayed()); 
		WebElement element = driver.findElement(By.name("q"));	
	      
	     element.sendKeys("Guru99");	
	     System.out.println("ChromeHeadless JS entered text in search-box");
	     element.submit();
	     Thread.sleep(2000);
	     File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"Screenshot"+Math.random()+".png"));
			System.out.println("Screenshot taken!");
	       	
	     System.out.println("Page title is: " + driver.getTitle());	
	     driver.findElement(By.xpath("//h3[contains(text(),'Meet Guru99')]")).click();
	     System.out.println("Website launched is: " +driver.getTitle());

	}
}
