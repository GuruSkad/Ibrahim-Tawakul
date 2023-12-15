package IbrahimTawakul.testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import IbrahimTawakul.testComponents.BaseTest;

public class VatConsultationTest extends BaseTest {

	@BeforeClass
	public void constr() {
		gotoVatConsultation();
	}
	
	@Test
	public void titleValidation() {
		String title = vatc.pageTitle.getText();
		Assert.assertTrue(title.contains("VAT Consultation"), "Page title not matching");
	}

	@Test
	public void placeHolder() {
		Assert.assertEquals(vatc.dateP.getText(), "Choose a date", "Date placeholder not found");
		Assert.assertEquals(vatc.timep.getText(), "Choose a time", "Time placeholder not found");
		Assert.assertEquals(vatc.topicp.getText(), "Topic *", "Topic placeholder not found");
		Assert.assertEquals(vatc.languageP.getText(), "Language", "Language placeholder not found");
	}

	@Test
	public void emptyErrMsg() {
	    SoftAssert softAssert = new SoftAssert();
	    waitForClickable(vatc.submitBtn);
	    vatc.submitBtn.click();
	    String dateErr = vatc.dateErrMsg.getText();
	    String timeErr = vatc.timeErrMsg.getText();
	    String topicErr = vatc.topicErrMsg.getText();
	    String langErr = vatc.LangErrMsg.getText();

	    softAssert.assertEquals(dateErr, "Date is required", "Date error message mismatch");
	    softAssert.assertEquals(timeErr, "Time is required", "Time error message mismatch");
	    softAssert.assertEquals(topicErr, "Topic is required", "Topic error message mismatch");
	    softAssert.assertEquals(langErr, "Language is required", "Language error message mismatch");
	    softAssert.assertAll(); // To assert all conditions and collect all failures
}

	@Test
	public void previousDateBooking() throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String currentUrl = driver.getCurrentUrl();
		waitForClickable(vatc.dateBox);
		vatc.dateBox.click();

		while (true) {
			String currentMonth = vatc.dates.get(0).getText();
			if (currentMonth.equals("MAR")) {
				break;
			}
			executor.executeScript("arguments[0].click();", vatc.previousMnthBtn);
		}

		for (WebElement date : vatc.dates) {
			if (date.getText().equals("3")) {
				date.click();
				break;
			}
		}

