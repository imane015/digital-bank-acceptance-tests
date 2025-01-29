package co.wedevx.digitalbank.automation.ui.utils;

// build a logic that reads the config file(properties file)

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static FileInputStream input;

    //static initializer run the block once foe the whole project
    //instance initializer run the block once for every object creation from the class
    static {


        //filepath --> the directory of your properties file

        String filepath = "src/test/resources/properties/digitalbank.properties";

        // this is a class to enable you to read files
        try {
            input = new FileInputStream(filepath);
            properties = new Properties();
            properties.load(input);

        } catch (IOException e) {
            System.out.println("File not fount" + e.getMessage());
        }
        finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);
    }
}
