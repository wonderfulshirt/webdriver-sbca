package sbca.pageobjects.contacts;

import org.openqa.selenium.*;
import sbca.pageobjects.framework.BasePage;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactsPage extends BasePage {

    @FindBy(css = "h1[contains(text(), 'Contacts')]")
    private WebElement pageTitle;

    @FindBy(id = "filter_search_text")
    private WebElement searchTextBox;

    @FindBy(css = "button[name='submit']")
    private WebElement searchButton;

    @FindBy(css = "table[class='grid-layout']")
    private WebElement contactsTable;

    @FindBy(css = "span[class='records']")
    private WebElement numberOfRecordsText;

    @FindBy(css = "div[data-role='new_customer']")
    private WebElement newCustomerButton;

    @FindBy(css = "div[data-role='new_vendor']")
    private WebElement newSupplierButton;

    @FindBy(css = "i[class='icon-bulk_destroy']")
    private WebElement deleteIcon;

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public void clickNewCustomerButton() {
        newCustomerButton.click();
    }

    public void clickNewSupplierButton() {
        newSupplierButton.click();
    }

    public void setSearchTextBox(String text) {
        enterTextInElement(searchTextBox, text);
    }

    public void clickSearchButton() {
        clickElementWithJS(searchButton);
    }

    public void clickDeleteIcon() {
        deleteIcon.click();
    }

    private List<WebElement> getContactsTableRows() {
        waitForElementToBeVisible(contactsTable);
        return contactsTable.findElements(By.xpath(".//tbody/tr"));
    }

    private WebElement getContactsTableCell(int row, int cell) {
        WebElement tableRow;
        WebElement tableRowCell;

        try {
            tableRow = getContactsTableRows().get(row);
            tableRowCell = tableRow.findElement(By.xpath(".//td[" + cell + "]"));
        } catch(StaleElementReferenceException e) {
            tableRow = getContactsTableRows().get(row);
            tableRowCell = tableRow.findElement(By.xpath(".//td[" + cell + "]"));
        }

        return tableRowCell;
    }

    public void validateContactsTableCellText(int row, int cell, String text) {
        try {
            waitForElementTextToBe(getContactsTableCell(row, cell), text);
        } catch (TimeoutException e) {
            waitForElementTextToBe(getContactsTableCell(row, cell), text);
        }
    }

    public void waitForNumberOfRecordsTextToEqual(String text) {
        waitForElementTextToBe(numberOfRecordsText, text);
    }

    public void clickTableCell(int row, int cell) {
        clickElementWithJS(getContactsTableCell(row, cell));
    }

    public void clickTableRowCheckbox(int row) {
        getContactsTableCell(row, 1).click();
    }

}
