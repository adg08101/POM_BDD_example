package selenium_cucumber.selenium_cucumber.goheavy.account.page;

//import java.util.HashMap;
import java.util.List;
import java.util.Random;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;

import selenium_cucumber.selenium_cucumber.general.PageObject;
import selenium_cucumber.selenium_cucumber.general.Setup;

public class AccountPage extends PageObject {
	private String formScroll = "//*[@id=\"account-settings\"]/ancestor::div[@class=\"templateStyles__ContentDiv-sc-144t9h2-1 bcVeZj\"]";

	public AccountPage() {
		super();
		this.urlpath = "accountsettings";
	}

	public WebElement getFrom() {
		return this.getWebElement(By.cssSelector("#account-settings"));
	}
	
	public void setWebElementText(By by, String value) {
		WebElement element = getWebElement(by);
		clear_element_text(element);
		element.sendKeys(value);
	}

	public void getFromElements() {
		waitForSpinningElementDissapear();
		Setup.getWait().thread(7000);
		
		By avatar_element = By.xpath("//input[@type='file']");
		getWait().until(ExpectedConditions.presenceOfElementLocated(avatar_element));
		
		//Setting avatar
		WebElement photo = this.getWebElement(avatar_element);
		String url = (String) Setup.getValueStore("avatar");
		photo.sendKeys(url);

		//Changing Data
		//Be careful on changing email, may change login credentials
		setWebElementText(By.xpath("//input[@id='firstName']"), getFaker().name().firstName());
		setWebElementText(By.xpath("//input[@id='lastName']"), getFaker().name().lastName());
		setWebElementText(By.xpath("//input[@id='mobilePhone']"), getFaker().phoneNumber().cellPhone());
		setWebElementText(By.xpath("//textarea[@id='address']"), getFaker().address().fullAddress());
		setWebElementText(By.xpath("//input[@id='addressCity']"), getFaker().address().cityName());
		setWebElementText(By.xpath("//input[@id='addressZipCode']"), getFaker().address().zipCode());
		
		// Scrolling the page to get the element activated
		this.scroll(formScroll, By.id("addressStateId"));
		
		waitForSpinningElementDissapear();

		// Getting State
		By state_element = By.id("addressStateId");
		getWait().until(ExpectedConditions.elementToBeClickable(state_element));
		
		WebElement stateInput = this.getWebElement(state_element);
		Setup.getActions().moveToElement(stateInput).click().perform();
		
		By state_list_elements = By.xpath("//div[@id='addressStateId_list']/"
				+ "ancestor::div[contains(@class,'ant-select-dropdown')]/descendant::div[contains("
				+ "@class,'ant-select-item ant-select-item-option')]/span");
		getWait().until(ExpectedConditions.presenceOfElementLocated(state_list_elements));
	
		List<WebElement> addressStateId_list = this.getWebElements(state_list_elements);
		
		WebElement addr = addressStateId_list.get(new Random().nextInt(addressStateId_list.size()));
		Setup.getActions().moveToElement(addr).click().perform();
	}

	public WebElement getUpdateButton() {
		return this.getWebElement(By.xpath("//*[@id=\"account-settings\"]//button"));
	}

	public WebElement getPopupMessage() {
		Setup.getWait().visibilityOfElement(By.xpath("//div[@class='ant-notification-notice-message']"),
				"Not element message");
		return this.getWebElement(By.xpath("//div[@class='ant-notification-notice-message']"));
	}
	
	public void clear_element_text(WebElement element) {
		int length = element.getAttribute("value").length();
		for (int i = 0;i <= length;i++) {
			Setup.getActions().sendKeys(element, Keys.BACK_SPACE).perform();
		}	
	}

	public boolean clear_mandatory_field(String field, String inputType) throws Exception {
		waitForSpinningElementDissapear();
		Setup.getWait().thread(3000);
		try {
			waitForSpinningElementDissapear();
			By element_xpath = By.xpath("//" + inputType + "[@id='" + field + "']");
			getWait().until(ExpectedConditions.elementToBeClickable(element_xpath));
			WebElement form_input = getWebElement(element_xpath);
			Setup.getActions().moveToElement(form_input).perform();
			Setup.getWait().thread(500);
			this.scroll(formScroll, element_xpath);
			clear_element_text(form_input);
			waitForSpinningElementDissapear();
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public WebElement get_required_field_message() {
		waitForSpinningElementDissapear();
		By alert = By.xpath("//div[@role='alert']");
		getWait().until(ExpectedConditions.presenceOfElementLocated(alert));
		return getWebElement(alert);
	}

	public boolean set_invalid_data_to_field(String field, String fieldType) {
		waitForSpinningElementDissapear();
		Setup.getWait().thread(3000);
		setWebElementText(By.xpath("//" + fieldType + "[@id='" + field + "']"), getFaker().phoneNumber().cellPhone());
		System.exit(0);
		return false;
	}
}
