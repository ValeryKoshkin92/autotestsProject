package tests.api;

import api.config.BaseTest;
import api.order.OrderAssertions;
import api.order.OrderClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("backend. Проверки получения списка заказов")
public class GetOrderListTest extends BaseTest {

    @Test
    @DisplayName("Получние списка заказов")
    public void checkListOrderTest() {
        OrderClient orderClient = new OrderClient();
        OrderAssertions checkOrder = new OrderAssertions();
        checkOrder.getListOrders(orderClient.getOrdersList());
    }
}