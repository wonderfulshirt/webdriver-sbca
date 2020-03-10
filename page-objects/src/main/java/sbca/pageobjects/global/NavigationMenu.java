package sbca.pageobjects.global;

import sbca.pageobjects.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationMenu extends BasePage {

    @FindBy(css = "a[href='/']")
    private WebElement summaryMenuItem;

    @FindBy(css = "a[href='/contacts/contacts']")
    private WebElement contactsMenuItem;

    public NavigationMenu(WebDriver driver) {
        super(driver);
    }

    public void clickSummaryMenuItem() {
        clickElementWithJS(summaryMenuItem);
    }

    public void clickContactsMenuItem() {
        clickElementWithJS(contactsMenuItem);
    }

}
