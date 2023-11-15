package IbrahimTawakul.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPageLocators {
	WebDriver driver;

	public SignUpPageLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".signUpBtn.signUpForm")
	public WebElement SignUpButton;
	
	@FindBy(name = "CompanyName")
	public WebElement CompanyName;
	
	@FindBy(name = "ContactNumber")
	public WebElement ContactNumber;
	
	@FindBy(name = "email")
	public WebElement email;
	
	@FindBy(name = "areaAddress")
	public WebElement areaAddress;
	
	@FindBy(name = "city")
	public WebElement city;
	
	@FindBy(id = "floatingSelect")
	public WebElement State;
	
	@FindBy(xpath = "//h1[normalize-space()='Sign Up']")
	public WebElement SignUpText;
	
	@FindBy(xpath = "//option[normalize-space()='Dubai']")
	public WebElement Dubai;
	
	
	public void selectFuel(String Dubaia) {
		Select fuelType = new Select(Dubai);
		fuelType.selectByVisibleText(Dubaia);
	}
	
//	public void SignUpText () {
//		SignUpPageText.getText();
//	}
	
}
