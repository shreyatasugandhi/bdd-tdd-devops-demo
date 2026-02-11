package BDDtestcases;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BDDLoginTest {

    public static WebDriver driver;

    @Given("A {string} browser initialized")
    public void initiateBrowser(String browser) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Accept SSL certificates automatically
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @And("Open application {string}")
    public void openApp(String url) {
        driver.get(url);
    }

    @And("I click on Signin button on home page")
    public void clickOnSignInButton() {
        driver.findElement(By.id("signin_button")).click();
    }

    @Given("I am on login page")
    public void i_am_on_login_page() {
        assertTrue(driver.getTitle().contains("Log in"),
                "Not on Login page");
    }

    @When("I enter user id as {string} and password as {string}")
    public void i_enter_credentials(String uname, String pass) {

        driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys(uname);

        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.id("user_password")).sendKeys(pass);
    }

    @And("Click on Signin button")
    public void click_on_Signin_button() {
        driver.findElement(By.name("submit")).click();
    }

    @Then("I validate that I am able to log into the application and I land on Account summery page")
    public void validateSuccess() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(driver -> 
            driver.getCurrentUrl().contains("account-summary")
        );

        boolean isOnSummaryPage = 
            driver.getCurrentUrl().contains("account-summary");

        assertTrue(isOnSummaryPage,
            "Login failed – Not redirected to Account Summary page. Current URL: "
            + driver.getCurrentUrl());
    }

    @Then("I validate that I get an error message {string}")
    public void validateError(String expectedMsg) {

        String actualMsg =
                driver.findElement(By.className("alert-error")).getText();

        assertTrue(actualMsg.contains(expectedMsg),
                "Expected error message not displayed");
    }

    @After
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
