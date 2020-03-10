package sbca.pageobjects.contacts;

import sbca.pageobjects.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewContactDialogAccountDetailsTab extends BasePage {

    @FindBy(css = "div[class='view-country-select']")
    private WebElement countryDropdownContainer;

    @FindBy(css = "input[name='contact[addresses_attributes][0][line_1]']")
    private WebElement addressLine1;

    @FindBy(css = "input[name='contact[addresses_attributes][0][line_2]']")
    private WebElement addressLine2;

    public NewContactDialogAccountDetailsTab(WebDriver driver) {
        super(driver);
    }

    public void setAddressLine1(String text) {
        enterTextInElement(addressLine1, text);
    }

    public void setAddressLine2(String text) {
        enterTextInElement(addressLine2, text);
    }
}
