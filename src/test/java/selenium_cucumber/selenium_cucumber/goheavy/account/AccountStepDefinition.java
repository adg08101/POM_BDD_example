package selenium_cucumber.selenium_cucumber.goheavy.account;

import io.cucumber.java.en.*;
import selenium_cucumber.selenium_cucumber.general.GeneralSteps;

public class AccountStepDefinition {
	private AccountStep accountStep;
	private GeneralSteps generalSteps;

	public AccountStepDefinition() {
		accountStep = new AccountStep();
		generalSteps = new GeneralSteps();
	}

	/*
	@Given("The user is in  \"Account Settings\" view")
	public void the_user_is_in_view() {
		accountStep.openAccountSetting();
		accountStep.checkPage();
	}
	*/
	
	@Given("The user is in  \"Account Settings\" view")
	public void the_user_is_in_account_settings_view() {
		try {
			generalSteps.goToAccountSettingsView();
			//accountStep.checkPage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@When("User insert valid data")
	public void user_insert_valid_data() {
		accountStep.fillValidData();
	}

	@When("Clicks on the \"Update\" button")
	public void clicks_on_the_button() {
		accountStep.clicksUpdate();
	}

	@Then("The system saves the user profile information")
	public void the_system_saves_the_user_profile_information() {
		accountStep.checkSpinningAppears();

	}

	@Then("The system displays popup with success message: {string}")
	public void the_system_displays_popup_with_success_message(String string) {
		accountStep.checkUpdateMessage(string);
	}
	
	@When("The user clears a mandatory {string} on {string}")
	public void the_user_clears_a_mandatory_field(String field, String inputType) {
		accountStep.clearMandatoryField(field, inputType);
	}
	
	@Then("The system displays an error message {string}.")
	public void the_system_shows_an_error_message(String message) {
		accountStep.systemShowsErrorMessage(message);
	}
	
	@When("The user sets invalid data to {string} on {string}")
	public void the_user_sets_invalid_data(String field, String fieldType) {
		accountStep.setsInvalidDataToField(field, fieldType);
	}
}
