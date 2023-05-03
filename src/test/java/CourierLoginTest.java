import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;

public class CourierLoginTest extends CourierBaseTest {

    @Test
    @DisplayName("Корректный вход")
    public void loginCourierTest() {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response responseCreate = courierClient.courierCreateResponse(courierCreateRequest);
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest(CourierData.LOGIN, CourierData.PASSWORD);
        Response response = courierClient.courierLoginResponse(courierLoginRequest);
        response.then().assertThat().statusCode(200).and().body("id", notNullValue());
    }

}
