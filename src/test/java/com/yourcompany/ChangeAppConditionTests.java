package com.yourcompany;

import lib.CoreTestCase;
import org.junit.jupiter.api.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation =  ArticlePageObject.getArticleTitle();

        assertEquals(
                title_before_rotation,
                title_after_rotation,
                "Unexpected title after rotation"
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                title_before_rotation,
                title_after_second_rotation,
                "Unexpected title after rotation"
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
