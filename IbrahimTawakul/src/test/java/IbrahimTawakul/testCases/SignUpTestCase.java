package IbrahimTawakul.testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import IbrahimTawakul.pageobjects.SignUpPageLocators;
import IbrahimTawakul.testComponents.BaseTest;
import IbrahimTawakul.testComponents.SignUpTestData;

public class SignUpTestCase extends BaseTest {
	private SignUpPageLocators signUp;

	@BeforeMethod
	public void initializeLoginPageLocators() {
		signUp = new SignUpPageLocators(driver);
	}

	// @Test
	public void prsncOfAllFlds() {
		signUp.gotoSignup();
		signUp.logo.isDisplayed();
		signUp.signUpText.isDisplayed();
		signUp.companyName.isDisplayed();
		signUp.contactNumber.isDisplayed();
		signUp.email.isDisplayed();
		signUp.address.isDisplayed();
		signUp.city.isDisplayed();
		signUp.state.isDisplayed();
		signUp.signUpButton.isDisplayed();
	}

	// @Test
	public void editBoxValidation() {
		signUp.gotoSignup();
		String text1 = "Abc Company";
		String mobileNumber = "123456789012";
		String email1 = "anmol@mail.com";

		signUp.companyName.sendKeys(text1);
		String companyText = signUp.companyName.getAttribute("value");
		assert companyText.equals(text1);

		signUp.contactNumber.sendKeys(mobileNumber);
		String mob = signUp.contactNumber.getAttribute("value");
		assert mob.equals(mobileNumber);

		signUp.email.sendKeys(email1);
		String eml = signUp.email.getAttribute("value");
		assert eml.equals(email1);

		signUp.address.sendKeys(text1);
		String adrs = signUp.address.getAttribute("value");
		assert adrs.equals(text1);

		signUp.city.sendKeys(text1);
		String cty = signUp.city.getAttribute("value");
		assert cty.equals(text1);

		signUp.state.click();
		boolean state = signUp.state.isDisplayed();
		Assert.assertTrue(state);
	}

