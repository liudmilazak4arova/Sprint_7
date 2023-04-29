import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierLoginWrongPassword  extends CourierBaseTest{

    @Test
    @DisplayName("Вход с несуществующим паролем")
    public void loginCourierWrongPasswordTest () {

        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response responseCreate =
                given()
                        .header("Content-type", "application/json")
                        .body(courierCreateRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER);

        CourierLoginRequest courierLoginRequest = new CourierLoginRequest(CourierData.LOGIN,"3214");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courierLoginRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER_LOGIN);
        //System.out.println(response.body().asString());
        response.then().assertThat().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));

    }

}
