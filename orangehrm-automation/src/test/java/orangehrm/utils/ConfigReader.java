package orangehrm.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader 
{
    Properties property;

    public ConfigReader() 
    {
        try 
        {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            property = new Properties();
            property.load(fis);
        } 
        catch (Exception e) 
        {
            System.out.println("Error loading config file");
        }
    }

    public String getProperty(String key) 
    {
        return property.getProperty(key);
    }
}