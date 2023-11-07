package IbrahimTawakul.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmai")
	WebElement userEmail;

	public void loginApplication(String email) {

		userEmail.sendKeys(email);

	}
	
	public void goTo() {
		driver.get("https://ibrahimtawakul.hexagonsolutions.dev/");
	}

}
