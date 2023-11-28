package IbrahimTawakul.testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.pageobjects.VatFilingLocator;
import IbrahimTawakul.pageobjects.VatRegistrationLocators;
import IbrahimTawakul.testComponents.BaseTest;
import IbrahimTawakul.testComponents.VATRegistrationHelper;
import IbrahimTawakul.testComponents.BaseTest.ScrollType;

public class VATFilingTest extends BaseTest {
	private LoginPageLocators login;
	private Robot rb;
	private VATRegistrationHelper helper;
	private VatFilingLocator vatf;
	private SoftAssert softAssert;

	@BeforeClass
	public void initilazeLocatprs() throws AWTException, InterruptedException {
		login = new LoginPageLocators(driver);
		rb = new Robot();
		helper = new VATRegistrationHelper(driver);
		vatf = new VatFilingLocator(driver);
		gotoVatFiling();
		softAssert = new SoftAssert();
	}

	public void gotoVatFiling() throws InterruptedException {
		login.userName.sendKeys("anmol@skadits.com");
		login.password1.sendKeys("Testing@121");
		login.loginButton.click();
		Thread.sleep(4000);
		vatf.vatFilingBtn.click();
	}

	public void vatUploadFiles(VatFilingLocator vatf, String[] filePaths) throws InterruptedException, AWTException {
		for (int i = 0; i < 3; i++) {
			WebElement currentUploadBtn = helper.vatFilingUploadBtn(vatf, i);
			if (currentUploadBtn != null) {
				scrollPage(ScrollType.TO_ELEMENT, currentUploadBtn, 0, 0);
				waitForClickable(currentUploadBtn);
				currentUploadBtn.click();
				rb.delay(2000);

				StringSelection uploadSelection = new StringSelection(filePaths[i]);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
				helper.KeyPress();
				Thread.sleep(3000);
			}
		}
	}

	public void submit() {
		scrollPage(ScrollType.TO_ELEMENT, vatf.submitBtn, 0, 0);
		waitForClickable(vatf.submitBtn);
		vatf.submitBtn.click();
	}

	@Test
	public void titleValidation() {
		Assert.assertEquals(vatf.pageHeading.getText(), "VAT Filing", "Page title not found or not correct");
	}

	@Test
	public void autodataFetch() {
		vatf.showPswdBtn.click();
		String actualEmail = vatf.emailBox.getText();
		String actualPass = vatf.passwordBox.getText();
		Assert.assertEquals(actualEmail, "anmol@skadits.com", "autodataFetch is not showing 'email'");
		Assert.assertEquals(actualPass, "Testing@121", "autodatafetch is not displaying password");
	}

	@Test
	public void placeholderVerification() {
		vatf.clearFeilds();
		String emailPlaceholder = vatf.emailP.getText();
		String passPlaceholder = vatf.passwordP.getText();
		String cnfpswdPlaceholder = vatf.cnfPasswordP.getText();
		String commentsPlaceholder = vatf.commentbox.getText();
		Assert.assertTrue(emailPlaceholder.equalsIgnoreCase("email"), "Email placeholder not found");
		Assert.assertEquals(passPlaceholder.equalsIgnoreCase("password"), "password placeholder not found");
		Assert.assertEquals(cnfpswdPlaceholder.equalsIgnoreCase("Confirm Password"),
				"Confirm Password placeholder not found");
		Assert.assertEquals(commentsPlaceholder.equalsIgnoreCase("Comments"), "Comments placeholder not found");

	}

	// @Test
	public void passwordVisibility() {
		driver.navigate().refresh();
		vatf.clearFeilds();
		vatf.passwordBox.sendKeys("Asjkdf@32#56");
		vatf.confirmPassBox.sendKeys("134367gyujv");
		String passwordBeforeClick = vatf.passwordBox.getAttribute("type");
		String cfnPassBfrClick = vatf.confirmPassBox.getAttribute("type");
		Assert.assertEquals(passwordBeforeClick, "password", "Password field should be hidden initially.");
		Assert.assertEquals(cfnPassBfrClick, "password", " confirm Password field should be hidden initially.");
		vatf.showPswdBtn.click();
		vatf.showCnfPswd.click();
		String passwordAfterClick = vatf.passwordBox.getAttribute("type");
		String cnfPassAfter = vatf.confirmPassBox.getAttribute("type");
		Assert.assertEquals(passwordAfterClick, "text", "Password field should be visible after clicking the button.");
		Assert.assertEquals(cnfPassAfter, "text",
				"confirm Password field should be visible after clicking the button.");
	}

