package IbrahimTawakul.testCases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import IbrahimTawakul.pageobjects.LoginPageLocators;
import IbrahimTawakul.testComponents.BaseTest;

public class ResetPassword extends BaseTest {

	private LoginPageLocators login;

	@BeforeMethod
	public void initializeLoginPageLocators() {
		login = new LoginPageLocators(driver);
	}

	@Test
	public void resetPasswordlinksValidation() {
		SoftAssert assert1 = new SoftAssert();
		login.gotoResetPassword();
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
	public void resetPasswordBtn() {
		login.gotoResetPassword();
		String resetText = login.resetPassText.getText();
		Assert.assertTrue(resetText.contains("Enter Your Email ID"));
	}

	@Test
	public void returnToLogin() {
		login.gotoResetPassword();
		login.gotoLoginBtn.click();
		Assert.assertTrue(login.loginText().contains("Login"));
	}

	@Test 
	public void placeholderVerification() throws InterruptedException {
		login.gotoResetPassword();
		Thread.sleep(3000);
		String placeholder = login.emailLabel.getText();
		System.out.println(placeholder);
		Assert.assertTrue(placeholder.contains("Email ID"), "Placeholder is not found");
	}

	@Test
	public void validEmail() throws InterruptedException {
		login.gotoResetPassword();
		login.sendEmailForPswdReset("anmol@skadits.com");
		waitForElementToBeVisible(login.otpVrfText);
		Assert.assertTrue(login.otpVrfText.getText().contains("OTP Verification"));
		Thread.sleep(Duration.ofMinutes(1));		
		login.submitOtp();
		Thread.sleep(Duration.ofMinutes(1));
		Assert.assertTrue(login.loginText().contains("Login"));
	}

	@Test
	public void invalidEmail() {
		login.gotoResetPassword();
		login.sendEmailForPswdReset("invalid@gmail.com");
		String resetText = login.resetPassText.getText();
		Assert.assertTrue(resetText.contains("Enter Your Email ID"));
	}

	@Test
	public void emptyEmail() {
		login.gotoResetPassword();
		login.resetMail.sendKeys(Keys.ENTER);
		Assert.assertTrue(login.errorMessage.getText().contains("Email Id is required"));

	}	
	
	@Test
	public void invalidEmailFormat() {
		login.gotoResetPassword();
		login.resetMail.sendKeys("anmol.skad.com");
		login.resetMail.sendKeys(Keys.ENTER);
		Assert.assertTrue(login.errorMessage.getText().contains("Email is invalid"),
				"invalid mail format not displayed");

	}
	@Test
	public void incorrectOtp() throws InterruptedException {
		login.gotoResetPassword();
		login.sendEmailForPswdReset("anmol@skadits.com");
		waitForElementToBeVisible(login.otpVrfText);
		login.otpBox1.sendKeys("1");
		login.otpBox2.sendKeys("2");
		login.otpBox3.sendKeys("3");
		login.otpBox4.sendKeys("4");
		login.submitOtp();
		Assert.assertTrue(login.otpVrfText.getText().contains("OTP Verification"));
	}
	
	
	@Test
		public void emptyOtp() throws InterruptedException {
			login.gotoResetPassword();
			login.sendEmailForPswdReset("anmol@skadits.com");
			waitForElementToBeVisible(login.otpVrfText);
			Thread.sleep(2000);
			login.submitOtp();
			Assert.assertTrue(login.otpreqMsg.getText().contains("OTP is required"));
		}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
