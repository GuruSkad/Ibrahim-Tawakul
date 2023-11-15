package IbrahimTawakul.testCases;

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

	@Test
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

	@Test
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
	public void validSignUpTest(String companyName, String email, String mobile, String address, String city) {

		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);

//		List<WebElement> stateOptions = stateSelect.getOptions();
//		int randomIndex = new Random().nextInt(stateOptions.size());
//		stateSelect.selectByIndex(randomIndex);
		signUp.submitBtn.click();

	}
	
	@Test (dataProviderClass = SignUpTestData.class, dataProvider = "unnecesarrySpace")
	public void whiteSpace(String companyName, String email, String mobile, String address, String city) {
		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);
	}
	
	@Test (dataProviderClass = SignUpTestData.class, dataProvider = "mandatoryinfo")
	public void mandatoryFeild(String companyName, String email, String mobile, String address, String city) {
		signUp.companyName.sendKeys(companyName);
		signUp.email.sendKeys(email);
		signUp.contactNumber.sendKeys(mobile);
		signUp.address.sendKeys(address);
		signUp.city.sendKeys(city);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
