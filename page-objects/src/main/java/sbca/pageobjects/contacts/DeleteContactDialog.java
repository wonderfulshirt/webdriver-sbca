package sbca.pageobjects.contacts;

import sbca.pageobjects.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteContactDialog extends BasePage {

    @FindBy(css = "span[data-role='confirm']")
    private WebElement yesButton;

    public DeleteContactDialog(WebDriver driver) {
        super(driver);
    }

    public void clickYesButton() {
        clickElementWithJS(yesButton);
    }

    public void waitForYesButtonToBeInvisible() {
        waitForElementToBeInvisible(yesButton);
    }

}
