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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.errorprone.annotations.Keep;

import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.pageobjects.VatRegistrationLocators;
import IbrahimTawakul.testComponents.BaseTest;

public class VatRegistrationTest extends BaseTest{
	private VatRegistrationLocators vat;
	private LoginPageLocators login;
	private Robot rb;

	@BeforeMethod
	public void initializeLoginPageLocators() throws AWTException {
		vat = new VatRegistrationLocators(driver);
		login = new LoginPageLocators(driver);
		rb = new Robot();
	}
	
	public void loginApplication() {
		login.userName.sendKeys("anmol.smit@gmail.com");
		login.password1.sendKeys("Anmolkumar@1234");
		login.loginButton.click();
	}
	
	private void KeyPress() {
    	rb.keyPress(KeyEvent.VK_CONTROL);
    	rb.keyPress(KeyEvent.VK_V);
    	
    	rb.keyRelease(KeyEvent.VK_CONTROL);
    	rb.keyRelease(KeyEvent.VK_V);
    	
    	rb.keyPress(KeyEvent.VK_ENTER);
    	rb.keyRelease(KeyEvent.VK_ENTER); 
	}
	
	private WebElement getUploadButton(int index) {
	    switch (index) {
	        case 0:
	            return vat.tradeUploadBtn;
	        case 1:
	            return vat.moaUploadBtn;
	        case 2:
	            return vat.idUploadBtn;
	        case 3:
	            return vat.pasportUploadBtn;
	        case 4:
	            return vat.dclrationUploadBtn;
	        default:
	            return null;
	    }}

	//@Test
	public void heading() {
		loginApplication();
		for(WebElement ab: vat.uploadBoxes) {
			String text = ab.getText();
			System.out.println(text);
			
		}
	}
	
	//@Test
	public void uiTesting() {
	    loginApplication();
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

	//@Test
	public void verifyPlaceholders() {
	    loginApplication();
	    String emailPlaceholder = vat.emailBox.getAttribute("placeholder");
	    String contactPlaceholder = vat.mobileBox.getAttribute("placeholder");

	    Assert.assertEquals(emailPlaceholder, "Email", "Email placeholder is incorrect");
	    Assert.assertEquals(contactPlaceholder, "Contact Number", 
	    		"Contact number placeholder is incorrect");
	}
	
	//@Test
	public void emptyMsg() {
		loginApplication();
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
	
	//@Test
	public void incorrectEmailMsg() {
		loginApplication();
		scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
		vat.makePymntBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String emailEmptyMsg = vat.emailErrTxt.getText();
		Assert.assertTrue(emailEmptyMsg.contains("Email is invalid"), 
				"Incorrect email error msg not displaying");
	}
	
	//@Test
	public void IncorrectContactNoMsg() {
		loginApplication();
		scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
		vat.makePymntBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String contactEmptyMsg = vat.mobileErrTxt.getText();
		Assert.assertTrue(contactEmptyMsg.contains("Mobile no is invalid"), 
				"Invalid contact no.error msg not displaying");
	}
	
   // @Test
    public void verifyCountryCodeInDropdown() {
        loginApplication();
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
    
   // @Test
    public void verifyCountryCodeSearch() {
    	loginApplication();
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
	
   // @Test
    public void verifyMenuListSpellings() {
        loginApplication();
        waitForElementToBeVisible(vat.menuText);
    	List<String> expectedMenuItems = Arrays.asList("Dashboard", "VAT Registration", 
    			"VAT Filing", "VAT deregistration","VAT Amendment", "VAT Consultation",
    			"Other Services","Orders");
        List<WebElement> actualMenuItems = vat.menuItems;
        Assert.assertEquals(actualMenuItems.size(), expectedMenuItems.size(),
            "Number of menu items doesn't match");
        for (WebElement menuItem : actualMenuItems) {
            String menuText = menuItem.getText().trim();
            Assert.assertTrue(expectedMenuItems.contains(menuText),
                "Unexpected or misspelled menu item found: " + menuText);
        }
    }
    
  //  @Test
    public void verifyMenuColorOnHover() throws InterruptedException {
        Actions action = new Actions(driver);

        loginApplication();
        waitForElementToBeVisible(vat.menuText);
        Thread.sleep(5000);
        for (WebElement menuItem : vat.menuItems) {
            String colorBeforeHover = menuItem.getCssValue("color");

            action.moveToElement(menuItem).perform();

            String colorAfterHover = menuItem.getCssValue("color");

            Assert.assertNotEquals(colorBeforeHover, colorAfterHover,
                "Color did not change on hover for menu item: " + menuItem.getText());
        }
    }
    
  //  @Test
    public void verifyDocumentsTitle() {
        loginApplication();
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
    
   // @Test
    public void uploadDocumentMsg() {
        loginApplication();
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
    
    //@Test
    public void moveToTopBtn() throws InterruptedException
    {
    	loginApplication();
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
    public void pngFileUpload() throws InterruptedException, AWTException {
    	loginApplication();
    	Thread.sleep(8000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.tradeUploadBtn, 0, 0);
    	vat.tradeUploadBtn.click();
    	
    	rb = new Robot();
    	rb.delay(2000);
    	
    	StringSelection upload1 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png");
    	StringSelection upload2 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc2.png");
    	StringSelection upload3 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc3.png");
    	StringSelection upload4 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc4.png");
    	StringSelection upload5 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc5.png"); 	
    	  	
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload1, null);
    	KeyPress();  
    	
    	Thread.sleep(2000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.moaUploadBtn, 0, 0);
    	vat.moaUploadBtn.click();
    	rb.delay(2000);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload2, null);
    	KeyPress();
    	
    	Thread.sleep(2000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.idUploadBtn, 0, 0);
    	vat.idUploadBtn.click();
    	rb.delay(2000);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload3, null);
    	KeyPress();
    	
    	Actions action = new Actions(driver);
    	
    	Thread.sleep(3000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.pasportUploadBtn, 0, 0);
    	waitForClickable(vat.pasportUploadBtn);
    	action.moveToElement(vat.pasportUploadBtn).click().build().perform();
    	rb.delay(2000);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload4, null);
    	KeyPress();
    	
    	Thread.sleep(2000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.dclrationUploadBtn, 0, 0);
    	vat.dclrationUploadBtn.click();
    	rb.delay(2000);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload5, null);
    	KeyPress();
    	
    	Thread.sleep(3000);
    	scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
    	Assert.assertEquals(vat.uploadedFiles.size(), 5,"all png files are not uploaded");
    	vat.submitWithoutPmntBtn.click();
    	Thread.sleep(2000);
    	Assert.assertTrue(vat.successMessage().contains("Your Request has been Submitted"),
    		    "Success message is not displayed");
    	vat.trackYourOrdrBtn.click();
    
    }
    
    @Test
    public void jpegFileUpload() throws InterruptedException, AWTException {
    	loginApplication();
    	Thread.sleep(8000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.tradeUploadBtn, 0, 0);
    	vat.tradeUploadBtn.click();
    	
    	rb = new Robot();
    	rb.delay(2000);
    	
    	//change the file location
    	StringSelection upload1 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png");
    	StringSelection upload2 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc2.png");
    	StringSelection upload3 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc3.png");
    	StringSelection upload4 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc4.png");
    	StringSelection upload5 = new StringSelection
    			("C:\\Users\\Dell\\Pictures\\Screenshots\\sc5.png"); 	
    	  	
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload1, null);
    	KeyPress();  
    	
    	Thread.sleep(2000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.moaUploadBtn, 0, 0);
    	vat.moaUploadBtn.click();
    	rb.delay(2000);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload2, null);
    	KeyPress();
    	
    	Thread.sleep(2000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.idUploadBtn, 0, 0);
    	vat.idUploadBtn.click();
    	rb.delay(2000);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload3, null);
    	KeyPress();
    	
