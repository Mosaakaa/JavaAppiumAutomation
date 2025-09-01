package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String

            ONBOARD_SKIP_BUTTON = "org.wikipedia:id/closeButton",
            TITLE = "//android.widget.TextView",
            FOOTER_ELEMENT = "//android.widget.TextView[@text='View article in browser']";

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
}
