package sbca.pageobjects.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private static final int TIMEOUT = 5; //seconds
    private static final int POLLING = 100; //milliseconds

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected void waitForElementToBeVisible(WebElement element) throws Error{
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeInvisible(WebElement element) throws Error{
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementTextToBe(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void enterTextInElement(WebElement element, String text) {
        waitForElementToBeClickable(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void clickElementWithJS(WebElement element) {
        waitForElementToBeVisible(element);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    protected String getElementText(WebElement element) {
        return element.getText();
    }

}
