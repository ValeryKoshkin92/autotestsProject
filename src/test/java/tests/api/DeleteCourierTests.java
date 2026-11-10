package tests.api;

import api.config.BaseTest;
import api.courier.CourierAssertions;
import api.courier.CourierGenerator;
import api.courier.Credentials;
import api.courier.Steps;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DeleteCourierTests extends BaseTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierAssertions check = new CourierAssertions();
    private final Steps steps = new Steps();


    @Test
    @DisplayName("Успешное удаление курьера")
    public void deleteCourierSuccessfully(){
        var courier = generator.random();
        Credentials creds = Credentials.getCourierCreds(courier);

        courierId = steps.createAndLoginCourier(courier);
        client.deleteCourier(courierId);
        check.loggedInUnsuccesfullyUserNotExist(client.logIn(creds));
    }

    @Test
    @DisplayName("Проверка вызова /delete без ID")
    public void deleteCourierWithoutId(){
        ValidatableResponse response = client.deleteCourierWithoutId();
        check.checkUserNotFound(response);
    }

    @Test
    @DisplayName("Проверка удаления несуществующего юзера")
    public void deleteCourierNotExist(){
        ValidatableResponse response = client.deleteCourier("23456789");
        check.checkUserNotExist(response);
    }
}