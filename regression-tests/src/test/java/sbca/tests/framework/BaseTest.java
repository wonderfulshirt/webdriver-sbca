package sbca.tests.framework;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import sbca.pageobjects.login.LoginPage;

@Listeners(TestListener.class)
public class BaseTest {

    protected static WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        String path = System.getProperty("user.dir");
        System.out.println(path);

        String separator = System.getProperty("file.separator");

        // WHEN RUNNING WITH MVN
        System.setProperty("webdriver.chrome.driver", path + separator + "test-classes" + separator + "chromedriver.exe");

        // WHEN RUNNING WITH A TESTNG RUN CONFIG IN THE IDE
//        System.setProperty("webdriver.chrome.driver", path + separator + "target" + separator + "classes" + separator + "chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://app.sageone.com/login");
        loginPage.setEmailTextBox("rstraavaldson@mailinator.com");
        loginPage.setPasswordTextBox("P@55w0rd");
        loginPage.clickSubmitButton();
    }

    @AfterSuite
    public void afterSuite() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public void takeScreenshot(String testMethodName) {
        String workingDir = System.getProperty("user.dir");
        File screenshotsDir = new File(workingDir + "\\screenshots");
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "_" + testMethodName + ".jpg";

        try {
            if(!screenshotsDir.exists()) {
                screenshotsDir.mkdir();
            }

            FileUtils.copyFile(screenshot, new File(screenshotsDir + "\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomAlphanumericString(int length) {
        StringBuilder builder = new StringBuilder();
        while (length-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