	@Test(dataProviderClass = SignUpTestData.class, dataProvider = "validSignUpData")
	public void validSignUpTest(String companyName, String email, String mobile, String address, String city) throws InterruptedException {
		signUp.gotoSignup();
		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);
		Select stateDropdown = new Select(signUp.state);
        stateDropdown.selectByIndex(2);
        scrollPage(ScrollType.TO_ELEMENT,signUp.submitBtn, 0, 0);
		Thread.sleep(4000);
        signUp.submitBtn.click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String confirmationMessage = alert.getText();
        alert.accept();
        assert confirmationMessage.contains("Successful signup");

	}

	// @Test (dataProviderClass = SignUpTestData.class, dataProvider = "unnecesarrySpace")
	public void whiteSpace(String companyName, String email, String mobile, String address, String city) throws InterruptedException {
		signUp.gotoSignup();
		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);
		Select stateDropdown = new Select(signUp.state);
        stateDropdown.selectByIndex(1);
        scrollPage(ScrollType.TO_ELEMENT,signUp.submitBtn, 0, 0);
		Thread.sleep(4000);
        signUp.submitBtn.click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String confirmationMessage = alert.getText();
        alert.accept();
        assert confirmationMessage.contains("Successful signup");
		
	}

	// @Test (dataProviderClass = SignUpTestData.class, dataProvider = "mandatoryInfo")
	public void mandatoryFeild(String companyName, String email, String mobile, String address, String city) {
		signUp.gotoSignup();
		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);
		Select stateDropdown = new Select(signUp.state);
        stateDropdown.selectByIndex(3);
        scrollPage(ScrollType.TO_ELEMENT,signUp.submitBtn, 0, 0);
        signUp.submitBtn.click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String confirmationMessage = alert.getText();
        alert.accept();
        assert confirmationMessage.contains("Successful signup");
	}

	// @Test (dataProviderClass = SignUpTestData.class, dataProvider = "invalidEmail")
	public void invalidEmailFormat(String companyName, String email, String mobile, String address, String city) {
		signUp.gotoSignup();
		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);
		signUp.submitBtn.click();
		String errorText = signUp.emlErrMsg.getText();
		assert errorText.contains(" Email is invalid ");
	}

	//@Test(dataProviderClass = SignUpTestData.class, dataProvider = "registeredMail")
	public void preRegisteredMail(String companyName, String email, String mobile, String address, String city) {
		signUp.gotoSignup();
		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);
		signUp.submitBtn.click();
		String errorText = signUp.emlErrMsg.getText();
		assert errorText.contains("Email Id is required");
	}
	
	@Test(dataProviderClass = SignUpTestData.class, dataProvider = "registeredCompany")
	public void preRegisteredCmpny(String companyName, String email, String mobile, String address, String city) {
		signUp.gotoSignup();
		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);
		signUp.submitBtn.click();
		String errorText = signUp.cmpnyErrMsg.getText();
		assert errorText.contains("Company Name is required");
	}
	
	//@Test
	public void letterInContactno() {
		signUp.gotoSignup();
		signUp.contactNumber.sendKeys("anykeyWH");
		String num = signUp.contactNumber.getAttribute("value");
		Assert.assertTrue(num.isBlank());
	}
	
	@Test
	public void multipleSignupClick() {
		signUp.gotoSignup();
		signUp.email.sendKeys("email46327@yahoo.com");
		signUp.contactNumber.sendKeys("5678907654");
		for(int i=0; i<10; i++) {		
		signUp.submitBtn.click();
		}
		String errorText = signUp.cmpnyErrMsg.getText();
		assert errorText.contains("Company Name is required");
		
	}
	
	//@Test
	public void blankMandatoryFeilds() {
		signUp.gotoSignup();
		waitForElementToBeVisible(signUp.submitBtn);
		scrollPage(ScrollType.TO_ELEMENT,signUp.submitBtn, 0, 0);
		signUp.submit();
		String errorText = signUp.cmpnyErrMsg.getText();
		assert errorText.contains("Company Name is required");
		String errorText1 = signUp.contErrMsg.getText();
		assert errorText1.contains("Contact Number is required");
		String errorText2 = signUp.emlErrMsg.getText();
		assert errorText2.contains("Email Id is required");
		
	}
	
	@Test
	public void buttonHoverColor() {
		signUp.gotoSignup();
		scrollPage(ScrollType.TO_ELEMENT,signUp.submitBtn, 0, 0);
		String origLoginColor = signUp.submitBtn.getCssValue("background-color");
		Actions actions = new Actions(driver);
		actions.moveToElement(signUp.submitBtn).build().perform();
		String SignupColorAfterHover = signUp.submitBtn.getCssValue("background-color");
		assert !origLoginColor.equals(SignupColorAfterHover);
	}
	
	@Test
	public void backToLogin() throws InterruptedException {
		signUp.gotoSignup();
		scrollPage(ScrollType.TO_ELEMENT,signUp.loginBtn, 0, 0);
		waitForClickable(signUp.loginBtn);
		Thread.sleep(3000);
		signUp.returntoLogin();
		Assert.assertTrue(signUp.loginText.getText().contains("Login"));
	}

	@Test
    public void keyboardValidation() {
        signUp.gotoSignup();
        signUp.companyName.sendKeys("wap jio");
        signUp.companyName.sendKeys(Keys.TAB);
        signUp.contactNumber.sendKeys("1432536712");
        signUp.email.sendKeys(Keys.ENTER);
        String errorTxt = signUp.emlErrMsg.getText();
        assert errorTxt.contains("Email Id is required");
    }
	
	 @Test
	    public void verifyPlaceholders() {
	     signUp.gotoSignup();   
		 Assert.assertEquals(signUp.companyName.getText(), "Company Name", "Placeholder for Company name doesn't match");
	        Assert.assertEquals(signUp.email.getText(), "Email ID", "Placeholder for Email doesn't match");
	        Assert.assertEquals(signUp.contactNumber.getText(), "Contact Number", "Placeholder for Mobile doesn't match");
	        Assert.assertEquals(signUp.address.getText(), "Area Address", "Placeholder for Address doesn't match");
	        Assert.assertEquals(signUp.city.getText(), "City", "Placeholder for City doesn't match");
	    }

		
	// @AfterClass
	public void tearDown() {
		driver.quit();
	}
}
