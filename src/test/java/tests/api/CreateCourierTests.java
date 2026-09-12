package tests.api;

import api.config.BaseTest;
import api.CourierAssertions;
import api.CourierGenerator;
import api.Credentials;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.parseInt;

public class CreateCourierTests extends BaseTest {

    private final CourierGenerator generator = new CourierGenerator();
    // private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("Успешное создание курьера")
    public void createCourierSuccessfulTest() {
        var courier = generator.random();
        check.createdSuccessfully(client.createCourier(courier));
        Credentials creds = Credentials.from(courier);
        courierId = check.loggedInSuccessfully(client.logIn(creds));
    }

    @Test
    @DisplayName("Попытка создания существующего курьера")
    public void createDoubleCouriers() {

        var courier = generator.random();
        check.createdSuccessfully(client.createCourier(courier));
        check.createdUnsuccesfullyDouble(client.createCourier(courier));

        Credentials creds = Credentials.from(courier);
        courierId = check.loggedInSuccessfully(client.logIn(creds));
        assert parseInt(courierId) != 0;
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierWithoutPassword() {
        var courier = generator.random();
        courier.setPassword("");
        check.createdUnsuccesfullyPassword(client.createCourier(courier));
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void createCourierWithoutLogin() {
        var courier = generator.random();
        courier.setLogin("");
        check.createdUnsuccesfullyPassword(client.createCourier(courier));
    }

    @AfterEach
    public void deleteCourier() {
        if (courierId != null) {
            client.deleteCourier(courierId);
        }
    }
}