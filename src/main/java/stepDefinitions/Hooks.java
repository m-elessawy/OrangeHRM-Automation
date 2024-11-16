package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static utilities.PropertyReaderHelper.getCustomProperty;

public class Hooks {

    protected static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);  // Log4j logger

    @Before
    public void setUp(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@web")) {
            // Set up browser for web tests
            String browserType = getCustomProperty("browserTypeKey");
            logger.info("Browser Type: " + browserType);
            driver = initializeDriver(browserType);
            driver.get(getCustomProperty("urlKey"));
            driver.manage().window().maximize();
            logger.info("Browser setup complete for web test");
        } else if (scenario.getSourceTagNames().contains("@api")) {
            // Set up API client or logging for API tests
            logger.info("API test setup started");
            // You can initialize API-specific configurations here if needed
        }
    }

    private WebDriver initializeDriver(String browserType) {
        WebDriver driver;
        switch (browserType.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.warn("Browser not supported. Defaulting to Chrome.");
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@web") && driver != null) {
            driver.quit();
            logger.info("Browser closed after web test");
        }
        // API-related cleanup if needed
        if (scenario.getSourceTagNames().contains("@api")) {
            logger.info("API test cleanup completed");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
