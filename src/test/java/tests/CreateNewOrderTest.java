package tests;

import config.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;

import java.util.stream.Stream;

import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CreateNewOrderTest extends BaseTest {

    static Stream<Arguments> getTestData() {
        String dateFromToday = now().format(ofPattern("d"));
        String dateFromTomorrow = now().plusDays(1).format(ofPattern("d"));
        return Stream.of(
                arguments("Иванов", "Иван", "ул. Мира 16-34", "+79124445533", dateFromToday, "сутки", "Оплата картой", "orderButtonTop"),
                arguments("Пётр", "Петров", "ул. Никольская 20-77", "+79224785413", dateFromTomorrow, "двое суток", "Оплата наличными", "orderButtonDown")
        );
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    @DisplayName("Создание нового заказа")
    public void createNewOrder(String firstName, String lastName, String address, String phone, String dateFrom, String rentPeriod, String courierComment, String orderButton) {

        new MainPage(webDriver)
                .clickOrderButton(orderButton)
                .setUserData(firstName, lastName, address, phone)
                .clickMetroStation()
                .nextClick()
                .setOrderOptions(dateFrom, rentPeriod, courierComment)
                .checkSuccessWindow();
    }
}