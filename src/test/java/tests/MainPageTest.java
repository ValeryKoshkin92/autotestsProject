package tests;

import config.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

@DisplayName("Тест сьют")
public class MainPageTest extends BaseTest {
    String yandexURL = "https://dzen.ru/?yredirect=true";
    String orderButton = "orderButtonTop";

    @Test
    @DisplayName("Клип по лого Яндеса")
    public void clickYandexLogoTest() {
        MainPage mainPage = new MainPage(webDriver);

        //Переключаемся на открытую вкладку. Код взят с оф. документации Selenium
        String originalWindow = webDriver.getWindowHandle();
        assert webDriver.getWindowHandles().size() == 1;
        mainPage.clickYandexLogo();

        new WebDriverWait(webDriver, ofSeconds(5))
                .until(numberOfWindowsToBe(2));

        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(urlToBe(yandexURL));
    }
}