package sbca.pageobjects.summary;

import sbca.pageobjects.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SummaryPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeader;

    public SummaryPage(WebDriver driver) {
        super(driver);
    }

    public void validatePageHeaderTextIs(String text) {
        waitForElementTextToBe(pageHeader, text);
    }

}
