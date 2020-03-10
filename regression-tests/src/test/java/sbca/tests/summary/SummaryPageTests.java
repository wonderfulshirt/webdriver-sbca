package sbca.tests.summary;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import sbca.pageobjects.contacts.ContactsPage;
import sbca.pageobjects.global.NavigationMenu;
import sbca.pageobjects.summary.SummaryPage;
import sbca.tests.framework.BaseTest;
import sbca.tests.framework.TestListener;

import static org.testng.Assert.assertEquals;

@Listeners(TestListener.class)
public class SummaryPageTests extends BaseTest {

    @BeforeMethod
    public void shouldClickSummaryPageNavigationMenuItem() {
        NavigationMenu navigationMenu = new NavigationMenu(driver);
        navigationMenu.clickSummaryMenuItem();
    }

    @Test
    public void shouldFailOnValidationOfPageHeaderText() {
        SummaryPage summaryPage = new SummaryPage(driver);
        summaryPage.validatePageHeaderTextIs("THIS IS A DELIBERATELY FAILING TEST");
    }
}
