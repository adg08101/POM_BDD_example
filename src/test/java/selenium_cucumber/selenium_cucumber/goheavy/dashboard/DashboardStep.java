package selenium_cucumber.selenium_cucumber.goheavy.dashboard;

import org.junit.Assert;

import selenium_cucumber.selenium_cucumber.general.Steps;
import selenium_cucumber.selenium_cucumber.goheavy.dashboard.pages.Dashboard;

public class DashboardStep extends Steps{
	private Dashboard dashboard;

	public DashboardStep() {
		dashboard = new Dashboard();
	}

	public void checkPage() {
		String path = dashboard.getPagePath().toLowerCase();
		Assert.assertTrue("The path provided is not correct in the url. path: " + path,
				dashboard.getCurrentUrl().toLowerCase().contains(path));
	}

}
