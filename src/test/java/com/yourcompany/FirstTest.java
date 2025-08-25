package com.yourcompany;

import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import ui.MainPageObject;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    @BeforeEach
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'java'",
                15
        );
    }

    @Test
    public void testCancelSearch()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot find onboarding button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "cannot find 'search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "cannot find search input",
                15
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "cannot find x to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "X is still present on the page",
                5
        );

    }

    @Test
    public void testCompareArticleTitle()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "cannot find search input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                30
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        WebElement title_element = MainPageObject.waitForElementPresent(
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
        MainPageObject.waitForElementAndClick(
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
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "test",
                "cannot find Search Wikipedia input",
                15
        );

        List<WebElement> searchResults = MainPageObject.waitForListElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "cannot find search results",
                10
        );

        assertFalse(searchResults.isEmpty(),
               "Search results should not be empty" );
        assertTrue(searchResults.size()>1,
        "search results<1");

        MainPageObject.waitForElementAndClear(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "cannot find search input",
                10
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[1]"),
                "search results are still present on the page",
                10
        );

    }

    @Test //ДЗ Ex4 Тест проверка слов в поиске
    public void testFindSearchResult() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "java",
                "cannot find search input",
                15
        );

        List<WebElement> searchResults = MainPageObject.waitForListElementsPresent(
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
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Appium",
                "cannot find search input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Automation for Apps']"),
                "Cannot find 'Appium' article in search",
                30
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Appium']"),
                "Cannot find article title",
                15
        );

        MainPageObject.swipeUpToFindElement(
                By.xpath("//android.widget.TextView[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );


    }

    @Test
    public void testSaveFirstArticleToMyList()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "cannot find search input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                30
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find button to save article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find option to add article to reading list",
                15
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning programming",
                "Cannot put text into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Cannot find option to add article to reading list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Got it']"),
                "Cannot find close button",
                10
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot delete savewd article",
                5
        );

    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "Linkin Park Discography";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";

        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request" + search_line,
                15
        );

        int amount_of_search_results = MainPageObject.getAmountOfElements(
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
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "ghghgh";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "cannot find search input",
                5
        );

        String empty_result_label = "//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";

        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result by the request" + search_line,
                15
        );

        MainPageObject.assertElementNotPresent(
                By.xpath(empty_result_label),
                "We found same results by request" + search_line
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                15
        );

        String search_line = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "cannot find search input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by" + search_line,
                30
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        String title_before_rotation = MainPageObject.waitForeElementAndGetAttribute(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "text",
                "Cannot find title of article",
                15
        );

        ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.waitForeElementAndGetAttribute(
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

        String title_after_second_rotation = MainPageObject.waitForeElementAndGetAttribute(
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

    @Test
    public void testCheckSearchArticleInBackground()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "cannot find search input",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                30
        );

        ((AndroidDriver) driver).runAppInBackground(Duration.ofSeconds(2));

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning background",
                30
        );

    }

    @Test //ДЗ Ex6 Тест assert title
    public void testArticleTitleIsPresent()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                5
        );

        String search_line = "java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']"),
                search_line,
                "cannot find search input",
                30
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by" + search_line,
                30
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        MainPageObject.assertElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find title of article",
                30);


    }

    @Test
    public void testSaveTwoArticleToMyList() //ДЗ Ex5 Тест сохранение двух статей.txt
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
                "Cannot skip onboarding",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Search Wikipedia']"),
                "Cannot find Search Wikipedia input",
                30
        );

        String search_input = "//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath(search_input),
                "Java",
                "cannot find search input",
                15
        );

        String java_article_search_result = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Object-oriented programming language']";

        MainPageObject.waitForElementAndClick(
                By.xpath(java_article_search_result),
                "Cannot find Search Wikipedia input",
                30
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/closeButton"),
                "Cannot find close Button",
                30
        );

        String java_article_title = "//android.widget.TextView[@text='Java (programming language)']";

        MainPageObject.waitForElementPresent(
                By.xpath(java_article_title),
                "Cannot find article title",
                15
        );

        String save_button = "//android.widget.TextView[@content-desc='Save']";

        MainPageObject.waitForElementAndClick(
                By.xpath(save_button),
                "Cannot find button to save article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_toolbar_button_search"),
                "Cannot find Search Wikipedia input on article page",
                15
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath(search_input),
                "appium",
                "Cannot find Search Wikipedia input on article page",
                30
        );

        String appium_article_search_result = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Automation for Apps']";

        MainPageObject.waitForElementAndClick(
                By.xpath(appium_article_search_result),
                "Cannot find Search Wikipedia input",
                30
        );

        String appium_article_title = "(//android.widget.TextView[@text='Appium'])[1]";

        MainPageObject.waitForElementPresent(
                By.xpath(appium_article_title),
                "Cannot find article title",
                15
        );

        String title_before_saved = MainPageObject.waitForeElementAndGetAttribute(
                By.xpath(appium_article_title),
                "text",
                "Cannot find title of article",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath(save_button),
                "Cannot find button to save article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu"),
                "Cannot find toolbar button",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_explore"),
                "Cannot find explore button",
                15
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/main_toolbar"),
                "Cannot see main page",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find saved-page button",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("(//android.widget.TextView[@text='Saved'])"),
                "Cannot see saved page",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/recycler_view']/android.view.ViewGroup"),
                "Cannot find saved group button",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/buttonView"),
                "Cannot find skiped onboarding button",
                15
        );

        MainPageObject.swipeElementToLeft(
                By.xpath(java_article_title),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementAndClick(
                By.xpath(appium_article_title),
                "Cannot find article button",
                15
        );

        String title_after_saved = MainPageObject.waitForeElementAndGetAttribute(
                By.xpath(appium_article_title),
                "text",
                "Cannot find title of article",
                30
        );

        assertEquals(
                title_before_saved,
                title_after_saved,
                "Unexpected title after saved"
        );

    }

    //дз Ex2 Создание метода
    private WebElement assertElementHasText(By by, String expected_text, String error_message, long timeoutInSeconds) {
        WebElement element = MainPageObject.waitForElementPresent(by, error_message, timeoutInSeconds);
        String expectedText = element.getAttribute("text");
        assertEquals(
                expected_text,
                expectedText,
                "error_message"
        );
        return element;
    }








    }

