import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;


public class CourierCreateDoubleTest extends CourierBaseTest{


    @Test
    @DisplayName("Создание курьера с существующим логином")
    public void createCourierDoubleTest() {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest(CourierData.LOGIN, CourierData.PASSWORD, CourierData.FIRSTNAME);
        Response response1 =
                given()
                        .header("Content-type", "application/json")
                        .body(courierCreateRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER);

        Response response2 =
                given()
                        .header("Content-type", "application/json; charset=utf-8")
                        .body(courierCreateRequest)
                        .when()
                        .post("/api/v1/courier");
        //System.out.println(response.body().asString());
        response2.then().assertThat().statusCode(409).and().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

}
