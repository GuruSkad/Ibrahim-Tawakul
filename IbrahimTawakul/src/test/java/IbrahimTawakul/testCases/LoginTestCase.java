package IbrahimTawakul.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import IbrahimTawakul.testComponents.BaseTest;

public class LoginTestCase extends BaseTest{

	@Test
	public void loginTest() throws IOException {
		
		landingPage.loginApplication("test@gmail.com");

		System.out.print(landingPage);

	}

}
