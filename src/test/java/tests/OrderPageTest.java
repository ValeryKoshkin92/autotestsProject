package tests;

import config.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@DisplayName("Страница заказа")
public class OrderPageTest extends BaseTest {

    String incorrectFirstName = "John";
    String incorrectLastName = "Smith";
    String incorrectAddress = "Moscow";
    String incorrectPhone = "7512";
    String orderButton = "orderButtonTop";

    @Test
    @DisplayName("Некорректные данные")
    public void checkErrorInputs() {
        new MainPage(webDriver)
                .clickOrderButton(orderButton)
                .setUserData(incorrectFirstName, incorrectLastName, incorrectAddress, incorrectPhone)
                .nextClick()
                .checkInputValidation();
    }
}