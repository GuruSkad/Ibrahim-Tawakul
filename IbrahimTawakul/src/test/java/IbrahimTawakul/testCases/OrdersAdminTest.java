package IbrahimTawakul.testCases;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;

import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.pageobjects.VatRegistrationLocators;
import IbrahimTawakul.testComponents.BaseTest;
import IbrahimTawakul.testComponents.VATRegistrationHelper;

public class OrdersAdminTest extends BaseTest{
	private VatRegistrationLocators vat;
	private LoginPageLocators login;
	private Robot rb;
	private VATRegistrationHelper helper;
	private Actions action;	

	@BeforeMethod
	public void initializeLocators() throws AWTException {
		vat = new VatRegistrationLocators(driver);
		login = new LoginPageLocators(driver);
		rb = new Robot();
		helper = new VATRegistrationHelper(driver);
		action = new Actions(driver);
		adminLogin();
	}
	
	public void adminLogin() {
		login.userName.sendKeys("infoibrahimtawakul@gmail.com");
		login.password1.sendKeys("Ibrahim@1234");
		login.loginButton.click();
	}

}
