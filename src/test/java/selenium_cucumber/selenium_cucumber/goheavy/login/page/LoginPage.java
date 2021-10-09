package selenium_cucumber.selenium_cucumber.goheavy.login.page;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium_cucumber.selenium_cucumber.general.PageObject;
import selenium_cucumber.selenium_cucumber.general.Setup;

public class LoginPage extends PageObject {
	String user_id = "email";
	String password_id = "password";
	String logo_alt_text = "Site Logo";
	String logo_image_src = 
			"https://goheavy-qa.gsoftinnovation.net/storage/public/setting/wBkh9qGXs0tiTOIJaA0i4GT18fSQsAKs0wzhfo5z.png";
	String LoginH1Xpath;
	String LoginFormXpath;
	String LoginPassShowHideButtonXpath;
	String CompanyLogoXpath;
	String FormSubmitButtonXpath;
	String email;
	String password;
	private By element;
	private String missingRequiredFieldText;
	private String EmailRequiredTextXpath;
	private String PassRequiredTextXpath;
	private String ForgotPassLinkXpath;
	private String IncorrectEmailOrPassDivXpath;
	private HashMap<String, WebElement> eles;

	public LoginPage() {
		super();
		setLoginH1Xpath("//h1[text()='Login']");
		setLoginFormXpath("//form[@id='admin-form-session']");
		setCompanyLogoXpath("//img[@alt='Site Logo']");
		setLoginPassShowHideButtonXpath(
				"//span[@role='img' and @aria-label='eye-invisible' and "
				+ "@class='anticon anticon-eye-invisible ant-input-password-icon']");
		setFormSubmitButtonXpath("//button[@environment='admin' and @type='button']");
		setForgotPassLinkXpath("//span[@class='ant-tag ant-tag-checkable']");
		setEmailRequiredTextXpath("//label[@title='Email']/../..//div[@role='alert']");
		setPassRequiredTextXpath("//label[@title='Password']/../..//div[@role='alert']");
		setIncorrectEmailOrPassDivXpath("//div[@class='ant-notification-notice-message']");
		setEles(new HashMap<String, WebElement>());
		setWaitTime(5000);
		this.urlpath = "login";
	}
	
	public WebElement getElement(By by) throws Exception {
		return getWebElement(by);
	}

	By getElement() {
		return element;
	}

	void setElement(By element) {
		this.element = element;
	}

	public String getIncorrectEmailOrPassDivXpath() {
		return IncorrectEmailOrPassDivXpath;
	}

	void setIncorrectEmailOrPassDivXpath(String incorrectEmailOrPassDivXpath) {
		IncorrectEmailOrPassDivXpath = incorrectEmailOrPassDivXpath;
	}

	String getMissingRequiredFieldText() {
		return missingRequiredFieldText;
	}

