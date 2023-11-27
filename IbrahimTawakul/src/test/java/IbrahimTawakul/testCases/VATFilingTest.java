package IbrahimTawakul.testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.pageobjects.VatFilingLocator;
import IbrahimTawakul.pageobjects.VatRegistrationLocators;
import IbrahimTawakul.testComponents.BaseTest;
import IbrahimTawakul.testComponents.VATRegistrationHelper;
import IbrahimTawakul.testComponents.BaseTest.ScrollType;

public class VATFilingTest extends BaseTest{
	private LoginPageLocators login;
	private Robot rb;
	private VATRegistrationHelper helper;
	private VatFilingLocator vatf;
		
	@BeforeClass
	public void initilazeLocatprs() throws AWTException, InterruptedException {
		login = new LoginPageLocators(driver);
		rb = new Robot();
		helper = new VATRegistrationHelper(driver);
		vatf = new VatFilingLocator(driver);
		gotoVatFiling();
	}
	
	public void gotoVatFiling() throws InterruptedException {
		login.userName.sendKeys("anmol@skadits.com");
		login.password1.sendKeys("Testing@121");
		login.loginButton.click();
		Thread.sleep(8000);
		vatf.vatFilingBtn.click();
	}

	public void uploadFiles(VatRegistrationLocators vat, String[] filePaths) 
    		throws InterruptedException, AWTException {
        for (int i = 0; i < filePaths.length; i++) {
            WebElement currentUploadBtn = helper.getUploadButton(vat, i);
            if (currentUploadBtn != null) {
                scrollPage(ScrollType.TO_ELEMENT, currentUploadBtn, 0, 0);
                waitForClickable(currentUploadBtn);
                currentUploadBtn.click();
                rb.delay(2000);

                StringSelection uploadSelection = new StringSelection(filePaths[i]);
                Toolkit.getDefaultToolkit().getSystemClipboard().
                setContents(uploadSelection, null);
                helper.KeyPress();
                Thread.sleep(3000);
            }
        }
	}
	
	public void submit() {
		scrollPage(ScrollType.TO_ELEMENT,vatf.submitBtn, 0, 0);
	    waitForClickable(vatf.submitBtn);
	    vatf.submitBtn.click();
	}
	
	@Test
	public void titleValidation() {
		Assert.assertEquals(vatf.pageHeading.getText(), "VAT Filing", 
				"Page title not found or not correct");
	}
	
	@Test
	public void autodataFetch() {
		vatf.showPswdBtn.click();
		String actualEmail = vatf.emailBox.getText();
		String actualPass  = vatf.passwordBox.getText();
		Assert.assertEquals(actualEmail, "anmol@skadits.com", 
				"autodataFetch is not showing 'email'");
		Assert.assertEquals(actualPass, "Testing@121", 
				"autodatafetch is not displaying password");
	}
	
	@Test
	public void placeholderVerification() {
		vatf.clearFeilds();
		String emailPlaceholder = vatf.emailP.getText();
		String passPlaceholder = vatf.passwordP.getText();
		String cnfpswdPlaceholder  = vatf.cnfPasswordBox.getText();
		String commentsPlaceholder  = vatf.commentbox.getText();
		Assert.assertTrue(emailPlaceholder.equalsIgnoreCase("email"),
				"Email placeholder not found");
		Assert.assertEquals(passPlaceholder.equalsIgnoreCase("password"),
				"password placeholder not found");
		Assert.assertEquals(cnfpswdPlaceholder.equalsIgnoreCase("Confirm Password"),
				"Confirm Password placeholder not found");
		Assert.assertEquals(commentsPlaceholder.equalsIgnoreCase("Comments"),
				"Comments placeholder not found");
	
	}
	
	//@Test
	public void passwordVisibility() {
		driver.navigate().refresh();
		vatf.clearFeilds();
		vatf.passwordBox.sendKeys("Asjkdf@32#56");
		vatf.cnfPasswordBox.sendKeys("134367gyujv");
		String passwordBeforeClick = vatf.passwordBox.getAttribute("type");
	    String cfnPassBfrClick = vatf.cnfPasswordBox.getAttribute("type");
		Assert.assertEquals(passwordBeforeClick, "password",
	            "Password field should be hidden initially.");
		Assert.assertEquals(cfnPassBfrClick,"password", 
				" confirm Password field should be hidden initially.");
		vatf.showPswdBtn.click();
		vatf.showCnfPswd.click();
		String passwordAfterClick = vatf.passwordBox.getAttribute("type");
		String cnfPassAfter = vatf.cnfPasswordBox.getAttribute("type");
	    Assert.assertEquals(passwordAfterClick, "text",
	            "Password field should be visible after clicking the button.");
	    Assert.assertEquals(cnfPassAfter, "text",
	            "confirm Password field should be visible after clicking the button.");
	    }
	
	//@Test
	public void emptyErrMsg() {
	    vatf.clearFeilds();
	    submit();
	    scrollPage(ScrollType.TO_TOP, null, 0, 0);
	    String[] errorMessages = vatf.getEmptyErrorMessages();
	    // Assuming errorMessages are in order of email, password, confirmation, sales, purchase, statement
	    Assert.assertEquals(errorMessages[0], "Username is required", 
	    		"Email field error message not as expected");
	    Assert.assertEquals(errorMessages[1], "Password is required", 
	    		"Password field error message not as expected");
	    Assert.assertEquals(errorMessages[2], "Password is required", 
	    		"Confirmation field error message not as expected");
	    Assert.assertEquals(errorMessages[3], "Sales Invoice is required", 
	    		"Sales field error message not as expected");
	    Assert.assertEquals(errorMessages[4], "Purchase Invoice is required", 
	    		"Purchase field error message not as expected");
	    Assert.assertEquals(errorMessages[5], "Statement is required", 
	    		"Statement field error message not as expected");
	}
	
	@Test
	public void incorrectEmailtype()
	{
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		vatf.emailBox.sendKeys("asgdc.com");
		submit();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String email = vatf.emailErrMsg.getText();
		Assert.assertEquals(email, "incorrect email format", 
	    		"incorrect email type error message not as expected");
	}

	//@Test
	public void incorrectPswd() {		
			vatf.clearFeilds();
			vatf.emailBox.sendKeys("anmol@skadits.com");
			vatf.passwordBox.sendKeys("Testing@121");
			vatf.cnfPasswordBox.sendKeys("Testing@123");
			submit();
			scrollPage(ScrollType.TO_TOP, null, 0, 0);
			Assert.assertTrue(vatf.pageHeading.getText().contains("VAT Filing"), 
					"Application is not failing for unmatching passwords");
	//add assertion for error message as well
		}

	@Test
	public void testPasswordCopyPaste() {
		vatf.clearFeilds();
	    vatf.passwordBox.sendKeys("TestPassword123");
	    String passwordBeforePaste = vatf.passwordBox.getAttribute("value");
	
	    // Click on the confirm password field and attempt to paste the password
	    vatf.cnfPasswordBox.click();
	    vatf.cnfPasswordBox.sendKeys(Keys.chord(Keys.CONTROL, "v"));
	
	    // Get the password from the confirm password field after the attempted paste
	    String passwordAfterPaste = vatf.cnfPasswordBox.getAttribute("value");
	
	    // Assert that the confirm password field value is not equal to the original password value
	    Assert.assertNotEquals(passwordBeforePaste, passwordAfterPaste, 
	    		"Copy-paste of password detected");
	}
	} 
