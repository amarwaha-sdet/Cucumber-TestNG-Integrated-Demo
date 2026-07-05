import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "com/utility"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@windowAndTabOpening"
)

public class CucumberRunner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
