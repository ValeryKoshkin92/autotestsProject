package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderStatusPage {
    WebDriver webDriver;

    public OrderStatusPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    private By notFoundContainer = By.xpath(".//div[@class='Track_NotFound__6oaoY']");

    public void checknotFoundContainer() {
        boolean isDisplayed = webDriver.findElement(notFoundContainer).isDisplayed();
        assertTrue(isDisplayed);
    }
}