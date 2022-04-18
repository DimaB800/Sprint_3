import client.ApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CreateCourier;
import model.LoginCourierRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {

    private CreateCourier createCourier;
    private ApiClient apiClient;
    private int courierId;

    @Before
    public void setUp() {
        apiClient = new ApiClient();
        createCourier = CreateCourier.getRandom();
    }

    @After
    public void deleteCourier() {
        ValidatableResponse response = apiClient.deleteCourier(courierId);
        response.assertThat().body("ok", equalTo(true)).and().statusCode(200);
    }

    @Test
    @DisplayName("Проверка создания курьера")
    public void createCourier() {

        ValidatableResponse response = apiClient.createCourier(createCourier);
        courierId = apiClient.login(LoginCourierRequest.from(createCourier)).extract().path("id");
        response.assertThat().body("ok", equalTo(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("Проверка, что нельзя создать двух одинаковых курьеров")
    public void createIdenticalCourier() {

        apiClient.createCourier(createCourier);
        courierId = apiClient.login(LoginCourierRequest.from(createCourier)).extract().path("id");
        ValidatableResponse response = apiClient.createCourier(createCourier);

        int statusCode = response.extract().statusCode();
        String messageError = response.extract().path("message");

        Assert.assertEquals("Ожидаемый результат не соответствует фактическому", statusCode, 409);
        Assert.assertEquals("Ожидаемый результат не соответствует фактическому", messageError, "Этот логин уже используется. Попробуйте другой.");
    }

    @Test
    @DisplayName("Проверка, что нельзя создать курьера с существующим логином")
    public void createCourierIdenticalLogin() {

        apiClient.createCourier(createCourier);
        courierId = apiClient.login(LoginCourierRequest.from(createCourier)).extract().path("id");
        createCourier.setPassword(RandomStringUtils.randomAlphabetic(10));
        createCourier.setFirstName(RandomStringUtils.randomAlphabetic(10));

        ValidatableResponse response = apiClient.createCourier(createCourier);

        int statusCode = response.extract().statusCode();
        String messageError = response.extract().path("message");

        Assert.assertEquals("Ожидаемый результат не соответствует фактическому", statusCode, 409);
        Assert.assertEquals("Ожидаемый результат не соответствует фактическому", messageError, "Этот логин уже используется. Попробуйте другой.");
    }

}