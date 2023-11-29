package IbrahimTawakul.testCases;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.pageobjects.VatDeregistrationLocator;
import IbrahimTawakul.testComponents.BaseTest;

public class VatDeregistrationTest extends BaseTest {
	private LoginPageLocators login;
	private VatDeregistrationLocator vatd;
	private SoftAssert softAssert;
	private Actions action;

	@BeforeClass
	public void initilazeLocatprs() throws AWTException, InterruptedException {
		login = new LoginPageLocators(driver);
		vatd = new VatDeregistrationLocator(driver);
		action = new Actions(driver);
		softAssert = new SoftAssert();
		gotoVatDereg();
	}

	public void gotoVatDereg() throws InterruptedException {
		login.userName.sendKeys("anmol@skadits.com");
		login.password1.sendKeys("Testing@121");
		login.loginButton.click();
		Thread.sleep(4000);
		vatd.vatDereBtn.click();
	}
	
	@Test
	public void titleValidation() {
		Assert.assertEquals(vatd.pageHeading.getText(), "VAT Deregistration", "Page title not found or not correct");
	}
	
	

	
	@Test
	public void placeholderVerification() {
	    String userNamePlaceholder = vatd.userNameP.getText();
	    String passPlaceholder = vatd.passwordP.getText();
	    String cnfpswdPlaceholder = vatd.cnfPassP.getText();
	    String commentsPlaceholder = vatd.reasonforP.getText();

	    softAssert.assertTrue(userNamePlaceholder.equalsIgnoreCase("User Name"), "User Name placeholder not found");
	    softAssert.assertTrue(passPlaceholder.equalsIgnoreCase("password"), "Password placeholder not found");
	    softAssert.assertTrue(cnfpswdPlaceholder.equalsIgnoreCase("Confirm Password"), "Confirm Password placeholder not found");
	    softAssert.assertTrue(commentsPlaceholder.equalsIgnoreCase("ReasonFor"), "ReasonFor placeholder not found");

	    softAssert.assertAll();
	}
	
	@Test
	public void passwordVisibility() throws InterruptedException {
		//driver.navigate().refresh();
		Thread.sleep(3000);
		vatd.password.sendKeys("Asjkdf@32#56");
		vatd.cnfPassword.sendKeys("134367gyujv");
		String passwordBeforeClick = vatd.password.getAttribute("type");
		String cfnPassBfrClick = vatd.cnfPassword.getAttribute("type");
		Assert.assertEquals(passwordBeforeClick, "password", "Password field should be hidden initially.");
		Assert.assertEquals(cfnPassBfrClick, "password", " confirm Password field should be hidden initially.");
		vatd.pswdVisibilityBtn.click();
		vatd.cnfpswdVisibilityBtn.click();
		String passwordAfterClick = vatd.password.getAttribute("type");
		String cnfPassAfter = vatd.cnfPassword.getAttribute("type");
		Assert.assertEquals(passwordAfterClick, "text", "Password field should be visible after clicking the button.");
		Assert.assertEquals(cnfPassAfter, "text",
				"confirm Password field should be visible after clicking the button.");
	}
	
	@Test
	public void emptyErrMsg() {
		scrollPage(ScrollType.TO_BOTTOM, null, 0, 0);
		vatd.submitBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String[] errorMessages = vatd.getEmptyErrorMessages();
		// Assuming errorMessages are in order of email, password, confirmation
		Assert.assertEquals(errorMessages[0], "Username is required", "Email field error message not as expected");
		Assert.assertEquals(errorMessages[1], "Password is required", "Password field error message not as expected");
		Assert.assertEquals(errorMessages[2], "Confirm password is required",
				"Confirmation field error message not as expected");
	}
	
