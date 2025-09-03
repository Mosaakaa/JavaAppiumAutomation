package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String

            ONBOARD_SKIP_BUTTON = "org.wikipedia:id/closeButton",
            TITLE = "//android.widget.TextView",
            FOOTER_ELEMENT = "//android.widget.TextView[@text='View article in browser']",
            SAVE_BUTTON = "//android.widget.TextView[@content-desc='Save']",
            SNACKBAR_BUTTON = "org.wikipedia:id/snackbar_action",
            ADD_TO_MY_LIST_OVERLAY = "//*[@text='Got it']",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//android.widget.Button[@resource-id='android:id/button1']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_Element = waitForTitleElement();
        return title_Element.getAttribute("text");
    }

    public void skipArticleOnboarding()
    {
        this.waitForElementAndClick(By.id(ONBOARD_SKIP_BUTTON), "Cannot find button to skip article onboarding", 15);
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find button to save article",
                5
        );

        this.waitForElementAndClick(
                By.id(SNACKBAR_BUTTON),
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON ),
                "Cannot press 'OK' button",
                15
        );

        this.waitForElementAndClick(
                By.id(SNACKBAR_BUTTON),
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find close button",
                10
        );
    }
}
