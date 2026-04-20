package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import configReader.ObjectReader;
import java.time.Duration;

/**
 * Page Object class for the Flights Page.
 * Encapsulates locators and actions related to booking flights.
 */
public class FlightsPage {
     private WebDriver driver;       // WebDriver instance to interact with the browser
     private ObjectReader obr;       // Utility to read locators from properties file

    // Locators for flight booking form fields
     private By tripType, passengers;
     private By departureCity, departureMonth, departureDay;
     private By arrivalCity, arrivalMonth, arrivalDay;
     private By airline, continueBtn;

    /**
     * Constructor initializes locators using ObjectReader.
     * This ensures locators are externalized in a properties file,
     * making them easy to maintain if the UI changes.
     */
    public FlightsPage(WebDriver driver) throws Exception {
        this.driver = driver;
        obr = new ObjectReader();
        tripType        = By.name(obr.getProperty("flights.tripType.round")); // round trip radio button
        passengers      = By.name(obr.getProperty("flights.passengers"));     // passengers dropdown
        departureCity   = By.name(obr.getProperty("flights.departureCity"));  // departure city dropdown
        departureMonth  = By.name(obr.getProperty("flights.departureMonth")); // departure month dropdown
        departureDay    = By.name(obr.getProperty("flights.departureDay"));   // departure day dropdown
        arrivalCity     = By.name(obr.getProperty("flights.arrivalCity"));    // arrival city dropdown
        arrivalMonth    = By.name(obr.getProperty("flights.arrivalMonth"));   // arrival month dropdown
        arrivalDay      = By.name(obr.getProperty("flights.arrivalDay"));     // arrival day dropdown
        airline         = By.name(obr.getProperty("flights.airline"));        // airline dropdown
        continueBtn     = By.name(obr.getProperty("flights.continue"));       // continue button
    }

    // Select round trip option
    public void selectRoundTrip() { 
    	driver.findElement(tripType).click(); 
    }

    // Select number of passengers
    public void selectPassengers(String count) {
    	driver.findElement(passengers).sendKeys(count);
    }

    // Select departure details
    public void selectDeparture(String city, String month, String day) {
        driver.findElement(departureCity).sendKeys(city);
        driver.findElement(departureMonth).sendKeys(month);
        driver.findElement(departureDay).sendKeys(day);
    }

    // Select arrival details
    public void selectArrival(String city, String month, String day) {
        driver.findElement(arrivalCity).sendKeys(city);
        driver.findElement(arrivalMonth).sendKeys(month);
        driver.findElement(arrivalDay).sendKeys(day);
    }

    // Select airline
    public void selectAirline(String name) { 
    	    driver.findElement(airline).sendKeys(name); 
    }

    /**
     * Submits the booking form.
     * Uses explicit wait to ensure the Continue button is clickable,
     * scrolls it into view, and clicks via JavaScript to avoid interception issues.
     */
    public void submitBooking() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    /**
     * Retrieves the booking result message displayed after submission.
     * Waits for a <b><font> element that contains the outcome text.
     */
    public WebElement getResultMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//b/font") // flexible locator for result message
        ));
    }
}
