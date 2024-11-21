package RemotelyFlows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class onBoarding {



    public static void main(String[] args) {

        // Variables used in this code :

//        String email = "AutomationBO@Test.com";
//        String mobile = "475256836";
//        String name = "Test Automation Flow";
//        String password = "123456";
//
//        String businessName = "Automation Test Activities";

        String email = "AunOoo@Test.com";
        String mobile = "4752536";
        String name = "Teoow";
        String password = "123456";

        String businessName = "Atttt";




        // ***********************************************************************************************************************  //
        // ***********************************************************************************************************************  //



        // Set up EdgeDriver using WebDriverManager and launch Edge browser

        WebDriverManager.edgedriver().setup();
        EdgeDriver driver = new EdgeDriver(); // Instantiate EdgeDriver for Edge browser
        driver.manage().window().maximize(); // Maximize the browser window for better visibility

        // Set an explicit wait timeout of 3 seconds for locating elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));



        // ***********************************************************************************************************************  //
        // ***********************************************************************************************************************  //




        // OnBoarding New Business --> create an account page


        // Navigate to the specified URL
        driver.navigate().to("https://app.remotely.store/onboarding");


        // Wait for the name field to become visible and locate it
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        nameField.sendKeys(name);

        // Wait for the mobile field to become visible and locate it
        WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("mobile")));
        mobileField.sendKeys(mobile);

        // Wait for the email field to become visible and locate it
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys(email);

        // Wait for the password field to become visible and locate it
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys(password);

        // Wait for the create Account Button to become visible and click
        WebElement createAccountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div/form/div[3]/button")));
        createAccountButton.click();



        // ***********************************************************************************************************************  //
        // ***********************************************************************************************************************  //



        // OnBoarding New Business --> Business Information page


        // Wait for the business Name field to become visible and locate it
        WebElement businessNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        businessNameField.sendKeys(businessName);


        // Wait for the create Business Button to become visible and locate it
        WebElement createBusinessButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*='inline-flex'][class*='items-center'][class*='bg-primary']")));
        createBusinessButton.click();




        // ***********************************************************************************************************************  //
        // ***********************************************************************************************************************  //



        // OnBoarding New Business --> GoTo Dashboard page


        // Wait for the GoTo Dashboard Button to become visible and locate it
        WebElement GoToDashboardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button'][normalize-space()='Go to Dashboard']")));
        GoToDashboardButton.click();



        System.out.println("\nEmail : "+email);
        System.out.println("Password : "+password);
        System.out.println("Business name : "+businessName);


        // Close the browser and end the session
        driver.quit();


    }
}

