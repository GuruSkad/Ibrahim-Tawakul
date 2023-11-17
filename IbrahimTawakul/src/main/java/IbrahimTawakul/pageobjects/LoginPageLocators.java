package IbrahimTawakul.pageobjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	@FindBy(id = "logout")
	public WebElement logoutbtn;

	@FindBy(name = "errorMessage")
	public WebElement getErrorMessage;
	
	@FindBy(name = "username")
	public WebElement userName;

	@FindBy(name = "password")
	public WebElement password1;

	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	public WebElement loginButton;

	@FindBy(css = "[class*='signUpBtn']")
	public WebElement signUpButton;

	@FindBy(xpath = "//div[1]/form[1]/div[2]/div[1]/div[1]/div[2]/*[1]")
	public WebElement showPassword;

	@FindBy(xpath = "//form/div[2]/div[2]/span")
	public WebElement emptyPassMsg;

	@FindBy(xpath = "//form/div[1]/div/span")
	public WebElement emptyUserMsg;

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

	// Locators for forget password module

	@FindBy(xpath = "//a[contains(text(),'Reset Password')]")
	public WebElement resetPassword;

	@FindBy(xpath = "//div/div/div[4]/h1")
	public WebElement resetPassText;

	@FindBy(xpath = "//div/div[4]/form/div[2]/div/a")
	public WebElement gotoLoginBtn;

	@FindBy(className = "text-md-start")
	WebElement loginText1;

	@FindBy(css = "input[type='email']")
	public WebElement resetMail;

	@FindBy(className = "inptLabel")
	public WebElement emailLabel;

	@FindBy(className ="gnrtOtpBtn")
	public WebElement gnrtotpBtn;

	@FindBy(id = "sucessmessage")
	public WebElement successMessage;

	@FindBy(className = "invalid-feedback")
	public WebElement errorMessage;
	
	@FindBy(className = "text-center")
	public WebElement otpVrfText;
	
	@FindBy(xpath = "//div/div[6]/form/div[2]/button")
	WebElement submitOtpBtn;
	
	@FindBy(xpath = "//div[6]/form/div[2]/span")
	public WebElement otpreqMsg;
	
	@FindBy(xpath = "//div[6]/form/div[1]/div")
	public WebElement otpBox;
	
	public void submitOtp() {
		submitOtpBtn.click();
	}

	public void gotoResetPassword() {
		resetPassword.click();
	}

	public String loginText() {
		String text = loginText1.getText();
		return text;
	}

	public void sendEmailForPswdReset(String yourmail) {
	    resetMail.sendKeys(yourmail);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(gnrtotpBtn));

	    gnrtotpBtn.click();
	}
}
