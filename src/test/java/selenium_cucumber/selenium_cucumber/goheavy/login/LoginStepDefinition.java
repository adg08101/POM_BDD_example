package selenium_cucumber.selenium_cucumber.goheavy.login;

//import java.io.InputStream;
import java.util.Properties;
import io.cucumber.java.en.*;
import selenium_cucumber.selenium_cucumber.general.*;
import selenium_cucumber.selenium_cucumber.goheavy.dashboard.DashboardStep;
import selenium_cucumber.selenium_cucumber.goheavy.driver.DriverStep;
import selenium_cucumber.selenium_cucumber.goheavy.fleetowners.FleetStep;

public class LoginStepDefinition {
	private LoginStep loginStep;
	private GeneralSteps generalSteps;

	public LoginStepDefinition() {
		loginStep = new LoginStep();
		generalSteps = new GeneralSteps();
	}

	@Given("The unauthenticated GoHeavy User is in the Login view")
	public void the_unauthenticated_go_heavy_user_is_in_the_view() {
		loginStep.the_unauthenticated_go_heavy_user_is_in_the_view();
	}

	@When("User insert email {string} and password {string}")
	public void user_insert_email_and_password(String email, String password) {
		loginStep.user_insert_email_and_password(email, password);
	}

	@When("User clicks on the \"Sign in\" button")
	public void user_clicks_on_the_button() {
		loginStep.user_clicks_on_the_button();
	}

	@Then("The system allows the user access to the system")
	public void the_system_allows_the_user_access_to_the_system() {
		loginStep.the_system_allows_the_user_access_to_the_system();
	}

	@Then("Sytem redirects to {string} view")
	public void sytem_redirects_to_dashboard_view(String redirect) {
		Steps view = new DashboardStep();

		if (redirect == "Drivers List")
			view = new DriverStep();
		else if (redirect == "Fleet Owners List")
			view = new FleetStep();
		
		view.checkPage();
	}
	
	@Then("The system displays an error {string} below each field")
	public void sytem_shows_error_message_for_required_fileds(String message) {
		loginStep.the_system_shows_error_message(message);
	}
	
	@Then("The system displays the following error {string} in a popup window")
	public void sytem_shows_error_message_for_not_registered_info(String message) {
		try {
			loginStep.the_system_shows_error_message_for_not_registed(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("System Logs Out")
	public void sytem_logs_out() {
		try {
			generalSteps.logoutProcess();
		} catch (Exception e) {
			System.out.println("-- Warning -- Maybe hard to click but clicked");
		}
	}
	
	@Given("Any user is logged")
	public void any_user_is_logged() {
		Properties cred = (Properties) Setup.getValueStore("defaultProperties");
		String email = cred.getProperty("defaul.email");
		String pass = cred.getProperty("defaul.password");
		// mock we get the data from excel or any file
		loginStep.openURL();
		loginStep.user_insert_email_and_password(email, pass);
		loginStep.user_clicks_on_the_button();
	}
	
	/*
	@Given("The user is in  \"Account Settings\" view")
	public void the_user_is_in_account_settings_view() {
		try {
			generalSteps.goToAccountSettingsView();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@When("User insert valid data")
	public void user_inserts_valid_data() {
		System.out.println("User insert valid data");
	}
	
	@When("Clicks on the \"Update\" button")
	public void user_click_update_btn() {
		System.out.println("User clicks update button");
	}
	
	@Then("The system saves the user profile information")
	public void system_saves_the_info() {
		System.out.println("System Saves the Info");
	}
	
	@Then("The system displays popup with success message: \"Your profile was successfully updated\"")
	public void system_shows_success_message() {
		System.out.println("System Shows Success Message");
	}
	*/
}
