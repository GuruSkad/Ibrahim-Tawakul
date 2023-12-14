package IbrahimTawakul.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VatConsultationLoc {
	WebDriver driver;

	public VatConsultationLoc(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "VAT Consultations")
	public WebElement vatConsBtn;
	
	@FindBy(xpath = "//div/app-vat-consultation/section/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath ="//form/div/div[1]/mat-form-field/div[1]/div[2]/div[1]/label")
	public WebElement dateP;
	
	@FindBy(xpath = "//form/div/div[1]/mat-form-field/div[1]/div[2]/div[1]")
	public WebElement dateBox;
	
	@FindBy(xpath ="//form/div/div[2]/mat-form-field/div[1]/div[2]/div")
	public WebElement timeBox;
	
	@FindBy(xpath = "//form/div/div[2]/mat-form-field/div[1]/div[2]/div/label")
	public WebElement timep;
	
	@FindBy(xpath = "//input[@name='Topic']")
	public WebElement topicBox;
	
	@FindBy(xpath = "//input[@name='Topic']/following-sibling::label")
	public WebElement topicp;
	
	@FindBy(xpath = "//form/div/div[4]/div/mat-form-field/div[1]")
	public WebElement languageBox;
	
	@FindBy(xpath = "//div[4]/div/mat-form-field/div[1]/div[2]/div/label")
	public WebElement languageP;
	
	@FindBy(css ="button[type='submit']")
	public WebElement submitBtn;
	
	@FindBy(xpath = "//mat-month-view/table/tbody/tr/td")
	public List <WebElement> dates;
	
	@FindBy(xpath = "(//span[@class='mat-mdc-focus-indicator'])[4]")
	public WebElement nextMnthBtn;
	
	@FindBy(xpath = "//mat-calendar-header/div/div/button[2]/span[1]")
	public WebElement previousMnthBtn;
	
	@FindBy(css ="input[aria-label='Hour']")
	public WebElement hourInput;
	
	@FindBy(css ="input[aria-label='Minute']")
	public WebElement minuteInput;
	
	@FindBy(xpath = "//span[@title='Click to toggle']")
	public WebElement amPm;
	
	@FindBy(xpath = "//*[@id='cdk-overlay-21']/div/mat-option[1]/span")
	public WebElement languages;
	
	@FindBy(xpath = "//button[normalize-space()='No, Cancel']")
	public WebElement cancleBtn;
	
	@FindBy(xpath = "//button[normalize-space()='Yes, Save it']")
	public WebElement finalSaveBtn;
	
	@FindBy(xpath = "//form/div/div[1]/div/span")
	public WebElement dateErrMsg;
	
	@FindBy(xpath = "//form/div/div[2]/div/span")
	public WebElement timeErrMsg;
	
	@FindBy(xpath = "//form/div/div[3]/div/span")
	public WebElement topicErrMsg;
	
	@FindBy(xpath = "//form/div/div[4]/div/span")
	public WebElement LangErrMsg;
	
}
