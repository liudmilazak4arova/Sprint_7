import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class CourierBaseTest {
    protected CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = GlobalData.URL;
        courierClient = new CourierClient();
    }


    @After
    public void revert() {
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest(CourierData.LOGIN, CourierData.PASSWORD);
        Response response = courierClient.courierLoginResponse(courierLoginRequest);
        CourierLoginResponse courierLoginResponse = response.body().as(CourierLoginResponse.class);
        String id = courierLoginResponse.getId();
        String jsonRemove = "{\"id\": \"" + id + "\"}";
        response =
                given()
                        .header("Content-type", "application/json")
                        .body(jsonRemove)
                        .when()
                        .delete(GlobalData.SERVICE_COURIER + "/" + id);
    }
}
