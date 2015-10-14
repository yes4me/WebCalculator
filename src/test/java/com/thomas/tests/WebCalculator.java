/* ===========================================================================
Created: 2015/10/13 Thomas Nguyen - thomas_ejob@hotmail.com
Purpose: Test an online calculator using DataProvider from a CSV file 
=========================================================================== */

package com.thomas.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lib.DriverFactory;
import lib.MyCSV;
import lib.MyCollection;
import page_objects.P_WebCalculator;


public class WebCalculator {
	private WebDriver driver;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = new DriverFactory().driver();
	}
	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

	@DataProvider(name="WebCalculatorCSV")
	public String[][] WebCalculatorCSV()
	{
		MyCSV myExcel		= new MyCSV();
		//Get The data from the second line
		List<String[]> data	= myExcel.read("WebCalculator.csv", 1);

		String[][] result = MyCollection.convertStringArray(data);

		//Get rid of comma everywhere
		for (int i=0; i<result.length; i++)
		{
			for (int j=0; j<result[i].length; j++)
				result[i][j] = result[i][j].replaceAll(",", "");
		}
		return result;
	}

	@Test(groups="smokeTesting", dataProvider="WebCalculatorCSV", enabled = true)
	public void basicOperationUsingButtons(String number1, String number2, String operation, String expectedResult) {
		System.out.println("TESTING: basicOperationUsingButtons");

		P_WebCalculator page = PageFactory.initElements(driver, P_WebCalculator.class);
		page.visit();
		page.windowsMaximize();
		
		page.pressButton(number1);
		page.pressOperation(operation);
		page.pressButton(number2);
		page.pressButton("=");

		String result = page.getConsole();
		assertEquals(result, expectedResult);
	}

	@Test(groups="smokeTesting", dataProvider="WebCalculatorCSV", enabled = false)
	public void basicOperationUsingConsole(String number1, String number2, String operation, String expectedResult) {
		System.out.println("TESTING: basicOperationUsingConsole");

		P_WebCalculator page = PageFactory.initElements(driver, P_WebCalculator.class);
		page.visit();
		page.windowsMaximize();
		
		String equation = number1 + page.convertOperation(operation) + number2; 
		page.setConsole(equation);
		page.pressButton("=");

		String result = page.getConsole();
		System.out.println("==>"+ result);
		assertEquals(result, expectedResult);
	}
}