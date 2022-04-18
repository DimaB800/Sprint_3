package client;


import io.restassured.response.ValidatableResponse;
import model.*;


import static io.restassured.RestAssured.given;

public class ApiClient extends PlaceholderRestClient {

    public ValidatableResponse createCourier(CreateCourier createCourier) {

        return given()
                .spec(baseSpec())
                .body(createCourier)
                .when()
                .post("/api/v1/courier").then();
    }

    public ValidatableResponse deleteCourier(Integer courierId) {
        return given().spec(baseSpec())
                .and().pathParam("id", courierId)
                .when().delete("/api/v1/courier/{id}")
                .then();
    }


    public ValidatableResponse login(LoginCourierRequest loginCourierRequest) {
        return given()
                .spec(baseSpec())
                .body(loginCourierRequest)
                .when()
                .post("/api/v1/courier/login/")
                .then();
    }

    public ValidatableResponse createOrder(Order order) {

        return given()
                .spec(baseSpec())
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then();
    }

    public ValidatableResponse cancelOrder(Order order) {

        return given()
                .spec(baseSpec())
                .body(order)
                .when()
                .post("/api/v1/orders/cancel")
                .then();
    }

    public ValidatableResponse takeOrder() {
        return given()
                .spec(baseSpec())
                .get("/api/v1/orders/")
                .then();
    }

}

