package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev113545.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		WebElement frameElement1 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frameElement1);
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("w6hnF2FRhwLC");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		Thread.sleep(1000);
		WebElement filterSearchElement = driver.findElement(By.xpath("//input[@id='filter']"));
		filterSearchElement.sendKeys("Incident");
		filterSearchElement.click();
		Thread.sleep(500);
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		Thread.sleep(500);
		WebElement frameElement2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frameElement2);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		
		String incNum = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("Incident No : " + incNum);
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();
		Set<String> windowHandlesSet1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandlesSet1);
		driver.switchTo().window(windowHandlesList1.get(1));
		driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();
		driver.switchTo().window(windowHandlesList1.get(0));
		driver.switchTo().frame(frameElement2);
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Short Description");
		driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
		WebElement selectWebElememt = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select drpdwn1 = new Select(selectWebElememt);
		drpdwn1.selectByValue("number");

		WebElement incidentSearchElement = driver.findElement(By.xpath("//input[@class='form-control']"));
		incidentSearchElement.sendKeys(incNum);
		incidentSearchElement.sendKeys(Keys.ENTER);
		WebElement ssWebElement = driver.findElement(By.xpath("//div[@class='vcr_controls']//span"));
		String rowNumber = ssWebElement.getText();
		System.out.println("Incident displayed after searching : " + rowNumber);
		if (rowNumber.contains("Showing rows 1 ")) {
			System.out.println("Incident created successfully");
		} else
			System.out.println("Incident not created");

		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snap/ServiceNow.png");
		FileUtils.copyFile(src1, dst);
	}

}
