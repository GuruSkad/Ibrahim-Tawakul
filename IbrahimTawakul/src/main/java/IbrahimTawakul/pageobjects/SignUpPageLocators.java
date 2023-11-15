package IbrahimTawakul.pageobjects;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath = "//div[3]//div/img")
	public WebElement logo;
	
	@FindBy(css = ".signUpBtn.signUpForm")
	public WebElement signUpButton;
	
	@FindBy(name = "CompanyName")
	public WebElement companyName;
	
	@FindBy(name = "ContactNumber")
	public WebElement contactNumber;
	
	@FindBy(name = "email")
	public WebElement email;
	
	@FindBy(name = "areaAddress")
	public WebElement address;
	
	@FindBy(name = "city")
	public WebElement city;
	
	@FindBy(id = "floatingSelect")
	public WebElement state;
	
	@FindBy(xpath = "//h1[normalize-space()='Sign Up']")
	public WebElement signUpText;
	
	@FindBy(xpath = "//option[normalize-space()='Dubai']")
	public WebElement Dubai;
	
	@FindBy(xpath = "//*[@id=\"signupForm\"]/button")
	public WebElement submitBtn;
	
	public void gotoSignup() {
		signUpButton.click();
	}
	
	public void clearFeilds() {
		companyName.clear();
		contactNumber.clear();
		email.clear();
		address.clear();
		city.clear();
		state.clear();
	}
	
	public void selectState(String Dubaia) {
		Select fuelType = new Select(Dubai);
		fuelType.selectByVisibleText(Dubaia);
	}
	
}
