package utility;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import configBrowser.BrowserConfig;

/**
 * Utility class for browser selection.
 * Prompts the user to choose Chrome or Edge at runtime,
 * then returns the corresponding WebDriver instance.
 */
public class Utils {
     BrowserConfig brc;     // Reference to BrowserConfig for driver setup
     WebDriver driver1;     // Holds the chosen WebDriver instance

    /**
     * Prompts the user to select a browser and initializes it.
     * @return WebDriver instance for the chosen browser
     */
    /*public WebDriver getBrowser() {
        brc = new BrowserConfig();

        // Scanner is used to read user input from the console
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Select the Browser:\n1) Chrome\n2) Edge");
            String choice = sc.nextLine().trim(); // read and trim input

            // Switch statement handles user choice
            switch (choice) {
                case "1":
                case "Chrome":
                    driver1 = brc.getChromeDriver(); // launch Chrome
                    break;
                case "2":
                case "Edge":
                    driver1 = brc.getEdgeDriver();   // launch Edge
                    break;
                default:
                    // If input is invalid, default to Chrome
                    System.out.println("Invalid choice, defaulting to Chrome");
                    driver1 = brc.getChromeDriver();
            }
        }

        return driver1; // return the intialized WebDriver
    }
    */
     public WebDriver getChromeBrowser() {
         brc = new BrowserConfig();
         driver1 = brc.getChromeDriver(); // launch Chrome directly
         return driver1;
     }
    public String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        // Updated path to store screenshots
        String path = System.getProperty("user.dir") 
                      + "/flightbookingTest.FlightBookingTest/Screenshots/" 
                      + screenshotName + ".png";

        File dest = new File(path);
        FileUtils.copyFile(src, dest);

        return path;
    }
  
}