    	Actions action = new Actions(driver);
    	
    	Thread.sleep(3000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.pasportUploadBtn, 0, 0);
    	waitForClickable(vat.pasportUploadBtn);
    	action.moveToElement(vat.pasportUploadBtn).click().build().perform();
    	rb.delay(2000);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload4, null);
    	KeyPress();
    	
    	Thread.sleep(2000);
    	scrollPage(ScrollType.TO_ELEMENT,vat.dclrationUploadBtn, 0, 0);
    	vat.dclrationUploadBtn.click();
    	rb.delay(2000);
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload5, null);
    	KeyPress();
    	
    	Thread.sleep(3000);
    	scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
    	Assert.assertEquals(vat.uploadedFiles.size(), 5);
    
    }

    @Test
    public void wordFileUpload() throws InterruptedException, AWTException {
    	loginApplication();
    	Thread.sleep(5000);    	
    	String[] filepaths = {
    			"C:\\Users\\Dell\\Pictures\\Screenshots\\wc1.png",
    			"C:\\Users\\Dell\\Pictures\\Screenshots\\wc2.png",
    			"C:\\Users\\Dell\\Pictures\\Screenshots\\wc3.png",
    			"C:\\Users\\Dell\\Pictures\\Screenshots\\wc4.png",
    			"C:\\Users\\Dell\\Pictures\\Screenshots\\wc5.png",
    	};
    	
    	for(int j=0; j<filepaths.length; j++) {
    		WebElement uploadBtn = getUploadButton(j);
    		scrollPage(ScrollType.TO_ELEMENT, uploadBtn, 0, 0);
    		waitForClickable(uploadBtn);
    		uploadBtn.click();
    		rb.delay(2000);
    		StringSelection uploadSelection = new StringSelection(filepaths[j]);
    		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
    		KeyPress();
    		Thread.sleep(3000);
    	}
    	scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
        Assert.assertEquals(vat.uploadedFiles.size(), filepaths.length,
            "The number of uploaded wordFiles does not match the expected count");
    }
    
    @Test
    public void pdfFileUpload() throws InterruptedException {
        loginApplication();
        Thread.sleep(5000);
    
        String[] filePaths = {
            "C:\\Users\\Dell\\Pictures\\Screenshots\\pc1.png",
            "C:\\Users\\Dell\\Pictures\\Screenshots\\pc2.png",
            "C:\\Users\\Dell\\Pictures\\Screenshots\\pc3.png",
            "C:\\Users\\Dell\\Pictures\\Screenshots\\pc4.png",
            "C:\\Users\\Dell\\Pictures\\Screenshots\\pc5.png"
        };

        for (int i = 0; i < filePaths.length; i++) {
            WebElement currentUploadBtn = getUploadButton(i);
            scrollPage(ScrollType.TO_ELEMENT, currentUploadBtn, 0, 0);
            waitForClickable(currentUploadBtn);
            currentUploadBtn.click();
            rb.delay(2000);

            StringSelection uploadSelection = new StringSelection(filePaths[i]);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
            KeyPress();
            Thread.sleep(3000); 
        }
        scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
        Assert.assertEquals(vat.uploadedFiles.size(), filePaths.length,
            "The number of uploaded PDF files does not match the expected count");
    }
    
    @Test
    public void txtFileUpload() throws InterruptedException, AWTException {
     	loginApplication();
        Thread.sleep(5000);
        String mp4FilePath = "C:\\Users\\Dell\\Desktop\\testcases for login page.txt";
        List<WebElement> uploadButtons = Arrays.asList(
            vat.tradeUploadBtn, vat.moaUploadBtn, vat.idUploadBtn, 
            vat.pasportUploadBtn, vat.dclrationUploadBtn
        );

        for (WebElement uploadButton : uploadButtons) {
            scrollPage(ScrollType.TO_ELEMENT, uploadButton, 0, 0);
            waitForClickable(uploadButton);
            uploadButton.click();
            rb.delay(2000);
            StringSelection uploadSelection = new StringSelection(mp4FilePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
            KeyPress();
            Thread.sleep(3000);
        }
        scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
    	Assert.assertTrue(vat.uploadedFiles == null || vat.uploadedFiles.isEmpty(), 
    			"Uploaded files list is not null unsupported txt file found");
    }
    
    @Test
    public void mp4FileUpload() throws InterruptedException, AWTException {
       	loginApplication();
        Thread.sleep(5000);
        scrollPage(ScrollType.TO_ELEMENT, vat.tradeUploadBtn, 0, 0);
        String mp4FilePath = "C:\\Users\\Dell\\Downloads\\file_example_MP4_480_1_5MG.mp4";
        List<WebElement> uploadButtons = Arrays.asList(
            vat.tradeUploadBtn, vat.moaUploadBtn, vat.idUploadBtn, 
            vat.pasportUploadBtn, vat.dclrationUploadBtn
        );

        for (WebElement uploadButton : uploadButtons) {
            scrollPage(ScrollType.TO_ELEMENT, uploadButton, 0, 0);
            waitForClickable(uploadButton);
            uploadButton.click();
            rb.delay(2000);
            StringSelection uploadSelection = new StringSelection(mp4FilePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
            KeyPress();
            Thread.sleep(5000);
        }
        scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
    	Assert.assertTrue(vat.uploadedFiles == null || vat.uploadedFiles.isEmpty(), 
    			"Uploaded files list is not null mp4 file found");
    }

    
   // @Test
    public void maxFileAllowed() throws InterruptedException, AWTException {
        loginApplication();
        Thread.sleep(5000);
        scrollPage(ScrollType.TO_ELEMENT, vat.tradeUploadBtn, 0, 0);      
        String[] filePaths = {
            "C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png",
            "C:\\Users\\Dell\\Pictures\\Screenshots\\sc2.png",
            "C:\\Users\\Dell\\Pictures\\Screenshots\\sc3.png"
        };
        for (String filePath : filePaths) {
            vat.tradeUploadBtn.click();
            rb.delay(2000);
            StringSelection uploadSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
            KeyPress();
            Thread.sleep(3000);
        }
        Assert.assertEquals(vat.uploadedFiles.size(), filePaths.length,
            "The number of uploaded files does not match the expected count");
    }

    
    //@Test
    public void fileDuplicacy() throws InterruptedException, AWTException {
    		loginApplication();
    	    Thread.sleep(5000);
    	    scrollPage(ScrollType.TO_ELEMENT, vat.tradeUploadBtn, 0, 0);
    	    String filePath = "C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png";
    	    for (int i = 0; i < 3; i++) {
    	        vat.tradeUploadBtn.click();
    	        rb.delay(2000);
    	        StringSelection uploadSelection = new StringSelection(filePath);
    	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadSelection, null);
    	        KeyPress();
    	        Thread.sleep(3000); 
    	    }
    	    String expectedErrorMessage = "File already exists";    	   
    	   String actualErrorMessage = vat.errorFrSameFile.getText();
    	    
    	    Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
    	            "Expected error message 'File already exists' not found");
    	}

    
    
	//@AfterMethod
		public void tearDown() {
			driver.quit();
		}
}
