package IbrahimTawakul.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CorporateTaxLoc {

	WebDriver driver;

	public CorporateTaxLoc(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='Other Services']")
	public WebElement corporateTaxBtn;

	@FindBy(xpath = "//h1[normalize-space()='Corporate Tax']")
	public WebElement pageTitle;

	@FindBy(xpath = "//input[@name='FTA_UserName']")
	public WebElement userName;

	@FindBy(xpath = "//section/form/div[1]/div[2]/div/input")
	public WebElement passwordBox;

	@FindBy(xpath = "(//*[name()='svg'])[4]")
	public WebElement showPswdBtn;

	@FindBy(xpath = "//form/div[2]/div[1]/div/label")
	public WebElement tradeUploadBtn;

	@FindBy(xpath = "//form/div[2]/div[2]/div/label")
	public WebElement moaUploadBtn;

	@FindBy(xpath = "//form/div[2]/div[3]/div/label")
	public WebElement idUploadBtn;

	@FindBy(xpath = "//form/div[2]/div[4]/div/label")
	public WebElement pasportUploadBtn;

	@FindBy(xpath = "//form/div[2]/div[1]/h3")
	public WebElement tradeTittle;

	@FindBy(xpath = "//form/div[2]/div[2]/h3")
	public WebElement moaTittle;

	@FindBy(xpath = "//form/div[2]/div[3]/h3")
	public WebElement idTittle;

	@FindBy(xpath = "//form/div[2]/div[4]/h3")
	public WebElement passportTittle;

	@FindBy(id = "back-to-top")
	public WebElement backToTopBtn;

	@FindBy(name = "error for same file")
	public WebElement errorFrSameFile;

	@FindBy(id = "swal2-html-container")
	WebElement SucessMsg;

	@FindBy(xpath = "//form/div[1]/div[1]/div/div")
	public WebElement emailErrMsg;

	@FindBy(xpath = "//section/form/div[1]/div[2]/div/div[2]/span")
	public WebElement PassErrMsg;

	@FindBy(xpath = "//form/div[2]/div[1]/div[2]")
	public WebElement errorTradeMsg;

	@FindBy(xpath = "//form/div[2]/div[2]/div[2]")
	public WebElement errorMoaMsg;

	@FindBy(xpath = "//form/div[2]/div[3]/div[2]")
	public WebElement errorIdMsg;

	@FindBy(xpath = "//form/div[2]/div[4]/div[2]")
	public WebElement errPassportMsg;

	@FindBy(css = "button[type='submit']")
	public WebElement submitBtn;

	@FindBy(xpath = "//section/form/div[3]/div/div")
	public List<WebElement> uploadedFiles;

	@FindBy(xpath = "//button[normalize-space()='Track your order']")
	public WebElement trackYourOrdrBtn;

	// placeholder
	@FindBy(xpath = "//label[normalize-space()='User Name']")
	public WebElement userNameP;

	@FindBy(xpath = "//label[normalize-space()='Password']")
	public WebElement passwordP;

	public String successMessage() {
		String msg = SucessMsg.getText();
		return msg;
	}

	public void clearFeilds() {
		userName.sendKeys("465");
		passwordBox.sendKeys("hjgj");
		userName.clear();
		passwordBox.clear();
	}
	
	public String[] getEmptyErrorMessages() {
	    String email = emailErrMsg.getText();
	    String pass = PassErrMsg.getText();
	    String trade = errorTradeMsg.getText();
	    String moa  = errorMoaMsg.getText();
	    String id = errorIdMsg.getText();
	    String psprt = PassErrMsg.getText();
	    return new String[] {email, pass, trade, moa, id, psprt};
	}

}
