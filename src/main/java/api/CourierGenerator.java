package api;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class CourierGenerator {

    public Courier random() {
        return new Courier(randomAlphanumeric(5, 10), "132456", "first");
    }
}