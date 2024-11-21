package TestWebsiteFlows;

import RemotelyFlows.LoginToWebsite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public class DashboardTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_WAIT_TIME = 10;
    private static final Logger logger = Logger.getLogger(DashboardTest.class.getName());
    private static final String URL = "https://app.remotely.store/";
    private static final String USERNAME = "bolast@gmail.com";
    private static final String PASSWORD = "123456";

    @BeforeTest
    public void setUp() {
        logger.info("Opening the browser and logging in...");
        driver = LoginToWebsite.loginToRemotelyStore(URL, USERNAME, PASSWORD);
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
        logger.info("Browser opened and login performed successfully.");
    }

    private WebElement waitForElement(By locator) {
        logger.info("Waiting for element: " + locator.toString());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Test(priority = 1)
    public void verifyLogin() {
        logger.info("Starting verifyLogin test...");
        WebElement dashboardTitle = waitForElement(By.xpath("//h1[contains(@class,'-mt-1 text-xl font-bold text-white lg:text-2xl')]"));
        Assert.assertTrue(dashboardTitle.isDisplayed(), "Dashboard Title is not displayed.");
        logger.info("verifyLogin test passed: Dashboard Title is displayed.");
    }

    @Test(priority = 2)
    public void verifyEmailPresence() {
        logger.info("Starting verifyEmailPresence test...");
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'bolast@gmail.com')]")));
        Assert.assertTrue(emailElement.isDisplayed() && emailElement.isEnabled(), "Email element is either not displayed or not clickable.");
        logger.info("verifyEmailPresence test passed: Email element is displayed and clickable.");
    }

    @Test(priority = 3)
    public void validateDashboardElements() {
        logger.info("Starting validateDashboardElements test...");
        String[] xpaths = {
                "(//div[@class='flex flex-col relative overflow-hidden text-foreground box-border bg-content1 outline-none data-[focus-visible=true]:z-10 data-[focus-visible=true]:outline-2 data-[focus-visible=true]:outline-focus data-[focus-visible=true]:outline-offset-2 shadow-none rounded-large transition-transform-background motion-reduce:transition-none h-[440px] print-scrollable border-2'])[1]",
                "(//div[@class='relative overflow-hidden text-foreground box-border bg-content1 outline-none data-[focus-visible=true]:z-10 data-[focus-visible=true]:outline-2 data-[focus-visible=true]:outline-focus data-[focus-visible=true]:outline-offset-2 shadow-none rounded-large transition-transform-background motion-reduce:transition-none flex flex-col h-[600px] md:h-[500px] lg:h-[440px] border-2'])[1]",
                "//body/div/div[@data-overlay-container='true']/div/div/main/div/div/div/div/div[3]/div[1]",
                "//body/div/div[@data-overlay-container='true']/div/div/main/div/div/div/div/div[4]/div[1]",
                "//body/div/div[@data-overlay-container='true']/div/div/main/div/div/div/div/div[5]/div[1]",
                "//body/div/div[@data-overlay-container='true']/div/div/main/div/div/div/div/div[6]/div[1]"
        };

        String[] messages = {
                "All Tasks Cart", "Overall Progress Cart", "Tasks Performance Cart",
                "Members Performance Cart", "Sites Performance Cart", "Projects Performance Cart"
        };

        for (int i = 0; i < xpaths.length; i++) {
            logger.info("Validating: " + messages[i]);
            WebElement element = waitForElement(By.xpath(xpaths[i]));
            Assert.assertTrue(element.isDisplayed(), messages[i] + " is not displayed.");
            logger.info(messages[i] + " is displayed.");
        }
        logger.info("validateDashboardElements test passed.");
    }

    @Test(priority = 4)
    public void validateFilterButtons() {
        logger.info("Starting validateFilterButtons test...");
        String[] buttonXpaths = {
                "//span[normalize-space()='History']", "//span[normalize-space()='Live']",
                "//button[.//p[contains(text(), 'All Projects')]]"
        };

        String[] buttonMessages = {
                "History Button", "Live Button", "All Projects Filter"
        };

        for (int i = 0; i < buttonXpaths.length; i++) {
            logger.info("Validating: " + buttonMessages[i]);
            WebElement button = waitForElement(By.xpath(buttonXpaths[i]));
            Assert.assertTrue(button.isDisplayed(), buttonMessages[i] + " is not displayed.");
            logger.info(buttonMessages[i] + " is displayed.");
        }
        logger.info("validateFilterButtons test passed.");
    }

    @Test(priority = 5)
    public void validateLeftMenuAndDashboardButtons() {
        logger.info("Starting validateLeftMenuAndDashboardButtons test...");
        String[] menuButtonXpaths = {
                "//h2[@class='text-gray-300 font-semibold block pt-8']", "//div[@id='menu-dashboard']",
                "//div[@id='menu-projects']", "//div[@id='menu-sites']", "//div[@id='menu-tasks']",
                "//div[@id='menu-members']", "(//div[@class='space-y-3'])[1]"
        };

        String[] menuButtonMessages = {
                "Left Menu Section", "Dashboard Button", "Projects Button",
                "Sites Button", "Tasks Button", "Members Button", "User Setting Button"
        };

        for (int i = 0; i < menuButtonXpaths.length; i++) {
            logger.info("Validating: " + menuButtonMessages[i]);
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(menuButtonXpaths[i])));
            Assert.assertTrue(button.isDisplayed() && button.isEnabled(), menuButtonMessages[i] + " is not clickable or not displayed.");
            logger.info(menuButtonMessages[i] + " is clickable and displayed.");
        }
        logger.info("validateLeftMenuAndDashboardButtons test passed.");
    }

    @Test(priority = 6)
    public void validateTaskChartTotals() {
        logger.info("Starting validateTaskChartTotals test...");
        WebElement totalTasksElement = waitForElement(By.xpath("(//span[@class='text-4xl md:text-5xl font-bold text-gray-700 dark:text-gray-50'])[1]"));
        int totalTasks = Integer.parseInt(totalTasksElement.getText());

        // Fetch individual task elements
        int doneTasks = Integer.parseInt(waitForElement(By.xpath("(//span[@class='text-3xl font-bold'][normalize-space()='0'])[1]")).getText());
        int missedTasks = Integer.parseInt(waitForElement(By.xpath("(//span[@class='text-3xl font-bold'][normalize-space()='0'])[2]")).getText());
        int progressTasks = Integer.parseInt(waitForElement(By.xpath("(//span[@class='text-3xl font-bold'][normalize-space()='0'])[3]")).getText());
        int nextTasks = Integer.parseInt(waitForElement(By.xpath("(//span[@class='text-3xl font-bold'][normalize-space()='3'])[1]")).getText());

        // Validate total tasks
        int sumOfTasks = doneTasks + missedTasks + progressTasks + nextTasks;
        Assert.assertEquals(sumOfTasks, totalTasks, "Total tasks do not match the sum of individual tasks.");
        logger.info("validateTaskChartTotals test passed: Total tasks match the sum of individual tasks.");
    }

    @Test(priority = 7)
    public void verifyTotalTaskCountInAllTasksSection() {
        logger.info("Starting verifyTotalTaskCountInAllTasksSection test...");

        // Locate the total task count element and get the expected count
        WebElement totalTasksElement = waitForElement(By.xpath("(//span[@class='text-4xl md:text-5xl font-bold text-gray-700 dark:text-gray-50'])[1]"));
        int expectedTaskCount = Integer.parseInt(totalTasksElement.getText());

        // CSS selector for the task cards
        By taskSelector = By.cssSelector("div.cursor-pointer.hover\\:shadow-xl.transition-shadow");

        // Initialize task counts
        int previousCount, currentCount = driver.findElements(taskSelector).size();

        // Start time to enforce a maximum wait period
        long startTime = System.currentTimeMillis();
        long maxWaitTime = 30000; // 30 seconds

        // Loop to keep loading more tasks until we stop seeing new tasks or reach max wait time
        do {
            previousCount = currentCount;
            List<WebElement> tasks = driver.findElements(taskSelector);

            if (!tasks.isEmpty()) {
                // Scroll to the last task element
                WebElement lastTask = tasks.get(tasks.size() - 1);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastTask);

                // Update the current task count and check if more tasks have loaded
                currentCount = driver.findElements(taskSelector).size();

                // Break the loop if the count hasn't increased or if max wait time is reached
                if (currentCount <= previousCount || System.currentTimeMillis() - startTime > maxWaitTime) {
                    break;
                }
            } else {
                // Break if no tasks are found (shouldn't happen if tasks are initially loaded)
                break;
            }
        } while (true);

        // Assert that the total number of tasks matches the expected count
        Assert.assertEquals(currentCount, expectedTaskCount, "Task count does not match the expected count.");
        logger.info("verifyTotalTaskCountInAllTasksSection test passed: Task count matches the expected count.");
    }



    @AfterTest
    public void tearDown() {
        logger.info("Closing the browser...");
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully.");
        }
    }
}
