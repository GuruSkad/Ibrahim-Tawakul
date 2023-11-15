package IbrahimTawakul.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import IbrahimTawakul.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;

	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties property = new Properties();
		FileInputStream FIS = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\IbrahimTawakul\\resources\\GlobalData.properties");
		property.load(FIS);
		String browserName = property.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		else {
			System.out.println("Error");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}

	@BeforeClass
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}

	public WebElement waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		return wait.until(ExpectedConditions.visibilityOf(element));
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
	 * to scroll upto a webelement:- scrollPage(ScrollType.TO_ELEMENT,
	 * yourWebElement, 0, 0);
	 * 
	 * to scroll to the top of the page:- scrollPage(ScrollType.TO_TOP, null, 0, 0);
	 * 
	 * to scroll to the bottom of the page:- scrollPage(ScrollType.TO_BOTTOM, null, 0,
	 * 0);
	 * 
	 * to scroll by given X, Y coordinates (give x and y value):-
	 * scrollPage(ScrollType.BY_COORDINATES, null, 500, 1000);
	 */

}
