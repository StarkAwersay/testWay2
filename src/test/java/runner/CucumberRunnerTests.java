package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class CucumberRunnerTests {
    @CucumberOptions(
            features = "src/test/resources/features/authorizationOnWay2.feature",
            glue = {"steps","hooks"}
    )
    public static class cucumberRunnerTests extends AbstractTestNGCucumberTests {
    }
}
