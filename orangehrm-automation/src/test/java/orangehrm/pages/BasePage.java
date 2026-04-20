package orangehrm.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage 
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void type(By locator, String value)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
        .sendKeys(value);
    }

    public String getText(By locator)
    {
        return wait.until(ExpectedConditions
        		.visibilityOfElementLocated(locator)).getText();
    }

    public boolean isDisplayed(By locator)
    {
        try
        {
            return wait
            		.until(ExpectedConditions
            		.visibilityOfElementLocated(locator))
            		.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public boolean isElementPresent(By locator) 
    {
        return driver.findElements(locator).size() > 0;
    }

    public String getCurrentPageUrl()
    {
        return driver.getCurrentUrl();
    }
}