package orangehrm.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage
{
  public DashboardPage(WebDriver driver) 
  {
      super(driver);
  }
  
  private By dashboardSideHeader = By.xpath("//span[text()='Dashboard']");
  
  public boolean isDashboardPageDisplayed()
  {
	  return wait.until(ExpectedConditions
			  .visibilityOfElementLocated(dashboardSideHeader))
			  .isDisplayed();
  }
  
  
  
  
}
