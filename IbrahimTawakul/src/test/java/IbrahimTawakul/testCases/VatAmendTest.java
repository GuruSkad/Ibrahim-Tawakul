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
import IbrahimTawakul.pageobjects.VatAmendmentLocators;
import IbrahimTawakul.testComponents.BaseTest;

public class VatAmendTest extends BaseTest {
	private LoginPageLocators login;
	private VatAmendmentLocators vatA;
	private SoftAssert softAssert;
	private Actions action;

	@BeforeClass
	public void initilazeLocators() throws AWTException, InterruptedException {
		login = new LoginPageLocators(driver);
		vatA = new VatAmendmentLocators(driver);
		action = new Actions(driver);
		softAssert = new SoftAssert();
		gotoVatAmend();
	}

	public void gotoVatAmend() throws InterruptedException {
		login.userName.sendKeys("anmol@skadits.com");
		login.password1.sendKeys("Testing@121");
		login.loginButton.click();
		Thread.sleep(4000);
		vatA.vatAnmdBtn.click();
	}
	
	@Test
	public void titleValidation() {
		Assert.assertEquals(vatA.pageHeading.getText().trim(), "VAT Amendment", "Page title not found or not correct");
	}
	
	

	
	@Test
	public void placeholderVerification() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(3000);
		String userNamePlaceholder = vatA.userNameP.getText();
	    String passPlaceholder = vatA.passwordP.getText();
	    String cnfpswdPlaceholder = vatA.cnfPassP.getText();
	    String commentsPlaceholder = vatA.reasonforP.getText();

	    softAssert.assertTrue(userNamePlaceholder.equalsIgnoreCase("User Name"), "User Name placeholder not found");
	    softAssert.assertTrue(passPlaceholder.equalsIgnoreCase("password"), "Password placeholder not found");
	    softAssert.assertTrue(cnfpswdPlaceholder.equalsIgnoreCase("Confirm Password"), "Confirm Password placeholder not found");
	    softAssert.assertTrue(commentsPlaceholder.equalsIgnoreCase("What to"), "What to placeholder not found");

	    softAssert.assertAll();
	}
	
	@Test
	public void passwordVisibility() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(3000);
		vatA.password.sendKeys("Asjkdf@32#56");
		vatA.cnfPassword.sendKeys("134367gyujv");
		String passwordBeforeClick = vatA.password.getAttribute("type");
		String cfnPassBfrClick = vatA.cnfPassword.getAttribute("type");
		Assert.assertEquals(passwordBeforeClick, "password", "Password field should be hidden initially.");
		Assert.assertEquals(cfnPassBfrClick, "password", " confirm Password field should be hidden initially.");
		vatA.pswdVisibilityBtn.click();
		vatA.cnfpswdVisibilityBtn.click();
		String passwordAfterClick = vatA.password.getAttribute("type");
		String cnfPassAfter = vatA.cnfPassword.getAttribute("type");
		Assert.assertEquals(passwordAfterClick, "text", "Password field should be visible after clicking the button.");
		Assert.assertEquals(cnfPassAfter, "text",
				"confirm Password field should be visible after clicking the button.");
	}
	
	@Test
	public void emptyErrMsg() {
		scrollPage(ScrollType.TO_BOTTOM, null, 0, 0);
		vatA.submitBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String[] errorMessages = vatA.getEmptyErrorMessages();
		// Assuming errorMessages are in order of email, password, confirmation
		Assert.assertEquals(errorMessages[0], "Username is required", "Email field error message not as expected");
		Assert.assertEquals(errorMessages[1], "Password is required", "Password field error message not as expected");
		Assert.assertEquals(errorMessages[2], "Confirm password is required",
				"Confirmation field error message not as expected");
	}
	
	@Test
	public void mismatchingPswd() throws InterruptedException {
		vatA.clearFields();
		vatA.userName.sendKeys("anmol@skadits.com");
		vatA.password.sendKeys("Testing@121");
		vatA.cnfPassword.sendKeys("Testing@123");
		scrollPage(ScrollType.TO_BOTTOM, null, 0, 0);
		vatA.submitBtn.click();
		Thread.sleep(3000);
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		Assert.assertTrue(vatA.pageHeading.getText().trim().contains("VAT Amendment"),
				"Application is not failing for unmatching passwords");
	}
	
	@Test
	public void testPasswordCopyPaste() {
		vatA.clearFields();
		vatA.password.sendKeys("TestPassword@123");
		vatA.cnfPassword.click();
		vatA.cnfPassword.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		String passwordBeforePaste = vatA.password.getAttribute("value");
		String passwordAfterPaste = vatA.cnfPassword.getAttribute("value");
		Assert.assertNotEquals(passwordBeforePaste, passwordAfterPaste, "Copy-paste of password detected");
	}
	
	@Test
	public void mandatoryTitle() {

		String emailPlaceholder = vatA.userNameP.getText();
		String passPlaceholder = vatA.passwordP.getText();
		String cnfpswdPlaceholder = vatA.cnfPassP.getText();

		softAssert.assertTrue(emailPlaceholder.equalsIgnoreCase("User Name*"), "User Name mandatory symbol not found");
		softAssert.assertTrue(passPlaceholder.equalsIgnoreCase("Password*"), "Password mandatory symbol not found");
		softAssert.assertTrue(cnfpswdPlaceholder.equalsIgnoreCase("Confirm Password*"),
				"Confirm Password mandatory symbol not found");
		softAssert.assertAll();
	}
	
	@Test
	public void vatAmendmentRej() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(3000);
		vatA.userName.sendKeys("anmol@skadits.com");
		vatA.password.sendKeys("Testing@121");
		vatA.cnfPassword.sendKeys("Testing@121");
		vatA.submitBtn.click();
		Thread.sleep(3000);
		Assert.assertEquals(vatA.vatAmnCnfMsg.getText(), 
				"Please check information before submitting!"," confirmation msg not found");
		vatA.cancleSubmitBtn.click();
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://ibrahimtawakul.hexagonsolutions.dev/pages/vat-amendment", 
		    "URL not matching for successful VAT Amendment");
	}
	
	@Test
	public void vatAmendment() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(3000);
		vatA.userName.sendKeys("anmol@skadits.com");
		vatA.password.sendKeys("Testing@121");
		vatA.cnfPassword.sendKeys("Testing@121");
		vatA.submitBtn.click();
		Thread.sleep(3000);
		Assert.assertEquals(vatA.vatAmnCnfMsg.getText(), 
				"Please check information before submitting!"," confirmation msg not found");
		vatA.finalSubmitBtn.click();
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://ibrahimtawakul.hexagonsolutions.dev/pages/order-list", 
		    "URL not matching for successful VAT deregistration");
		vatA.vatAnmdBtn.click();
	}
	
	@Test
	public void cursorUpdate() throws InterruptedException {
		
		vatA.userName.sendKeys("anmol");
		vatA.password.sendKeys("Testing@121");
		vatA.cnfPassword.sendKeys("Testing@121");
		vatA.submitBtn.click();
		Thread.sleep(3000);
		vatA.finalSubmitBtn.click();
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://ibrahimtawakul.hexagonsolutions.dev/pages/order-list", 
		    "URL not matching for successful VAT Amendment");
		boolean isCursorOnElement1 = vatA.ordersBtn.equals(driver.switchTo().activeElement());
        Assert.assertTrue(isCursorOnElement1, "Cursor should be on orders tab but found on VAT Amendment");

	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
}
