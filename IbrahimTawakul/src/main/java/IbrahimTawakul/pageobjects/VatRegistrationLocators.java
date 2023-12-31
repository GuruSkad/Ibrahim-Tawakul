package IbrahimTawakul.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VatRegistrationLocators {

	WebDriver driver;

	public VatRegistrationLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//app-sidebar/div[1]/div[1]/a[1]/span[2]/img")
	public WebElement logo;
	
	@FindBy(xpath = "//span[contains(text(),'MENU')]")
	public WebElement menuText;
	
	@FindBy(css = ".hamburger-icon")
	public WebElement minimiseMnuBtn;
	
	@FindBy(xpath = "//h1[contains(text(),'VAT Registration')]")
	public WebElement pageTitle;
	
	@FindBy(xpath = "//label[@class='uploader'][1]")
	public WebElement tradeUploadBtn;
	
	@FindBy(xpath = "//form/div[2]/div[2]/div/label")
	public WebElement moaUploadBtn;
	
	@FindBy(xpath = "//form/div[2]/div[3]/div/label")
	public WebElement idUploadBtn;
	
	@FindBy(xpath = "(//label[@class='uploader'])[4]")
	public WebElement pasportUploadBtn;
	
	@FindBy(xpath = "(//label[@class='uploader'])[5]")
	public WebElement dclrationUploadBtn;
	
	@FindBy(xpath = "//form/div[2]/div/h3")
	public List<WebElement> documentsTitle;
	
	@FindBy(xpath = "//form/div[2]/div[1]/h3")
	public WebElement tradeTittle;
	
	@FindBy(xpath = "//form/div[2]/div[2]/h3")
	public WebElement moaTittle;
	
	@FindBy(xpath = "//form/div[2]/div[3]/h3")
	public WebElement idTittle;
	
	@FindBy(xpath = "//form/div[2]/div[4]/h3")
	public WebElement passportTittle;
	
	@FindBy(xpath = "//form/div[2]/div[5]/h3")
	public WebElement dclrionTitel;
	
	@FindBy(className = "dwnldFieBtn")
	public WebElement dwnldSampleFilesBtn;
	
	@FindBy(id = "back-to-top")
	public WebElement backToTopBtn;
	
	@FindBy(name ="error for same file")
	public WebElement errorFrSameFile;
	
	@FindBy(id = "swal2-html-container")
	WebElement SucessMsg;
	
	@FindBy(name = "email")
	public WebElement emailBox;
	
	@FindBy(id = "phone")
	public WebElement mobileBox;
	
	@FindBy(xpath = "//ngx-intl-tel-input/div/div/div[1]")
	public WebElement countryListDropdwnBtn;
	
	@FindBy(xpath = "//*[contains(@class, 'iti__country-list')]/li")
	public List<WebElement> countriesList;
	
	@FindBy(xpath = "//ngx-intl-tel-input/div/div/div")
	public WebElement searchCntryBx;
	
	@FindBy(xpath = "//div[1]/div[1]/div/div/span")
	public WebElement emailErrTxt;
	
	@FindBy(xpath = "//div[1]/div[2]/div/div/span")
	public WebElement mobileErrTxt;
	
	@FindBy(xpath = "//*[@id=\"navbar-nav\"]/li/a/span")
	public List<WebElement> menuItems;
	
	@FindBy(xpath = "//section/form/div[3]/div/div")
	public List<WebElement> uploadedFiles;
	
	@FindBy(xpath = "(//label[@class='uploader'])")
	public List<WebElement> uploadBoxes;
	
	@FindBy(xpath = "//button[contains(text(),'Make Payment')]")
	public WebElement makePymntBtn;
	
	@FindBy(className = "submitWdoPaymnt.mt-4")
	public WebElement submitWithoutPmntBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Track your order']")
	public WebElement trackYourOrdrBtn;
	
	public String successMessage()
	{
		String msg = SucessMsg.getText();
		return msg;
	}
	
	
}