	@Test
	public void mismatchingPswd() {
		vatd.clearFields();
		vatd.userName.sendKeys("anmol@skadits.com");
		vatd.password.sendKeys("Testing@121");
		vatd.cnfPassword.sendKeys("Testing@123");
		scrollPage(ScrollType.TO_BOTTOM, null, 0, 0);
		vatd.submitBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		Assert.assertTrue(vatd.pageHeading.getText().contains("VAT Deregistration"),
				"Application is not failing for unmatching passwords");
	}
	
	@Test
	public void testPasswordCopyPaste() {
		vatd.clearFields();
		vatd.password.sendKeys("TestPassword@123");
		vatd.cnfPassword.click();
		vatd.cnfPassword.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		String passwordBeforePaste = vatd.password.getAttribute("value");
		String passwordAfterPaste = vatd.cnfPassword.getAttribute("value");
		Assert.assertNotEquals(passwordBeforePaste, passwordAfterPaste, "Copy-paste of password detected");
	}
	
	@Test
	public void improperPass() {

		// Test for password length error
		vatd.password.sendKeys("aA1@");
		String lengthErr = vatd.passwordErrMsg.getText().trim();
		System.out.println(lengthErr + "length error check");
		softAssert.assertTrue(lengthErr.contains(" Passwords must be at least 8 characters."),
				"Password error message does not match for a shorter password");

		// Test for absence of uppercase letter error
		vatd.password.clear();
		vatd.password.sendKeys("123456789#");
		String letterErr = vatd.passwordErrMsg.getText().trim();
		System.out.println(letterErr);
		softAssert.assertTrue(letterErr.contains(" Passwords must have at least one uppercase ('A'-'Z')."),
				"Password error message does not match for a password without an uppercase letter");

		// Test for absence of lowercase letter error
		vatd.password.clear();
		vatd.password.sendKeys("123456789A#");
		String lowercaseErr = vatd.passwordErrMsg.getText().trim();
		System.out.println(lowercaseErr);
		softAssert.assertTrue(lowercaseErr.contains(" Passwords must have at least one lowercase ('a'-'z')."),
				"Password error message does not match for a password without a lowercase letter");

		// Test for absence of digit error
		vatd.password.clear();
		vatd.password.sendKeys("Abcdefgh#");
		String digitErr = vatd.passwordErrMsg.getText().trim();
		System.out.println(digitErr);
		softAssert.assertTrue(digitErr.contains("Passwords must have at least one digit ('0'-'9')."),
				"Password error message does not match for a password without a digit");

		// Test for absence of special character error
		vatd.password.clear();
		vatd.password.sendKeys("Abcdefgh1");
		String specialCharErr = vatd.passwordErrMsg.getText().trim();
		System.out.println(specialCharErr);
		softAssert.assertTrue(specialCharErr.contains("Passwords must have at least one special character."),
				"Password error message does not match for a password without a special character");

		softAssert.assertAll();
	}
	
	@Test
	public void mandatoryTitle() {

		String emailPlaceholder = vatd.userNameP.getText();
		String passPlaceholder = vatd.passwordP.getText();
		String cnfpswdPlaceholder = vatd.cnfPassP.getText();

		softAssert.assertTrue(emailPlaceholder.equalsIgnoreCase("User Name*"), "User Name mandatory symbol not found");
		softAssert.assertTrue(passPlaceholder.equalsIgnoreCase("Password*"), "Password mandatory symbol not found");
		softAssert.assertTrue(cnfpswdPlaceholder.equalsIgnoreCase("Confirm Password*"),
				"Confirm Password mandatory symbol not found");
		softAssert.assertAll();
	}
	
	@Test
	public void deRegistration() throws InterruptedException {
		vatd.userName.sendKeys("anmol@skadits.com");
		vatd.password.sendKeys("Testing@121");
		vatd.cnfPassword.sendKeys("Testing@121");
		vatd.submitBtn.click();
		Thread.sleep(3000);
		vatd.finalSubmitBtn.click();
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://ibrahimtawakul.hexagonsolutions.dev/pages/order-list", 
		    "URL not matching for successful VAT deregistration");
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
}
