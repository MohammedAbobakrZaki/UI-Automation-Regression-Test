package RemotelyFlows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class LoginAndGetNumberOfActionsInTask {

    public static void main(String[] args) {


        // Login method with desired parameters
        WebDriver driver = LoginToWebsite.loginToRemotelyStore("https://app.remotely.store/", "bolast@gmail.com", "123456");


        // Set an explicit wait timeout of 3 seconds for locating elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));


        // Wait for the "Tasks" button to be visible, then click it
        WebElement tasksButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menu-tasks")));
        tasksButton.click();

        // Wait for a specific task item to be visible and click it
        WebElement taskButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Baladia Checklist']")));
        taskButton.click();

        // Initialize variables for tracking label numbers and counting found labels
        int labelNumber = 1; // Start with label "1"
        int labelCount = 0; // Track the number of labels found
        int maxLabels = 100000000; // Set a large upper limit to prevent infinite loop

        // Loop through label numbers in sequence until no more labels are found
        while (labelNumber <= maxLabels) {
            String xpath = String.format("//span[@aria-label='%d']", labelNumber); // Create the XPath for the current label number

            try {
                // Wait until the element with the current label number is visible
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

                // Scroll to the element to ensure it is visible on the screen
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

                // Increment the label count if the element is found
                labelCount++;
            } catch (Exception e) {
                // Break the loop if no element with the current label number is found
                break;
            }

            labelNumber++; // Move to the next label in sequence
        }

        // Print the total number of labels found
        System.out.println("Total actions found: " + labelCount);

        // Close the browser and end the session
        driver.quit();
    }
}
