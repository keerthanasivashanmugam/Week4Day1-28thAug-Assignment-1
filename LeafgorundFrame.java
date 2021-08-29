package week4.day1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafgorundFrame {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		
		WebElement frameWebElement1 = driver.findElement(By.xpath("//iframe[@src='default.html']"));
		driver.switchTo().frame(frameWebElement1);
		driver.findElement(By.xpath("//button[@id='Click']")).click();
		driver.switchTo().defaultContent();
		
		WebElement frameWebElement2 = driver.findElement(By.xpath("//iframe[@src='page.html']"));
		driver.switchTo().frame(frameWebElement2);
		WebElement frameWebElement3 = driver.findElement(By.xpath("//iframe[@src='nested.html']"));
		driver.switchTo().frame(frameWebElement3);
		driver.findElement(By.xpath("//button[@id='Click1']")).click();
		
		driver.switchTo().defaultContent();
		List<WebElement> frameSizeWebElement3 = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of Frames in this page is : "+frameSizeWebElement3.size());
		
		
	}

}
