import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;


import static io.restassured.RestAssured.given;

public class CourierBaseTest {
    @Before
    public void setUp() {
        RestAssured.baseURI =GlobalData.URL;
    }


    @After
    public void revert(){
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest(CourierData.LOGIN, CourierData.PASSWORD);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courierLoginRequest)
                        .when()
                        .post(GlobalData.SERVICE_COURIER_LOGIN);
        // System.out.println(response.body().asString());

        CourierLoginResponse courierLoginResponse = response.body().as(CourierLoginResponse.class);

        String id = courierLoginResponse.getId();

        String jsonRemove = "{\"id\": \""+id+"\"}";

        response =
                given()
                        .header("Content-type", "application/json")
                        .body(jsonRemove)
                        .when()
                        .delete(GlobalData.SERVICE_COURIER+"/"+id);
        //System.out.println(response.body().asString());
    }
}
