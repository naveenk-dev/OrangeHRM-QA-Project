package orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


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
