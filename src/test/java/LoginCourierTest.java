import client.ApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CreateCourier;
import model.LoginCourierRequest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginCourierTest {
    private CreateCourier createCourier;
    private ApiClient apiClient;
    private int courierId;


    @Before
    public void setUp() {
        apiClient = new ApiClient();
        createCourier = CreateCourier.getRandom();
        apiClient.createCourier(createCourier);
    }

    @After
    public void deleteCourier() {
            ValidatableResponse response = apiClient.deleteCourier(courierId);
            response.assertThat().body("ok", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Проверка авторизации курьера")
    public void loginCourier() {
        ValidatableResponse response = apiClient.login(LoginCourierRequest.from(createCourier));
        int statusCode = response.extract().statusCode();
        courierId = response.extract().path("id");
        Assert.assertEquals(statusCode, 200);
        assertThat("Ожидаемый результат не соответствует фактическому", courierId, is(not(0)));
    }
}
