package IbrahimTawakul.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {

	@FindBy(id = "username")
	WebElement userNameId;

	public void loginTest(String userName) {
		userNameId.sendKeys(userName);
	}
}
