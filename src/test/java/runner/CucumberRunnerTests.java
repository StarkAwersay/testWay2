package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class CucumberRunnerTests {
    @CucumberOptions(
            features = "src/test/resources/features/switchingToCareerPage.feature",
            glue = "steps"
    )
    public static class cucumberRunnerTests extends AbstractTestNGCucumberTests {
    }
}
