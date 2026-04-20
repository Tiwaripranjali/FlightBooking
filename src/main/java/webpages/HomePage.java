package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import configReader.ObjectReader;

/**
 * Page Object class for the Home Page.
 * Encapsulates locators and actions related to navigation from the home page.
 */
public class HomePage {
    private WebDriver driver;       // WebDriver instance to interact with the browser
    private ObjectReader obr;       // Utility to read locators from properties file

    // Locator for the "Flights" menu link
    private By flightsMenu;

    /**
     * Constructor initializes the locator using ObjectReader.
     * This ensures the locator is externalized in a properties file,
     * making it easy to maintain if the UI changes.
     */
    public HomePage(WebDriver driver) throws Exception {
        this.driver = driver;
        obr = new ObjectReader();
        flightsMenu = By.linkText(obr.getProperty("home.flightsMenu")); // locator for Flights menu link
    }

    // Navigates to the Flights page by clicking the Flights menu link.
    public void goToFlights() {
        driver.findElement(flightsMenu).click();
    }
}