	void setMissingRequiredFieldText(String missingRequiredFieldText) {
		this.missingRequiredFieldText = missingRequiredFieldText;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HashMap<String, WebElement> getEles() {
		return eles;
	}

	void setEles(HashMap<String, WebElement> eles) {
		this.eles = eles;
	}

	String getPassRequiredTextXpath() {
		return PassRequiredTextXpath;
	}

	void setPassRequiredTextXpath(String passRequiredTextXpath) {
		PassRequiredTextXpath = passRequiredTextXpath;
	}

	String getEmailRequiredTextXpath() {
		return EmailRequiredTextXpath;
	}

	void setEmailRequiredTextXpath(String emailRequiredTextXpath) {
		EmailRequiredTextXpath = emailRequiredTextXpath;
	}

	public String getForgotPassLinkXpath() {
		return ForgotPassLinkXpath;
	}

	void setForgotPassLinkXpath(String forgotPassLinkXpath) {
		ForgotPassLinkXpath = forgotPassLinkXpath;
	}

	public String getFormSubmitButtonXpath() {
		return FormSubmitButtonXpath;
	}

	public void setFormSubmitButtonXpath(String formSubmitButtonXpath) {
		FormSubmitButtonXpath = formSubmitButtonXpath;
	}

	public String getLoginPassShowHideButtonXpath() {
		return LoginPassShowHideButtonXpath;
	}

	public void setLoginPassShowHideButtonXpath(String loginPassShowHideButtonXpath) {
		LoginPassShowHideButtonXpath = loginPassShowHideButtonXpath;
	}

	String getCompanyLogoXpath() {
		return CompanyLogoXpath;
	}

	void setCompanyLogoXpath(String companyLogoXpath) {
		CompanyLogoXpath = companyLogoXpath;
	}

	public String getLoginH1Xpath() {
		return LoginH1Xpath;
	}

	public void setLoginH1Xpath(String loginH1Xpath) {
		LoginH1Xpath = loginH1Xpath;
	}

	public String getLoginFormXpath() {
		return LoginFormXpath;
	}

	public void setLoginFormXpath(String loginFormXpath) {
		LoginFormXpath = loginFormXpath;
	}
	
	public boolean getCompanyLogoAndImage() throws Exception {
		setElement(By.xpath(getCompanyLogoXpath()));
		getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
		WebElement element = getElement(By.xpath(getCompanyLogoXpath()));
		return element.isDisplayed() && element.getAttribute("alt").equals(logo_alt_text) && 
				element.getAttribute("src").equals(logo_image_src);
	}

	public HashMap<String, WebElement> fillCredentials(String email, String password) {
		setEmail(email);
		setPassword(password);
		WebElement email_element = getWebElement(By.id(user_id));
		email_element.sendKeys(getEmail());
		WebElement pass_element = getWebElement(By.id(password_id));
		pass_element.sendKeys(getPassword());
		
		getEles().put("email", email_element);
		getEles().put("password", pass_element);
		
		return eles;
	}
	
	public boolean getMissingFieldsErrorMesssage(String message) {
		setMissingRequiredFieldText(message);

		try {
			if (getEmail().isBlank()) {
				setElement(By.xpath(getEmailRequiredTextXpath()));
				getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
				Setup.getWait().waitUntilElementAppear(By.xpath(getEmailRequiredTextXpath()), getWaitTime());
				WebElement missing_email_element = getWebElement(By.xpath(getEmailRequiredTextXpath()));
				getEles().put("required_email", missing_email_element);
				if (!getEles().get("required_email").getText().equals(getMissingRequiredFieldText()))
					return false;
			} else {
				throw new Exception("--Information-- Email is not Missing from Login Form Input");
			}

			if (getPassword().isBlank()) {
				setElement(By.xpath(getPassRequiredTextXpath()));
				getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
				WebElement missing_pass_element = getWebElement(By.xpath(getPassRequiredTextXpath()));
				getEles().put("required_password", missing_pass_element);
				if (!getEles().get("required_password").getText().equals(getMissingRequiredFieldText()))
					return false;
			} else {
				throw new Exception("--Information-- Password is not Missing from Login Form Input");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	public boolean getNotRegisteredInfoErrorMessage(String message) throws Exception {
		setElement(By.xpath(getIncorrectEmailOrPassDivXpath()));
		getWait().until(ExpectedConditions.presenceOfElementLocated(getElement()));
		By element = By.xpath(getIncorrectEmailOrPassDivXpath());
		getWait().until(ExpectedConditions.presenceOfElementLocated(element));
		return getElement(By.xpath(getIncorrectEmailOrPassDivXpath())).getText().equals(message);
	}

	public void clickOn(By by) throws Exception {
		cliksOnButton(by);
	}
	
	public void waitForElelemtDisappear() throws Exception{
		Setup.getWait().waitUntilElementDisappear(By.id(user_id), getWaitTime());
		Setup.getWait().waitUntilElementDisappear(By.id(password_id), getWaitTime());		
	}
}
