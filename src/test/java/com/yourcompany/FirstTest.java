package com.yourcompany;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach; // Для JUnit 5
import org.junit.jupiter.api.BeforeEach; // Для JUnit 5
import org.junit.jupiter.api.Test; // Для JUnit 5
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest {
    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "13");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/Tkachenko.EA3/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org_wikipedia.apk");

        driver = new AndroidDriver(new URL("http://10.10.243.106:4723"), capabilities);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


//    @Test
//    public void firstTest() {
//        waitForElementAndClick(
//                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
//                "Cannot skip onboarding",
//                5
//        );
//
//        waitForElementAndClick(
//                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
//                "Cannot find Search Wikipedia input",
//                5
//        );
//
//        waitForElementAndSendKeys(
//                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
//                "Java",
//                "cannot find search input",
//                5
//        );
//
//        waitForElementPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
//                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
//                15
//        );
//    }
//
    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find onboarding button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "cannot find search input",
                15
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "cannot find x to cancel search",
                5
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "X is still present on the page",
                5
        );

    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "cannot find search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                30
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");

        assertEquals(
                "Java (programming language)",
                article_title,
                "We see unexpected title!"
        );
    }

    @Test //дз Ex2 Создание метода
    public void testSearchInputHasAText()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        assertElementHasText(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Search Wikipedia",
                "not expected text!",
                5
        );
    }

    @Test //дз Ex3: Тест: отмена поиска
    public void testFindSearchResultAndEndSurch()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "test",
                "cannot find Search Wikipedia input",
                15
        );

        List<WebElement> searchResults = waitForListElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "cannot find search results",
                10
        );

        assertFalse(searchResults.isEmpty(),
               "Search results should not be empty" );
        assertTrue(searchResults.size()>1,
        "search results<1");

        waitForElementAndClear(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "cannot find search input",
                10
        );

        waitForElementNotPresent(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[1]"),
                "search results are still present on the page",
                10
        );

    }

    @Test //ДЗ Ex4 Тест проверка слов в поиске
    public void testFindSearchResult() {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "java",
                "cannot find search input",
                15
        );

        List<WebElement> searchResults = waitForListElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "cannot find search results",
                10
        );

        String searchWord = "Java";

        for (WebElement element : searchResults) {
            String elementText = element.getText();
            if (!elementText.contains(searchWord)) {
                throw new AssertionError("Not all elements contains search Word");
            }
        };

    }


private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
{
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
}

private WebElement waitForElementPresent(By by, String error_message)
{
    return waitForElementPresent(by, error_message, 5);
}

private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
{
    WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
    element.click();
    return element;
}

private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent (By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    //дз Ex2 Создание метода
    private WebElement assertElementHasText(By by, String expected_text , String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
       String expectedText = element.getAttribute("text");
        assertEquals(
                expected_text,
                expectedText,
                "error_message"
        );
        return element;
    }

    private List<WebElement> waitForListElementsPresent (By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(by)
        );
    }

}