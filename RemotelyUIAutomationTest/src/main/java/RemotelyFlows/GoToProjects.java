package RemotelyFlows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoToProjects {

    private static final int DEFAULT_WAIT_TIME = 5;

    // Method to navigate to Projects
    public static void navigateToProjects(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));

        // Wait for the Projects button to be clickable and then click it
        WebElement projectsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Projects']")));
        projectsButton.click();
    }
}
