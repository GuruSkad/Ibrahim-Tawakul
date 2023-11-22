package IbrahimTawakul.testCases;

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
	    loginApplication();
	    String emailPlaceholder = vat.emailBox.getAttribute("placeholder");
	    String contactPlaceholder = vat.mobileBox.getAttribute("placeholder");

	    Assert.assertEquals(emailPlaceholder, "Email", "Email placeholder is incorrect");
	    Assert.assertEquals(contactPlaceholder, "Contact Number", 
	    		"Contact number placeholder is incorrect");
	}
	
	@Test
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
	
	@Test
	public void incorrectEmailMsg() {
		loginApplication();
		scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
		vat.makePymntBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String emailEmptyMsg = vat.emailErrTxt.getText();
		Assert.assertTrue(emailEmptyMsg.contains("Email is invalid"), 
				"Incorrect email error msg not displaying");
	}
	
	@Test
	public void IncorrectContactNoMsg() {
		loginApplication();
		scrollPage(ScrollType.TO_BOTTOM, null, 0,0);
		vat.makePymntBtn.click();
		scrollPage(ScrollType.TO_TOP, null, 0, 0);
		String contactEmptyMsg = vat.mobileErrTxt.getText();
		Assert.assertTrue(contactEmptyMsg.contains("Mobile no is invalid"), 
				"Invalid contact no.error msg not displaying");
	}
	
    @Test
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
    
    @Test
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
	
    @Test
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
    
    @Test
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
    
    @Test
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
    
    @Test
    public void uploadDocumentMsg() {
        loginApplication();
        waitForElementToBeVisible(vat.menuText);
    	String expectedText = ("Drag & drop files or Browse "
    			+ "Supported formates: JPEG, PNG, PDF, Word.");
        List<WebElement> actualText = vat.uploadBoxes;
        for (WebElement title : actualText) {
            String text = title.getText();
            Assert.assertTrue(expectedText.contains(text),
                "Instructions not found in dropbox" + text);
        }
    }
    
    @Test
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
    
	@AfterMethod
		public void tearDown() {
			driver.quit();
		}
}
