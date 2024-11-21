package RemotelyFlows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoToMembers {
    private static final int DEFAULT_WAIT_TIME = 8;

    // Method to navigate to Sites
    public static void navigateToSites(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));

        // Wait for the Members button to be clickable and then click it
        WebElement MembersButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='menu-members']")));
        MembersButton.click();
    }
}
