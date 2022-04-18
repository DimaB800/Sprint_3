import client.ApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.LoginCourierRequest;
import model.CreateCourier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class LoginCourierParameterizedTest {
    private final String displayName;
    private final LoginCourierRequest loginCourierRequest;
    private final int expectedCodeStatus;
    private final String expectedMessage;

    public LoginCourierParameterizedTest(String displayName, LoginCourierRequest loginCourierRequest, int expectedCodeStatus, String expectedMessage) {
        this.displayName = displayName;
        this.loginCourierRequest = loginCourierRequest;
        this.expectedCodeStatus = expectedCodeStatus;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[] getTestData() {
        return new Object[][]{
                {"Проверка авторизации незарегистрированного курьера", LoginCourierRequest.from(CreateCourier.getCourierWithLoginAndPassword()), 404, "Учетная запись не найдена"},
                {"Проверка авторизации с некорректным логином", LoginCourierRequest.getLoginCourierRequestWithInvalidLogin(CreateCourier.getCourierWithLoginAndPassword()), 404, "Учетная запись не найдена"},
                {"Проверка авторизации с некорректныи паролем", LoginCourierRequest.getLoginCourierRequestWithInvalidPassword(CreateCourier.getCourierWithLoginAndPassword()), 404, "Учетная запись не найдена"},
                {"Проверка авторизации только с логином", LoginCourierRequest.from(CreateCourier.getCourierWithLoginOnly()), 400, "Недостаточно данных для входа"},
                {"Проверка авторизации только с паролем", LoginCourierRequest.from(CreateCourier.getCourierWithPasswordOnly()), 400, "Недостаточно данных для входа"}
        };
    }

    @Test
    public void courierLoginWithInvalidData() {

        ValidatableResponse response = new ApiClient().login(loginCourierRequest);
        int actualCodeStatus = response.extract().statusCode();
        Assert.assertEquals("Ожидаемый результат не соответствует фактическому:", actualCodeStatus, expectedCodeStatus);
        String actualMessage = response.extract().path("message");
        Assert.assertEquals("Ожидаемый результат не соответствует фактическому:", actualMessage, expectedMessage);

    }


}