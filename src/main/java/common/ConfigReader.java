package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * Helper class to read the key=value pairs from the configuration.properties file
 */
public class ConfigReader {
    private static ThreadLocal<Properties> properties = new ThreadLocal<>();

    private static String PATH = "src/main/resources/configuration.properties";

    private static Properties getPropertiesFromFile() {
        var prop = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PATH));
            try {
                prop.load(reader);
                reader.close();
            } catch (IOException exception) {
                TestBase.failTest(String.format("Could not read or close the configuration file with the path %s", PATH));
            }
        } catch (FileNotFoundException exception) {
            TestBase.failTest(String.format("Could not find configuration file with the path %s", PATH));
        }
        return prop;
    }

    public static void setProperties() {
        properties.set(getPropertiesFromFile());
    }

    public static Properties getProperties() {
        if (properties.get() == null) {
            setProperties();
        }
        return properties.get();
    }

    public String getPetStoreUrl() {
        return getProperty("pet.store.url");
    }

    public String getProperty(String key) {
        var value = getProperties().getProperty(key);
        if (value == null) {
            throw new RuntimeException(String.format("%s not specified in configuration.properties", key));
        }
        return value;
    }
}
