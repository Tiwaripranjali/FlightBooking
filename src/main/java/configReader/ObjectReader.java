package configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ObjectReader class
 * Responsible for loading the Object Repository (properties file)
 * and providing access to locators/config values by key.
 */
public class ObjectReader {
    private Properties pro; // Holds all key-value pairs from the properties file

    /*
     Constructor
     Loads the properties file into memory so tests can fetch locators/URLs.
     @throws IOException if the file path is invalid or cannot be read
     */
    public ObjectReader() throws IOException {
        // Path to the properties file that contains locators and config values
        String objectFilePath = "src/main/resources/ObjectRepository/Object.properties";

        // Open the file as an input stream
        FileInputStream fis = new FileInputStream(objectFilePath);

        // Create a Properties object and load the file contents
        pro = new Properties();
        pro.load(fis);
    }

    /*
       Fetches the value for a given key from the properties file.
       Example: getProperty("login.username") → returns "userName"
      */
    public String getProperty(String key) {
        return pro.getProperty(key);
    }
}
