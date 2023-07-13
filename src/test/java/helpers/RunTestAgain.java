package helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RunTestAgain implements IRetryAnalyzer {
    /**
     * Минимальное количество рестартов.
     */
    private int nowCount = 0;
    /**
     * Максимальное количество рестартов.
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