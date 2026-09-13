package tests.api;

import api.config.BaseTest;

import api.order.Order;
import api.order.OrderAssertions;
import api.order.OrderClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

@DisplayName("backend. Проверки создания заказа")
public class CreateOrderTest extends BaseTest {
    private String firstName = "First";
    private String lastName = "Last";
    private String address = "Moscow";
    private int metroStation = 4;
    private String phone = "50511123";
    private int rentTime = 5;
    private String deliveryDate = "2024/05/05";
    private String comment = "comment";
    private String[] color;

    static Stream<Arguments> getTestData(){
        return Stream.of(
                Arguments.arguments((Object) new String[]{"BLACK", "GRAY"}),
                Arguments.arguments((Object) new String[]{"BLACK"}),
                Arguments.arguments((Object) new String[]{"GRAY"}),
                Arguments.arguments((Object) new String[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    @DisplayName("Создание нового заказа с разными параметрами")
    public void createNewOrder(String[] color) {
        OrderClient orderClient = new OrderClient();
        OrderAssertions checkOrder = new OrderAssertions();
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        checkOrder.createdOrderSuccesfully(orderClient.createOrder(order));
    }
}