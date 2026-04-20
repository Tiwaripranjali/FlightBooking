package flightbookingTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import configReader.ObjectReader;
import utility.Utils;
import webpages.*;

public class FlightBookingTest {
    // Core objects: driver controls the browser, util handles screenshots/setup,
    // obr reads config, and lp/hp/fp are page objects for login, home, and flights.
    private WebDriver driver;
    private Utils util;
    private ObjectReader obr;
    private LoginPage lp;
    private HomePage hp;
    private FlightsPage fp;

    @BeforeTest
    public void setUp() throws Exception {
        // Launch browser and navigate to landing page
        util = new Utils();
        driver = Utils.getBrowser();
        obr = new ObjectReader();
        driver.get(obr.getProperty("landingPage.URL"));
    }

    @Test(priority=1)
    public void loginUnsuccessfulTest() throws Exception {
        // Try logging in with wrong credentials
        lp = new LoginPage(driver);
        lp.login("wrongUser", "wrongPass");
        Thread.sleep(2000);

        try {
            // Force a failure by checking for a title that won't exist
            Assert.assertTrue(driver.getTitle().contains("Nonexistent Title"));
        } catch (AssertionError e) {
            // On failure, capture screenshot of the page
            util.captureScreenshot(driver, "LoginUnsuccessfulPage");
            System.out.println("LoginUnsuccessfulTest failed. Screenshot saved.");
            throw e;
        }

        // Print the actual error message shown on the page
        WebElement errorMsg = driver.findElement(
            By.xpath("//font[contains(text(),'Enter your userName and password correct')]"));
        System.out.println("Page displayed error message: " + errorMsg.getText());
    }

    @Test(priority=2)
    public void loginSuccessfulTest() throws Exception {
        // Reload landing page and login with correct credentials
        driver.get(obr.getProperty("landingPage.URL"));
        lp = new LoginPage(driver);
        lp.login("admin", "admin");
        Thread.sleep(2000);

        try {
            // Validate that login redirected away from the login page
            Assert.assertFalse(driver.getCurrentUrl().contains("index.php"));
        } catch (AssertionError e) {
            util.captureScreenshot(driver, "LoginSuccessfulPageFail");
            System.out.println("LoginSuccessfulTest failed. Screenshot saved.");
            throw e;
        }

        // Print the welcome message text from the page
        WebElement welcomeMsg = driver.findElement(By.xpath("//h3"));
        System.out.println("Page displayed welcome message: " + welcomeMsg.getText());
    }

    @Test(priority=3)
    public void navigateToFlightsTest() throws Exception {
        // From home page, click on Flights
        hp = new HomePage(driver);
        hp.goToFlights();

        try {
            // Validate that the Flights page opened
            Assert.assertTrue(driver.getTitle().contains("Find a Flight"));
        } catch (AssertionError e) {
            util.captureScreenshot(driver, "FlightsPageFail");
            System.out.println("NavigateToFlightsTest failed. Screenshot saved.");
            throw e;
        }

        // Print the heading text from Flights page
        WebElement heading = driver.findElement(By.cssSelector("h1, h2, h3, font"));
        System.out.println("Flights page heading: " + heading.getText());
    }

    @Test(priority=4)
    public void flightBookingWorkflowTest() throws Exception {
        // Fill out booking form step by step
        fp = new FlightsPage(driver);
        fp.selectRoundTrip();
        fp.selectPassengers("2");
        fp.selectDeparture("New York","April","10");
        fp.selectArrival("London","April","20");
        fp.selectAirline("Unified Airlines");
        fp.submitBooking();

        // Capture screenshot of workflow completion
        util.captureScreenshot(driver, "BookingWorkflow");
        System.out.println("Flight booking workflow executed successfully");
    }

    @Test(priority=5)
    public void resultValidationTest() throws Exception {
        // Capture booking confirmation message element
        WebElement resultMessage = fp.getResultMessage();
        String resultMsg = resultMessage.getText().trim();

        try {
            // Validate that result message is present
            Assert.assertFalse(resultMsg.isEmpty());
        } catch (AssertionError e) {
            util.captureScreenshot(driver, "ResultPageFail");
            System.out.println("ResultValidationTest failed. Screenshot saved.");
            throw e;
        }

        // Print the actual booking result message text
        System.out.println("Booking result message: " + resultMsg);
    }

    @AfterTest
    public void tearDown() {
        // Close browser after all tests
        driver.quit();
        System.out.println("Browser closed. All tests completed.");
    }
}
