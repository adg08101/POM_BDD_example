package selenium_cucumber.selenium_cucumber.general;

import java.util.HashMap;
import java.util.List;
//import java.util.Objects;
//import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class PageObject {
	private WebDriver driver;
	protected String urlpath = "";
	private WebDriverWait wait;
	private int waitTime;
	private String spinningElement = "//div[contains(@class,'ant-spin-spinning')]";
	public Faker faker;

	public PageObject() {
		this.setDriver(Setup.getDriver());
		this.setFaker(new Faker());
		PageFactory.initElements(this.getDriver(), this);
		setWaitTime(20);
		setWait(new WebDriverWait(this.getDriver(), this.getWaitTime()));
	}

	public Faker getFaker() {
		return faker;
	}

	void setFaker(Faker faker) {
		this.faker = faker;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void openURL() {
		Setup.openUrl(System.getProperty("defaultURL").concat("/").concat(urlpath));
	}

	protected WebElement getWebElement(By by) {
		return this.getDriver().findElement(by);
	}

	protected List<WebElement> getWebElements(By by) {
		return this.getDriver().findElements(by);
	}

	protected void cliksOnButton(By by) {
		getWebElement(by).click();
		waitForSpinningElementDissapear();
		Setup.getWait().waitForLoading((Integer) Setup.getTimeouts().get("implicit"));
	}

	public String getCurrentUrl() {
		return this.getDriver().getCurrentUrl();
	}

	public String getPagePath() {
		return this.urlpath;
	}
	
	public void checkSpinningAppears() {
		//Setup.getWait().waitForLoading((Integer) Setup.getTimeouts().get("implicit"));
		Setup.getWait().waitUntilElementAppear(By.xpath(spinningElement), (Integer) Setup.getTimeouts().get("pageLoad"));
		Setup.getWait().waitForLoading((Integer) Setup.getTimeouts().get("implicit"));
	}
	
	public void waitForSpinningElementDissapear() {
		try {
			//Setup.getWait().waitForLoading((Long) (Setup.getTimeouts().get("implicit")));
			checkSpinningAppears();
			Setup.getWait().waitUntilElementDisappear(By.xpath(spinningElement), 10);
			Setup.getWait().waitForLoading((Long) (Setup.getTimeouts().get("implicit")));
			Setup.getWait().thread(5);
		} catch (Exception e) {}
	}
	
	public void scroll(String scrollElementxpath, By targetElementxpath) {

		WebElement el = this.getWebElement(targetElementxpath);
		int desired_y = el.getSize().height / 2 + el.getLocation().y;

		int current_y = (Integer.parseInt(String.valueOf(Setup.executeScript("return window.innerHeight"))) / 2)
				+ Integer.parseInt(String.valueOf(Setup.executeScript("return window.pageYOffset")));
		int scroll_y_by = desired_y + current_y;

		Setup.executeScript("var el=" + "document.evaluate('" + scrollElementxpath + "',"
				+ " document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
				+ " el.scrollTo(0, arguments[0]);", scroll_y_by);

	}
	
	public HashMap<String, WebElement> getMenu(By by) {
		waitForSpinningElementDissapear();
		HashMap<String, WebElement> menu = new HashMap<String, WebElement>();
		
		menu.put("Menu", getWebElement(by));
		
		return menu;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

}
