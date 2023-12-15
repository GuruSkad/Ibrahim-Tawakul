package IbrahimTawakul.testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import IbrahimTawakul.testComponents.BaseTest;

public class VatConsultationTest extends BaseTest{

	
	@BeforeClass
	public void constr() throws InterruptedException{
		goToPage("vatconsultation");
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
	    Assert.assertEquals(vatc.topicp.getText(), "Topic", "Topic placeholder not found");
	    Assert.assertEquals(vatc.languageP.getText(), "Language", "Language placeholder not found");
	}
	
	@Test
	public void emptyErrMsg() {
	    waitForClickable(vatc.submitBtn);
	    vatc.submitBtn.click();

	    String dateErr = vatc.dateErrMsg.getText();
	    String timeErr = vatc.timeErrMsg.getText();
	    String topicErr = vatc.topicErrMsg.getText();
	    String langErr = vatc.LangErrMsg.getText();

	    Assert.assertEquals(dateErr, "Date is required", "Date error message mismatch");
	    Assert.assertEquals(timeErr, "Time is required", "Time error message mismatch");
	    Assert.assertEquals(topicErr, "Topic is required", "Topic error message mismatch");
	    Assert.assertEquals(langErr, "Language is required", "Language error message mismatch");
	}
	
	@Test
	public void previousDate() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
		waitForClickable(vatc.dateBox);
	    vatc.dateBox.click();

	    while (true) {
	        String currentMonth = vatc.dates.get(0).getText();
	        if (currentMonth.equals("MAR")) {
	            break;
	        }
	        executor.executeScript("arguments[0].click();", vatc.nextMnthBtn);
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
	    vatc.languageP.click();
	    Thread.sleep(2000);
	    vatc.languages.click();
	    vatc.submitBtn.click();
	    waitForClickable(vatc.finalSaveBtn);
	    vatc.finalSaveBtn.click();
	}



}
