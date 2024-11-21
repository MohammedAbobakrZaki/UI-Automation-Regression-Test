package TestWebsiteFlows;

import RemotelyFlows.GoToSites;
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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SitesTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_WAIT_TIME = 5;
    private static final Logger logger = Logger.getLogger(SitesTest.class.getName());

    //********************************************************************************************************//
    // Setup Browser And Login
    //********************************************************************************************************//

    @BeforeTest
    public void setUp() {
        logger.info("Setting up the browser and logging into the website...");
        driver = LoginToWebsite.loginToRemotelyStore("https://app.remotely.store/", "bolast@gmail.com", "123456");
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));

        logger.info("Navigating to the Sites section...");
        GoToSites.navigateToSites(driver);
        logger.info("Setup and navigation to Sites completed successfully.");
    }

    //********************************************************************************************************//
    // Verify New Site Button, Sites Title, Search Box, Site Cards, Export Report Button
    //********************************************************************************************************//

    @Test(priority = 1)
    public void verifyNewSiteButtonIsClickable() {
        logger.info("Starting verifyNewSiteButtonIsClickable test...");
        WebElement newSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/main[1]/div[1]/header[1]/div[2]/button[1]")));
        Assert.assertTrue(newSiteButton.isEnabled(), "New Site Button is not enabled on the screen.");
        logger.info("verifyNewSiteButtonIsClickable test passed.");
    }

    @Test(priority = 2)
    public void verifySitesTitleIsDisplayed() {
        logger.info("Starting verifySitesTitleIsDisplayed test...");
        WebElement sitesTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/main[1]/div[1]/header[1]/div[1]/div[1]/h1[1]")));
        Assert.assertTrue(sitesTitle.isEnabled(), "Sites Title is not enabled on the screen.");
        logger.info("verifySitesTitleIsDisplayed test passed.");
    }

    @Test(priority = 3)
    public void verifySearchBoxIsDisplayed() {
        logger.info("Starting verifySearchBoxIsDisplayed test...");
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
        Assert.assertTrue(searchBox.isEnabled(), "Search Box is not enabled on the screen.");
        logger.info("verifySearchBoxIsDisplayed test passed.");
    }

    @Test(priority = 4)
    public void verifyExportReportButtonIsClickable() {
        logger.info("Starting verifyExportReportButtonIsClickable test...");
        WebElement exportReportButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/button[1]")));
        Assert.assertTrue(exportReportButton.isEnabled(), "Export Report button is not enabled on the screen.");
        logger.info("verifyExportReportButtonIsClickable test passed.");
    }

    @Test(priority = 5)
    public void verifySiteCardsAreClickable() {
        logger.info("Starting verifySiteCardsAreClickable test...");
        List<WebElement> siteCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a.w-full.inline-block[href*='/sites/']")));
        logger.info("Number of site cards found: " + siteCards.size());

        for (WebElement siteCard : siteCards) {
            Assert.assertTrue(siteCard.isEnabled(), "Site card is not clickable: " + siteCard.getText());
            logger.info("Verified site card is clickable: " + siteCard.getAttribute("href"));
        }
        logger.info("verifySiteCardsAreClickable test passed.");
    }

    //********************************************************************************************************//
    // Verify Sites Count Matches Displayed Count
    //********************************************************************************************************//

    @Test(priority = 6)
    public void verifySiteCountMatchesDisplayedCount() {
        logger.info("Starting verifySiteCountMatchesDisplayedCount test...");
        WebElement siteCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='font-medium text-lg flex items-start flex-col'])[1]")));
        String siteCountText = siteCountElement.getText();
        int expectedSiteCount = Integer.parseInt(siteCountText.replaceAll("[^0-9]", ""));
        logger.info("Expected site count: " + expectedSiteCount);

        WebElement siteContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='grid grid-cols-1 lg:grid-cols-3 gap-4 px-4'])[1]")));
        List<WebElement> siteCards = siteContainer.findElements(By.xpath(".//div[contains(@class, 'flex flex-col relative overflow-hidden')]"));
        int actualSiteCount = siteCards.size();
        logger.info("Actual site count: " + actualSiteCount);

        Assert.assertEquals(actualSiteCount, expectedSiteCount, "Displayed site count does not match the actual count of sites.");
        logger.info("verifySiteCountMatchesDisplayedCount test passed.");
    }

    //********************************************************************************************************//
    // Verify Dropdown Options And Related Elements
    //********************************************************************************************************//

    @Test(priority = 7)
    public void verifyDropdownOptionsAreClickable() {
        logger.info("Starting verifyDropdownOptionsAreClickable test...");
        WebElement newSiteButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[1]/div[1]/main[1]/div[1]/header[1]/div[2]/button[1]")));
        newSiteButtonElement.click();

        WebElement siteNameSelection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")));
        Assert.assertTrue(siteNameSelection.isEnabled(), "Site Name Selection is not enabled on the screen.");
        logger.info("Site Name Selection is enabled.");

        WebElement projectSelection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/form[1]/div[1]/div[2]/div[1]/div[3]/div[2]/button[1]/div[1]")));
        Assert.assertTrue(projectSelection.isEnabled(), "Project Selection is not enabled on the screen.");
        logger.info("Project Selection is enabled.");

        WebElement geolocation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/form[1]/div[1]/div[2]/div[1]/div[5]/div[1]/div[1]/div[1]/input[1]")));
        Assert.assertTrue(geolocation.isEnabled(), "Geolocation is not enabled on the screen.");
        logger.info("Geolocation is enabled.");

        WebElement createSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/form[1]/div[1]/div[3]/button[1]")));
        Assert.assertTrue(createSiteButton.isEnabled(), "Create Site Button is not enabled on the screen.");
        logger.info("Create Site Button is enabled.");

        WebElement siteTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/main[1]/div[1]/div[3]/div[1]/form[1]/div[1]/div[2]/div[1]/div[2]/div[2]/button[1]/div[1]")));
        siteTypeDropdown.click();

        List<String> optionsToCheck = Arrays.asList("SITE", "TEAM", "BRANCH", "STATION", "STORE", "LOCATION");
        for (String optionKey : optionsToCheck) {
            WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@value='" + optionKey + "']")));
            Assert.assertTrue(optionElement.isDisplayed(), "Option '" + optionKey + "' is not displayed on the screen.");
            Assert.assertTrue(optionElement.isEnabled(), "Option '" + optionKey + "' is not clickable on the screen.");
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
