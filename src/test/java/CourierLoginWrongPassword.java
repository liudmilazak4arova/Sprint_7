import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierLoginWrongPassword extends CourierBaseTest {

    @Test
    @DisplayName("Вход с несуществующим паролем")
    public void loginCourierWrongPasswordTest() {

        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response responseCreate = courierClient.courierCreateResponse(courierCreateRequest);
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest(CourierData.LOGIN, "3214");
        Response response = courierClient.courierLoginResponse(courierLoginRequest);
        response.then().assertThat().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));

    }

}
