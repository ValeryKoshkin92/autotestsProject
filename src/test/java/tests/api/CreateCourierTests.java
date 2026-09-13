package tests.api;

import api.courier.Courier;
import api.config.BaseTest;
import api.courier.CourierAssertions;
import api.courier.CourierGenerator;
import api.courier.Credentials;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

@DisplayName("backend. Проверки создания курьера")
public class CreateCourierTests extends BaseTest {

    private final CourierGenerator generator = new CourierGenerator();
    // private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("Успешное создание курьера")
    public void createCourierSuccessfulTest() {
        var courier = generator.random();
        check.checkCreatedSuccessfully(client.createCourier(courier));
    }

    @Test
    @DisplayName("Повторное создание курьера")
    public void createDoubleCouriers() {

        var courier = generator.random();
        check.checkCreatedSuccessfully(client.createCourier(courier));
        check.createdUnsuccesfullyDouble(client.createCourier(courier));

        Credentials creds = Credentials.getCourierCreds(courier);
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

    @ParameterizedTest(name = "Создание курьера без {0}")
    @MethodSource("invalidCourierProvider")
    @DisplayName("Создание курьера с пустым обязательным полем")
    public void createCourierWithEmptyField(String fieldName, Consumer<Courier> mutator) {
        var courier = generator.random();
        mutator.accept(courier);
        check.createdUnsuccesfullyPassword(client.createCourier(courier));
    }

    static Stream<Arguments> invalidCourierProvider() {
        return Stream.of(
                Arguments.of("пароля", (Consumer<Courier>) c -> c.setPassword("")),
                Arguments.of("логина",  (Consumer<Courier>) c -> c.setLogin(""))
        );
    }

    @AfterEach
    public void deleteCourier() {
        if (courierId != null) {
            client.deleteCourier(courierId);
        }
    }
}