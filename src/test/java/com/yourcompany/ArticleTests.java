package com.yourcompany;

import lib.CoreTestCase;
import org.junit.jupiter.api.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.skipArticleOnboarding();
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Java (programming language)",
                article_title,
                "We see unexpected title!"
        );
    }

    @Test //4 урок
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.skipArticleOnboarding();
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }

}
