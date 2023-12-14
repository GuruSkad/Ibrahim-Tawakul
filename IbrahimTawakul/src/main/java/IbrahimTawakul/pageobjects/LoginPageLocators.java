package IbrahimTawakul.pageobjects;

import java.util.List;
import org.openqa.selenium.Keys;
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

	@FindBy(xpath = "(//img[@alt='Ibrahim-Tawakul-Logo'])[1]")
	public WebElement loginLogo;
	
	@FindBy(xpath = "//*[@id=\"page-header-user-dropdown\"]/span")
	public WebElement userBtn;
		
	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	public WebElement logoutbtn;

	@FindBy(name = "errorMessage")
	public WebElement getErrorMessage;
	
	@FindBy(name = "username")
	public WebElement userName;
	
	@FindBy(xpath = "//app-sidebar/div[1]/div[1]/a[1]/span[2]/img")
	public WebElement vatLogo;

	@FindBy(name = "password")
	public WebElement password1;

	@FindBy(xpath = "//button[normalize-space()='Log in']")
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

	@FindBy(xpath = "//app-vat-registration/section/h1")
	public WebElement vatRegistTitle;
	
	@FindBy(tagName = "a")
	public List<WebElement> links;
	
	//place holders
	@FindBy(xpath = "//section/div/div/div[1]/form/div[1]/label")
	public WebElement userP;
	
	@FindBy(xpath = "//section/div/div/div[1]/form/div[2]/label")
	public WebElement passP;

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

	public void loginApplication(String username, String password) throws InterruptedException {
		userName.sendKeys(username);
		password1.sendKeys(password);
		Thread.sleep(2000);
		loginButton.click();
	}
	
	public void logout() throws InterruptedException
	{
		userBtn.click();
		Thread.sleep(3000);
		logoutbtn.click();
	}

	// Locators for forget password module

	@FindBy(xpath = "//a[contains(text(),'Reset Password')]")
	public WebElement resetPassword;

	@FindBy(xpath = "//div/div/div[4]/h1")
	public WebElement resetPassText;

	@FindBy(xpath = "(//a[contains(text(),'Go to login')])[1]")
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

	@FindBy(xpath  = "//form/div[1]/div/span")
	public WebElement errorMessage;
	
	@FindBy(xpath = "//div/div[6]/h1")
	public WebElement otpVrfText;
	
	@FindBy(xpath = "//button[contains(text(),'Verify and Reset Password')]")
	WebElement submitOtpBtn;
	
	@FindBy(xpath = "//div[6]/form/div[2]/span")
	public WebElement otpreqMsg;
	
	@FindBy(id="digit1")
	public WebElement otpBox1;
	
	@FindBy(id="digit2")
	public WebElement otpBox2;
	
	@FindBy(id="digit3")
	public WebElement otpBox3;
	
	@FindBy(id="digit4")
	public WebElement otpBox4;
	
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
	    resetMail.sendKeys(Keys.ENTER);
	}
}
