package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import configReader.ObjectReader;

/**
 * Page Object class for the Login Page.
 * Encapsulates locators and actions related to logging in.
 */
public class LoginPage {
   private WebDriver driver;       // WebDriver instance to interact with the browser
   private ObjectReader obr;       // Utility to read locators from properties file

    // Locators for login page elements
    private By username;
    private By password;
    private By submit;

    /**
     * Constructor initializes locators using ObjectReader.
     * This ensures locators are externalized in a properties file,
     * making them easy to maintain if the UI changes.
     */
    public LoginPage(WebDriver driver) throws Exception {
        this.driver = driver;
        obr = new ObjectReader();
        username = By.name(obr.getProperty("login.username")); // locator for username field
        password = By.name(obr.getProperty("login.password")); // locator for password field
        submit   = By.name(obr.getProperty("login.submit"));   // locator for login button
    }

    /**
     * Performs login action.
     * @param user - username to enter
     * @param pass - password to enter
     *
     * Steps:
     * 1. Enter username
     * 2. Enter password
     * 3. Click submit button
     */
    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(submit).click();
    }
}
