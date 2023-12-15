package IbrahimTawakul.testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import IbrahimTawakul.pageobjects.CorporateTaxLoc;
import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.testComponents.BaseTest;
import IbrahimTawakul.testComponents.VATRegistrationHelper;

public class CorporateTaxTest extends BaseTest {
	private LoginPageLocators login;
	private Robot rb;
	private VATRegistrationHelper helper;
	private CorporateTaxLoc ctl;
	private SoftAssert softAssert;
	private Actions action;

	@BeforeClass
	public void initilazeLocatprs() throws AWTException, InterruptedException {
		login = new LoginPageLocators(driver);
		rb = new Robot();
		helper = new VATRegistrationHelper(driver);
		ctl = new CorporateTaxLoc(driver);
		action = new Actions(driver);
		gotoctl();
		softAssert = new SoftAssert();
	}
	
	@BeforeMethod
	public void refresh() {
		driver.navigate().refresh();
		}

	public void gotoctl() throws InterruptedException {
		login.userName.sendKeys("anmol@skadits.com");
		login.password1.sendKeys("Testing@121");
		login.loginButton.click();
		Thread.sleep(4000);
		ctl.corporateTaxBtn.click();
	}

	public void ctUploadFiles(CorporateTaxLoc ctl, String[] filePaths) 
			throws InterruptedException, AWTException {
		for (int i = 0; i < 4; i++) {
			WebElement currentUploadBtn = helper.ctlUploadBtn(ctl, i);
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

//	public void submit() throws InterruptedException {
//		scrollPage(ScrollType.TO_ELEMENT, ctl.submitBtn, 0, 0);
//		Thread.sleep(3000);
//		ctl.submitBtn.click();
//		Thread.sleep(3000);
//		Assert.assertEquals(ctl.vatAmnCnfMsg.getText(), 
//				"Please check information before submitting!"," confirmation msg not found");
//		ctl.finalSubmitBtn.click();
//		Thread.sleep(5000);
//		String url = driver.getCurrentUrl();
//		Assert.assertEquals(url, "https://ibrahimtawakul.hexagonsolutions.dev/pages/order-list", 
//		    "URL not matching for successful VAT deregistration");
//		ctl.vatAnmdBtn.click();
//		}
	
	
	@Test
	public void uiTesting() {
	    Assert.assertTrue(ctl.pageTitle.getText().contains("Corporate Tax"), 
	    		"Page title doesn't match");
	    Assert.assertTrue(ctl.userName.isDisplayed(), "user name box is not displayed");
	    Assert.assertTrue(ctl.passwordBox.isDisplayed(), "Password box is not displayed");
	    Assert.assertTrue(login.userBtn.isEnabled(), "User button is not enabled");
	    Assert.assertTrue(ctl.tradeTittle.isDisplayed(), 
	    		"Trade upload is not displayed");
	    Assert.assertTrue(ctl.moaTittle.isDisplayed(), 
	    		"Moa upload is not displayed");
	    Assert.assertTrue(ctl.idTittle.isDisplayed(),
	    		"Emirates id upload is not displayed");
	    Assert.assertTrue(ctl.passportTittle.isDisplayed(),
	    		"Pasport upload is not displayed");
	    Assert.assertTrue(ctl.submitBtn.isDisplayed(),
	    		"Submit btn is not displayed");
	}

	@Test
	public void responsiveUITesting() {
	    // iPad Viewport Size
	    driver.manage().window().setSize(new Dimension(768, 1024));
	    performAssertions("iPad");

	    // Android Viewport Size (considering a common mobile resolution)
	    driver.manage().window().setSize(new Dimension(360, 640));
	    performAssertions("Android");

	    // iPhone Viewport Size (considering an iPhone X resolution)
	    driver.manage().window().setSize(new Dimension(375, 812));
	    performAssertions("iPhone");
	}

	public void performAssertions(String device) {
	    // Assertions for each device-specific scenario
	    switch (device) {
	        case "iPad":
	            Assert.assertTrue(ctl.pageTitle.getText().contains("Corporate Tax"),
	                    "Page title doesn't match for iPad");
	            Assert.assertTrue(ctl.userName.isDisplayed(), "User name box is not displayed for iPad");
	            Assert.assertTrue(ctl.passwordBox.isDisplayed(), "Password box is not displayed for iPad");
	            Assert.assertTrue(login.userBtn.isEnabled(), "User button is not enabled for iPad");
	            Assert.assertTrue(ctl.tradeTittle.isDisplayed(), "Trade upload is not displayed for iPad");
	            Assert.assertTrue(ctl.moaTittle.isDisplayed(), "Moa upload is not displayed for iPad");
	            Assert.assertTrue(ctl.idTittle.isDisplayed(), "Emirates id upload is not displayed for iPad");
	            Assert.assertTrue(ctl.passportTittle.isDisplayed(), "Passport upload is not displayed for iPad");
	            Assert.assertTrue(ctl.submitBtn.isDisplayed(), "Submit button is not displayed for iPad");
	            break;

	        case "Android":
	            Assert.assertTrue(ctl.userName.isDisplayed(), "User name box is not displayed for Android");
	            Assert.assertTrue(ctl.passwordBox.isDisplayed(), "Password box is not displayed for Android");
	            Assert.assertTrue(login.userBtn.isEnabled(), "User button is not enabled for Android");
	            Assert.assertTrue(ctl.submitBtn.isDisplayed(), "Submit button is not displayed for Android");
	            break;

	        case "iPhone":
	            Assert.assertTrue(ctl.pageTitle.getText().contains("Corporate Tax"),
	                    "Page title doesn't match for iPhone");
	            Assert.assertTrue(ctl.passwordBox.isDisplayed(), "Password box is not displayed for iPhone");
	            Assert.assertTrue(ctl.tradeTittle.isDisplayed(), "Trade upload is not displayed for iPhone");
	            Assert.assertTrue(ctl.moaTittle.isDisplayed(), "Moa upload is not displayed for iPhone");
	            Assert.assertTrue(ctl.submitBtn.isDisplayed(), "Submit button is not displayed for iPhone");
	         
	            break;

	        default:
	            break;
	    }
	    Assert.assertTrue(ctl.submitBtn.isDisplayed(),
	            "Submit button is not displayed for " + device);
	    Assert.assertTrue(ctl.tradeTittle.isDisplayed(),
	            "Trade upload is not displayed for " + device);
	    Assert.assertTrue(ctl.moaTittle.isDisplayed(),
	            "Moa upload is not displayed for " + device);	    
	}

	
	@Test
	public void placeholderVerification() {

		String emailPlaceholder = ctl.userNameP.getText();
		String passPlaceholder = ctl.passwordP.getText();
		softAssert.assertTrue(emailPlaceholder.equalsIgnoreCase("User Name"), "User Name placeholder not found");
		softAssert.assertTrue(passPlaceholder.equalsIgnoreCase("Password"), 
				"password placeholder not found");
		softAssert.assertAll();
	}

	@Test
	public void passwordVisibility() {
		ctl.clearFeilds();
		ctl.passwordBox.sendKeys("Asjkdf@32#56");
	
		String passwordBeforeClick = ctl.passwordBox.getAttribute("type");
		Assert.assertEquals(passwordBeforeClick, "password", 
				"Password field should be hidden initially.");
		ctl.showPswdBtn.click();
		String passwordAfterClick = ctl.passwordBox.getAttribute("type");
		Assert.assertEquals(passwordAfterClick, "text", 
				"Password field should be visible after clicking the button.");
	}

	@Test
	public void emptyErrMsg() throws InterruptedException {
		scrollPage(ScrollType.TO_BOTTOM, null, 0, 0);
		Thread.sleep(2000);
		ctl.submitBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String[] errorMessages = ctl.getEmptyErrorMessages();

		softAssert.assertEquals(errorMessages[0], "Username is required", 
				"Username field error message not as expected");
		softAssert.assertEquals(errorMessages[1], "Password is required",
				"Password field error message not as expected");
		softAssert.assertEquals(errorMessages[2], "Trade license is required",
				"Trade license field error message not as expected");
		softAssert.assertEquals(errorMessages[3], "MOA is required",
				"MOA field error message not as expected");
		softAssert.assertEquals(errorMessages[4], "Emirates ID is required",
				"ID field error message not as expected");
		softAssert.assertEquals(errorMessages[5], "Passport is required",
				"passport error message not as expected");
		softAssert.assertAll();
	}

	@Test
	public void mandatoryInfo() {

		String emailPlaceholder = ctl.userNameP.getText();
		String passPlaceholder = ctl.passwordP.getText();
		String tradePlaceholder = ctl.tradeTittle.getText();
		String moa = ctl.moaTittle.getText();
		String id = ctl.idTittle.getText();
		String psprt = ctl.passportTittle.getText().trim();

		softAssert.assertTrue(emailPlaceholder.equalsIgnoreCase("User Name*"), "User Name title not matching");
		softAssert.assertTrue(passPlaceholder.equalsIgnoreCase("Password*"), "Password title not matching");
		softAssert.assertTrue(tradePlaceholder.equalsIgnoreCase("Trade License*"),
				"Trade License* title is not matching");
		softAssert.assertTrue(moa.equalsIgnoreCase("MOA File*"), "MOA File* title is not matching");
		softAssert.assertTrue(id.equalsIgnoreCase("Emirates ID*"), "Emirates ID* title is not matching");
		softAssert.assertTrue(psprt.equalsIgnoreCase("Passport*"), "Passport* title is not matching");
		softAssert.assertAll();
	}

	@Test
	public void uploadDocumentMsg() {
		String expectedText = "Drag & drop files or\r\n" + "Browse\r\n" + 
	"Supported formates: JPEG, PNG, PDF, Word.";
		List<WebElement> uploadElements = Arrays.asList(ctl.tradeUploadBtn,
				ctl.moaUploadBtn, ctl.idUploadBtn, ctl.pasportUploadBtn);

		for (WebElement element : uploadElements) {
			String text = element.getText();
			System.out.println(text);
			softAssert.assertTrue(expectedText.contains(text), "Instructions not found in dropbox: " + text);
		}
	}

	@Test
	public void JpegUpload() throws InterruptedException, AWTException {
		//driver.navigate().refresh();
		Thread.sleep(4000);
		ctUploadFiles(ctl, VATRegistrationHelper.Jpeg_FILE_PATHS);
		Assert.assertEquals(ctl.uploadedFiles.size(), 4,
				"The number of uploaded Jpeg files does not match the expected count");
	}

	@Test
	public void pngUpload() throws InterruptedException, AWTException {
		//driver.navigate().refresh();
		Thread.sleep(4000);
		ctUploadFiles(ctl, VATRegistrationHelper.Png_FILE_PATHS);
		Assert.assertEquals(ctl.uploadedFiles.size(), 4,
				"The number of uploaded png files does not match the expected count");

	}

	@Test
	public void pdfUpload() throws InterruptedException, AWTException {
		//driver.navigate().refresh();
		Thread.sleep(4000);
		ctUploadFiles(ctl, VATRegistrationHelper.PDF_FILE_PATHS);
		Assert.assertEquals(ctl.uploadedFiles.size(), 4,
				"The number of uploaded pdf files does not match the expected count");

	}

	@Test
	public void wordUpload() throws InterruptedException, AWTException {
		//driver.navigate().refresh();
		Thread.sleep(4000);
		ctUploadFiles(ctl, VATRegistrationHelper.WORD_FILE_PATHS);
		Assert.assertEquals(ctl.uploadedFiles.size(), 4,
				"The number of uploaded Word files does not match the expected count");

	}

	@Test
	public void txtUpload() throws InterruptedException, AWTException {
		//driver.navigate().refresh();
		Thread.sleep(4000);
		ctUploadFiles(ctl, VATRegistrationHelper.Txt_FILE_PATHS);
		Assert.assertNull(ctl.uploadedFiles, "Unsupported Txt file is getting uploaded");
	}

	@Test
	public void mp4Upload() throws InterruptedException, AWTException {
		//driver.navigate().refresh();
		Thread.sleep(4000);
		ctUploadFiles(ctl, VATRegistrationHelper.Mp4_FILE_PATHS);
		Assert.assertNull(ctl.uploadedFiles, "Unsupported Mp4 file is getting uploaded");

	}

	@Test
	public void multipleFileUpload() throws InterruptedException, AWTException {

		Thread.sleep(5000);
		scrollPage(ScrollType.TO_ELEMENT, ctl.tradeUploadBtn, 0, 0);
		for (String filePath : VATRegistrationHelper.Png_FILE_PATHS) {
			ctl.tradeUploadBtn.click();
			rb.delay(2000);
			StringSelection uploadSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
			helper.KeyPress();
			Thread.sleep(3000);
		}
		Assert.assertEquals(ctl.uploadedFiles.size(), VATRegistrationHelper.Png_FILE_PATHS.length,
				"The number of uploaded files does not match the expected count");
	}

	@Test
	public void fileDuplicacy() throws InterruptedException, AWTException {
		
		Thread.sleep(5000);
		ctUploadFiles(ctl, VATRegistrationHelper.DuplicatFile);
		Assert.assertEquals(ctl.uploadedFiles.size(), 1, 
				"Duplicate files are getting uploaded");
	}

	@Test
	public void hoveroverSubmitBtn() throws InterruptedException {
		
		scrollPage(ScrollType.TO_ELEMENT, ctl.submitBtn, 0, 0);
		Thread.sleep(3000);
		String colorBeforeHover = ctl.submitBtn.getCssValue("background-color");
		action.moveToElement(ctl.submitBtn).perform();
		String colorAfterHover = ctl.submitBtn.getCssValue("background-color");
		Assert.assertNotEquals(colorBeforeHover, colorAfterHover, "background-color did not change on hover for Submit Button");

	}
	
	@Test
	public void missMandDoc() throws InterruptedException, AWTException {
		ctl.userName.sendKeys("Automation");
		ctl.passwordBox.sendKeys("Testing@12");
		ctUploadFiles(ctl, VATRegistrationHelper.DuplicatFile);
		scrollPage(ScrollType.TO_BOTTOM, null, 0, 0);
		Thread.sleep(2000);
		ctl.submitBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		softAssert.assertEquals(ctl.errorMoaMsg.getText().trim(), "MOA is required",
				"MOA field error message not as expected");
		softAssert.assertEquals(ctl.errorIdMsg.getText().trim(), "Emirates ID is required",
				"ID field error message not as expected");
		softAssert.assertEquals(ctl.PassErrMsg.getText().trim(), "Passport is required",
				"passport error message not as expected");
		softAssert.assertAll();
	}


	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
