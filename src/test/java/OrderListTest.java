import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.Before;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsNull.notNullValue;

public class OrderListTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = GlobalData.URL;
    }

    @Test
    @DisplayName("Список заказов")
    public void  getOrderListTest() {

         given()
                .get(GlobalData.SERVICE_ORDERS).then().statusCode(200).and().body("orders", notNullValue());

    }
}
