package runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.cucumber.core.options.Constants;

@Suite
@SelectClasspathResource("features") // Membaca folder resources/features
@ConfigurationParameter(
        key = Constants.GLUE_PROPERTY_NAME,
        value = "steps" // Membaca folder java/steps
)
@ConfigurationParameter(
        key = Constants.PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-reports.html" // Bikin laporan otomatis
)
public class TestRunner {
    // Tetap dikosongkan ya Princess
}