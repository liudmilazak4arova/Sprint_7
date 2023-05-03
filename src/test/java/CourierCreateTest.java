import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;


public class CourierCreateTest extends CourierBaseTest {

    @Test
    @DisplayName("Создание курьера")
    public void createCourierTest() {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response response = courierClient.courierCreateResponse(courierCreateRequest);
        response.then().assertThat().statusCode(201).and().body("ok", equalTo(true));
    }


}
