package whtest.com;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:cucumber/features/PlaceBet.feature",
                    glue = "whtest.com",
                    format = { "html:target/cucumber-report/whtest" })
public class RunTest {
}