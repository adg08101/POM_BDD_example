package selenium_cucumber.selenium_cucumber.general;

//import io.cucumber.datatable.internal.difflib.myers.MyersDiff;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.InputStream;
import java.io.File;
//import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public final class Setup {
	private static WebDriver driver;
	private static HashMap<String, Object> store = new HashMap<String, Object>();
	private static Actions actions;
	private static WaitingObject waitingObject;
	private static String defaultURL = "https://webgoheavy-testing.gsoftinnovation.net/admin";
	private static JavascriptExecutor jsExecutor;
	public static Map<String, Object> timeouts;

	 @Before
	public void InitSetup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		//String browser = System.getProperty("browser");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		timeouts = new HashMap<String, Object>();
		timeouts.put("implicit", 50);
		timeouts.put("pageLoad", 5000000);
		timeouts.put("script", 300000);
		options.setCapability("timeouts", timeouts);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		initObject();
	}

	private static void initObject() {
		waitingObject = new WaitingObject(driver);
		actions = new Actions(driver);
		System.setProperty("defaultURL", defaultURL);
		setJsExecutor((JavascriptExecutor) driver);
		loadDefaultProperties();
	}
	
	public static Map<String, Object> getTimeouts() {
		return timeouts;
	}

	void setTimeouts(Map<String, Object> timeouts) {
		Setup.timeouts = timeouts;
	}

	public static Object executeScript(String script, Object... arg) {
		return getJsExecutor().executeScript(script,arg);
	}

	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Actions getActions() {
		return actions;
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public static Object getValueStore(String key) {
		return store.get(key);
	}

	/**
	 *
	 * @return Return an instance of wait.
	 */
	public static WaitingObject getWait() {
		return waitingObject;
	}

	/**
	 *
	 * @param key
	 * @param value
	 */
	public static void setKeyValueStore(String key, Object value) {
		store.put(key, value);
	}

	/**
	 * Open new url
	 * 
	 * @param url
	 */
	public static void openUrl(String url) {
		driver.get(url);
		//waitingObject.waitForLoading(3600);
	}

	@After
	public void close() {
		driver.close();
	}
	
	private static void loadDefaultProperties() {
		InputStream input = Setup.class.getResourceAsStream("/defaultproperties.properties");
		
		Properties pop = new Properties();
		try {
			pop.load(input);
		} catch (java.io.IOException e) {}
		setKeyValueStore("defaultProperties", pop);
		int number = (int) (Math.random() * 4 + 1);
		String avatar_name = "/avatar(" + String.valueOf(number) + ").png";
		setKeyValueStore("avatar", new File(Setup.class.getResource(avatar_name).getFile())
				.getAbsolutePath());
	}

	public static JavascriptExecutor getJsExecutor() {
		return jsExecutor;
	}

	public static void setJsExecutor(JavascriptExecutor jsExecutor) {
		Setup.jsExecutor = jsExecutor;
	}
}
