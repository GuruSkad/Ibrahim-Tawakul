package IbrahimTawakul.testComponents;

import org.testng.annotations.DataProvider;

public class SignUpTestData {
	 @DataProvider(name = "validSignUpData")
	    public static Object[][] getValidSignUpData() {
	        return new Object[][]{
	                {"CompanyA", "email@example.com", "1234567890", "Address123", "CityA",},
	                {"CompanyB", "another.email@example.com", "9876543210", "Address456", "CityB",},
	                
	        };
	    }
	 
	 @DataProvider(name = "unnecesarrySpace")
	    public static Object[][] extraSpace() {
	        return new Object[][]{
	                {"CompanyA  ", "  email@example.com", "12345 67890", "Address123", "CityA",},
	        };
	    }
	 
	 @DataProvider(name = "mandatoryInfo")
	    public static Object[][] mandatory() {
	        return new Object[][]{
	                {"CompanyB", "email@example.com", "12345 67890", " ", " ",},
	        };
	    }
}
