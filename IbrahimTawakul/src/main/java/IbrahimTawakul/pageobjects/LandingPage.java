package IbrahimTawakul.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;

	}

	public void goTo() {
		driver.get("https://test.ibrahimtawakul.hexagonsolutions.dev");
	}

}
