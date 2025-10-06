package com.yourcompany;

import lib.CoreTestCase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.ArticlePageObject;
import ui.MainPageObject;
import ui.MyListsPageObject;
import ui.SearchPageObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstTest extends CoreTestCase {

    @Test //дз Ex2 Создание метода
    public void testSearchInputHasAText()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();

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
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        List<WebElement> searchResults = SearchPageObject.waitForListElementSearchResult();

        assertFalse(searchResults.isEmpty(),
               "Search results should not be empty" );
        assertTrue(searchResults.size()>1,
        "search results<1");

        SearchPageObject.waitForClearSearchResult();
        SearchPageObject.searchResultIsNotPresent();

    }

    @Test //ДЗ Ex4 Тест проверка слов в поиске
    public void testFindSearchResult() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        List<WebElement> searchResults = SearchPageObject.waitForListElementSearchResult();

        String searchWord = "Java";

        for (WebElement element : searchResults) {
            String elementText = element.getText();
            if (!elementText.contains(searchWord)) {
                throw new AssertionError("Not all elements contains search Word");
            }
        }

    }

    @Test //ДЗ Ex6 Тест assert title
    public void testArticleTitleIsPresent()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();

        String search_line = "java";

        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();

        SearchPageObject.assertThereIsHaveResultOfSearch();
    }

    @Test
    public void testSaveTwoArticleToMyList() //ДЗ Ex5 Тест сохранение двух статей.txt
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.skipArticleOnboarding();
        ArticlePageObject.waitForTitleElement();
        String java_article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.SearchFromArticle();

        SearchPageObject.clickByArticle();

        String appium_article_title = ArticlePageObject.getArticleTitle();;

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

