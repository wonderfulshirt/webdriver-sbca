package sbca.pageobjects.contacts;

import org.openqa.selenium.By;
import org.testng.Assert;
import sbca.pageobjects.framework.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage extends BasePage {

    @FindBy(css = "span[class='contact-header__title-name']")
    private WebElement contactTitleName;

    @FindBy(css = "span[class='contact-header__title-type']")
    private WebElement contactTitleType;

    @FindBy(css = "span[class='contact-header__title-ref']")
    private WebElement contactTitleRef;

    @FindBy(css = "div[class='carbon-detail address-tile__name carbon-detail--has-icon']")
    private WebElement addressTileName;

    @FindBy(css = "div[class='carbon-detail address-tile__phone carbon-detail--has-icon']")
    private WebElement addressTilePhone;

    @FindBy(css = "div[class='carbon-detail address-tile__email carbon-detail--has-icon']")
    private WebElement addressTileEmail;

    @FindBy(css = "div[class='carbon-detail address-tile__location carbon-detail--has-icon']")
    private WebElement addressTileLocation;

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void validateContactTitleNameIs(String text) {
        waitForElementTextToBe(contactTitleName, text);
    }

    public void validateContactTitleTypeContains(String text) {
        Assert.assertTrue(contactTitleType.getText().contains(text));
    }

    public void validateContactTitleRefContains(String text) {
        Assert.assertTrue(contactTitleRef.getText().contains(text));
    }

    public void validateAddressTileNameIs(String text) {
        waitForElementTextToBe(addressTileName.findElement(By.xpath(".//div[@class='carbon-detail__content']")), text);
    }

    public void validateAddressTilePhoneIs(String text) {
        Assert.assertEquals(addressTilePhone.findElement(By.xpath(".//span[@class='carbon-link__content']")).getAttribute("innerText"), text);
        //waitForElementTextToBe(addressTilePhone.findElement(By.xpath("//span[@class='carbon-link__content']")), text);
    }

    public void validateAddressTileEmailIs(String text) {
        Assert.assertEquals(addressTileEmail.findElement(By.xpath(".//span[@class='carbon-link__content']")).getAttribute("innerText"), text);
        //waitForElementTextToBe(addressTileEmail.findElement(By.xpath("//span[@class='carbon-link__content']")), text);
    }

    public void validateAddressTileLocationIs(String text) {
        Assert.assertEquals(addressTileLocation.findElement(By.xpath(".//a[@class='carbon-link__anchor address-title__link']")).getAttribute("innerText"), text);
        //waitForElementTextToBe(addressTileLocation.findElement(By.xpath("//a[@class='carbon-link__anchor address-title__link']")), text);
    }
}
