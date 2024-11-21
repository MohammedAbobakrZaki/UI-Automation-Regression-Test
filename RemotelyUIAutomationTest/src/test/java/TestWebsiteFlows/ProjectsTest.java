package TestWebsiteFlows;

import RemotelyFlows.LoginToWebsite;
import RemotelyFlows.GoToProjects;
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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ProjectsTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_WAIT_TIME = 5;
    private static final Logger logger = Logger.getLogger(ProjectsTest.class.getName());

    //********************************************************************************************************//
    // Setup Browser And Login
    //********************************************************************************************************//

    @BeforeTest
    public void setUp() {
        logger.info("Setting up the browser and logging into the website...");
        driver = LoginToWebsite.loginToRemotelyStore("https://app.remotely.store/", "bolast@gmail.com", "123456");
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));

        logger.info("Navigating to the Projects section...");
        GoToProjects.navigateToProjects(driver);
        logger.info("Setup and navigation completed successfully.");
    }

    //********************************************************************************************************//
    // Helper Methods: Verify Clickable And Visibility Of The Element
    //********************************************************************************************************//

    private WebElement waitForElementToBeClickable(By locator, String errorMessage) {
        logger.info("Waiting for element to be clickable: " + locator.toString());
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Assert.assertTrue(element.isEnabled(), errorMessage);
        logger.info("Element is clickable: " + locator.toString());
        return element;
    }

    private WebElement waitForElementToBeVisible(By locator, String errorMessage) {
        logger.info("Waiting for element to be visible: " + locator.toString());
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(element.isDisplayed(), errorMessage);
        logger.info("Element is visible: " + locator.toString());
        return element;
    }

    //********************************************************************************************************//
    // Verify New Project Button, Projects Title, Search Box, Project Cards, Export Report Button
    //********************************************************************************************************//

    @Test(priority = 1)
    public void verifyNewProjectButtonIsClickable() {
        logger.info("Starting verifyNewProjectButtonIsClickable test...");
        By newProjectButtonLocator = By.xpath("//button[normalize-space()='New Project']");
        waitForElementToBeClickable(newProjectButtonLocator, "New Project Button is not clickable on the screen.");
        logger.info("verifyNewProjectButtonIsClickable test passed.");
    }

    @Test(priority = 2)
    public void verifyProjectsTitleIsDisplayed() {
        logger.info("Starting verifyProjectsTitleIsDisplayed test...");
        By projectsTitleLocator = By.xpath("//h1[@class='-mt-1 text-xl font-bold text-white lg:text-2xl']");
        waitForElementToBeVisible(projectsTitleLocator, "Projects Title is not displayed on the screen.");
        logger.info("verifyProjectsTitleIsDisplayed test passed.");
    }

    @Test(priority = 3)
    public void verifySearchBoxIsDisplayed() {
        logger.info("Starting verifySearchBoxIsDisplayed test...");
        By searchBoxLocator = By.xpath("//div[@class='inline-flex w-full items-center h-full box-border']");
        waitForElementToBeVisible(searchBoxLocator, "Search Box is not displayed on the screen.");
        logger.info("verifySearchBoxIsDisplayed test passed.");
    }

    @Test(priority = 4)
    public void verifyExportReportButtonIsClickable() {
        logger.info("Starting verifyExportReportButtonIsClickable test...");
        By exportReportButtonLocator = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/button[1]");
        waitForElementToBeClickable(exportReportButtonLocator, "Export Report button is not clickable on the screen.");
        logger.info("verifyExportReportButtonIsClickable test passed.");
    }

    @Test(priority = 5)
    public void verifyProjectCardsAreClickable() {
        logger.info("Starting verifyProjectCardsAreClickable test...");
        List<WebElement> projectCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("a.w-full.inline-block[href*='/projects/']")));
        logger.info("Number of project cards found: " + projectCards.size());

        for (WebElement projectCard : projectCards) {
            Assert.assertTrue(projectCard.isEnabled(), "Project card is not clickable: " + projectCard.getText());
            logger.info("Verified project card is clickable: " + projectCard.getAttribute("href"));
        }
        logger.info("verifyProjectCardsAreClickable test passed.");
    }

    @Test(priority = 6)
    public void verifyProjectCountMatchesDisplayedCount() {
        logger.info("Starting verifyProjectCountMatchesDisplayedCount test...");
        WebElement projectCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//p[@class='font-medium text-lg flex items-start flex-col'])[1]")));
        String projectCountText = projectCountElement.getText();
        int expectedProjectCount = Integer.parseInt(projectCountText.replaceAll("[^0-9]", ""));
        logger.info("Expected project count: " + expectedProjectCount);

        WebElement projectContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='grid grid-cols-1 lg:grid-cols-3 2xl:grid-cols-4 gap-4 px-4'])[1]")));
        List<WebElement> projectCards = projectContainer.findElements(
                By.xpath(".//div[contains(@class, 'flex flex-col relative overflow-hidden')]"));
        int actualProjectCount = projectCards.size();
        logger.info("Actual project count: " + actualProjectCount);

        Assert.assertEquals(actualProjectCount, expectedProjectCount, "Displayed project count does not match the actual count of projects.");
        logger.info("verifyProjectCountMatchesDisplayedCount test passed.");
    }

    @Test(priority = 7)
    public void verifyDropdownOptionsAreClickable() {
        logger.info("Starting verifyDropdownOptionsAreClickable test...");
        WebElement newProjectButtonElement = waitForElementToBeClickable(
                By.xpath("(//div[@class='flex gap-8 items-center'])[1]"), "New Project Button is not clickable on the screen.");
        newProjectButtonElement.click();

        WebElement createProjectButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/form[1]/div[1]/div[3]/button[1]")));
        Assert.assertTrue(createProjectButton.isEnabled(), "Create Project Button is not enabled on the screen.");
        logger.info("Create Project Button is enabled.");

        WebElement dropdownElement = waitForElementToBeClickable(
                By.xpath("(//div[@class='w-full flex flex-col'])[2]"), "Dropdown is not clickable.");
        dropdownElement.click();

        List<String> optionsToCheck = Arrays.asList("PROJECT", "BRAND", "DEPARTMENT", "REGION");
        for (String optionKey : optionsToCheck) {
            WebElement optionElement = waitForElementToBeVisible(
                    By.xpath("//li[@data-key='" + optionKey + "']"), "Option '" + optionKey + "' is not displayed in the dropdown.");
            Assert.assertTrue(optionElement.isEnabled(), "Option '" + optionKey + "' is not clickable in the dropdown.");
            logger.info("Verified option: " + optionKey + " is displayed and clickable.");
        }
        driver.navigate().back();
        logger.info("verifyDropdownOptionsAreClickable test passed.");
    }

    //********************************************************************************************************//
    // Close The Browser
    //********************************************************************************************************//

    @AfterTest
    public void tearDown() {
        logger.info("Closing the browser...");
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully.");
        }
    }
}
