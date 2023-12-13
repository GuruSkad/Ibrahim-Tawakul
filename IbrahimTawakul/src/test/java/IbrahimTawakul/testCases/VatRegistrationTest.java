package IbrahimTawakul.testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.errorprone.annotations.Keep;

import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.pageobjects.VatRegistrationLocators;
import IbrahimTawakul.testComponents.BaseTest;
import IbrahimTawakul.testComponents.VATRegistrationHelper;
import IbrahimTawakul.testComponents.BaseTest.ScrollType;

public class VatRegistrationTest extends BaseTest{
	private VatRegistrationLocators vat;
	private LoginPageLocators login;
	private Robot rb;
	private VATRegistrationHelper helper;
	private Actions action;	

	@BeforeClass
	public void initializeLocators() throws AWTException {
		vat = new VatRegistrationLocators(driver);
		login = new LoginPageLocators(driver);
		rb = new Robot();
		helper = new VATRegistrationHelper(driver);
		action = new Actions(driver);
		loginApplication();
	}
	
	public void loginApplication() {
		login.userName.sendKeys("anmol@skadits.com");
		login.password1.sendKeys("Testing@121");
		login.loginButton.click();
	}
	@BeforeMethod
	public void refresh() {
		driver.navigate().refresh();
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
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
                helper.KeyPress();
                Thread.sleep(3000);
            }
        }
	}	
		
	@Test
	public void uiTesting() {
	    
	    Assert.assertTrue(vat.logo.isDisplayed(), "Logo is not displayed");
	    Assert.assertTrue(vat.menuText.getText().contains("MENU"), "Menu not found");
	    Assert.assertTrue(vat.pageTitle.getText().contains("VAT Registration"), 
	    		"Page title doesn't match");
	    Assert.assertTrue(vat.emailBox.isDisplayed(), "Email box is not displayed");
	    Assert.assertTrue(vat.mobileBox.isDisplayed(), "Mobile box is not displayed");
	    boolean uploadTextFound = vat.uploadBoxes.stream().anyMatch(e -> e.getText()
	    		.contains("Drag & drop files"));
	    Assert.assertTrue(uploadTextFound, "Upload boxes do not contain expected text");
	    Assert.assertTrue(login.userBtn.isEnabled(), "User button is not enabled");
	    Assert.assertTrue(vat.dwnldSampleFilesBtn.isDisplayed(), 
	    		"Download sample files button is not displayed");
	    Assert.assertTrue(vat.makePymntBtn.isDisplayed(), 
	    		"Make payment button is not displayed");
	    Assert.assertTrue(vat.submitWithoutPmntBtn.isDisplayed(),
	    		"Submit without payment button is not displayed");
	}

	@Test
	public void verifyPlaceholders() {
	    
	    String emailPlaceholder = vat.emailBox.getAttribute("placeholder");
	    String contactPlaceholder = vat.mobileBox.getAttribute("placeholder");

	    Assert.assertEquals(emailPlaceholder, "Email", "Email placeholder is incorrect");
	    Assert.assertEquals(contactPlaceholder, "Contact Number", 
	    		"Contact number placeholder is incorrect");
	}
	
	@Test
	public void emptyMsg() {
		
		scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
		vat.makePymntBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String emailEmptyMsg = vat.emailErrTxt.getText();
		Assert.assertTrue(emailEmptyMsg.contains("Email Id is required"), 
				"Blank email error msg not displaying");
		String contactEmptyMsg = vat.mobileErrTxt.getText();
		Assert.assertTrue(contactEmptyMsg.contains("Mobile no is required"), 
				"Blank contact no.error msg not displaying");
	}
	
	@Test
	public void incorrectEmailMsg() {
		
		scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
		vat.makePymntBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String emailEmptyMsg = vat.emailErrTxt.getText();
		Assert.assertTrue(emailEmptyMsg.contains("Email is invalid"), 
				"Incorrect email error msg not displaying");
	}
	
	@Test
	public void IncorrectContactNoMsg() {
		
		scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
		vat.makePymntBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String contactEmptyMsg = vat.mobileErrTxt.getText();
		Assert.assertTrue(contactEmptyMsg.contains("Mobile no is invalid"), 
				"Invalid contact no.error msg not displaying");
	}
	
    @Test
    public void verifyCountryCodeInDropdown() {
        
        waitForElementToBeVisible(vat.logo);
        vat.countryListDropdwnBtn.click();
        List<WebElement> countryItems = vat.countriesList;
        String expectedCountryCode = "India";
        boolean countryCodeFound = false;
        for (WebElement country : countryItems) {
            if (country.getText().contains(expectedCountryCode)) {
                countryCodeFound = true;
                break;
            }
        }

        Assert.assertTrue(countryCodeFound, "Expected country code is not "
        		+ "found in the dropdown");
    }
    
   @Test
    public void verifyCountryCodeSearch() {
    	
        waitForElementToBeVisible(vat.menuText);
        vat.countryListDropdwnBtn.click();
        String searchQuery = "India";
        vat.searchCntryBx.sendKeys(searchQuery);
        List<WebElement> countryItems = vat.countriesList;
        boolean countryCodeFound = false;
        for (WebElement country : countryItems) {
            if (country.isDisplayed() && country.getText().contains(searchQuery)) {
                countryCodeFound = true;
                break;
            }
        }
        Assert.assertTrue(countryCodeFound, "Expected country code is not "
        		+ "found in the search results");
    }
	
   @Test
    public void verifyMenuListSpellings() {
        
        waitForElementToBeVisible(vat.menuText);
    	List<String> expectedMenuItems = Arrays.asList("Dashboard", "VAT Registration", 
    			"VAT Filing","VAT Amendment", "VAT Consultation","Corporate Tax",
    			"Orders","Other Services");
        List<WebElement> actualMenuItems = vat.menuItems;
        Assert.assertEquals(actualMenuItems.size(), expectedMenuItems.size(),
            "Number of menu items doesn't match");
        for (WebElement menuItem : actualMenuItems) {
            String menuText = menuItem.getText().trim();
            Assert.assertTrue(expectedMenuItems.contains(menuText),
                "Unexpected or misspelled menu item found: " + menuText);
        }
    }
    
   @Test
   public void verifyMenuColorOnHover() throws InterruptedException {
       waitForElementToBeVisible(vat.menuText);
       Thread.sleep(3000);
       for (int i = 0; i < vat.menuItems.size(); i++) {
           WebElement menuItem = vat.menuItems.get(i);
           
           if (i != 1) {
               String colorBeforeHover = menuItem.getCssValue("color");

               action.moveToElement(menuItem).perform();

               String colorAfterHover = menuItem.getCssValue("color");

               Assert.assertNotEquals(colorBeforeHover, colorAfterHover,
                       "Color did not change on hover for menu item: " + menuItem.getText());
           }
       }
   }

    
  @Test
    public void verifyUploadDocumentsTitle() {
        
        waitForElementToBeVisible(vat.menuText);
    	List<String> expectedTitles = Arrays.asList("Trade License*", "MOA*", 
    			"Emirates ID of Owner*", "Passport of Owner*", "Declaration Form*");
        List<WebElement> actualTitles = vat.documentsTitle;
        for (WebElement title : actualTitles) {
            String documentsName = title.getText();
            Assert.assertTrue(expectedTitles.contains(documentsName),
                "Unexpected or misspelled document name found: " + documentsName);
        }
    }
    
   @Test
    public void uploadDocumentInst() {
       
        waitForElementToBeVisible(vat.menuText);
    	String expectedText = ("Drag & drop files or Browse"
    			+ "Supported formates: JPEG, PNG, PDF, Word.");
        List<WebElement> actualText = vat.uploadBoxes;
        for (WebElement title : actualText) {
            String text = title.getText();
            Assert.assertTrue(expectedText.contains(text),
                "Instructions not found in dropbox" + text);
        }
    }
    
    @Test
    public void moveToTopBtn() throws InterruptedException{
    
    	waitForElementToBeVisible(vat.menuText);
    	scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
    	Thread.sleep(3000);
    	vat.backToTopBtn.click();
    	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Double scrollPosition = (Double) jsExecutor.executeScript("return window.scrollY;");
        double tolerance = 10; 
        Assert.assertTrue(scrollPosition < tolerance, "Page did not scroll to the top");
     }
    
    @Test
    public void jpegFileUpload() throws InterruptedException, 
    AWTException {
       
        Thread.sleep(3000);
        uploadFiles(vat, VATRegistrationHelper.Jpeg_FILE_PATHS);
       	        Assert.assertEquals(vat.uploadedFiles.size(), VATRegistrationHelper.Jpeg_FILE_PATHS.length,
                "The number of uploaded Word files does not match the expected count");
       	        }
    
    @Test
    public void pngFileUploadTest() throws InterruptedException, 
    AWTException {
       
        Thread.sleep(3000);
        uploadFiles(vat, VATRegistrationHelper.Png_FILE_PATHS);
       	        Assert.assertEquals(vat.uploadedFiles.size(), VATRegistrationHelper.Png_FILE_PATHS.length,
                "The number of uploaded Word files does not match the expected count");
       	        }
    
    @Test
    public void wordFileUploadTest() throws InterruptedException, AWTException {
      
        Thread.sleep(3000);
        uploadFiles(vat, VATRegistrationHelper.WORD_FILE_PATHS);
       	        Assert.assertEquals(vat.uploadedFiles.size(), VATRegistrationHelper.WORD_FILE_PATHS.length,
                "The number of uploaded Word files does not match the expected count");
       	        }

    @Test
    public void pdfFileUploadTest() throws InterruptedException, AWTException {
      
        Thread.sleep(3000);
        uploadFiles(vat, VATRegistrationHelper.PDF_FILE_PATHS);
       	        Assert.assertEquals(vat.uploadedFiles.size(), VATRegistrationHelper.PDF_FILE_PATHS.length,
                "The number of uploaded Word files does not match the expected count");
       	        }
  
    @Test
	public void txtUpload() throws InterruptedException, AWTException {
    	
		Thread.sleep(4000);
		uploadFiles(vat, VATRegistrationHelper.Txt_FILE_PATHS);
		Assert.assertNull(vat.uploadedFiles, 
				"Unsupported Txt file is getting uploaded");
    }
    
    @Test
	public void mp4FileUpload() throws InterruptedException, AWTException {
    	
		Thread.sleep(4000);
		uploadFiles(vat, VATRegistrationHelper.Mp4_FILE_PATHS);
		Assert.assertNull(vat.uploadedFiles, 
				"Unsupported Mp4 file is getting uploaded");
    }

    @Test
    public void maxFileAllowed() throws InterruptedException, AWTException {
    	
        Thread.sleep(3000);
        scrollPage(ScrollType.TO_ELEMENT, vat.pasportUploadBtn, 0, 0);      
        for (String filePath : VATRegistrationHelper.Png_FILE_PATHS) {
            vat.pasportUploadBtn.click();
            rb.delay(2000);
            StringSelection uploadSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
            helper.KeyPress();
            Thread.sleep(3000);
        }
        Assert.assertEquals(vat.uploadedFiles.size(), 
        		VATRegistrationHelper.Png_FILE_PATHS.length,
            "The number of uploaded files does not match the expected count");
    }
 
    @Test
    public void fileDuplicacy() throws InterruptedException, AWTException {
    
     Thread.sleep(3000);
     uploadFiles(vat, VATRegistrationHelper.DuplicatFile);
    	        Assert.assertEquals(vat.uploadedFiles.size(), 1,
             "Duplicate files are getting uploaded");
    	        } 
    
	@AfterClass
		public void tearDown() {
			driver.quit();
		}
		
	@Test
	public void minimiseMenu() {
	    vat.minimiseMnuBtn.click();
	    
	    for (WebElement menuItem : vat.menuItems) {
	        Assert.assertFalse(menuItem.isDisplayed(), "Menu items are still displayed after minimizing");
	    }
	}
	}
