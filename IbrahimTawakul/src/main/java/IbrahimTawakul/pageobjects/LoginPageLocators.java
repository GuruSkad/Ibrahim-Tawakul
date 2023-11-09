package IbrahimTawakul.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageLocators {
	WebDriver driver;

	public LoginPageLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.text-center.loginLogo")
	public WebElement loginLogo;
	
	@FindBy(name = "username")
	public WebElement userName;

	@FindBy(name = "password")
	public WebElement password1;

	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	public WebElement loginButton;

	@FindBy(css = "[class*='signUpBtn']")
	public WebElement signUpButton;

	@FindBy(xpath = "//a[contains(text(),'Reset Password')]")
	public WebElement resetPassword;

	@FindBy(xpath = "//p[contains(text(),'Forget Password?')]")
	public WebElement forgotPassword;

	@FindBy(xpath = "//div[1]/form[1]/div[2]/div[1]/div[1]/div[2]/*[1]")
	public WebElement showPassword;

	@FindBy(xpath = "//form/div[2]/div[2]/span")
	public WebElement emptyPassMsg;

	@FindBy(xpath = "//form/div[1]/div/span")
	public WebElement emptyUserMsg;
	
	@FindBy(css = "input[type='email']")
	WebElement resetMail;
	
	@FindBy(xpath = "//span[contains(text(),'Next')]")
	public WebElement nextButton;
	
	@FindBy(tagName = "a")
    public List<WebElement> links;

	public void enterUsername(String username) {
		userName.sendKeys(username);
	}

	public void enterPassword(String password) {
		password1.sendKeys(password);
	}
	
	public void clearFields() {
		userName.clear();
		password1.clear();
	}

	public void loginApplication(String username, String password) {
		userName.sendKeys(username);
		password1.sendKeys(password);
		loginButton.click();
	}
}
