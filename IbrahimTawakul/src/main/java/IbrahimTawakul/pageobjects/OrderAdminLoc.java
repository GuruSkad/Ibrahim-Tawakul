package IbrahimTawakul.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderAdminLoc {
	WebDriver driver;
	
	public OrderAdminLoc(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@id=\"navbar-nav\"]/li[2]/a")
	public WebElement ordersBtn;
	
	@FindBy(xpath = "//section/h1")
	public WebElement pageTitle;
	
	@FindBy(xpath = "//section/div[1]/div[1]")
	public WebElement allBtn;
	
	@FindBy(xpath = "//section/div[1]/div[2]")
	public WebElement approvedBtn;
	
	@FindBy(xpath = "//section/div[1]/div[3]")
	public WebElement pendingBtn;
	
	@FindBy(xpath = "//section/div[1]/div[4]")
	public WebElement cancelledBtn;
	
	@FindBy(css = "input[type='search']")
	public WebElement srchBtn;
	
	@FindBy(xpath = "//section/div[2]/ng-select/div")
	public WebElement sortBtn;
	
	@FindBy(xpath = "//*[@id=\"abcacd2f2cd5\"]/div/div/div/span")
	public List<WebElement> sortingEleList;
	
	@FindBy(linkText = "Order ID number")
	public WebElement orderId;
	
	@FindBy(linkText = "Service Type")
	public WebElement serviceType;
	
	@FindBy(linkText = "Client Name")
	public WebElement clientName;
	
	@FindBy(linkText = "Date")
	public WebElement dateTitle;
	
	@FindBy(linkText = "Status")
	public WebElement statusTitle;
	
	@FindBy(linkText = "Comments")
	public WebElement comentsTitle;
	
	@FindBy(linkText = "Actions")
	public WebElement actionsTitle;
	
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1\"]/tbody/tr/td[1]/input")
	public List<WebElement> checkBoxes;
	
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1\"]/tbody/tr/td[2]")
	public List<WebElement> orderIdList;
	
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1\"]/tbody/tr/td[3]")
	public List<WebElement> serviceTypes;
	
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1\"]/tbody/tr/td[4]")
	public List<WebElement> clientNames;
	
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1\"]/tbody/tr/td[5]")
	public List<WebElement> dates;
	
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1\"]/tbody/tr/td[6]")
	public List<WebElement> statusList;
	
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1_paginate\"]/a[1]")
	public WebElement backPageBtn;
	
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1_paginate\"]/a[2]")
	public WebElement nextPageBtn;
	
}
