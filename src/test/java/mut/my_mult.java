package mut;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class my_mult {

	@Test
	public void web() throws AWTException, InterruptedException, CsvValidationException, IOException {

		String userdir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userdir + "/src/test/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		int page_num = 374;
		int start_num= 261;
		String book_name = "enjoymentoflaugh0000east";
		String url_withBooknam = "https://archive.org/details/"+book_name+"/page/n";
		driver.get("https://archive.org/account/login");		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("venkey402@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("@Venkey402");
		driver.findElement(By.xpath("//input[@name='submit-to-login']")).click();

		for (int i=start_num;i<page_num;i++)
		{
			String final_url = url_withBooknam+i+"/mode/1up";
			System.out.println(final_url);
			Thread.sleep(5000);		
			driver.get(final_url);
			System.out.print("url enterered");
			Thread.sleep(5000);		
//			WebElement zoomIn = driver.findElement(By.xpath("//button/div[@name='icon icon-magnify plus']"));
//
//			zoomIn.click();
//			zoomIn.click();
//			zoomIn.click();
			WebElement brimage = driver.findElement(By.xpath("//img[@class='BRpageimage']"));
			String img_url = brimage.getAttribute("src");
			System.out.println(img_url);
			driver.get(img_url);
			Actions action= new Actions(driver);
			action.contextClick(driver.findElement(By.xpath("//img"))).build().perform();

			Robot robot = new Robot();
			// To press D key.
			robot.keyPress(KeyEvent.VK_V);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
		}
	}


}
