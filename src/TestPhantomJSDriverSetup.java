import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

public class TestPhantomJSDriverSetup {

	public static void main(String[] args) throws IOException {

		String PHANTOM_DRIVER_PATH = System.getProperty("user.dir") + System.getProperty("file.separator")
				+ "phantomjs-2.1.1-windows/bin" + System.getProperty("file.separator") + "phantomjs.exe";
		System.out.println("Setting up Phantom Driver.");
		System.setProperty("phantomjs.binary.path", PHANTOM_DRIVER_PATH);

		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
		caps.setJavascriptEnabled(true);
		//caps.setCapability("takesScreenshot", true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_DRIVER_PATH);
		WebDriver driver = new PhantomJSDriver(caps);

		driver.get("http://www.google.com");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		try {
			WebElement element = driver.findElement(By.name("q"));
			element.sendKeys("Guru99");
			System.out.println("Phantom JS entered text in search");
			element.submit();
			System.out.println("Clicked on submit button");
			System.out.println("Page title is: " + driver.getTitle());
			driver.findElement(By.partialLinkText("Meet Guru99")).click();
			Thread.sleep(3000);
			File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"Screenshot"+Math.random()+".png"));
			System.out.println("Screenshot taken!");
			System.out.println("Website launched is: " + driver.getTitle());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"Screenshot"+Math.random()+".png"));
			System.out.println("Screenshot taken!");
			
		}

	}

}
