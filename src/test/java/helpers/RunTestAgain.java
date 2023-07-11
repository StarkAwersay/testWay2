package helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RunTestAgain implements IRetryAnalyzer {
    /**
     * nowCount.
     */
    private int nowCount = 0;
    /**
     * maxCount.
     */
    private int maxCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount < maxCount) {
            nowCount++;
            return true;
        }
        nowCount = 0;
        return false;
    }
}