package tests;

import config.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@DisplayName("Создание заказа")
public class OrderStatusPageTest extends BaseTest {

    String nonexistentOrder = "1234";

    @Test
    @DisplayName("Поиск несуществующего заказа")
    public void checkSearchOfIncorrectOrder() {
        new MainPage(webDriver)
                .clickOrderStatusButton()
                .fillOrderNumberInput(nonexistentOrder)
                .clickGoButton()
                .checknotFoundContainer();
    }
}