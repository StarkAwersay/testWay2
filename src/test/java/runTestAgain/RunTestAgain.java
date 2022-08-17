package runTestAgain;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RunTestAgain implements IRetryAnalyzer {
    private int nowCount=0;
    private int maxCount=2;


    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount<maxCount) {
            nowCount++;
            return true;
        }
        nowCount=0;
        return false;
    }
}