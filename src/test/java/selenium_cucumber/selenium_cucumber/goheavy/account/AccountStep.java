package selenium_cucumber.selenium_cucumber.goheavy.account;

import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;

import selenium_cucumber.selenium_cucumber.general.Setup;
import selenium_cucumber.selenium_cucumber.general.Steps;
import selenium_cucumber.selenium_cucumber.goheavy.account.page.AccountPage;
//import selenium_cucumber.selenium_cucumber.goheavy.login.page.LoginPage;

public class AccountStep extends Steps {

	private AccountPage accountPage;

	public AccountStep() {
		accountPage = new AccountPage();
	}

	@Override
	public void checkPage() {
		String path = accountPage.getPagePath().toLowerCase();
		Assert.assertTrue(" The path privide is not correct in the url. path: " + path,
				accountPage.getCurrentUrl().toLowerCase().contains(path));
		try {
			accountPage.waitForSpinningElementDissapear();
			accountPage.getFrom();
		} catch (Exception e) {
			fail("The view do not match with Account page.");
		}
	}

	public void openAccountSetting() {
		HashMap<String, WebElement> li = accountPage
				.getMenu(By.xpath("//span[@aria-label='setting']/ancestor::span[@class='ant-menu-title-content']"));
		WebElement setting = li.get("Settings");
		Setup.getActions().moveToElement(setting).click().perform();
		Setup.getWait().thread(400);
		accountPage.waitForSpinningElementDissapear();
		WebElement el2 = setting.findElement(By.xpath("//span[@aria-label='profile']/ancestor::li[@role='menuitem']"));
		Setup.getActions().moveToElement(el2).click().perform();
		accountPage.waitForSpinningElementDissapear();
		Setup.getWait().thread(4000);

	}

	public void fillValidData() {
		accountPage.getFromElements();
	}

	public void clicksUpdate() {
		Setup.getActions().moveToElement(accountPage.getUpdateButton()).click().perform();

	}

	public void checkSpinningAppears() {
		accountPage.waitForSpinningElementDissapear();
	}

	public void checkUpdateMessage(String string) {

		WebElement notifEle = accountPage.getPopupMessage();
		Setup.getWait().thread(2);
		WebElement parent = notifEle
				.findElement(By.xpath("ancestor::div[contains(@class,'ant-notification-topRight')]"));

		// Checking messages match
		Assert.assertEquals("Update notification message was not found.", string.toLowerCase(),
				notifEle.getText().toLowerCase());
		
		// Checking that popoup is in the right
		String style = parent.getAttribute("style");
		Assert.assertTrue("Poup s not in the right corner.", style.contains("right: 0px"));

	}

	public void clearMandatoryField(String field, String inputType) {
		// TODO Auto-generated method stub
		try {
			Assert.assertTrue(accountPage.clear_mandatory_field(field, inputType));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void systemShowsErrorMessage(String message) {
		// TODO Auto-generated method stub
		Assert.assertTrue(accountPage.get_required_field_message().getText().equals(message));
	}

	public void setsInvalidDataToField(String field, String fieldType) {
		// TODO Auto-generated method stub
		Assert.assertTrue(accountPage.set_invalid_data_to_field(field, fieldType));
	}
}
