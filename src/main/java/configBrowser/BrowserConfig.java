package configBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * This class is responsible for browser setup.
 * It provides methods to launch Chrome or Edge and return a WebDriver instance.
 */
public class BrowserConfig {
    WebDriver driver = null; // Holds the browser instance

    /*
     Opens a new Chrome browser window.
     The window is maximized so elements are easier to interact with.
     @return WebDriver for Chrome
     */
    public WebDriver getChromeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    /*
     Opens a new Edge browser window.
     The window is maximized so elements are easier to interact with.
     @return WebDriver for Edge
     */
    public WebDriver getEdgeDriver() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
