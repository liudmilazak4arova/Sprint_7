import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierLoginNullPasswordTest extends CourierBaseTest {


    @Test
    @DisplayName("Вход без пароля")
    public void loginCourierNullPasswordTest() {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response responseCreate = courierClient.courierCreateResponse(courierCreateRequest);
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest(CourierData.LOGIN, null);
        Response response = courierClient.courierLoginResponse(courierLoginRequest);
        response.then().assertThat().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));

    }


}

