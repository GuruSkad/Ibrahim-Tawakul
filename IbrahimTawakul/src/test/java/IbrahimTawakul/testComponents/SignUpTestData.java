package IbrahimTawakul.testComponents;

import org.testng.annotations.DataProvider;

public class SignUpTestData {
	@DataProvider(name = "validSignUpData")
	public static Object[][] getValidSignUpData() {
		return new Object[][] { { "CompanyA a8", "exampl8@gmail.com", "42355679", "Address123", "CityA", },};
	}

	@DataProvider(name = "unnecesarrySpace")
	public static Object[][] extraSpace() {
		return new Object[][] { { "CompanyA1 b2", "email3 @example.com", "12345 67872", "Address123", "CityA", }, };
	}

	@DataProvider(name = "mandatoryInfo")
	public static Object[][] mandatory() {
		return new Object[][] { { "Company Bb4", "email.12d@example.com", "1234567862", " ", " ", }, };
	}
	
	@DataProvider(name = "invalidEmail")
	public static Object[][] InvalidEmail() {
		return new Object[][] { { "Company B2", "email.12example.com", "1234567892", " ", " ", }, };
	}
	
	@DataProvider(name = "registeredMail")
	public static Object[][] preRegsMail() {
		return new Object[][] { { "Company B89", "email.12@example.com", "1234567892", " ", " ", }, };
	}
	
	@DataProvider(name = "registeredCompany")
	public static Object[][] company() {
		return new Object[][] { { "Company B", "email.121@example.com", "1234567893", " ", " ", }, };
	}
}
