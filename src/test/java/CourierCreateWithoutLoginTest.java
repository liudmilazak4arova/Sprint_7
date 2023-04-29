import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateWithoutLoginTest extends  CourierBaseTest {

        @Test
        @DisplayName("Создание курьера без логина")
        public void createCourierWithoutLoginTest(){
            CourierCreateRequest courierCreateRequest = new CourierCreateRequest(null, CourierData.PASSWORD, CourierData.FIRSTNAME);
            Response response =
                    given()
                            .header("Content-type", "application/json")
                            .body(courierCreateRequest)
                            .when()
                            .post(GlobalData.SERVICE_COURIER);
            //  System.out.println(response.body().asString());
            response.then().assertThat().statusCode(400).and().body("message", equalTo("Недостаточно данных для создания учетной записи"));
        }
}

