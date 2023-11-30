package IbrahimTawakul.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VatAmendmentLocators {
	WebDriver driver;
	public VatAmendmentLocators(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='VAT Amendment']")
	public WebElement vatAnmdBtn;
	
	@FindBy(xpath = "//h1[normalize-space()='VAT Amendment']")
	public WebElement pageHeading;
	
	@FindBy(xpath = "//h1[normalize-space()='Order Detail List']")
	public WebElement orderPageTitle;
	
	@FindBy(name = "username")
	public WebElement userName;
	
	@FindBy(name = "password")
	public WebElement password;
	
	@FindBy(xpath = "//input[@name='Password']")
	public WebElement cnfPassword;
	
	@FindBy(xpath = "(//*[name()='svg'])[4]")
	public WebElement pswdVisibilityBtn;
	
	@FindBy(xpath = "(//*[name()='svg'])[6]")
	public WebElement cnfpswdVisibilityBtn;
	
	@FindBy(xpath = "//form/div/div[4]/div/div/textarea")
	public WebElement commentBox;
	
	@FindBy(css = "button[type='submit']")
	public WebElement submitBtn;
	
	@FindBy(xpath = "//form/div/div[1]/div/div/span")
	public WebElement userNameErrMsg;
	
	@FindBy(xpath = "//form/div/div[2]/div/div/span")
	public WebElement passwordErrMsg;
	
	@FindBy(xpath = "//form/div/div[3]/div/div/span")
	public WebElement cnfPassErrMsg;
	
	@FindBy(xpath = "(//div[@id='swal2-html-container'])[1]")
	public WebElement vatAmnCnfMsg;
	
	@FindBy(xpath = "//button[normalize-space()='Yes, Save it']")
	public WebElement finalSubmitBtn;
	
	@FindBy(xpath = "//button[normalize-space()='No, Cancel']")
	public WebElement cancleSubmitBtn;
	
	@FindBy(xpath = "//span[normalize-space()='Orders']")
	public WebElement ordersBtn;
	
	//placeholder
	@FindBy(xpath = "//section/form/div/div[1]/div/label")
	public WebElement userNameP;
	@FindBy(xpath = "//section/form/div/div[2]/div/label")
	public WebElement passwordP;
	@FindBy(xpath = "//section/form/div/div[3]/div/label")
	public WebElement cnfPassP;
	@FindBy(xpath = "//section/form/div/div[4]/div/div/label")
	public WebElement reasonforP;
	
	public String[] getEmptyErrorMessages() {
	    String email = userNameErrMsg.getText();
	    String pass = passwordErrMsg.getText();
	    String cnf = cnfPassErrMsg.getText();
	    return new String[] {email, pass, cnf};
	}
	
	public void clearFields() {
		userName.sendKeys("ghh");
		password.sendKeys("hkjh");
		cnfPassword.sendKeys("kjgh");
		cnfPassword.clear();
		userName.clear();
		password.clear();
	}
	
		
}
