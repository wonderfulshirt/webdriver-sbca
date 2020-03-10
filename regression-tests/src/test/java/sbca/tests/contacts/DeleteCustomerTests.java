package sbca.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import sbca.pageobjects.contacts.*;
import sbca.pageobjects.global.NavigationMenu;
import sbca.tests.framework.BaseTest;
import sbca.tests.framework.TestListener;
import java.util.UUID;

@Listeners(TestListener.class)
public class DeleteCustomerTests extends BaseTest {

    private String contactBusinessName;

    @BeforeMethod
    public void shouldCreateNewCustomer() {
        setContactBusinessName(UUID.randomUUID().toString());
        String businessName = getContactBusinessName();

        NavigationMenu navigationMenu = new NavigationMenu(driver);
        navigationMenu.clickContactsMenuItem();

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.setSearchTextBox("");
        contactsPage.clickSearchButton();
        contactsPage.clickNewCustomerButton();

        NewContactDialog newContactDialog = new NewContactDialog((driver));
        newContactDialog.setBusinessNameTextBox(businessName);
        newContactDialog.clickSaveButton();
        newContactDialog.waitForSaveButtonToBeInvisible();

        contactsPage.setSearchTextBox(businessName);
        contactsPage.clickSearchButton();
        contactsPage.waitForNumberOfRecordsTextToEqual("1");
        contactsPage.validateContactsTableCellText(0, 3, businessName);
    }

    @Test
    public void shouldDeleteTheSelectedCustomer() {
        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.clickTableRowCheckbox(0);
        contactsPage.clickDeleteIcon();

        DeleteContactDialog deleteContactDialog = new DeleteContactDialog(driver);
        deleteContactDialog.clickYesButton();
        deleteContactDialog.waitForYesButtonToBeInvisible();

        contactsPage.setSearchTextBox(getContactBusinessName());
        contactsPage.clickSearchButton();
        contactsPage.waitForNumberOfRecordsTextToEqual("0");
    }

    public void setContactBusinessName(String contactBusinessName) {
        this.contactBusinessName = contactBusinessName;
    }

    public String getContactBusinessName() {
        return contactBusinessName;
    }

}
