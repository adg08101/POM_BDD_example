package selenium_cucumber.selenium_cucumber.goheavy.login;

import static org.junit.Assert.fail;

//import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import selenium_cucumber.selenium_cucumber.general.*;
import selenium_cucumber.selenium_cucumber.goheavy.login.page.LoginPage;

public class LoginStep extends Steps {

	private LoginPage loginPage;
	GeneralSteps generalSteps;

	public LoginStep() {
		loginPage = new LoginPage();
		generalSteps = new GeneralSteps();
	}

	public void checkPage() {
		try {
		String path = loginPage.getPagePath().toLowerCase();
		//The user is at the login page
		Assert.assertTrue("The path provided is not correct in the url. path: " + path,
				loginPage.getCurrentUrl().toLowerCase().contains(path));
		
		//The system displays the login form with a background image
		Assert.assertTrue("The login Form is not displayed", loginPage.getElement(
				By.xpath(loginPage.getLoginFormXpath())).isDisplayed());

		//Get the system logo and name
		Assert.assertTrue("The login System Logo and Image is not displayed", 
				loginPage.getCompanyLogoAndImage());
		
		//Get the Login H1
		Assert.assertTrue("The Login H1 is not displayed", loginPage.getElement(
				By.xpath(loginPage.getLoginH1Xpath())).isDisplayed());
		
		//The show/hide button is displayed
		Assert.assertTrue("The Show/Hide Password Button is not clickable", 
				loginPage.getElement(By.xpath(loginPage.getLoginPassShowHideButtonXpath())).isDisplayed());
		
		//The Login Button is Enabled
				Assert.assertTrue("The Login Submit Button is not displayed", loginPage.getElement(
						By.xpath(loginPage.getFormSubmitButtonXpath())).isEnabled());
				
		//The "Forgot Password?" link is displayed
		Assert.assertTrue("The Forgot Password is not Displayed", loginPage.getElement(
						By.xpath(loginPage.getForgotPassLinkXpath())).isEnabled());
				
		//The show/hide button is clickable
		loginPage.clickOn(By.xpath(loginPage.getLoginPassShowHideButtonXpath()));
		
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void openURL() {
		loginPage.openURL();
	}

	public void the_unauthenticated_go_heavy_user_is_in_the_view() {
		this.openURL();
		checkPage();
	}
	
	public void user_insert_email_and_password(String email, String password) {
		loginPage.fillCredentials(email, password);	}

	public void user_clicks_on_the_button() {
		try {
			loginPage.clickOn(By.xpath(loginPage.getFormSubmitButtonXpath()));
		} catch (Exception e) {
			fail("The submit button is not clickable.");
		}
	}

	public void the_system_allows_the_user_access_to_the_system() {
		try {
			loginPage.waitForElelemtDisappear();
		} catch (Exception e) {
			fail("The login view did not disappear.");
		}
	}
	
	public void the_system_shows_error_message(String message) {
		try {
			Assert.assertTrue(loginPage.getMissingFieldsErrorMesssage(message));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	public void the_system_shows_error_message_for_not_registed(String message) throws Exception {
		try {
			Assert.assertTrue(loginPage.getNotRegisteredInfoErrorMessage(message));
		} catch (Exception e) {
			fail("The element does not shows or Message is not the one expected.");
		}
	}
	
	public void send_logout_request_to_keep_on_testing() {
		try {
			generalSteps.logoutProcess();
		} catch (Exception e) {
			fail("The logout Process failed.");
		}
	}
}
