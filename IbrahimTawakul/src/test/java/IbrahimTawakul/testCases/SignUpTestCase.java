package IbrahimTawakul.testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import IbrahimTawakul.pageobjects.SignUpPageLocators;
import IbrahimTawakul.testComponents.BaseTest;

public class SignUpTestCase extends BaseTest{
	private SignUpPageLocators SignUp;

	@BeforeMethod
	public void initializeLoginPageLocators() {
		SignUp = new SignUpPageLocators(driver);
	}
	
	@Test
	public void test() {
		SignUp.SignUpButton.click();
		assert SignUp.SignUpText.getText().equals("Sign Up");
//		SignUp.State.click();
//		SignUp.selectFuel("Dubai");
	}
}
