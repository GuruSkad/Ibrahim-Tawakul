package IbrahimTawakul.retryTest;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	private int count = 0;
	private static final int Max = 3;

	@Override
	public boolean retry(ITestResult result) {
		if(!result.isSuccess() && count < Max) {
			count++;
			return true;
		}
		return false;
	}

}
