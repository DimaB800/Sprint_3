import client.ApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    public ApiClient apiClient;

    @Before
    public void setUp() {
        apiClient = new ApiClient();
    }

    @DisplayName("Проверка что список заказов не пустой")
    @Test
    public void oderListIsNotNull() {

        ValidatableResponse response = apiClient.takeOrder();
        response.assertThat().statusCode(200).and().body("orders", notNullValue());
    }
}