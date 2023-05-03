import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;


public class CourierCreateDoubleTest extends CourierBaseTest {


    @Test
    @DisplayName("Создание курьера с существующим логином")
    public void createCourierDoubleTest() {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response response1 = courierClient.courierCreateResponse(courierCreateRequest);
        Response response2 = courierClient.courierCreateResponse(courierCreateRequest);
        response2.then().assertThat().statusCode(409).and().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

}
