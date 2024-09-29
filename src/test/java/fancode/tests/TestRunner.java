package fancode.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features", // Path to the feature files
		glue = {"fancode.tests"}, // Path to the step definitions
		//plugin = {"pretty","junit:target/cucumber-reports/report_xml.xml","json:target/cucumber-reports/report_json.json","html:target/cucumber-reports/report_html.html"}
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class TestRunner extends AbstractTestNGCucumberTests{
}
