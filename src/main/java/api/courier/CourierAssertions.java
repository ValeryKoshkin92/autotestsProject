package api.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierAssertions {

    @Step("Проверяем успешное создание курьера")
    public void checkCreatedSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    @Step("Проверяем успешный логин курьера")
    public String loggedInSuccessfully(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("id", notNullValue())
                .extract()
                .path("id").toString();
    }

    @Step("Проверяем, что повторное создание не прошло")
    public void createdUnsuccesfullyDouble(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .extract()
                .path("message");
    }

    @Step("Проверяем, не хватает пароля")
    public void createdUnsuccesfullyPassword(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .extract()
                .path("message");
    }

    @Step("Проверяем неуспешный логин")
    public void loggedInUnsuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Step("Проверяем что пользователь не найден")
    public void loggedInUnsuccesfullyUserNotExist(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}