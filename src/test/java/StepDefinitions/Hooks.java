package StepDefinitions;

import Functions.CreateDriver;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;

public class Hooks {
    public static WebDriver driver;
    // it reads the hook
    Logger log = Logger.getLogger(Hooks.class);
    private Scenario scenario;

    @Before
    public void initDriver(Scenario scenario) throws IOException {
        log.info("***********************************************************************************************************");
        log.info("[ Configuration ] - Initializing driver configuration");
        log.info("***********************************************************************************************************");
        driver = CreateDriver.initConfig();
        this.scenario = scenario;
        log.info("***********************************************************************************************************");
        log.info("[ Scenario ] - "+ scenario.getName());
        log.info("***********************************************************************************************************");
    }

    @After
    public void embedScreenshot(Scenario scenario) throws IOException {

        if(scenario.isFailed()) {
            try {
                scenario.write("The scenario failed.");
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "src/test/resources/Data/Screenshots/Failed");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }

        log.info("***********************************************************************************************************");
        log.info("[ Driver Status ] - Clean and close the intance of the driver");
        log.info("***********************************************************************************************************");
        driver.quit();

    }

}
