package selenium_cucumber.selenium_cucumber.goheavy.fleetowners;

import org.junit.Assert;

import selenium_cucumber.selenium_cucumber.general.Steps;
import selenium_cucumber.selenium_cucumber.goheavy.fleetowners.pages.FleetPage;

public class FleetStep extends Steps{
	private FleetPage fleetPage;

	public FleetStep() {
		fleetPage = new FleetPage();
	}

	public void checkPage() {
		String path = fleetPage.getPagePath().toLowerCase();
		Assert.assertTrue(" The path privide is not correct in the url. path: " + path,
				fleetPage.getCurrentUrl().toLowerCase().contains(path));
	}

}
