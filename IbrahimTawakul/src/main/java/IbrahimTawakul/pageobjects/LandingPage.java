package IbrahimTawakul.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goTo() {
		driver.get("https://ibrahimtawakul.hexagonsolutions.dev/");
	}

}
