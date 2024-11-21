package TestWebsiteFlows;

import RemotelyFlows.LoginToWebsite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;

public class LoginTest {

    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(LoginTest.class.getName());
    private static final int DEFAULT_WAIT_TIME = 5; // Increased wait time for reliability

    // Use environment variables or configuration files for sensitive information
    private static final String URL = System.getenv("TEST_URL"); // Replace with your URL
    private static final String USERNAME = System.getenv("USERNAME"); // Replace with your username
    private static final String PASSWORD = System.getenv("PASSWORD"); // Replace with your password

    @BeforeTest
    public void openBrowser() {
        logger.info("Opening the browser and logging in...");
        driver = LoginToWebsite.loginToRemotelyStore(
                URL != null ? URL : "https://app.remotely.store/",
                USERNAME != null ? USERNAME : "bolast@gmail.com",
                PASSWORD != null ? PASSWORD : "123456"
        );
    }

    @Test
    public void login() {
        logger.info("Waiting for the email element to become visible...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
            WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(), 'bolast@gmail.com')]")
            ));

            // Assert that the email element is displayed
            Assert.assertTrue(emailElement.isDisplayed(), "Login was not successful; email is not displayed on the dashboard.");
            logger.info("Login was successful.");
        } catch (Exception e) {
            logger.severe("An error occurred during login validation: " + e.getMessage());
            throw e; // Re-throwing the exception to ensure the test fails
        }
    }

    @AfterTest
    public void tearDown() {
        logger.info("Closing the browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
