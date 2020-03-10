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
public class CreateNewSupplierTests extends BaseTest {

    @BeforeMethod
    public void shouldLoadContactsPageViaGlobalNavigationMenu() {
        NavigationMenu navigationMenu = new NavigationMenu(driver);
        navigationMenu.clickContactsMenuItem();
        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.setSearchTextBox("");
        contactsPage.clickSearchButton();
        contactsPage.clickNewSupplierButton();
    }

    @Test
    public void shouldCreateSupplierWithOnlyRequiredFields() {
        String businessName = UUID.randomUUID().toString();
        NewContactDialog newContactDialog = new NewContactDialog((driver));
        newContactDialog.setBusinessNameTextBox(businessName);
        newContactDialog.clickSaveButton();
        newContactDialog.waitForSaveButtonToBeInvisible();

        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.setSearchTextBox(businessName);
        contactsPage.clickSearchButton();
        contactsPage.waitForNumberOfRecordsTextToEqual("1");
        contactsPage.validateContactsTableCellText(0,3,businessName);
        contactsPage.clickTableCell(0, 3);

        ContactPage contactPage = new ContactPage(driver);
        contactPage.validateContactTitleNameIs(businessName);
        contactPage.validateContactTitleTypeContains("Supplier");
    }

    @Test
    public void shouldCreateSupplierWithAllFieldsComplete() {
        String businessName = UUID.randomUUID().toString();
        String contactName = "Sheila Supplier";
        String reference = generateRandomAlphanumericString(10);
        String email = "techtestsupplier@sbca.com";
        String mobile = "07987654321";
        String telephone = "01918765432";

        // Inputs
        NewContactDialog newContactDialog = new NewContactDialog((driver));
        newContactDialog.setBusinessNameTextBox(businessName);
        newContactDialog.setContactNameTextBox(contactName);
        newContactDialog.setReferenceTextBox(reference);
        newContactDialog.setEmailTextBox(email);
        newContactDialog.setMobileTextBox(mobile);
        newContactDialog.setTelephoneTextBox(telephone);

        // Add other inputs here

        newContactDialog.clickSaveButton();
        newContactDialog.waitForSaveButtonToBeInvisible();

        // Find record in grid and click
        ContactsPage contactsPage = new ContactsPage(driver);
        contactsPage.setSearchTextBox(businessName);
        contactsPage.clickSearchButton();
        contactsPage.waitForNumberOfRecordsTextToEqual("1");
        contactsPage.validateContactsTableCellText(0,3,businessName);
        contactsPage.clickTableCell(0, 3);

        // Validate inputs on the contact page
        ContactPage contactPage = new ContactPage(driver);
        contactPage.validateContactTitleNameIs(businessName);
        contactPage.validateContactTitleTypeContains("Supplier");
        contactPage.validateContactTitleRefContains(reference);
        contactPage.validateAddressTileNameIs(contactName);
        contactPage.validateAddressTilePhoneIs(telephone);
        contactPage.validateAddressTileEmailIs(email);

        // Validate other inputs here
    }

    @Test
    public void shouldNotCreateSupplierWithBusinessNameOmitted() {
        NewContactDialog newContactDialog = new NewContactDialog((driver));
        newContactDialog.setBusinessNameTextBox("");
        newContactDialog.clickSaveButton();
        newContactDialog.waitForValidationSummaryErrorsToBeVisible();
    }

}

