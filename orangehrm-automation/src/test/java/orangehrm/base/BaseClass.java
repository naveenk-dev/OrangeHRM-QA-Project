package orangehrm.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import orangehrm.utils.ConfigReader;

public class BaseClass 
{
    protected WebDriver driver;
    protected ConfigReader config;

    @BeforeMethod
    public void setUp() 
    {
        config = new ConfigReader();

        String browser = config.getProperty("browser");

        if (browser == null) 
        {
            throw new RuntimeException("Browser value is not mentioned in config file");
        }

        switch (browser.toLowerCase()) 
        {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(config.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() 
    {
        if (driver != null) 
        {
            driver.quit();
        }
    }
}