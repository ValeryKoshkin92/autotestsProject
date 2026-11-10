package api.courier;

import io.qameta.allure.Step;

public class Steps {

    private final CourierAssertions check = new CourierAssertions();
    protected final CourierClient client = new CourierClient();

    @Step("Создаём курьера и получаем его id")
    public String createAndLoginCourier(Courier courier) {
        check.checkCreatedSuccessfully(client.createCourier(courier));
        Credentials creds = Credentials.getCourierCreds(courier);
        return check.loggedInSuccessfully(client.logIn(creds));
    }
}