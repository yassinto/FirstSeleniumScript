package scripts;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunScript { // This script will automatically respond or sign a user up for any activity on a website

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", /*Insert location of Chrome Driver here as string*/);
		WebDriver driver = new ChromeDriver();
		final Properties oAuthProperties = new Properties();
        try {
            oAuthProperties.load(RunScript.class.getResourceAsStream("oAuth.properties"));
        } catch (IOException e) {
            System.out.println("Unable to read OAuth configuration. Make sure you have a properly formatted oAuth.properties file. See README for details.");
            return;
        }

        final String pass = oAuthProperties.getProperty("psw"); // Get password from oAuth.properties file so it's not hardcoded in this script
		
		driver.get(/*Insert URL login page here as a string*/);
		driver.findElement(By.id("username")).sendKeys(/*Insert username here as string*/); // Note: ID of element might differ
		driver.findElement(By.id("password")).sendKeys(pass); // Note: ID of element might differ
		driver.findElement(By.className("js-submit-button")).click(); // Note: ID of element might differ
		driver.navigate().to(/*Insert URL reaction here page as a string*/);
		
		boolean displayed = false;
		do{ // This loop will refresh page until element/button to submit is present 
		  try{
		    displayed = driver.findElement(By.id("btnSave")).isDisplayed(); // Note: ID of element might differ
		  } catch (NoSuchElementException ex){
		    driver.navigate().refresh();
		  }
		} while(!displayed);
		
		driver.findElement(By.id("btnSave")).click(); // Note: ID of element might differ
		
		// Now the script should have automatic signed user up/respond for user/or any other function needed 
	}
}
