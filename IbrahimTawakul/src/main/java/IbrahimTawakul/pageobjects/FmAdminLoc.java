package IbrahimTawakul.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FmAdminLoc {
	WebDriver driver;
	
	public FmAdminLoc(WebDriver driver) {
		this.driver  = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".MainTitle.mb-3")
	public WebElement pageTitle;
	
	@FindBy(linkText = "File Manager")
	public WebElement fMBtn;
	
	@FindBy(css ="input[type='text']")
	public WebElement srchBtnGrid;
	
	@FindBy(css = "input[type='search']")
	public WebElement srchBtnList;
	
	@FindBy(xpath = "//div[@class='btn-group dropdown']")
	public WebElement sortBtn;
	
	@FindBy(className = "gridVw")
	public WebElement gridVwBtn;
	
	@FindBy(css =".listVw.ViewActv")
	public WebElement listView;
	
	@FindBy(xpath = "(//div[@class='fldrFileWrapper'])")
	public List<WebElement> filesGrdvw;
	
	@FindBy(xpath = "(//span[contains(text(),'View')])")
	public List<WebElement> vwFileGrdBtns;
	
	@FindBy(xpath = "(//span[contains(text(),'Download')])")
	public List<WebElement> downldFileGrdBtns;
	
	@FindBy(linkText = "Name")
	public WebElement fileNameHeading;
	
	@FindBy(linkText = "File Counts")
	public WebElement fileCountsHeading;
	
	@FindBy(linkText = "Last modified")
	public WebElement fileModifiedHeading;
	
	@FindBy(xpath = "//tbody/tr/td[1]")
	public List<WebElement> fileNamesLst;
	
	@FindBy(xpath = "//tbody/tr/td[3]")
	public List<WebElement> datesModifiedLst;
	
	@FindBy(xpath = "(//a[normalize-space()='Next'])")
	public WebElement nextBtn;
	
	@FindBy(xpath = "(//a[normalize-space()='Previous'])")
	public WebElement previousBtn;	
	
	@FindBy(id = "page-header-user-dropdown")
	public WebElement userBtn;
	
	@FindBy(xpath = "//a[@routerlink='/pages/my-account']")
	public WebElement profileSetting;
	
	@FindBy(xpath = "(//a[@class='dropdown-item'])[2]")
	public WebElement logoutBtn;
	

}
