package config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import static config.AppConfig.APP_MAIN_PAGE_URL;

public class BaseTest {
    public static WebDriver webDriver;
    String CHROME = "chrome";
    String FIREFOX = "firefox";
    String EDGE = "edge";

    @BeforeEach
    public void init() {
        webDriver = WebDriverFactory.getBrowser(EDGE);

        webDriver.get(APP_MAIN_PAGE_URL);
        System.out.println("Current URL for cookie: " + webDriver.getCurrentUrl());
        Cookie newCookie = new Cookie.Builder("Cartoshka", "true")
                .domain("qa-scooter.praktikum-services.ru")
                .path("/")
                .build();
        webDriver.manage().addCookie(newCookie);

        // При необходимости обновляем страницу, чтобы сервер увидел куку
        webDriver.navigate().refresh();
    }

    @AfterEach
    public void close() {
        webDriver.quit();
    }
}