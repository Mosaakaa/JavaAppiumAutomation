package com.yourcompany;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;
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
        }

    }

    @Test //4 урок
    public void testSwipeArticle()
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
                "Appium",
                "cannot find search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Automation for Apps']"),
                "Cannot find 'Appium' article in search",
                30
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Appium']"),
                "Cannot find article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//android.widget.TextView[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );


    }

    @Test
    public void saveFirstArticleToMyList()
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

        waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find button to save article",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find option to add article to reading list",
                15
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find option to add article to reading list",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Got it']"),
                "Cannot find close button",
                10
        );

        swipeElementToLeft(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot delete savewd article",
                5
        );

    }

    @Test
    public void testAmountOfNotEmptySearch()
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

        String search_line = "Linkin Park Discography";

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        assertTrue(
                amount_of_search_results > 0,
                "We found too few results!"
        );


    }

    @Test
    public void testAmountOfEmptySearch()
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

        String search_line = "ghghgh";

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "cannot find search input",
                5
        );

        String empty_result_label = "//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";

                waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result by the request" + search_line,
                15
        );

        assertElementNotPresent(
                By.xpath(empty_result_label),
                "We found same results by request" + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                15
        );

        String search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "cannot find search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by" + search_line,
                30
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        String title_before_rotation = waitForeElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForeElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        assertEquals(
                title_before_rotation,
                title_after_rotation,
                "Unexpected title after rotation"
        );

        ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = waitForeElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        assertEquals(
                title_before_rotation,
                title_after_second_rotation,
                "Unexpected title after rotation"
        );
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

    protected void swipeUp(int timeOfSwipe) {
        // Получаем размеры экрана
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        // Создаем объект PointerInput для имитации касания
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO,
                        PointerInput.Origin.viewport(), x, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(timeOfSwipe + 100),
                        PointerInput.Origin.viewport(), x, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Выполняем свайп
        driver.perform(Collections.singletonList(swipe));
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++ already_swiped;
        }

    }

    protected void swipeElementToLeft (By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        // Создаем последовательность действий
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        // 1. Перемещаем палец в начальную позицию
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), right_x, middle_y));
        // 2. Нажимаем пальцем на экран
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // 4. Перемещаем палец в конечную позицию
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(200),
                PointerInput.Origin.viewport(), left_x, middle_y));

        // 5. Отжимаем палец от экрана
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Выполняем последовательность действий
        driver.perform(Collections.singletonList(swipe));

    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 1) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);

        }
    }

    private String waitForeElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }




}