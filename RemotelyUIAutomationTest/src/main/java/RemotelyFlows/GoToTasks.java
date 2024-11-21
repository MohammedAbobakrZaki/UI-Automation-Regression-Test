package RemotelyFlows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoToTasks {
    private static final int DEFAULT_WAIT_TIME = 5;

    // Method to navigate to Sites
    public static void navigateToSites(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));

        // Wait for the Sites button to be clickable and then click it
        WebElement sitesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='menu-tasks']")));
        sitesButton.click();
    }
}
