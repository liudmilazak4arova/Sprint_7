import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class CourierLoginTest extends CourierBaseTest{

    @Test
    @DisplayName("Корректный вход")
    public void loginCourierTest () {

        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response responseCreate =
                given()
                        .header("Content-type", "application/json")
                        .body(courierCreateRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER);

        CourierLoginRequest courierLoginRequest = new CourierLoginRequest(CourierData.LOGIN, CourierData.PASSWORD);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courierLoginRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER_LOGIN);
        System.out.println(response.body().asString());
        response.then().assertThat().statusCode(200).and().body("id", notNullValue());
    }

}
