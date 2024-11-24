package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\ma347\\Desktop\\LAYP\\LAYP-UI\\src\\test\\resources\\features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class TestRunner 
{
	
}