	// @Test
	public void emptyErrMsg() {
		vatf.clearFeilds();
		submit();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String[] errorMessages = vatf.getEmptyErrorMessages();
		// Assuming errorMessages are in order of email, password, confirmation, sales,
		// purchase, statement
		Assert.assertEquals(errorMessages[0], "Username is required", "Email field error message not as expected");
		Assert.assertEquals(errorMessages[1], "Password is required", "Password field error message not as expected");
		Assert.assertEquals(errorMessages[2], "Password is required",
				"Confirmation field error message not as expected");
		Assert.assertEquals(errorMessages[3], "Sales Invoice is required", "Sales field error message not as expected");
		Assert.assertEquals(errorMessages[4], "Purchase Invoice is required",
				"Purchase field error message not as expected");
		Assert.assertEquals(errorMessages[5], "Statement is required", "Statement field error message not as expected");
	}

	@Test
	public void incorrectEmailtype() {
		scrollPage(ScrollType.TO_BOTTOM, null, 0, 0);
//		scrollPage(ScrollType.TO_TOP, null, 0, 0);
//		vatf.emailBox.sendKeys("asgdc.com");
//		submit();
//		scrollPage(ScrollType.TO_TOP, null, 0, 0);
//		String email = vatf.emailErrMsg.getText();
//		Assert.assertEquals(email, "incorrect email format", 
//	    		"incorrect email type error message not as expected");
	}

	// @Test
	public void mismatchingPswd() {
		vatf.clearFeilds();
		vatf.emailBox.sendKeys("anmol@skadits.com");
		vatf.passwordBox.sendKeys("Testing@121");
		vatf.confirmPassBox.sendKeys("Testing@123");
		submit();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		Assert.assertTrue(vatf.pageHeading.getText().contains("VAT Filing"),
				"Application is not failing for unmatching passwords");
		// add assertion for error message as well
	}

	@Test
	public void testPasswordCopyPaste() {
		waitForClickable(vatf.passwordBox);
		vatf.passwordBox.sendKeys("TestPassword@123");
		vatf.passwordBox.sendKeys(Keys.TAB);
		waitForElementToBeVisible(vatf.cnfPasswordP);
		vatf.cnfPasswordP.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		String passwordBeforePaste = vatf.passwordBox.getAttribute("value");
		String passwordAfterPaste = vatf.confirmPassBox.getAttribute("value");
		Assert.assertNotEquals(passwordBeforePaste, passwordAfterPaste, "Copy-paste of password detected");
	}

	@Test
	public void improperPass() {
		waitForClickable(vatf.passwordBox);
		vatf.clearFeilds();

		// Test for password length error
		vatf.passwordBox.sendKeys("aA1@");
		String lengthErr = vatf.PassErrMsg.getText().trim();
		System.out.println(lengthErr + "length error check");
		softAssert.assertTrue(lengthErr.contains(" Passwords must be at least 8 characters."),
				"Password error message does not match for a shorter password");

		// Test for absence of uppercase letter error
		vatf.passwordBox.clear();
		vatf.passwordBox.sendKeys("123456789#");
		String letterErr = vatf.PassErrMsg.getText().trim();
		System.out.println(letterErr);
		softAssert.assertTrue(letterErr.contains(" Passwords must have at least one uppercase ('A'-'Z')."),
				"Password error message does not match for a password without an uppercase letter");

		// Test for absence of lowercase letter error
		vatf.passwordBox.clear();
		vatf.passwordBox.sendKeys("123456789A#");
		String lowercaseErr = vatf.PassErrMsg.getText().trim();
		System.out.println(lowercaseErr);
		softAssert.assertTrue(lowercaseErr.contains(" Passwords must have at least one lowercase ('a'-'z')."),
				"Password error message does not match for a password without a lowercase letter");

		// Test for absence of digit error
		vatf.passwordBox.clear();
		vatf.passwordBox.sendKeys("Abcdefgh#");
		String digitErr = vatf.PassErrMsg.getText().trim();
		System.out.println(digitErr);
		softAssert.assertTrue(digitErr.contains("Passwords must have at least one digit ('0'-'9')."),
				"Password error message does not match for a password without a digit");

		// Test for absence of special character error
		vatf.passwordBox.clear();
		vatf.passwordBox.sendKeys("Abcdefgh1");
		String specialCharErr = vatf.PassErrMsg.getText().trim();
		System.out.println(specialCharErr);
		softAssert.assertTrue(specialCharErr.contains("Passwords must have at least one special character."),
				"Password error message does not match for a password without a special character");

		softAssert.assertAll();
	}

