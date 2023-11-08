package IbrahimTawakul.testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import java.io.IOException;
import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.testComponents.BaseTest;

public class LoginTestCase extends BaseTest {
	private LoginPageLocators login;

	@BeforeMethod
	public void initializeLoginPageLocators() {
		login = new LoginPageLocators(driver);
	}

	@Test
	public void testValidLogin() throws IOException, InterruptedException {
		login.enterUsername("Automation");
		login.enterPassword("Testing");
		login.login.click();
		for (int i=0; i<3; i++) {
		login.nextButton.click();
		}
		String title = driver.getTitle();
		assert title.equals("Expected title");
		Thread.sleep(5000);
	}

	//@Test (can't be checked now)
	public void testInvalidLogin() throws IOException, InterruptedException {
	    login.enterUsername("InvalidUser");
	    login.enterPassword("InvalidPassword");
	    login.login.click();
	   // String errorMessage = message.getText();
	    //assert errorMessage.equals("Expected Error Message");
	    Thread.sleep(5000);
	}

	@Test
	public void testEmptyUsername() throws IOException, InterruptedException {
	    login.enterUsername(" ");
	    login.enterPassword("Password123");
	    login.login.click();
	    String emptyUserMsg = login.emptyUserMsg.getText();
	    assert emptyUserMsg.equals("Username is required");
	    
	}

	@Test
	public void testEmptyPassword() throws IOException, InterruptedException {
	    login.enterUsername("ValidUser");
	    login.enterPassword("  ");
	    login.login.click();
	    String emptyPassMsg = login.emptyPassMsg.getText();
	    assert emptyPassMsg.equals("Password is required");
	    
	}
	
	@Test
	public void testForgotPasswordLink() {
	    login.forgotPassword.click();
	    // check once the link gets activated
	}

	@Test
	public void testSignUpLink() {
	    login.signUp.click();
	    // check signup form is getting displayed
	}

	@Test
	public void testResetPasswordLink() {
	    login.resetPassword.click();
	    // Add code to handle the "Reset Password" page and assert relevant elements
	}

	@Test
	public void testShowPasswordCheckbox() {
	    login.showPassword.click();
	   
	}
	@Test
	public void testSuccessfulLogout() {
	    
	}

	@Test
	public void testUnsuccessfulLogout() {
	    
	}
	
	// @Test
	public void negativeLogin() {
		driver.findElement(By.name("username")).sendKeys("user");
	}
}
