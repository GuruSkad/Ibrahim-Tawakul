package IbrahimTawakul.testComponents;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		System.out.println(browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Error");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}

	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}

}
