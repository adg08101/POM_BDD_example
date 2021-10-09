package selenium_cucumber.selenium_cucumber.general;

//import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GeneralSteps extends PageObject {
	private String LogOutStep1Xpath;
	private String LogOutStep2Xpath;
	private String menuXpath;
	private String SettingsXpath;
	private String AccountSettingsXpath;
	private By element;
	
	public GeneralSteps() {
		super();
		setLogOutStep1Xpath("//span[@class='ant-avatar ant-avatar-circle ant-avatar-image']");
		setLogOutStep2Xpath("//span[@class='ant-dropdown-menu-title-content' and text()='Logout']");
		setMenuXpath("//ul[@class='ant-menu ant-menu-root ant-menu-inline ant-menu-light' and @role='menu']");
		setSettingsXpath("//span[text()='Settings']");
		setAccountSettingsXpath("//..//..//..//ul//span[text()='Account Settings']");
	}

	private By getElement() {
		return element;
	}

	private void setElement(By element) {
		this.element = element;
	}

	private String getAccountSettingsXpath() {
		return AccountSettingsXpath;
	}

	private void setAccountSettingsXpath(String accountSettingsXpath) {
		AccountSettingsXpath = accountSettingsXpath;
	}

	private String getSettingsXpath() {
		return SettingsXpath;
	}

	private void setSettingsXpath(String settingsXpath) {
		SettingsXpath = settingsXpath;
	}

	private String getMenuXpath() {
		return menuXpath;
	}

	private void setMenuXpath(String menuXpath) {
		this.menuXpath = menuXpath;
	}

	String getLogOutStep1Xpath() {
		return LogOutStep1Xpath;
	}

	void setLogOutStep1Xpath(String logOutStep1Xpath) {
		LogOutStep1Xpath = logOutStep1Xpath;
	}
	
	String getLogOutStep2Xpath() {
		return LogOutStep2Xpath;
	}

	private void setLogOutStep2Xpath(String logOutStep2Xpath) {
		LogOutStep2Xpath = logOutStep2Xpath;
	}
	
	private void waitForSpinnerToDissapear() {
		waitForSpinningElementDissapear();
	}

	public void logoutProcess() throws Exception {
		waitForSpinnerToDissapear();
		Setup.getActions().moveToElement(getWebElement(By.xpath(getLogOutStep1Xpath()))).click().perform();
		Setup.getActions().moveToElement(getWebElement(By.xpath(getLogOutStep2Xpath()))).click().perform();
	}
	
	public void goToAccountSettingsView() throws Exception {
		waitForSpinnerToDissapear();
		setElement(By.xpath(getMenuXpath() + getSettingsXpath()));
		getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
		WebElement settings = getWebElement(getElement());
		Setup.getActions().moveToElement(settings).click().perform();
		Setup.getWait().thread(200);
		setElement(By.xpath(getMenuXpath() + getSettingsXpath() + 
				getAccountSettingsXpath()));
		getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
		WebElement accountSettings = getWebElement(getElement());
		Setup.getActions().moveToElement(accountSettings).click().perform();
		waitForSpinnerToDissapear();
	}
}
