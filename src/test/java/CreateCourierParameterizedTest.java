import client.ApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.CreateCourier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateCourierParameterizedTest {
    private final String displayName;
    private final int expectedCodeStatus;
    private final String expectedMessage;
    private final CreateCourier createCourier;

    public CreateCourierParameterizedTest(String displayName, CreateCourier createCourier, int expectedCodeStatus, String expectedMessage) {
        this.displayName = displayName;
        this.expectedCodeStatus = expectedCodeStatus;
        this.expectedMessage = expectedMessage;
        this.createCourier = createCourier;
    }

    @Parameterized.Parameters(name = "{0}")
    public static  Object[] getTestData() {
        return new Object[][]{
                {"Проверка создания курьера без указания пароля", CreateCourier.getCourierWithLoginAndFirstName(), 400, "Недостаточно данных для создания учетной записи"},
                {"Проверка создания курьера без указания имени", CreateCourier.getCourierWithLoginAndPassword(), 400, "Недостаточно данных для создания учетной записи"},
                {"Проверка создания курьера без указания логина", CreateCourier.getCourierWithPasswordAndFirstName(), 400, "Недостаточно данных для создания учетной записи"},
        };
    }

    @Test
   // @DisplayName("Проверка обязательности полей при создании курьера")
    public void createCourierWithInvalidData() {
        ValidatableResponse response = new ApiClient().createCourier(createCourier);
        int actualCodeStatus = response.extract().statusCode();
        String actualMessage = response.extract().path("message");
        Assert.assertEquals("Ожидаемый результат не соответствует фактическому:", expectedCodeStatus, actualCodeStatus);
        Assert.assertEquals("Ожидаемый результат не соответствует фактическому:", expectedMessage, actualMessage);
    }
}
