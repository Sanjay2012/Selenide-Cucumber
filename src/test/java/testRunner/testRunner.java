package features;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".//src/test/java/features",
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {
                "pretty", "summary", "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json" }
        //tags="@datadriven or datatable"
)

public class testRunner {
}
