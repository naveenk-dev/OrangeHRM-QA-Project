package orangehrm.testcases;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import orangehrm.base.BaseClass;
import orangehrm.pages.DashboardPage;
import orangehrm.pages.LoginPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginTest extends BaseClass
{
	private LoginPage lp;
	private static final Logger logger = 
			LogManager.getLogger(LoginTest.class);
	
	@BeforeMethod
	public void setupPage()
	{
		lp = new LoginPage(driver);
	}

	//=================VALID LOGIN=================
    @Test(description = "Verify login with valid credentials") 
    public void verifyLoginWithValidCredentials()
    {   
    	logger.info("Starting test: verifyLoginWithValidCredentials");
        
    	lp.login(
            config.getProperty("username"),
            config.getProperty("password")
        );

        boolean isLoginSuccess = lp.isLoginSuccessful();

        Assert.assertTrue(
            isLoginSuccess, 
            "login failed. Dashboard not displayed after login"
        );
        
        logger.info("Login successful. dashboard displayed");
        
        lp.logout();
    }
    
    //=================INVALID USERNAME=================
    @Test(description = "Verify login with invalid username")
    public void verifyLoginWithInvalidUsername()
    {
    	logger.info("Starting test: verifyLoginWithInvalidUsername");
    	
    	lp.login(config.getProperty("invalid.username"), 
    			config.getProperty("password"));
    	
    	Assert.assertTrue(
    		    lp.isLoginFailed(),
    		    "Login should fail with invalid username"
    		);
        
    	String actualErrorMessage = lp.getErrorMessageText();
    	
    	Assert.assertEquals(
    			actualErrorMessage,
    	        "Invalid credentials",
    	        "Error message mismatch for invalid username");	
    	
    	logger.info("Validation successful for invalid username. "
    			+ "Message: {}", actualErrorMessage);
    }
    
    //=================INVALID PASSWORD=================
    @Test(description = "Verify login with invalid password")
    public void verifyLoginWithInvalidPassword()
    {
    	logger.info("Starting test: verifyLoginWithInvalidPassword");
    	
    	lp.login(config.getProperty("username"), 
    			config.getProperty("invalid.password"));
    	
    	Assert.assertTrue(
    		    lp.isLoginFailed(),
    		    "Login should fail with invalid password"
    		);
    	
    	String actualErrorMessage = lp.getErrorMessageText();
    	
    	Assert.assertEquals(
    			actualErrorMessage,
    	        "Invalid credentials",
    	        "Error message mismatch for invalid password");
    	
    	logger.info("Validation successful for invalid password. "
    			+ "Message: {}", actualErrorMessage);  	
    }
      
    //================= BOTH EMPTY =================
    @Test(description = "Verify login with empty credentials")
    public void verifyLoginWithEmptyCredentials()
    {   
    	logger.info("Starting test: verifyLoginWithEmptyCredentials");
    	
    	lp.login("", "");
    	
    	String actualUsernameError = lp.getUsernameRequiredMessage();
    	String actualPasswordError = lp.getPasswordRequiredMessage();
    	
    	Assert.assertEquals(actualUsernameError, "Required", 
    			"Username required message is incorrect");
    	
    	Assert.assertEquals(actualPasswordError, "Required", 
    			"password required message is incorrect");
    	
    	System.out.println("Captured  username error: "+actualUsernameError);
    	System.out.println("Captured  password error: "+actualPasswordError);
    	
    	logger.info("Validation successful for empty credentials");
    }
    
    //================= EMPTY PASSWORD =================
    @Test(description = "Verify login with empty password")
    public void verifyLoginWithEmptyPassword()
    {
    	logger.info("Starting test: verifyLoginWithEmptyPassword");
    	
    	lp.login(config.getProperty("username"), "");
    	
    	String actualPasswordError = lp.getPasswordRequiredMessage();
    	
    	Assert.assertEquals(actualPasswordError, "Required", 
    			"password required message is incorrect");
    	
    	System.out.println("Captured  password error: "+actualPasswordError);
    	logger.info("Validation successful for empty password");
    }
    
    //================= EMPTY USERNAME =================
    @Test(description = "Verify login with empty username")
    public void verifyLoginWithEmptyUsername()
    {    	
    	logger.info("Starting test: verifyLoginWithEmptyUsername");
    	lp.login("", config.getProperty("password"));
    	
    	String actualUsernameError = lp.getUsernameRequiredMessage();
    	
    	Assert.assertEquals(actualUsernameError, "Required", 
    			"username required message is incorrect");
   	
    	System.out.println("Captured username error: "+actualUsernameError);
    	logger.info("Validation successful for empty username");
    }
    
    //================= INVALID BOTH =================
    @Test(description = "Verify error message for invalid credentials")
    public void verifyErrorMessageForInvalidCredentials()
    {   	
    	logger.info("Starting test: verifyErrorMessageForInvalidCredentials");
    	
    	lp.login(config.getProperty("invalid.username"), 
    			config.getProperty("invalid.password"));
    	
    	Assert.assertTrue(
    		    lp.isLoginFailed(),
    		    "Login should fail with invalid credentials"
    		);
    	
    	String actualErrorMessage = lp.getErrorMessageText();
    	
    	Assert.assertEquals(
    			actualErrorMessage,
    	        "Invalid credentials",
    	        "Error message mismatch for invalid credentials login");	
    	
    	logger.info("Validation successful for invalid credentials. "
    			+ "Message: {}", actualErrorMessage);  	
    }

    	//================= NAVIGATION =================
    	@Test(description = "Verify navigation after successful login")
        public void verifyNavigationAfterLogin() 
        {
    		logger.info("Starting test: verifyNavigationAfterLogin");
            DashboardPage dp = new DashboardPage(driver);
            
            lp.login(config.getProperty("username"),
            		config.getProperty("password"));
         
            Assert.assertTrue(dp.isDashboardPageDisplayed(), 
            		"Dasnboard is not displayed.");

            // step 5: verify URL
            String currentURL = dp.getCurrentPageUrl();
            Assert.assertTrue(currentURL.contains("dashboard"),
                    "URL does not contain dashboard");
            logger.info("Navigation successful-User redirected to dashboard");
        }
    
    //================= LOGOUT =================
    @Test(description = "Verify logout functionality")
    public void verifyLogoutFunctionality()
    {
    	logger.info("Starting test: verifyLogoutFunctionality");
    	
        lp.login(
            config.getProperty("username"),
            config.getProperty("password")
        );

        boolean isLoginSuccess = lp.isLoginSuccessful();

        Assert.assertTrue(
            isLoginSuccess, 
            "login failed. dashboard not displayed after login"
        );
        
        logger.info("login successful. user navigated to dashboard");
        
        lp.logout();
        
        boolean isLogoutSuccess = lp.isLogoutSuccessful();
        
        Assert.assertTrue(
                isLogoutSuccess,
                "Logout failed... Login page not displayed after logout"
            );

        logger.info("Logout successful - User redirected to login page");
    }
    
    //================= FORGOT PASSWORD =================
    @Test(description = "Verify forgot password page navigation")
    public void verifyForgotPassword()
    {
    	logger.info("Starting test: verifyForgotPassword");
        lp.clickForgotPassword();

        Assert.assertTrue(
        		lp.isResetPasswordPageDisplayed(), 
            "forgot password link is not working");
        
        lp.resetPassword(config.getProperty("invalid.username"));
        
        Assert.assertTrue(lp.isResetPasswordSuccessful(), 
        		"Reset password is not successful");
        
        logger.info(
        	    "Forgot password page displayed successfully "
        	    + "(Demo application limitation: "
        	    + "reset password functionality not working "
        	    + "for username: {})", config.getProperty("username"));
    }
}