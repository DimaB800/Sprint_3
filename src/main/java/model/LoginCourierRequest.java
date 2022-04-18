package model;

import org.apache.commons.lang3.RandomStringUtils;

public class LoginCourierRequest {
    public String login;
    public String password;


    public LoginCourierRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginCourierRequest from(CreateCourier createCourier) {
        return new LoginCourierRequest(createCourier.login, createCourier.password);
    }

    public static LoginCourierRequest getLoginCourierRequestWithInvalidLogin(CreateCourier createCourier) {
        createCourier.setLogin(RandomStringUtils.randomAlphabetic(5));
        return new LoginCourierRequest(createCourier.login, createCourier.password);
    }

    public static LoginCourierRequest getLoginCourierRequestWithInvalidPassword(CreateCourier createCourier) {
        createCourier.setPassword(RandomStringUtils.randomAlphabetic(5));
        return new LoginCourierRequest(createCourier.login, createCourier.password);
    }
}
