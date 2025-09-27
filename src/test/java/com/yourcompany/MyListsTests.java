package com.yourcompany;

import lib.CoreTestCase;
import org.junit.jupiter.api.Test;
import ui.ArticlePageObject;
import ui.MyListsPageObject;
import ui.SearchPageObject;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.skipArticleOnboarding();
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

        MyListsPageObject.swipeByArticleToDelete(article_title);

    }
}
