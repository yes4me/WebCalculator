/* ===========================================================================
Created:	2015/06/26
Author:		Thomas Nguyen - thomas_ejob@hotmail.com
Purpose:	This is a standard in the industry according to the book "Instant RSpec Test-Driven Development How-to" and some website
=========================================================================== */

package lib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.Constants;
import config.Paths;

public class DriverFactory {
	private static WebDriver driver = null;

	public WebDriver driver() {
		if (Constants.BROWSER.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", Paths.CHROME_FILE);
			driver = new ChromeDriver();
		}
		else if (Constants.BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Constants.GLOBAL_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
}