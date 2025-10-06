package ui;

import io.appium.java_client.AppiumDriver;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "//android.widget.TextView[@text='Search Wikipedia']",
            SEARCH_INPUT = "//android.widget.AutoCompleteTextView[@resource-id='org.wikipedia:id/search_src_text']",
            ONBOARDING_BUTTON = "//android.widget.Button[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']",
            SEARCH_CANCEL_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup",
            SEARCH_EMPTY_RESULT_ELEMENT = "//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup",
            SEARCH_RESULT_TITLE = "org.wikipedia:id/page_list_item_title",
            SEARCH_RESULT_FORE_SAVE = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Automation for Apps']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void skipOnboarding()
    {
        this.waitForElementAndClick(By.xpath(ONBOARDING_BUTTON), "Cannot find button to skip onboarding", 15);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot Find and click search init element", 15);

    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button!", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Search cancel button is still present!", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find and click saerch cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 15);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring" + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click search result with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
       this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15
        );
      return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void assertThereIsHaveResultOfSearch()
    {
        this.assertElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed to find any results", 15);
    }

    public List waitForListElementSearchResult()
    {
        this.waitForListElementsPresent(
                By.id(SEARCH_RESULT_TITLE),
                "cannot find search results",
                10
        );

        return this.waitForListElementSearchResult();
    }

    public void waitForClearSearchResult()
    {
        this.waitForElementAndClear(
                By.xpath(SEARCH_INPUT),
                "cannot find search input",
                10
        );
    }

    public void searchResultIsNotPresent()
    {
        this.waitForElementNotPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "search results are still present on the page",
                10
        );
    }

    public void clickByArticle()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_RESULT_FORE_SAVE),"Cannot find and click search result", 10);
    }
}
