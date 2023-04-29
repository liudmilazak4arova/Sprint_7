import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierLoginNullPasswordTest extends CourierBaseTest {


    @Test
    @DisplayName("Вход без пароля")
    public void loginCourierNullPasswordTest () {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response responseCreate =
                given()
                        .header("Content-type", "application/json")
                        .body(courierCreateRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER);

        CourierLoginRequest courierLoginRequest = new CourierLoginRequest(CourierData.LOGIN,null);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courierLoginRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER_LOGIN);
        System.out.println(response.body().asString());
        response.then().assertThat().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));

    }


}

