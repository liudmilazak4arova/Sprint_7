import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {

    @Step("Get response for create courier requests")
    public Response courierCreateResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(GlobalData.SERVICE_COURIER);
    }

    @Step("Get response for login courier requests|")
    public Response courierLoginResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(GlobalData.SERVICE_COURIER_LOGIN);
    }
}
