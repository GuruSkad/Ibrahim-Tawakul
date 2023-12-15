package IbrahimTawakul.testComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import IbrahimTawakul.pageobjects.LandingPage;
import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.pageobjects.SignUpPageLocators;
import IbrahimTawakul.pageobjects.VatAmendmentLocators;
import IbrahimTawakul.pageobjects.VatConsultationLoc;
import IbrahimTawakul.pageobjects.VatFilingLocator;

public class BaseTest {

	public WebDriver driver;
	ChromeOptions options = new ChromeOptions();
	public LandingPage landingPage;
	public LoginPageLocators login;
	public VatConsultationLoc vatc;
	public SignUpPageLocators signUp;
	public VatFilingLocator vatf;
	public VatAmendmentLocators vatA;
	public Actions action;
	public SoftAssert softAssert;
	public Robot rb;
	public VATRegistrationHelper helper;
	

	public WebDriver initializeDriver() throws IOException {
		Properties property = new Properties();
		FileInputStream FIS = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\IbrahimTawakul\\resources\\GlobalData.properties");
		property.load(FIS);
		String browserName = property.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(options);
			options.addArguments("clear-automation-profile-settings");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		else {
			System.out.println("Error");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}
	
	

	
	@BeforeClass
	public LandingPage launchApplication() throws IOException, AWTException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		login = new LoginPageLocators(driver);
		vatc = new VatConsultationLoc(driver);
		signUp = new SignUpPageLocators(driver);
		vatf = new VatFilingLocator(driver);
		vatA = new VatAmendmentLocators(driver);
		action = new Actions(driver);
		softAssert = new SoftAssert();
		rb = new Robot();
		helper = new VATRegistrationHelper(driver);
		return landingPage;
	}
	
	@BeforeMethod
	public void refresh() {
		driver.navigate().refresh();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	
	public void goToPage(String pageName) throws InterruptedException {
	    login.loginApplication("anmol@skadits.com", "Testing@121");
	    
	    switch (pageName.toLowerCase()) {
	        case "vatconsultation":
	            waitForClickable(vatc.vatConsBtn);
	            vatc.vatConsBtn.click();
	            break;
	        case "vatfiling":
	        	waitForClickable(vatf.vatFilingBtn);
	        	vatf.vatFilingBtn.click();
	            break;
	        case "vatamend":
	        	waitForClickable(vatA.vatAnmdBtn);
	        	vatA.vatAnmdBtn.click();
	            break;
	        default:
	            System.out.println("Page not found or invalid page name");
	            break;
	    }	
	    //eg: goToPage("vatconsultation");
	}
	
	
	public WebElement waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	 public WebElement waitForClickable(WebElement element) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	 
	public void scrollPage(ScrollType scrollType, WebElement element, int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		switch (scrollType) {
		case TO_ELEMENT:
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			break;

		case TO_TOP:
			js.executeScript("window.scrollTo(0, 0);");
			break;

		case TO_BOTTOM:
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			break;

		case BY_COORDINATES:
			js.executeScript("window.scrollTo(" + x + "," + y + ");");
			break;

		default:
			throw new IllegalArgumentException("Invalid ScrollType provided: " + scrollType);
		}
	}

	public enum ScrollType {
		TO_ELEMENT, TO_TOP, TO_BOTTOM, BY_COORDINATES
	}

	/*
	 * to scroll upto a webelement:- scrollPage(ScrollType.TO_ELEMENT,yourWebElement, 0, 0);
	 * 
	 * to scroll to the top of the page:- scrollPage(ScrollType.TO_TOP, null, 0, 0);
	 * 
	 * to scroll to the bottom of the page:- scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
	 * 
	 * to scroll by given X, Y coordinates (give x and y value):-
	 * scrollPage(ScrollType.BY_COORDINATES, null, 500, 1000);
	 */

}
