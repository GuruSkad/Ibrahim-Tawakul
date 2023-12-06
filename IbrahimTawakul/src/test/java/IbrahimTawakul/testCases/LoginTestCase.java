package IbrahimTawakul.testCases;

import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
	public void editBoxValidation() {
		String loginId = "abc@gmail.com";
		String pass = "fchg@12K";
		login.enterUsername(loginId);
		login.enterPassword(pass);
		String actualId = login.userName.getAttribute("value");
		String actualPass = login.password1.getAttribute("value");
		assert actualId.contains(loginId);
		assert actualPass.contains(pass);

	}

	@Test
	public void editBoxColorChange() {
		login.loginLogo.click();
		String origLoginIdColor = login.userName.getCssValue("background-color");
		String origPassColor = login.password1.getCssValue("background-color");

		login.userName.sendKeys("a");
		String userIdColorAfter = login.userName.getCssValue("background-color");
		assert !origLoginIdColor.equals(userIdColorAfter);

		login.password1.sendKeys("b");
		String passColorAfter = login.password1.getCssValue("background-color");
		assert !origPassColor.equals(passColorAfter);

	}

	@Test
	public void testValidLogin() throws InterruptedException {
		login.loginApplication("anmol.smit@gmail.com", "Anmolkumar@1234");
		String title = driver.getTitle();
		assert title.equals("Ibrahim Tawakul");
		Thread.sleep(3000);
		String currentURL = driver.getCurrentUrl();
		String expectedURL = "https://ibrahimtawakul.hexagonsolutions.dev/pages/vat-registration";
		Assert.assertEquals(currentURL, expectedURL, "URLs don't match");
		login.logout();
	}

	@Test
	public void keyboardTest() throws InterruptedException {
		login.loginApplication("anmol.smit@gmail.com", "Anmolkumar@1234");
		String originalWindow = driver.getWindowHandle();
		((JavascriptExecutor) driver)
				.executeScript("window.open('https://ibrahimtawakul.hexagonsolutions.dev/pages/vat-registration');");

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
		login.logout();
		driver.switchTo().window(originalWindow);
		driver.navigate().refresh();
		String currentURL = driver.getCurrentUrl();
		String expectedURL = "https://ibrahimtawakul.hexagonsolutions.dev/login";
		Assert.assertEquals(currentURL, expectedURL, "URLs don't match - Not on the login page");
	}

	@Test
	public void multipleLoginClick() {
		login.userName.sendKeys("anmoltiwary4@gmail.com");
		for (int i = 0; i < 10; i++) {
			login.loginButton.click();
		}
		String passErrTxt = login.emptyPassMsg.getText();
		assert passErrTxt.contains("Password is required");
	}

	@Test
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

	@Test
	public void testLogoDisplay() {
		assert login.loginLogo.isDisplayed() : "Logo is not displayed on the page";
	}

	@Test
	public void userPassFeildValidation() {
		login.clearFields();
		login.loginApplication("username", "password");
		String usernameText = login.userName.getAttribute("value");
		String passwordText = login.password1.getAttribute("value");
		assert usernameText.equalsIgnoreCase("username");
		assert passwordText.equalsIgnoreCase("password");

	}

	@Test
	// (modify later)
	public void invalidUser() throws IOException, InterruptedException {
		login.loginApplication("InvalidUser", "Anmolkumar@1234");
		Thread.sleep(5000);
		String displayBox = login.loginText();
		assert displayBox.contains("Login");

	}

	@Test
	// (modify for message if included in functionality)
	public void incorrectPassword() throws IOException, InterruptedException {
		login.loginApplication("anmol.smit@gmail.com", "wrongPassword");
		Thread.sleep(5000);
		String displayBox = login.loginText();
		assert displayBox.contains("Login");

	}

	@Test
	public void testEmptyUsername() throws IOException, InterruptedException {
		login.enterPassword("Password123");
		login.loginButton.click();
		String emptyUserMsg = login.emptyUserMsg.getText();
		assert emptyUserMsg.equals("Username is required");

	}

	@Test
	public void testEmptyPassword() throws IOException, InterruptedException {
		login.enterUsername("ValidUser");
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

	@Test
	public void testResetPasswordLink() {
		login.resetPassword.click();
		String text = login.resetPassText.getText();
		assert text.contains("Enter Your Email ID to Generate OTP");

	}

	// @AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
