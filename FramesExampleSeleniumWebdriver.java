package week4.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesExampleSeleniumWebdriver {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get(" https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		WebElement textBoxFrameWebElement = driver.findElement(By.xpath("//iframe[@id='frame1']"));
		driver.switchTo().frame(textBoxFrameWebElement);
		driver.findElement(By.xpath("//b[@id='topic']/following::input")).sendKeys("Selenium");
		WebElement checkBoxFrameWebElement = driver.findElement(By.xpath("//iframe[@id='frame3']"));
		driver.switchTo().frame(checkBoxFrameWebElement);
		driver.findElement(By.xpath("//input[@id='a']")).click();
	    driver.switchTo().defaultContent();
		WebElement selectBoxFrameWebElement = driver.findElement(By.id("frame2"));
		driver.switchTo().frame(selectBoxFrameWebElement);
		WebElement selectWebElement = driver.findElement(By.xpath("//select[@id='animals']"));
		Select drpdwn1 = new Select(selectWebElement);
		drpdwn1.selectByValue("babycat");
	}

}
