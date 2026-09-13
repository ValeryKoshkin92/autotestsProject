package tests.ui;

import ui.config.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.MainPage;

@DisplayName("Проверки страницы статуса заказа")
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