import io.qameta.allure.junit4.DisplayName;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;


public class CourierCreateTest extends  CourierBaseTest {

    @Test
    @DisplayName("Создание курьера")
    public void createCourierTest() {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courierCreateRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER);
        //System.out.println(response.body().asString());
       response.then().assertThat().statusCode(201).and().body("ok",equalTo(true));
    }


}
