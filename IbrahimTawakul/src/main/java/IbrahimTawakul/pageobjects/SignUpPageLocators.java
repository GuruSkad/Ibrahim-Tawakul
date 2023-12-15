package IbrahimTawakul.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class SignUpPageLocators {
	WebDriver driver;

	public SignUpPageLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "label[for='CompanyName']")
	public WebElement cmpPlaceholder;
	
	@FindBy(xpath = "//div[3]//div/img")
	public WebElement logo;
	
	@FindBy(className = "logInForm")
	public WebElement loginBtn;
	
	@FindBy(css = ".signUpBtn.signUpForm")
	public WebElement signUpButton;
	
	@FindBy(name = "CompanyName")
	public WebElement companyName;
	
	@FindBy(id = "phone")
	public WebElement contactNumber;
	
	@FindBy(name = "EmailId")
	public WebElement email;
	
	@FindBy(id = "areaAddress")
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
	
	@FindBy(xpath = "//*[@id=\"signupForm\"]/div[2]/div/div/span")
	public WebElement contErrMsg;
	
	@FindBy(xpath = "//*[@id=\"signupForm\"]/div[1]/div")
	public WebElement cmpnyErrMsg;
	
	@FindBy(xpath = "//*[@id=\"signupForm\"]/div[3]/div/span")
	public WebElement emlErrMsg;
	
	@FindBy(xpath = "//h1[contains(text(),'Login')]")
	public WebElement loginText;
	
	@FindBy(xpath = "//*[@id='swal2-html-container']/h3")
	public WebElement confirmationText;
	
	@FindBy(xpath = "//button[normalize-space()='Continue']")
	public WebElement continueBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Change Email Address']")
	public WebElement changeEmailBtn;
	
	public void gotoSignup() {
		signUpButton.click();
	}
	
	public void submit() {
		submitBtn.click();
	}
	public void returntoLogin() {
		loginBtn.click();
	}
	
	public void clearFeilds() {
		companyName.clear();
		contactNumber.clear();
		email.clear();
		address.clear();
		city.clear();
	}
	
	public void selectState(String Dubaia) {
		Select fuelType = new Select(Dubai);
		fuelType.selectByVisibleText(Dubaia);
	}
	
}
