package TestWebsiteFlows;

import RemotelyFlows.GoToMembers;
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

public class MembersTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_WAIT_TIME = 8;
    private static final Logger logger = Logger.getLogger(MembersTest.class.getName());

    //********************************************************************************************************//
    // Setup Browser And Login
    //********************************************************************************************************//

    @BeforeTest
    public void setUp() {
        logger.info("Setting up the browser and logging into the website...");
        driver = LoginToWebsite.loginToRemotelyStore("https://app.remotely.store/", "bolast@gmail.com", "123456");
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));

        logger.info("Navigating to the Members section...");
        GoToMembers.navigateToSites(driver);
        logger.info("Setup and navigation completed successfully.");
    }

    //********************************************************************************************************//
    // Verify Member Count Display
    //********************************************************************************************************//

    @Test(priority = 1)
    public void verifyMemberCardCount() {
        logger.info("Starting verifyMemberCardCount test...");

        // Retrieve the displayed member count from the "My Members" title
        WebElement memberCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//p[@class='font-medium text-lg flex items-start flex-col'])[1]")));
        String memberCountText = memberCountElement.getText().replaceAll("[^0-9]", "");
        int displayedMemberCount = Integer.parseInt(memberCountText);
        logger.info("Displayed Member Count: " + displayedMemberCount);

        // Count the actual number of member cards displayed
        List<WebElement> memberCards = driver.findElements(By.cssSelector("a.w-full.inline-block[href*='/members/']"));
        int actualMemberCount = memberCards.size();
        logger.info("Actual Member Count: " + actualMemberCount);

        // Validate that the actual count matches the displayed count
        Assert.assertEquals(actualMemberCount, displayedMemberCount, "The number of member cards does not match the count displayed in the 'My Members' title.");
        logger.info("verifyMemberCardCount test passed: Member counts match.");
    }

    //********************************************************************************************************//
    // Verify Each Member Card Is Clickable
    //********************************************************************************************************//

    @Test(priority = 2)
    public void verifyMemberCardsAreClickable() {
        logger.info("Starting verifyMemberCardsAreClickable test...");

        // Locate all member cards
        List<WebElement> memberCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("a.w-full.inline-block[href*='/members/']")));
        logger.info("Number of member cards found: " + memberCards.size());

        // Verify each member card is clickable
        for (WebElement memberCard : memberCards) {
            Assert.assertTrue(memberCard.isEnabled(), "Member card is not clickable: " + memberCard.getText());
            logger.info("Verified member card is clickable: " + memberCard.getAttribute("href"));
        }
        logger.info("verifyMemberCardsAreClickable test passed: All member cards are clickable.");
    }

    //********************************************************************************************************//
    // Verify "Invite New Member" Button
    //********************************************************************************************************//

    @Test(priority = 3)
    public void verifyInviteNewMemberButtonIsClickable() {
        logger.info("Starting verifyInviteNewMemberButtonIsClickable test...");

        // Locate the "Invite New Member" button
        WebElement inviteNewMemberButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Invite New Member')]")));

        // Verify that the "Invite New Member" button is displayed and clickable
        Assert.assertTrue(inviteNewMemberButton.isDisplayed(), "Invite New Member button is not displayed.");
        Assert.assertTrue(inviteNewMemberButton.isEnabled(), "Invite New Member button is not clickable.");
        logger.info("Invite New Member button is displayed and clickable.");

        // Click the "Invite New Member" button
        inviteNewMemberButton.click();
        logger.info("Invite New Member button clicked successfully.");
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
