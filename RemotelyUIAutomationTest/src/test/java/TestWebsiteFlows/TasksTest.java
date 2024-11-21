package TestWebsiteFlows;

import RemotelyFlows.GoToTasks;
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
import java.util.List;
import java.util.logging.Logger;

public class TasksTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_WAIT_TIME = 8;
    private static final Logger logger = Logger.getLogger(TasksTest.class.getName());

    //********************************************************************************************************//
    // Setup Browser And Login
    //********************************************************************************************************//

    @BeforeTest
    public void setUp() {
        logger.info("Setting up the browser and logging into the website...");
        driver = LoginToWebsite.loginToRemotelyStore("https://app.remotely.store/", "bolast@gmail.com", "123456");
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));

        logger.info("Navigating to the Tasks section...");
        GoToTasks.navigateToSites(driver);
        logger.info("Setup and navigation to Tasks completed successfully.");
    }

    //********************************************************************************************************//
    // Verify Create Task Button, Tasks Title, Each Task Card, Search Box
    //********************************************************************************************************//

    @Test(priority = 1)
    public void verifyCreateTaskButtonIsClickable() {
        logger.info("Starting verifyCreateTaskButtonIsClickable test...");
        WebElement createTaskButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/main[1]/div[1]/header[1]/div[2]/button[1]")));
        Assert.assertTrue(createTaskButton.isEnabled(), "Create Task Button is not enabled on the screen.");
        logger.info("verifyCreateTaskButtonIsClickable test passed.");
    }

    @Test(priority = 2)
    public void verifyTasksTitleIsDisplayed() {
        logger.info("Starting verifyTasksTitleIsDisplayed test...");
        WebElement tasksTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/main[1]/div[1]/header[1]/div[1]/div[1]/h1[1]")));
        Assert.assertTrue(tasksTitle.isEnabled(), "Tasks Title is not enabled on the screen.");
        logger.info("verifyTasksTitleIsDisplayed test passed.");
    }

    @Test(priority = 3)
    public void verifySearchBoxIsDisplayed() {
        logger.info("Starting verifySearchBoxIsDisplayed test...");
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
        Assert.assertTrue(searchBox.isEnabled(), "Search Box is not enabled on the screen.");
        logger.info("verifySearchBoxIsDisplayed test passed.");
    }

    @Test(priority = 4)
    public void verifyTaskCardsAreClickable() {
        logger.info("Starting verifyTaskCardsAreClickable test...");
        List<WebElement> taskCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a.w-full.inline-block[href*='/tasks/']")));
        logger.info("Number of task cards found: " + taskCards.size());

        for (WebElement taskCard : taskCards) {
            Assert.assertTrue(taskCard.isEnabled(), "Task card is not clickable: " + taskCard.getText());
            logger.info("Verified task card is clickable: " + taskCard.getAttribute("href"));
        }
        logger.info("verifyTaskCardsAreClickable test passed.");
    }

    //********************************************************************************************************//
    // Verify Task Card Count Matches Displayed Count
    //********************************************************************************************************//

    @Test(priority = 5)
    public void verifyTaskCardCount() {
        logger.info("Starting verifyTaskCardCount test...");
        WebElement taskCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(@class, 'font-medium text-lg flex items-start flex-col')]")));
        String taskCountText = taskCountElement.getText().replaceAll("[^0-9]", "");
        int displayedTaskCount = Integer.parseInt(taskCountText);
        logger.info("Displayed Task Count: " + displayedTaskCount);

        List<WebElement> taskCards = driver.findElements(By.cssSelector("a.w-full.inline-block[href*='/tasks/']"));
        int actualTaskCount = taskCards.size();
        logger.info("Actual Task Count: " + actualTaskCount);

        Assert.assertEquals(actualTaskCount, displayedTaskCount, "The number of task cards does not match the count displayed in the 'Tasks' title.");
        logger.info("verifyTaskCardCount test passed.");
    }

    //********************************************************************************************************//
    // Verify Search Functionality In The Template Creation Flow
    //********************************************************************************************************//

    @Test(priority = 6)
    public void verifyCompleteTemplateCreationFlow() {
        logger.info("Starting verifyCompleteTemplateCreationFlow test...");
        WebElement createTaskButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Create Task')]")));
        createTaskButton.click();
        logger.info("Create Task button clicked.");

        WebElement createFromTemplateOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-key='template']")));
        createFromTemplateOption.click();
        logger.info("Create from Template option clicked.");

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search for templates, topics, goals...']")));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box is not displayed in the 'Create from Template' section.");
        logger.info("Search box in 'Create from Template' section is displayed.");

        List<WebElement> templates = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.relative.w-full.inline-flex.tap-highlight-transparent.flex-row.items-center")));
        for (WebElement template : templates) {
            Assert.assertTrue(template.isEnabled(), "Template is not clickable: " + template.getText());
            logger.info("Verified template is clickable: " + template.getText());
        }

        WebElement firstTemplate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//h3[@class='font-bold text-xs md:text-lg'][normalize-space()='Opening Checklist'])[1]")));
        firstTemplate.click();
        logger.info("First template selected.");

        WebElement useThisTemplateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Use This Template')]")));
        Assert.assertTrue(useThisTemplateButton.isDisplayed(), "Use This Template button is not displayed.");
        Assert.assertTrue(useThisTemplateButton.isEnabled(), "Use This Template button is not clickable.");
        logger.info("Use This Template button is displayed and clickable.");

        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Close')]")));
        closeButton.click();
        logger.info("Close button clicked to exit the template creation flow.");
    }

    //********************************************************************************************************//
    // Verify New Task And Create From Template Options Are Clickable
    //********************************************************************************************************//

    @Test(priority = 7)
    public void verifyCompleteNewTaskCreationFlow() {
        logger.info("Starting verifyCompleteNewTaskCreationFlow test...");
        WebElement createTaskButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/main[1]/div[1]/header[1]/div[2]/button[1]")));
        createTaskButtonElement.click();
        logger.info("Create Task button clicked.");

        WebElement newTaskButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-key='new' and contains(@class, 'flex') and contains(@class, 'w-full')]")));
        Assert.assertTrue(newTaskButtonElement.isEnabled(), "New Task Button is not enabled on the screen.");
        newTaskButtonElement.click();
        logger.info("New Task button clicked.");

        WebElement nameTaskENElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='relative w-full inline-flex tap-highlight-transparent shadow-sm px-3 border-medium border-default-200 data-[hover=true]:border-default-400 group-data-[focus=true]:border-default-foreground min-h-10 rounded-medium flex-col items-start justify-center gap-0 transition-background !duration-150 transition-colors motion-reduce:transition-none h-14 py-2'])[1]")));
        Assert.assertTrue(nameTaskENElement.isEnabled(), "Name Task EN Section is not enabled on the screen.");

        WebElement nameTaskARElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='relative w-full inline-flex tap-highlight-transparent shadow-sm px-3 border-medium border-default-200 data-[hover=true]:border-default-400 group-data-[focus=true]:border-default-foreground min-h-10 rounded-medium flex-col items-start justify-center gap-0 transition-background !duration-150 transition-colors motion-reduce:transition-none h-14 py-2'])[2]")));
        Assert.assertTrue(nameTaskARElement.isEnabled(), "Name Task AR Section is not enabled on the screen.");

        WebElement taskDescriptionENElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='relative w-full inline-flex tap-highlight-transparent shadow-sm px-3 border-medium border-default-200 data-[hover=true]:border-default-400 group-data-[focus=true]:border-default-foreground min-h-10 rounded-medium flex-col items-start justify-center gap-0 transition-background !duration-150 transition-colors motion-reduce:transition-none h-14 py-2'])[3]")));
        Assert.assertTrue(taskDescriptionENElement.isEnabled(), "Task Description EN Section is not enabled on the screen.");

        WebElement taskDescriptionARElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='relative w-full inline-flex tap-highlight-transparent shadow-sm px-3 border-medium border-default-200 data-[hover=true]:border-default-400 group-data-[focus=true]:border-default-foreground min-h-10 rounded-medium flex-col items-start justify-center gap-0 transition-background !duration-150 transition-colors motion-reduce:transition-none h-14 py-2'])[4]")));
        Assert.assertTrue(taskDescriptionARElement.isEnabled(), "Task Description AR Section is not enabled on the screen.");

        WebElement geofenceToggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.group.relative.touch-none.tap-highlight-transparent.w-full.inline-flex.flex-row-reverse")));
        Assert.assertTrue(geofenceToggleButton.isEnabled(), "Geofence toggle button is not clickable.");
        logger.info("Geofence toggle button is clickable.");

        WebElement createTaskButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'z-0 group relative inline-flex items-center justify-center box-border') and contains(text(), 'Create Task')]")));
        Assert.assertTrue(createTaskButton.isDisplayed(), "Create Task button is not displayed.");

        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'z-0 group relative inline-flex items-center justify-center box-border') and contains(text(), 'Close')]")));
        Assert.assertTrue(closeButton.isDisplayed(), "Close button is not displayed.");
        Assert.assertTrue(closeButton.isEnabled(), "Close button is not clickable.");
        logger.info("Create Task flow verified and completed successfully.");
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
