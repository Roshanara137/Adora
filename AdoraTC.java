package testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class adora_EndtoEnd {
	public static void main(String[] args) throws InterruptedException {
		// Setting system properties of ChromeDriver
		System.setProperty("webdriver.chrome.driver", "C:\\Roshan Downloads\\chromedriver.exe");
		// Creating an object of ChromeDriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Deleting all the cookies
		driver.manage().deleteAllCookies();
		// driver.close();
		driver.get("https://generic.qa.app.adoraexperiences.com/");
		// *[@id="__layout"]/div/div[1]/div[5]/div[2]/div/div/div[2]/div[2]/a/div[2]
		// *[@id="feature-title-bar"]/div[1]/a[2]/h5
		// driver.findElement(By.xpath("//*[@id='feature-title-bar']/div[1]/a[2]/h5"));

		// Home Page Validation - Logo Verification
		Boolean Display = driver.findElement(By.xpath("//*[@id='feature-title-bar']/div[1]/a[2]/h5")).isDisplayed();
		if (Display.booleanValue() == true) {
			System.out.println("Adora Application Home Page Loaded Successfully");
		} else {
			System.out.println("Error in Home Page");
		}

		// Newly Added Schools verification
		if (driver.getPageSource().contains("Newly Added Schools")) {
			System.out.println("Newly Added Schools are displayed in Home Page");
		} else {
			System.out.println("Missing Newly Added Schools in Home Page");
		}

		String addedSchoolList = driver
				.findElement(By.xpath("//*[@id='__layout']/div/div[1]/div[5]/div[1]/div/div[1]/p/span")).getText();

		String numschool = addedSchoolList.replaceAll("[^0-9]", "");
		System.out.println("Total number of Added School is " + numschool);

		// All Universities verification
		if (driver.getPageSource().contains("All Universitie")) {
			System.out.println("All Universities are displayed in Home Page");
		} else {
			System.out.println("Missing All Universities in Home Page");
		}
		
		String allSchoolList = driver
				.findElement(By.xpath("//*[@id='__layout']/div/div[1]/div[5]/div[2]/div/div/div[1]/p/span")).getText();

		String numallschool = allSchoolList.replaceAll("[^0-9]", "");
		System.out.println("Total number of All Universities is " + numallschool);

		int count = Integer.parseInt(numallschool);
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		
		//List of All Universities
		for(int i=1;i<=count;i++)
		{
			String UniversityName = driver
					.findElement(By.xpath("//*[@id='__layout']/div/div[1]/div[5]/div[2]/div/div/div[2]/div["+i+"]/a/div[2]/h5")).getText();
			System.out.println("University Name from the List of "+i+" is "+UniversityName);
			//String Univ = "Arizona State University";
			
			Matcher m = p.matcher(UniversityName);
			boolean b = m.find();

			Boolean isdis = driver.findElement(By.xpath("//h5[ contains (text(),'"+UniversityName+"')]")).isDisplayed();
			if (isdis.booleanValue() == true && !b) {
				
				JavascriptExecutor executor = (JavascriptExecutor) driver;
			     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//h5[ contains (text(),'"+UniversityName+"')]")));
			  
				System.out.println("Selected University is displayed");
				Thread.sleep(2000);
				// University Page verification
				if (driver.getPageSource().contains(UniversityName)) {
					System.out.println("Your Selected University Clicked & Page loaded");
					Thread.sleep(2000);
					String Location = driver.findElement(By.xpath("//*[@id='details-header']/div/div/div[2]/p")).getText();
					System.out.println("Your Selected University "+UniversityName+" Located at "+Location);
					
					//Total number of Available Take a Tour
					int taketour = driver.findElements(By.xpath("//*[@id='__layout']/div/div/div[3]/div[3]/div")).size();
					if(taketour>0)
					{
						System.out.println("Number of Take a tour is "+taketour);
					}
				} else {
					System.out.println("Your selected University Page not loaded");
				}
			} else {
				System.out.println("University not found");
			}
			
			driver.findElement(By.xpath("//*[@id='feature-title-bar']/div[1]/a")).click();
			Thread.sleep(2000);
		}
		
		//scrolling & clicking the University
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//Entering into Particular University
		//Find element by link text and store in variable "Element"        		
      //  WebElement Element = driver.findElement(By.xpath("//h5[ contains (text(),'Arizona State University')]"));

		
		
		driver.close();
	}
}
