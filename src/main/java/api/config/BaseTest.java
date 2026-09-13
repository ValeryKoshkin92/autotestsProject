package api.config;

import api.courier.CourierClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {
    protected String courierId;
    protected final CourierClient client = new CourierClient();

    static {
        RestAssured.filters(AllureFilterFactory.create());
    }

    @BeforeEach
    public void setUp() {
        baseURI = "https://qa-scooter.praktikum-services.ru";

    }

    @AfterEach
    public void deleteCourier() {
        if (courierId != null) {
            client.deleteCourier(courierId);
        }
    }
}