package IbrahimTawakul.testComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import IbrahimTawakul.pageobjects.VatFilingLocator;
import IbrahimTawakul.pageobjects.VatRegistrationLocators;

public class VATRegistrationHelper {
	    private WebDriver driver;
	    private VatRegistrationLocators vat;
	    private Robot rb;

	    // Constructor to initialize WebDriver and Robot
	    public VATRegistrationHelper(WebDriver driver) {
	        this.driver = driver;
	        try {
	            this.rb = new Robot();
	        } catch (AWTException e) {
	            e.printStackTrace();
	        }
	    }

	    // Reusable method to perform key press actions
	    public void KeyPress() {
	        rb.keyPress(KeyEvent.VK_CONTROL);
	        rb.keyPress(KeyEvent.VK_V);

	        rb.keyRelease(KeyEvent.VK_CONTROL);
	        rb.keyRelease(KeyEvent.VK_V);

	        rb.keyPress(KeyEvent.VK_ENTER);
	        rb.keyRelease(KeyEvent.VK_ENTER);
	    }

	    // Method to get the upload button based on the index
	    public WebElement getUploadButton(VatRegistrationLocators vat, int index) {
	        switch (index) {
	            case 0:
	                return vat.tradeUploadBtn;
	            case 1:
	                return vat.moaUploadBtn;
	            case 2:
	                return vat.idUploadBtn;
	            case 3:
	                return vat.pasportUploadBtn;
	            case 4:
	                return vat.dclrationUploadBtn;
	            default:
	                return null;
	        }
	    }

	    //upload btns for vat filing
	    public WebElement vatFilingUploadBtn(VatFilingLocator vatf, int index) {
	        switch (index) {
	            case 0:
	                return vatf.salesUpload;
	            case 1:
	                return vatf.purchaseUpload;
	            case 2:
	                return vatf.statementUpload;
	            default:
	                return null;
	        }
	    }
	    // Change file paths
	    public static final String[] PDF_FILE_PATHS = {
	        "C:\\Users\\Dell\\Pictures\\Screenshots\\pc1.png",
	        "C:\\Users\\Dell\\Pictures\\Screenshots\\pc2.png",
	        "C:\\Users\\Dell\\Pictures\\Screenshots\\pc3.png",
	        "C:\\Users\\Dell\\Pictures\\Screenshots\\pc4.png",
	        "C:\\Users\\Dell\\Pictures\\Screenshots\\pc5.png"
	    };

	    public static final String[] WORD_FILE_PATHS = {
	    		"C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc2.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc3.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc4.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc5.png"
	    	    };
	    
	    public static final String[] Jpeg_FILE_PATHS = {
	    		"C:\\Users\\Dell\\Pictures\\Screenshots\\sc9.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc10.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc11.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc4.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc5.png"
	    	    };
	    
	    public static final String[] Png_FILE_PATHS = {
	    		"C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc2.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc3.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc4.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc5.png"
	    	    };
	    
	    public static final String[] Txt_FILE_PATHS = {
	    		"C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc2.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc3.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc4.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc5.png"
	    	    };
	    
	    public static final String[] Mp4_FILE_PATHS = {
	    		"C:\\Users\\Dell\\Pictures\\Screenshots\\sc1.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc2.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc3.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc4.png",
	    	    "C:\\Users\\Dell\\Pictures\\Screenshots\\sc5.png"
	    	    };

	}
