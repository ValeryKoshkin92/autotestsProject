package api.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient {

    static private final String orders = "api/v1/orders";


    public ValidatableResponse createOrder(Order order){
        return given().log().all()
                .body(order)
                .when()
                .post(orders)
                .then();
    }

    @Step("Получаем список заказов")
    public ValidatableResponse getOrdersList(){
        return given().log().all()
                .when()
                .get(orders)
                .then()
                .assertThat();
    }
}