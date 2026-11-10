package tests.api;

import api.config.BaseTest;
import api.courier.CourierAssertions;
import api.courier.CourierGenerator;
import api.order.Order;
import api.order.OrderAssertions;
import api.order.OrderClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TakeOrderTest extends BaseTest {

    private String firstName = "First";
    private String lastName = "Last";
    private String address = "Moscow";
    private int metroStation = 4;
    private String phone = "50511123";
    private int rentTime = 5;
    private String deliveryDate = "2024/05/05";
    private String comment = "comment";
    private String[] color = {"GREY"};

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("Успешное принятие заказа")
    public void deleteCourierSuccessfully(){
        var courier = generator.random();
        check.checkCreatedSuccessfully(client.createCourier(courier));

        OrderClient orderClient = new OrderClient();
        OrderAssertions checkOrder = new OrderAssertions();

        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        checkOrder.createdOrderSuccesfully(orderClient.createOrder(order));

    }
}