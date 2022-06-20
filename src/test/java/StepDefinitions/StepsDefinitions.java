package StepDefinitions;

import Functions.CreateDriver;
import cucumber.api.java.en.Given;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class StepsDefinitions {

    private static Properties prop = new Properties();
    private static InputStream in = CreateDriver.class.getResourceAsStream("../resources/test.properties");

    WebDriver driver;

    /** Log attributes **/

    Logger log = Logger.getLogger(StepsDefinitions.class);

    public StepsDefinitions(){
        driver = Hooks.driver;
    }

    @Given("I am in App main site(.*)")
    public void iAmInAppMainSite(String url) {

        log.info("Navigate to: " + url);
        driver.get(url);
    }
}
