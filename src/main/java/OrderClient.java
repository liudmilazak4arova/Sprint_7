import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {
    @Step("Get response for create courier requests")
    public Response ordersResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post(GlobalData.SERVICE_ORDERS);
    }

}
