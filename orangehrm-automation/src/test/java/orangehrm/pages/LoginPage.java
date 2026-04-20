package orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage
{
    public LoginPage(WebDriver driver) 
    {
        super(driver);
    }
    
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.xpath("//button[@type ='submit']");
    
    private By profileDropdown = 
    		By.xpath("//span[@class='oxd-userdropdown-tab']");
    
    private By logoutButton = By.linkText("Logout");
    
    private By errorMessage = 
    		By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
    
    private By usernameError = 
    		By.xpath("//input[@name='username']/following::"
    				+ "span[text()='Required'][1]");
    
    private By passwordError = 
    		By.xpath("//input[@name='password']/following::"
    				+ "span[text()='Required'][1]");
    
    private By forgotPassword = 
    		By.xpath("//p[text()='Forgot your password? ']");
    private By resetPassword = By.xpath("//h6[text()='Reset Password']");
    
    private By resetPasswordUsername = 
    		By.xpath("//input[@name='username']");
    
    private By resetPasswordButton = 
    		By.xpath("//button[@type='submit']");
    
    private By resetPasswordMessage = 
    		By.xpath("//h6[text()='Reset Password link sent successfully']");
    
    public void login(String user, String psw) 
    {
        type(username, user);
        type(password, psw);
        click(loginButton);
    }
    
    public boolean isLoginFailed()
    {
        return driver.findElements(errorMessage).size() > 0;
    }
    
    public void logout()
    {
        click(profileDropdown);
        click(logoutButton);
    }
    
    public boolean isLoginSuccessful()
    {
        return isDisplayed(profileDropdown);
    }
       
    public String getErrorMessageText()
    {
        return getText(errorMessage);
    }
    
    public String getUsernameRequiredMessage()
    {
        return getText(usernameError);
    }
    
    public String getPasswordRequiredMessage()
    {
        return getText(passwordError);
    }
    
    public boolean isLogoutSuccessful()
    {
        return isDisplayed(username);
    }
    
    public void clickForgotPassword()
    {
        click(forgotPassword);
    }
    
    public void resetPassword(String username)
    {
    	type(resetPasswordUsername, username);
    	click(resetPasswordButton);
    }
    
    public boolean isResetPasswordPageDisplayed()
    {
        return isDisplayed(resetPassword);
    }
    
    public boolean isResetPasswordSuccessful()
    {
    	return isDisplayed(resetPasswordMessage);
    }
    
    
}