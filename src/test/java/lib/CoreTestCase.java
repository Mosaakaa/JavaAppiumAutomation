package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http://10.10.243.106:4723";

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

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);

        ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT); // ДЗ Ex7*: Поворот экрана - надо сделать так, чтобы все тесты начинались с портретной ориентации экрана
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
