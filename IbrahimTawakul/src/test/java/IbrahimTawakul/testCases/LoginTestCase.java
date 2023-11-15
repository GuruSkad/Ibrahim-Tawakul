package IbrahimTawakul.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.testComponents.BaseTest;

public class LoginTestCase extends BaseTest {
	private LoginPageLocators login;

	@BeforeMethod
	public void initializeLoginPageLocators() {
		login = new LoginPageLocators(driver);
		login.clearFields();
	}

	@Test
	public void linkValidation() {
		SoftAssert assert1 = new SoftAssert();
		for (WebElement link : login.links) {
			String url = link.getAttribute("href");

			try {
				URL urlObject = new URL(url);
				// to check the protocol of the URL (e.g., http, https)
				String protocol = urlObject.getProtocol();

				if (protocol.equalsIgnoreCase("http") || protocol.equalsIgnoreCase("https")) {
					HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
					conn.setRequestMethod("HEAD");
					conn.connect();
					int respCode = conn.getResponseCode();
					assert1.assertTrue(respCode < 400,
							"The link with text " + link.getText() + " is broken " + respCode);
				} else {
					System.out.println("Skipping link with unsupported protocol: " + url);
				}
			} catch (MalformedURLException e) {
				System.out.println("Invalid URL: " + url);
			} catch (IOException e) {
				System.out.println("Exception while checking link: " + e.getMessage());
			}
		}
		assert1.assertAll();
	}

	@Test
	public void testValidLogin() {
		login.loginApplication("anmol@skadits.com", "wY5+zG7!");
		for (int i = 0; i < 3; i++) {
			login.nextButton.click();
		}
		String title = driver.getTitle();
		assert title.equals("Ibrahim Tawakul");

	}

	@Test
	public void passwordVisibility() {
		String password = "Testing";
		login.clearFields();
		login.enterPassword(password);
		login.showPassword.click();
		String passwordText = login.password1.getAttribute("value");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(password, passwordText);
		softAssert.assertAll();
	}

	//@Test
	public void buttonHoverColorChange() {
		String origLoginColor = login.loginButton.getCssValue("background-color");
		String origSignupColor = login.signUpButton.getCssValue("background-color");
		Actions actions = new Actions(driver);
		actions.moveToElement(login.loginButton).build().perform();
		String loginColorAfterHover = login.loginButton.getCssValue("background-color");
		assert !origLoginColor.equals(loginColorAfterHover);

		actions.moveToElement(login.signUpButton).build().perform();
		String signupColorAfterHover = login.signUpButton.getCssValue("background-color");
		assert !origSignupColor.equals(signupColorAfterHover);

	}

	//@Test
	public void testLogoDisplay() {
		assert login.loginLogo.isDisplayed() : "Logo is not displayed on the page";
	}

	//@Test
	public void caseInsensitiveUsername() throws InterruptedException {
		login.loginApplication("automationUSER", "testing");
		// this test need to be modified once the requirements is clear
		Thread.sleep(5000);
		for (int i = 0; i < 3; i++) {
			login.nextButton.click();
		}
		String title = driver.getTitle();
		System.out.println(title);
		// assert title.equals("Expected title");
	}

	//@Test
	public void specialCharacterInPass() throws InterruptedException {
		login.loginApplication("Automation", "@test#&g");
		// this also need modification
		Thread.sleep(3000);
		for (int i = 0; i < 3; i++) {
			login.nextButton.click();
		}
		String title = driver.getTitle();
		System.out.println(title);
		// assert title.equals("Expected title");
	}

	//@Test
	public void userPassFeildValidation() {
		login.clearFields();
		String usernameText = login.userName.getAttribute("value");
		String passwordText = login.password1.getAttribute("value");
		System.out.println(usernameText);
		System.out.println(passwordText);
		
		assert usernameText.equalsIgnoreCase("username");
		assert passwordText.equalsIgnoreCase("password");
		
		
	}

	// @Test (can't be checked now)
	public void invalidUser() throws IOException, InterruptedException {
		login.loginApplication("InvalidUser", "validPassword");
		// String errorMessage = message.getText();
		// assert errorMessage.equals("Expected Error Message");

	}

	// @Test (can't be checked now)
	public void incorrectPassword() throws IOException, InterruptedException {
		login.loginApplication("automation", "wrongPassword");
		// String errorMessage = login.getErrorMessage();
		// assert errorMessage.equals("Expected Error Message");

	}

	//@Test
	public void testEmptyUsername() throws IOException, InterruptedException {
		login.enterUsername(" ");
		login.enterPassword("Password123");
		login.loginButton.click();
		String emptyUserMsg = login.emptyUserMsg.getText();
		assert emptyUserMsg.equals("Username is required");

	}

	//@Test
	public void testEmptyPassword() throws IOException, InterruptedException {
		login.enterUsername("ValidUser");
		login.enterPassword("  ");
		login.loginButton.click();
		String emptyPassMsg = login.emptyPassMsg.getText();
		assert emptyPassMsg.equals("Password is required");

	}

	// @Test
	public void inactiveAccount() {
		login.loginApplication("inactive user", "testing");
		String errorMessage = login.getErrorMessage.getText();
		Assert.assertTrue(errorMessage.contains("inactive"), "Login error message");
	}

	// @Test
	public void updatedPassword() {
		login.loginApplication("Automation", "oldpassword");
		String errorMessage = login.getErrorMessage.getText();
		Assert.assertTrue(errorMessage.contains("change password"), "Login error message");
	}

	// @Test
	public void testResetPasswordLink() {
		login.resetPassword.click();
		// Add code to handle the "Reset Password" page and assert relevant elements
	}

	// @Test
	public void testSuccessfulLogout() {

	}

	// @Test
	public void testUnsuccessfulLogout() {

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
