package selenium_cucumber.selenium_cucumber.goheavy.driver;

import org.junit.Assert;

import selenium_cucumber.selenium_cucumber.general.Steps;
import selenium_cucumber.selenium_cucumber.goheavy.driver.pages.DriverListPage;

public class DriverStep extends Steps{
	private DriverListPage driverListPage;

	public DriverStep() {
		driverListPage = new DriverListPage();
	}

	public void checkPage() {
		String path = driverListPage.getPagePath().toLowerCase();
		Assert.assertTrue(" The path provided is not correct in the url. path: " + path,
				driverListPage.getCurrentUrl().toLowerCase().contains(path));
	}
}
