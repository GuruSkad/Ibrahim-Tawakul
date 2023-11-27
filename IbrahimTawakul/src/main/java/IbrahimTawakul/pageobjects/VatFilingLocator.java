package IbrahimTawakul.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VatFilingLocator {
	WebDriver driver;

	public VatFilingLocator(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='VAT Filing']")
	public WebElement vatFilingBtn;
	@FindBy(xpath = "//h1[normalize-space()='VAT Filing']")
	public WebElement pageHeading;
	
	@FindBy(xpath = "//input[@name='FTA_UserName']")
	public WebElement emailBox;
	
	@FindBy(xpath = "(//input[contains(@placeholder,'')])[2]")
	public WebElement passwordBox;
	
	@FindBy(xpath = "(//*[name()='svg'])[4]")
	public WebElement showPswdBtn;
		
	@FindBy(xpath = "//label[normalize-space()='Confirm Password']")
	public WebElement cnfPasswordBox;
	
	@FindBy(xpath = "(//*[name()='svg'])[6]")
	public WebElement showCnfPswd;
	
	@FindBy(id = "mat-select-value-1")
	public WebElement periodbox;
	
	@FindBy(xpath = "//label[normalize-space()='Comments']")
	public WebElement commentbox;
	
	//for placeholder
	@FindBy(xpath = "//label[normalize-space()='User Name']")
	public WebElement emailP;
	
	@FindBy(xpath = "//label[normalize-space()='Password']")
	public WebElement passwordP;
	
	@FindBy(xpath = "//*[@class='row']/div[1]/h3")
	public WebElement salesTitle;
	
	@FindBy(xpath = "//*[@class='row']/div[2]/h3")
	public WebElement purchaseTitle;
	
	@FindBy(xpath = "//*[@class='row']/div[1]/h3")
	public WebElement statemntTitle;
	
	@FindBy(xpath = "//h3[normalize-space()='Uploaded Files']")
	public WebElement uplodedTitle;
	
	@FindBy(xpath = "//section/form/div[3]/div/div/div")
	public WebElement uploadedFiles;
	
	@FindBy(className = "greenBtn.mt-4")
	public WebElement submitBtn;
	
	@FindBy(xpath = "(//label[@class='uploader'])[1]")
	public WebElement salesUpload;
	
	@FindBy(xpath = "(//label[@class='uploader'])[2]")
	public WebElement purchaseUpload;
	
	@FindBy(xpath = "(//label[@class='uploader'])[3]")
	public WebElement statementUpload;
	
	@FindBy(xpath = "//form/div[1]/div[1]/div/div")
	public WebElement emailErrMsg;
	
	@FindBy(xpath = "//form/div[1]/div[2]/div/div")
	public WebElement PassErrMsg;
	
	@FindBy(xpath = "//form/div[1]/div[3]/div/div")
	public WebElement CnfPassErrMsg;
	
	@FindBy(xpath = "//form/div[2]/div[1]/div[2]")
	public WebElement errorSalesMsg;
	
	@FindBy(xpath = "//form/div[2]/div[2]/div[2]")
	public WebElement errorPurchaseMsg;
	
	@FindBy(xpath = "//form/div[2]/div[3]/div[2]")
	public WebElement errorStatementMsg;
	
	public void clearFeilds() {
		emailBox.clear();
		passwordBox.clear();
		cnfPasswordBox.clear();
	}
	
	public String[] getEmptyErrorMessages() {
	    String email = emailErrMsg.getText();
	    String pass = PassErrMsg.getText();
	    String cnf = CnfPassErrMsg.getText();
	    String sales  = errorSalesMsg.getText();
	    String purch = errorPurchaseMsg.getText();
	    String stmt = errorStatementMsg.getText();
	    return new String[] {email, pass, cnf, sales, purch, stmt};
	}

	
}
