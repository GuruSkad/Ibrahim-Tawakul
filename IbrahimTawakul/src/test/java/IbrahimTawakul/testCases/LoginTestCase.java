package IbrahimTawakul.testCases;

import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
	
	@BeforeMethod
	public void initializeLocators() {
		login.clearFields();
	}
	
	@Test
	public void linkValidation() {
		SoftAssert assert1 = new SoftAssert();
		for (WebElement link : login.links) {
			String url = link.getAttribute("href");

			try {
				URL urlObject = new URL(url);
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
	    public void editBoxClassChange() {
	        login.loginLogo.click();
	        
	        String origLoginIdClass = login.userName.getAttribute("class");
	        String origPassClass = login.password1.getAttribute("class");

	        login.userName.sendKeys("a");
	        String userIdClassAfter = login.userName.getAttribute("class");
	        Assert.assertNotEquals(origLoginIdClass, userIdClassAfter, "Login box class didn't change");

	        login.password1.sendKeys("b");
	        String passClassAfter = login.password1.getAttribute("class");
	        Assert.assertNotEquals(origPassClass, passClassAfter, "Password box class didn't change");
	    }

	@Test
	public void testValidLogin() throws InterruptedException {
		login.loginApplication("anmol@skadits.com", "Testing@121");
		waitForElementToBeVisible(login.vatRegistTitle);
		String title = driver.getTitle();
		assert title.equals("Ibrahim Tawakul");
		Thread.sleep(3000);
		String currentURL = driver.getCurrentUrl();
		String expectedURL = "https://test.ibrahimtawakul.hexagonsolutions.dev/pages/vat-registration";
		Assert.assertEquals(currentURL, expectedURL, "URLs don't match");
		login.logout();
	}

	@Test
	public void logoLinkValidation() throws InterruptedException {
		login.loginApplication("anmol@skadits.com", "Testing@121");
		waitForElementToBeVisible(login.vatRegistTitle);
		String title = driver.getTitle();
		assert title.equals("Ibrahim Tawakul");
		Thread.sleep(3000);
		String currentURL = driver.getCurrentUrl();
		login.vatLogo.click();
		Thread.sleep(3000);
		String logoUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, logoUrl, "By clicking on Logo is redirecting to the login page");
		login.logout();
	}
	
	@Test
	public void logoutFunctionality() throws InterruptedException {
		login.loginApplication("anmol@skadits.com", "Testing@121");
		waitForElementToBeVisible(login.vatRegistTitle);
		String LogedUrl = driver.getCurrentUrl();
		String originalWindow = driver.getWindowHandle();
		((JavascriptExecutor) driver)
				.executeScript("window.open('https://test.ibrahimtawakul.hexagonsolutions.dev/pages/vat-registration');");

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
		Assert.assertNotEquals(currentURL, LogedUrl, "Logout functionality not working");
	}

	@Test
	public void verifyBackButton() throws InterruptedException {
	    driver.get("https://test.ibrahimtawakul.hexagonsolutions.dev/auth/login");
	    login.loginApplication("anmol@skadits.com", "Testing@121");
	    waitForElementToBeVisible(login.vatRegistTitle);
	    String loggedInURL = driver.getCurrentUrl();
	    driver.navigate().back();
	    String redirectedURL = driver.getCurrentUrl();
	    Assert.assertEquals(redirectedURL, loggedInURL, "Test fail Back button redirects to login page");
	}
	
	@Test
	public void placeHolder() {
		Assert.assertEquals(login.userP.getText(), "email","email placeholder not found");
		Assert.assertEquals(login.passP.getText(), "password","Password placeholder not found");
	}
	
	@Test
	public void multipleLoginClick() {
		login.userName.sendKeys("anmoltiwary4@gmail.com");
		waitForClickable(login.loginButton);
		for (int i = 0; i < 20; i++) {
			login.loginButton.click();
		}
		String passErrTxt = login.emptyPassMsg.getText();
		assert passErrTxt.contains("Password is required");
	}

	@Test
	public void buttonHoverColorChange() {
		waitForClickable(login.loginButton);
		String origLoginColor = login.loginButton.getCssValue("background-color");
		waitForClickable(login.signUpButton);
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
	public void userPassFeildValidation() throws InterruptedException {
		login.clearFields();
		login.loginApplication("username", "password");
		String usernameText = login.userName.getAttribute("value");
		String passwordText = login.password1.getAttribute("value");
		assert usernameText.equalsIgnoreCase("username");
		assert passwordText.equalsIgnoreCase("password");

	}

	@Test
	public void invalidUser() throws IOException, InterruptedException {
		login.loginApplication("InvalidUser", "Anmolkumar@1234");
		waitForElementToBeVisible(login.loginButton);
		String displayBox = login.loginText();
		assert displayBox.contains("Login");
	}

	@Test
	public void incorrectPassword() throws IOException, InterruptedException {
		login.loginApplication("anmol.smit@gmail.com", "wrongPassword");
		waitForElementToBeVisible(login.loginButton);
		String displayBox = login.loginText();
		assert displayBox.contains("Login");

	}

	@Test
	public void testEmptyUsername() throws IOException, InterruptedException {
		login.enterPassword("Password123");
		waitForClickable(login.loginButton);
		login.loginButton.click();
		String emptyUserMsg = login.emptyUserMsg.getText();
		assert emptyUserMsg.equals("Username is required");

	}

	@Test
	public void testEmptyPassword() throws IOException, InterruptedException {
		login.enterUsername("anmol@skadits.com");
		waitForClickable(login.loginButton);
		login.loginButton.click();
		String emptyPassMsg = login.emptyPassMsg.getText();
		assert emptyPassMsg.equals("Password is required");

	}

	@Test(enabled  = false)
	public void inactiveAccount() throws InterruptedException {
		login.loginApplication("inactive user", "testing");
		String errorMessage = login.getErrorMessage.getText();
		Assert.assertTrue(errorMessage.contains("inactive"), "Login error message");
	}

	@Test
	public void testResetPasswordBtn() {
		waitForClickable(login.resetPassword);
		login.resetPassword.click();
		String text = login.resetPassText.getText();
		assert text.contains("Enter Your Email ID to Generate OTP");
	}
}
