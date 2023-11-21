package IbrahimTawakul.testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.pageobjects.VatRegistrationLocators;
import IbrahimTawakul.testComponents.BaseTest;

public class VatRegistrationTest extends BaseTest{
	private VatRegistrationLocators vat;
	private LoginPageLocators login;

	@BeforeMethod
	public void initializeLoginPageLocators() {
		vat = new VatRegistrationLocators(driver);
		login = new LoginPageLocators(driver);
	}
	
	public void loginApplication() {
		login.userName.sendKeys("anmol.smit@gmail.com");
		login.password1.sendKeys("Anmolkumar@1234");
		login.loginButton.click();
	}

	@Test
	public void heading() {
		loginApplication();
		for(WebElement ab: vat.uploadBoxes) {
			String text = ab.getText();
			System.out.println(text);
			
		}
	}
	
	@Test
	public void uiTesting() {
		loginApplication();
		Assert.assertTrue(vat.logo.isDisplayed());
		Assert.assertTrue(vat.menuText.getText().contains("MENU"));
		Assert.assertTrue(vat.pageTitle.getText().contains("VAT Registration"));
		
	}
	
	@AfterMethod
		public void tearDown() {
			driver.quit();
		}
}
