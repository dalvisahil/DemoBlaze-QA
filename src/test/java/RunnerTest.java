import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:reports/cucumber-html-reports/html-report.html",
        },
        glue = {"stepDefs"},
        features = {"classpath:features/"}
)
public class RunnerTest {
}
