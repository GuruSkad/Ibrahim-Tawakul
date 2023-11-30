package IbrahimTawakul.testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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
	private Actions action;
	@BeforeClass
	public void initilazeLocatprs() throws AWTException, InterruptedException {
		login = new LoginPageLocators(driver);
		rb = new Robot();
		helper = new VATRegistrationHelper(driver);
		vatf = new VatFilingLocator(driver);
		action = new Actions(driver);
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

	public void submit() throws InterruptedException {	     
	scrollPage(ScrollType.TO_BOTTOM, null, 0, 0);
    Thread.sleep(2000);
    vatf.submitBtn.click();}

	@Test
	public void titleValidation() {
		Assert.assertEquals(vatf.pageHeading.getText(), "VAT Filing", "Page title not found or not correct");
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

	 @Test
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

	 @Test
	 public void emptyErrMsg() throws InterruptedException {
	     submit();
	     scrollPage(ScrollType.TO_TOP, null, 0, 0);
	     String[] errorMessages = vatf.getEmptyErrorMessages();
	     
	     // Assuming errorMessages are in order of email, password, confirmation, sales, purchase, statement
	     softAssert.assertEquals(errorMessages[0], "Username is required", "Email field error message not as expected");
	     softAssert.assertEquals(errorMessages[1], "Password is required", "Password field error message not as expected");
	     softAssert.assertEquals(errorMessages[2], "Confirm password is required",
	             "Confirmation field error message not as expected");
	     softAssert.assertEquals(errorMessages[3], "Sales Invoice is required", "Sales field error message not as expected");
	     softAssert.assertEquals(errorMessages[4], "Purchase Invoice is required",
	             "Purchase field error message not as expected");
	     softAssert.assertEquals(errorMessages[5], "Statement is required", "Statement field error message not as expected");	     
	     softAssert.assertAll();
	 }

	@Test
	public void mismatchingPswd() throws InterruptedException {
		vatf.clearFeilds();
		vatf.emailBox.sendKeys("anmol@skadits.com");
		vatf.passwordBox.sendKeys("Testing@121");
		vatf.confirmPassBox.sendKeys("Testing@123");
		submit();
		Thread.sleep(3000);
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		Assert.assertTrue(vatf.pageHeading.getText().contains("VAT Filing"),
				"Application is not failing for unmatching passwords");
	}

	@Test
	public void testPasswordCopyPaste() {
		vatf.passwordBox.sendKeys("TestPassword@123");
		vatf.confirmPassBox.click();
		vatf.confirmPassBox.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		String passwordBeforePaste = vatf.passwordBox.getAttribute("value");
		String passwordAfterPaste = vatf.confirmPassBox.getAttribute("value");
		Assert.assertNotEquals(passwordBeforePaste, passwordAfterPaste, "Copy-paste of password detected");
	}

	@Test
	public void mandatoryTitle() {

		String emailPlaceholder = vatf.emailP.getText();
		String passPlaceholder = vatf.passwordP.getText();
		String cnfpswdPlaceholder = vatf.cnfPasswordP.getText();
		String sales = vatf.salesTitle.getText();
		String purchase = vatf.purchaseTitle.getText();
		String statement = vatf.statemntTitle.getText().trim();
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
			softAssert.assertTrue(expectedText.contains(text), "Instructions not found in dropbox: " + text);
		}
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

	 @Test
	    public void multipleFileUpload() throws InterruptedException, AWTException {
	        
	        Thread.sleep(5000);
	        scrollPage(ScrollType.TO_ELEMENT, vatf.salesUpload, 0, 0);      
	        for (String filePath : VATRegistrationHelper.Png_FILE_PATHS) {
	            vatf.salesUpload.click();
	            rb.delay(2000);
	            StringSelection uploadSelection = new StringSelection(filePath);
	            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
	            helper.KeyPress();
	            Thread.sleep(3000);
	        }
	        Assert.assertEquals(vatf.vatUploadedFiles.size(), 
	        		VATRegistrationHelper.Png_FILE_PATHS.length,
	            "The number of uploaded files does not match the expected count");
	    }
	 
	 @Test
	    public void fileDuplicacy() throws InterruptedException, AWTException {
	    	    Thread.sleep(5000);
	    	    scrollPage(ScrollType.TO_ELEMENT, vatf.purchaseUpload, 0, 0);
	    	    String filePath[] = {"C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png"};
	    	    vatUploadFiles(vatf, filePath);
	 	       Assert.assertEquals(vatf.vatUploadedFiles.size(), 1,
		               "Duplicate files are getting uploaded");
	    }
	    
	    @Test
	    public void hoveroverSubmitBtn() {
	    	scrollPage(ScrollType.TO_ELEMENT, vatf.submitBtn, 0, 0);
	    	
	    	String colorBeforeHover = vatf.submitBtn.getCssValue("color");
	            action.moveToElement(vatf.submitBtn).perform();
	            String colorAfterHover = vatf.submitBtn.getCssValue("color");
	            Assert.assertNotEquals(colorBeforeHover, colorAfterHover,
	                "Color did not change on hover for Submit Button");

	    }
	    
	   @AfterClass
		public void teardown()
		{
			driver.quit();
		}
}