	@Test
	public void mandatoryTitle() {

		String emailPlaceholder = vatf.emailP.getText();
		String passPlaceholder = vatf.passwordP.getText();
		String cnfpswdPlaceholder = vatf.cnfPasswordP.getText();
		String sales = vatf.salesTitle.getText();
		String purchase = vatf.purchaseTitle.getText();
		String statement = vatf.statemntTitle.getText();
		System.out.println(statement);

		softAssert.assertTrue(emailPlaceholder.equalsIgnoreCase("User Name*"), "User Name title not matching");
		softAssert.assertTrue(passPlaceholder.equalsIgnoreCase("Password*"), "Password title not matching");
		softAssert.assertTrue(cnfpswdPlaceholder.equalsIgnoreCase("Confirm Password*"),
				"Confirm Password title is not matching");
		softAssert.assertTrue(sales.equalsIgnoreCase("Sales Invoice*"), "Sales title is not matching");
		softAssert.assertTrue(purchase.equalsIgnoreCase("Purchase Invoice*"), "Purchase title is not matching");
		softAssert.assertTrue(statement.equalsIgnoreCase("Statement*"), "Statement title is not matching");
		softAssert.assertAll();
	}

	@Test
	public void uploadDocumentMsg() {
		String expectedText = "Drag & drop files or\r\n" + "Browse\r\n" + "Supported formates: JPEG, PNG, PDF, Word.";
		List<WebElement> uploadElements = Arrays.asList(vatf.salesUpload, vatf.purchaseUpload, vatf.statementUpload);

		for (WebElement element : uploadElements) {
			String text = element.getText();
			System.out.println(text);
			Assert.assertTrue(expectedText.contains(text), "Instructions not found in dropbox: " + text);
		}
	}

	@Test
	public void missedMandatoryFile() {
		// driver.navigate().refresh();
		waitForClickable(vatf.passwordBox);
		vatf.emailBox.sendKeys("anmol@skadits.com");
		vatf.passwordBox.sendKeys("Testing@121");
		vatf.confirmPassBox.sendKeys("Testing@121");
		submit();
		String saleErr = vatf.errorSalesMsg.getText();
		String purErr = vatf.errorPurchaseMsg.getText();
		String stErr = vatf.errorStatementMsg.getText();
		System.out.println(saleErr);
		System.out.println(purErr);
		System.out.println(stErr);

		softAssert.assertTrue(saleErr.equalsIgnoreCase("User Name*"), "User Name title not matching");
		softAssert.assertTrue(purErr.equalsIgnoreCase("User Name*"), "User Name title not matching");
		softAssert.assertTrue(stErr.equalsIgnoreCase("User Name*"), "User Name title not matching");
		softAssert.assertAll();
	}

	@Test
	public void JpegUpload() throws InterruptedException, AWTException {
		driver.navigate().refresh();
		Thread.sleep(4000);
		vatUploadFiles(vatf, VATRegistrationHelper.Jpeg_FILE_PATHS);
	       Assert.assertEquals(vatf.vatUploadedFiles.size(),3,
	               "The number of uploaded Jpeg files does not match the expected count");
	    }

	@Test
	public void pngUpload() throws InterruptedException, AWTException {
		driver.navigate().refresh();
		Thread.sleep(4000);
		vatUploadFiles(vatf, VATRegistrationHelper.Png_FILE_PATHS);
	       Assert.assertEquals(vatf.vatUploadedFiles.size(), 3,
	               "The number of uploaded png files does not match the expected count");
	   
	}
	
	@Test
	public void pdfUpload() throws InterruptedException, AWTException {
		driver.navigate().refresh();
		Thread.sleep(4000);
		vatUploadFiles(vatf, VATRegistrationHelper.PDF_FILE_PATHS);
	       Assert.assertEquals(vatf.vatUploadedFiles.size(), 3,
	               "The number of uploaded pdf files does not match the expected count");
	   
	}
	
	@Test
	public void wordUpload() throws InterruptedException, AWTException {
		driver.navigate().refresh();
		Thread.sleep(4000);
		vatUploadFiles(vatf, VATRegistrationHelper.WORD_FILE_PATHS);
	       Assert.assertEquals(vatf.vatUploadedFiles.size(), 3,
	               "The number of uploaded Word files does not match the expected count");
	   
	}
	
	@Test
	public void txtUpload() throws InterruptedException, AWTException {
		driver.navigate().refresh();
		Thread.sleep(4000);
		vatUploadFiles(vatf, VATRegistrationHelper.Txt_FILE_PATHS);
		Assert.assertNull(vatf.vatUploadedFiles, 
				"Unsupported Txt file is getting uploaded");
	   
	}
	@Test
	public void mp4Upload() throws InterruptedException, AWTException {
		driver.navigate().refresh();
		Thread.sleep(4000);
		vatUploadFiles(vatf, VATRegistrationHelper.Mp4_FILE_PATHS);
		Assert.assertNull(vatf.vatUploadedFiles, 
				"Unsupported Mp4 file is getting uploaded");
	   
	}

}
