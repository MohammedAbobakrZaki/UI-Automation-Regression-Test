package RemotelyFlows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class LoginToWebsite {

    private static final int DEFAULT_WAIT_TIME = 5;


    // Global method to set up WebDriver and log in to the Remotely Store app
    public static WebDriver loginToRemotelyStore(String url, String username, String password) {
        // Set up EdgeDriver using WebDriverManager
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();

        // Maximize the browser window for better visibility
        driver.manage().window().maximize();

        // Navigate to the specified URL
        driver.navigate().to(url);

        // Set an explicit wait timeout of 3 seconds for locating elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));

        // Locate and interact with the username and password fields
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Wait for the login button to be clickable, then click it
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Log In')]")));
        loginButton.click();

        // Return the driver instance for further interactions
        return driver;
    }
}
