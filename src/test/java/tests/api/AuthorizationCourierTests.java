package tests.api;

import api.courier.CourierAssertions;
import api.courier.CourierClient;
import api.courier.CourierGenerator;
import api.courier.Credentials;
import api.config.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.parseInt;

public class AuthorizationCourierTests extends BaseTest {
    String courierId;

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("backend. Успешная авторизация курьера")
    public void authorizationCourierSuccessfully() {
        var courier = generator.random();
        check.checkCreatedSuccessfully(client.createCourier(courier));
        Credentials creds = Credentials.getCourierCreds(courier);
        courierId = check.loggedInSuccessfully(client.logIn(creds));
        assert parseInt(courierId) != 0;
    }

    @Test
    @DisplayName("Попытка авторизации курьера без логина")
    public void authorizationCourierWithoutLogin() {
        var courier = generator.random();
        client.createCourier(courier);

        String password = courier.getPassword();

        Credentials creds = new Credentials(password);
        check.loggedInUnsuccessfully(client.logIn(creds));
    }

    @Test
    @DisplayName("Попытка авторизации курьера с некорректным логином")
    public void authorizationCourieraWithIncorrectCreds() {
        var courier = generator.random();
        client.createCourier(courier);

        courier.setLogin("incorrectLogin");
        Credentials creds = Credentials.getCourierCreds(courier);
        check.loggedInUnsuccesfullyUserNotExist(client.logIn(creds));
    }

    @AfterEach
    public void deleteCourier() {
        if (courierId != null) {
            client.deleteCourier(courierId);
        }
    }
}