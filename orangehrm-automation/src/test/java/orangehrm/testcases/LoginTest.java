package orangehrm.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangehrm.base.BaseClass;
import orangehrm.pages.DashboardPage;
import orangehrm.pages.LoginPage;

public class LoginTest extends BaseClass
{
	private LoginPage lp;
	
	@BeforeMethod
	public void setupPage()
	{
		lp = new LoginPage(driver);
	}

    @Test
    public void verifyLoginWithValidCredentials()
    {       
        lp.login(
            config.getProperty("username"),
            config.getProperty("password")
        );

        boolean isLoginSuccess = lp.isLoginSuccessful();

        Assert.assertTrue(
            isLoginSuccess, 
            "login failed. Dashboard not displayed after login"
        );
        
        System.out.println("login successful. user navigated to dashboard");
        
        lp.logout();
    }
    
    @Test
    public void verifyLoginWithInvalidUsername()
    {
    	
    	lp.login("wrongusername", config.getProperty("password"));
    	
    	boolean isLoginSuccess = lp.isLoginSuccessful();
        Assert.assertFalse(
            isLoginSuccess, 
            "Login should not succeed with invalid username");
        
    	String actualErrorMessage = lp.getErrorMessageText();
    	
    	Assert.assertEquals(
    			actualErrorMessage,
    	        "Invalid credentials",
    	        "Error message mismatch for invalid username");	
    	
    	System.out.println("Captured message: "+actualErrorMessage);
    	System.out.println("error message displayed correctly "
    			+ "for invalid username and valid password login");
    }
    
    @Test
    public void verifyLoginWithInvalidPassword()
    {
    	lp.login(config.getProperty("username"), "wrongpassword");
    	
    	boolean isLoginSuccess = lp.isLoginSuccessful();
    	
    	Assert.assertFalse(isLoginSuccess, 
    			"Login should not succeed with invalid password");
    	
    	String actualErrorMessage = lp.getErrorMessageText();
    	
    	Assert.assertEquals(
    			actualErrorMessage,
    	        "Invalid credentials",
    	        "Error message mismatch for invalid password");
    	
    	System.out.println("Captured message: "+actualErrorMessage);
    	System.out.println("error message displayed correctly "
    			+ "for valid useranme and invalid password login");  	
    }
      
    @Test
    public void verifyLoginWithEmptyCredentials()
    {   	
    	lp.login("", "");
    	
    	String actualUsernameError = lp.getUsernameRequiredMessage();
    	String actualPasswordError = lp.getPasswordRequiredMessage();
    	
    	Assert.assertEquals(actualUsernameError, "Required", 
    			"Username required message is incorrect");
    	
    	Assert.assertEquals(actualPasswordError, "Required", 
    			"password required message is incorrect");
    	
    	System.out.println("Captured  username error: "+actualUsernameError);
    	System.out.println("Captured  password error: "+actualPasswordError);
    	
    	System.out.println("required field validation working correctly for both fields");
    }
    
    @Test
    public void verifyLoginWithEmptyPassword()
    {
    	
    	
    	lp.login(config.getProperty("username"), "");
    	
    	String actualPasswordError = lp.getPasswordRequiredMessage();
    	
    	Assert.assertEquals(actualPasswordError, "Required", 
    			"password required message is incorrect");
    	
    	System.out.println("Captured  password error: "+actualPasswordError);
    	System.out.println("required field validation "
    			+ "working correctly for empty password");
    }
    
    @Test
    public void verifyLoginWithEmptyUsername()
    {    	
    	
    	lp.login("", config.getProperty("password"));
    	
    	String actualUsernameError = lp.getUsernameRequiredMessage();
    	
    	Assert.assertEquals(actualUsernameError, "Required", 
    			"username required message is incorrect");
   	
    	System.out.println("Captured username error: "+actualUsernameError);
    	System.out.println("required field validation "
    			+ "working correctly for empty username");
    }
    
    @Test
    public void verifyErrorMessageForInvalidCredentials()
    {   	
    	
    	lp.login("wrongusername", "wrongpassword");
    	
    	boolean isLoginSuccess = lp.isLoginSuccessful();
    	
    	Assert.assertFalse(isLoginSuccess, 
    			"Login should not succeed with invalid credentials");
    	
    	String actualErrorMessage = lp.getErrorMessageText();
    	
    	Assert.assertEquals(
    			actualErrorMessage,
    	        "Invalid credentials",
    	        "Error message mismatch for invalid credentials login");	
    	
    	System.out.println("Captured message: "+actualErrorMessage);
    	System.out.println("error message displayed correctly for "
    			+ "invalid credentials login");   	
    }

        @Test
        public void verifyNavigationAfterLogin() 
        {
            DashboardPage dp = new DashboardPage(driver);
            
            lp.login(config.getProperty("username"),
            		config.getProperty("password"));
         
            Assert.assertTrue(dp.isDashboardPageDisplayed(), 
            		"Dasnboard is not displayed.");

            // step 5: verify URL
            String currentURL = dp.getCurrentPageUrl();
            Assert.assertTrue(currentURL.contains("dashboard"),
                    "URL does not contain dashboard");
            System.out.println("Dashboard page displayed after successful login");
        }
    
    @Test
    public void verifyLogoutFunctionality()
    {
        lp.login(
            config.getProperty("username"),
            config.getProperty("password")
        );

        boolean isLoginSuccess = lp.isLoginSuccessful();

        Assert.assertTrue(
            isLoginSuccess, 
            "login failed. dashboard not displayed after login"
        );
        
        System.out.println("login successful. user navigated to dashboard");
        
        lp.logout();
        
        boolean isLogoutSuccess = lp.isLogoutSuccessful();
        
        Assert.assertTrue(
                isLogoutSuccess,
                "Logout failed... Login page not displayed after logout"
            );

            System.out.println("Logout successful. user redirected to login page");
    }
    
    @Test
    public void verifyForgotPassword()
    {
        lp.clickForgotPassword();

        Assert.assertTrue(
        		lp.isResetPasswordPageDisplayed(), 
            "forgot password link is not working");
        
        lp.resetPassword(config.getProperty("username"));
        
        Assert.assertTrue(lp.isResetPasswordSuccessful(), 
        		"Reset password is not successful");
        
        System.out.println("forgot Password link is working correctly "
        		+ "and reset password mail send successfully");
    }
}