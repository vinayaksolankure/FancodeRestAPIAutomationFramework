package fancode.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features", // Path to the feature files
	    glue = {"fancode.tests"}, // Path to the step definitions
	    plugin = {"pretty", "html:target/cucumber-reports"} // Reporting
	)

public class TestRunner extends AbstractTestNGCucumberTests{
}
