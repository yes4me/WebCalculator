package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.Paths;
import lib.BasePage;
import lib.Util;

public class P_WebCalculator extends BasePage implements BasicPageObject {
	final int WAIT_TIME = 500;


	@FindBy(id = "input") private WebElement display_input;

	@FindBy(id = "Btn1") private WebElement button_1;
	@FindBy(id = "Btn2") private WebElement button_2;
	@FindBy(id = "Btn3") private WebElement button_3;
	@FindBy(id = "Btn4") private WebElement button_4;
	@FindBy(id = "Btn5") private WebElement button_5;
	@FindBy(id = "Btn6") private WebElement button_6;
	@FindBy(id = "Btn7") private WebElement button_7;
	@FindBy(id = "Btn8") private WebElement button_8;
	@FindBy(id = "Btn9") private WebElement button_9;
	@FindBy(id = "Btn0") private WebElement button_0;
	@FindBy(id = "BtnDot") private WebElement button_dot;
	@FindBy(id = "BtnSign") private WebElement button_sign;
	
	@FindBy(id = "BtnCalc") private WebElement button_equal;
	@FindBy(id = "BtnPlus") private WebElement button_plusSign;
	@FindBy(id = "BtnMinus") private WebElement button_minusSign;


	public P_WebCalculator(WebDriver driver) {
		super(driver);
	}

	//Go to webpage 
	public void visit() {
		visit(Paths.CALCULATOR_URL);
	}

	//Check if you are located at the correct webpage 
	public boolean checkPage() {
		return compareURL(getCurrentURL(), Paths.CALCULATOR_URL);
	}

	//Type directly on the console 
	public boolean setConsole(String equation)
	{
		return type(display_input, equation);
	}
	
	//Get the string displayed on the console 
	public String getConsole()
	{
		return display_input.getAttribute("value");
	}
	
	//Press a number using the buttons of the calculator
	public boolean pressButton(String value)
	{
		for (int i=0; i<value.length(); i++)
		{
			switch ( value.charAt(i) )
			{
				case '=':
					click(button_equal);
					//wait for the result to be updated on the console
					Util.wait(WAIT_TIME);
					break;
				case '1': click(button_1); break;
				case '2': click(button_2); break;
				case '3': click(button_3); break;
				case '4': click(button_4); break;
				case '5': click(button_5); break;
				case '6': click(button_6); break;
				case '7': click(button_7); break;
				case '8': click(button_8); break;
				case '9': click(button_9); break;
				case '0': click(button_0); break;
				case '.': click(button_dot); break;
				case '+': click(button_plusSign); break;
				case '-': click(button_minusSign); break;
				case '*': type(display_input, "*"); break;
				case '/': type(display_input, "/"); break;
				case ',': break;
				case ' ': break;
				default : return false;
			}
		}
		return true;
	}

	//Convert an operator string into a symbol
	public String convertOperation(String operator)
	{
		operator = operator.toLowerCase();
		if ( operator.matches("(?i)^(enter|equal|submit|calculate)$") )
			return "=";
		else if ( operator.matches("(?i)^(add|addition)$") )
			return "+";
		else if ( operator.matches("(?i)^(subtract|subtraction)$") )
			return "-";
		else if ( operator.matches("(?i)^(multiply|multiplication)$") )
			return "*";
		else if ( operator.matches("(?i)^(divide|division)$") )
			return "/";
		return operator;
	}
	//Press an operator using the button of the calculator
	public boolean pressOperation(String operator)
	{
		return pressButton( convertOperation(operator) );
	}
}