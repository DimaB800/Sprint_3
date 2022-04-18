package model;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateCourier {

    public String login;
    public String password;
    public String firstName;

    public CreateCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourier() {
    }

    public CreateCourier setLogin(String login) {
        this.login = login;
        return this;
    }

    public CreateCourier setPassword(String password) {
        this.password = password;
        return this;
    }

    public CreateCourier setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public static CreateCourier getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new CreateCourier(login, password, firstName);
    }

    public static CreateCourier getCourierWithLoginAndFirstName() {
        return new CreateCourier().setLogin(RandomStringUtils.randomAlphabetic(10)).setFirstName(RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateCourier getCourierWithPasswordAndFirstName() {
        return new CreateCourier().setPassword(RandomStringUtils.randomAlphabetic(10)).setFirstName(RandomStringUtils.randomAlphabetic(10));
    }


    public static CreateCourier getCourierWithLoginAndPassword() {
        return new CreateCourier().setLogin(RandomStringUtils.randomAlphabetic(10)).setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateCourier getCourierWithLoginOnly() {
        return new CreateCourier().setLogin(RandomStringUtils.randomAlphabetic(10));
    }

    public static CreateCourier getCourierWithPasswordOnly() {
        return new CreateCourier().setPassword(RandomStringUtils.randomAlphabetic(10));
    }

}
