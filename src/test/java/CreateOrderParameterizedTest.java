import client.ApiClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Order;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {
    private final String displayName;
    private final Order order;
    private final int expectedCodeStatus;

    public CreateOrderParameterizedTest(String displayName, Order order, int expectedCodeStatus) {
        this.displayName = displayName;
        this.order = order;
        this.expectedCodeStatus = expectedCodeStatus;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[] getTestData() {
        return new Object[][]{
                {"Проверка создания заказа с указанием двух цветов", Order.getOrderWithColor(new String[]{"BLACK", "GREY"}), 201},
                {"Проверка создания заказа без указания цвета", Order.getOrderWithoutColor(), 201},
                {"Проверка создания заказа с указанием черного цвета", Order.getOrderWithColor(new String[]{"BLACK"}), 201},
                {"Проверка создания заказа с указанием серого цветов", Order.getOrderWithColor(new String[]{"GREY"}), 201}
        };
    }


    @After
    public void orderCancel() {
        new ApiClient().cancelOrder(order);
    }

    @Test
    public void createOrderWithValidData() {

        ValidatableResponse response = new ApiClient().createOrder(order);
        int actualCodeStatus = response.extract().statusCode();
        int track = response.extract().path("track");

        Assert.assertEquals("Ожидаемый результат не соответствует фактическому", actualCodeStatus, expectedCodeStatus);
        assertThat("Ожидаемый результат не соответствует фактическому", track, is(not(0)));

    }
}