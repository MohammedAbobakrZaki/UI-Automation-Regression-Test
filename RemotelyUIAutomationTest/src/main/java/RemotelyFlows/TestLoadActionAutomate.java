package RemotelyFlows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestLoadActionAutomate {

    // Declare driver and wait as class-level variables
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {

        // Initialize driver using the login method
        driver = LoginToWebsite.loginToRemotelyStore("https://app.remotely.store/", "bolast@gmail.com", "123456");

        // Set a reusable explicit wait timeout of 10 seconds for locating elements
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the "Tasks" button
        clickElement(By.id("menu-tasks"));

        // Click on the "TestLoadAction Task" button
        clickElement(By.xpath("(//div[contains(@class, 'flex p-3 z-10 w-full')])[2]"));

        // Create 200 actions
        for (int i = 1; i <= 200; i++) {
            createNewAction(i);
        }

        // Quit the driver after completion
        driver.quit();
    }

    private static void createNewAction(int actionNumber) {
        // Click on "New Action" button
        clickElement(By.xpath("//button[contains(@class, 'z-0 group relative inline-flex items-center justify-center') and contains(text(), 'New Action')]"));

        // Click on "Create OK Action" button
        clickElement(By.xpath("//label[contains(@class, 'tap-highlight-transparent')]"));

        // Enter action name
        WebElement actionNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Action Name' and contains(@placeholder, 'Clear your Desktop')]")));
        actionNameField.sendKeys("Action " + actionNumber);

        // Click "Create" button
        clickElement(By.xpath("//button[contains(@class, 'z-0 group relative inline-flex items-center justify-center box-border') and text()='Create']"));

        System.out.println("Action " + actionNumber + " created successfully.");
    }

    // Helper method to click elements
    private static void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
}
