package mut;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class FindAPerson {

	@Test
	public void web() throws AWTException, InterruptedException, CsvValidationException, IOException {

		String userdir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", userdir + "/src/test/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("https://www.chandrannabima.ap.gov.in/new/SearchName2324.aspx");

		driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_RadioButtonList1_2\"]")).click();

		WebElement dist = driver.findElement(By.xpath("//*[@name='ctl00$ContentPlaceHolder1$Distric']"));
		Select selDist = new Select(dist);
		selDist.selectByVisibleText("ANAKAPALLI");
		WebElement mandal = driver.findElement(By.xpath("//*[@name='ctl00$ContentPlaceHolder1$mandal']"));
		Select selMandal = new Select(mandal);
		List<WebElement> eleMan = selMandal.getOptions();
		List<String> strMan= new ArrayList<String>();
		for(WebElement me:eleMan)
		{
			strMan.add(me.getText());
		}

		for(String m:strMan)
		{			
			if(!m.contains("--Select Mandal--"))
			{
				mandal = driver.findElement(By.xpath("//*[@name='ctl00$ContentPlaceHolder1$mandal']"));
				selMandal = new Select(mandal);
				System.out.println("Mandal is : "+m);
				selMandal.selectByVisibleText(m);
				WebElement vil = driver.findElement(By.xpath("//*[@name='ctl00$ContentPlaceHolder1$village']"));
				Select selVil = new Select(vil);
				List<WebElement> eleVil = selVil.getOptions();
				List<String> strVil= new ArrayList<String>();
				for(WebElement ve:eleVil)
				{
					strVil.add(ve.getText());
				}
				int i=0;
				for(String v:strVil)
				{
					if(!v.contains("--Select Secretariat--"))
					{
						vil = driver.findElement(By.xpath("//*[@name='ctl00$ContentPlaceHolder1$village']"));
						selVil = new Select(vil);
						
						selVil.selectByIndex(i++);
						WebElement nameEle = driver.findElement(By.xpath("//*[@id='Textnamesearch']"));

						nameEle.clear();
						nameEle.sendKeys("%san%d%ra%giri%");
						WebElement searchEle = driver.findElement(By.xpath("//*[@value=\"Search\"]"));
						searchEle.click();
						Thread.sleep(4000);
						List<WebElement> riceCardNos= driver.findElements(By.xpath("//*[@id=\"ContentPlaceHolder1_gridview1\"]//td[2]"));
						if(riceCardNos.size()>0)
						{
							System.out.println("Village is : "+v);
							for(WebElement e:riceCardNos)
							{
								System.out.println("Rice card No : "+e.getText());
							}
						}
					
					}
				}
			}
		}

	}

}