		vatc.timeBox.click();
		vatc.hourInput.sendKeys("10");
		vatc.topicBox.sendKeys("testing automation 1");
		vatc.languageBox.click();
		Thread.sleep(2000);
		vatc.languages.get(1).click();
		vatc.submitBtn.click();
		waitForClickable(vatc.finalSaveBtn);
		vatc.finalSaveBtn.click();
		waitForClickable(vatc.orderHeading);
		String orderUrl = driver.getCurrentUrl();
		vatc.vatConsBtn.click();
		Assert.assertEquals(currentUrl, orderUrl, "Accepting past date booking not giving error");

	}
	
	@Test
	public void cancleBooking() throws InterruptedException {
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    waitForClickable(vatc.dateBox);
		    vatc.dateBox.click();
		    
		    while (!vatc.dates.get(0).getText().equals("JAN")) {
		        executor.executeScript("arguments[0].click();", vatc.nextMnthBtn);
		    }
		    vatc.dates.stream().filter(date -> date.getText().equals("12")).findFirst().ifPresent(WebElement::click);
		    String currentURL = driver.getCurrentUrl();
		    vatc.timeBox.click();
		    vatc.hourInput.sendKeys("9");
		    vatc.topicBox.sendKeys("testing automation 2");
		    vatc.languageBox.click();
		    Thread.sleep(2000);
		    vatc.languages.get(2).click();
		    vatc.submitBtn.click();
		    waitForClickable(vatc.finalSaveBtn);
		    vatc.cancleBtn.click();
		    waitForClickable(vatc.pageTitle);
			String laterUrl = driver.getCurrentUrl();
			Assert.assertEquals(currentURL, laterUrl, "Order is not getting cancle at final stage");
		}
	
	@Test
	public void validBooking() throws InterruptedException {
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    waitForClickable(vatc.dateBox);
		    vatc.dateBox.click();
		    
		    while (!vatc.dates.get(0).getText().equals("JAN")) {
		        executor.executeScript("arguments[0].click();", vatc.nextMnthBtn);
		    }
		    vatc.dates.stream().filter(date -> date.getText().equals("12")).findFirst().ifPresent(WebElement::click);
		    String currentURL = driver.getCurrentUrl();
		    vatc.timeBox.click();
		    vatc.hourInput.sendKeys("9");
		    vatc.topicBox.sendKeys("testing automation 2");
		    vatc.languageBox.click();
		    Thread.sleep(2000);
		    vatc.languages.get(2).click();
		    vatc.submitBtn.click();
		    waitForClickable(vatc.finalSaveBtn);
		    vatc.finalSaveBtn.click();
		    waitForClickable(vatc.orderHeading);
			String orderUrl = driver.getCurrentUrl();
			vatc.vatConsBtn.click();
			Assert.assertNotEquals(currentURL, orderUrl, "Not being redirected to 'Orders Page' after valid submission");
		}
	
	@Test
	public void cursorUpdate() throws InterruptedException {
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    waitForClickable(vatc.dateBox);
		    vatc.dateBox.click();
		    
		    while (!vatc.dates.get(0).getText().equals("JAN")) {
		        executor.executeScript("arguments[0].click();", vatc.nextMnthBtn);
		    }
		    vatc.dates.stream().filter(date -> date.getText().equals("12")).findFirst().ifPresent(WebElement::click);
		  
		    vatc.timeBox.click();
		    vatc.hourInput.sendKeys("9");
		    vatc.topicBox.sendKeys("testing automation 2");
		    vatc.languageBox.click();
		    Thread.sleep(2000);
		    vatc.languages.get(2).click();
		    vatc.submitBtn.click();
		    waitForClickable(vatc.finalSaveBtn);
		    vatc.finalSaveBtn.click();
		    waitForClickable(vatc.orderHeading);
		    boolean isConsultationFocused = isElementFocused(vatc.vatConsBtn);
		    boolean isOrdersFocused = isElementFocused(vatc.OrderBtn);
		    vatc.vatConsBtn.click();
		    Assert.assertTrue(isConsultationFocused, "Cursor is not on Consultation element");
		    Assert.assertFalse(isOrdersFocused, "Cursor is on Orders element");
		}

		private boolean isElementFocused(WebElement element) {
		    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    WebElement activeElement = (WebElement) jsExecutor.executeScript("return document.activeElement;");
		    return element.equals(activeElement);
		}
	
	@Test
	public void missingInfo() throws InterruptedException {
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    waitForClickable(vatc.dateBox);
		    vatc.dateBox.click();
		    
		    while (!vatc.dates.get(0).getText().equals("JAN")) {
		        executor.executeScript("arguments[0].click();", vatc.nextMnthBtn);
		    }
		    vatc.dates.stream().filter(date -> date.getText().equals("12")).findFirst().ifPresent(WebElement::click);
		  
		    vatc.timeBox.click();
		    vatc.hourInput.sendKeys("9");
		    vatc.submitBtn.click();
		    String topicErr = vatc.topicErrMsg.getText();
			String langErr = vatc.LangErrMsg.getText();
			Assert.assertEquals(topicErr, "Topic is required", "Topic error message mismatch");
			Assert.assertEquals(langErr, "Language is required", "Language error message mismatch");
		}
	
	@Test
	public void wrongHourInput() {
		waitForClickable(vatc.dateBox);
		vatc.timeBox.click();
		vatc.hourInput.sendKeys("97");
		vatc.submitBtn.click();
		String err = vatc.timeErrMsg.getText();
		Assert.assertEquals(err, "wrong time","accepting incorrect time");
	}

}
