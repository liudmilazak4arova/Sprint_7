import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateWithoutPassword extends CourierBaseTest {

    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierWithoutPasswordTest() {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, null, CourierData.FIRSTNAME);
        Response response = courierClient.courierCreateResponse(courierCreateRequest);
        response.then().assertThat().statusCode(400).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


}



