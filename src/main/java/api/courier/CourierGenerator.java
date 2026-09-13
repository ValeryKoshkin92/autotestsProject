package api.courier;

import io.qameta.allure.Step;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class CourierGenerator {

    @Step("Генерируем курьера")
    public Courier random() {
        return new Courier(randomAlphanumeric(5, 10), "132456", "first");
    }
}