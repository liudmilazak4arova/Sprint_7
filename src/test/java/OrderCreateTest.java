// импортируем RestAssured

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;


    public OrderCreateTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        String[] color1 = {"BLACK"};
        String[] color2 = {"BLACK", "GREY"};
        String[] color3 = null;

        Object[][] data = new Object[][]{
                {"Сергей", "Иванов", "Садовая 4", "Павелецкая", "+79876543214", 2, "2023-05-20", "comment1", color1},
                {"Сергей", "Иванов", "Садовая 4", "Павелецкая", "+79876543214", 2, "2023-05-21", "comment2", color2},
                {"Сергей", "Иванов", "Садовая 4", "Павелецкая", "+79876543214", 2, "2023-05-22", "comment3", color3},
        };
        return Arrays.asList(data);
    }


    @Before
    public void setUp() {
        RestAssured.baseURI = GlobalData.URL;
    }

    @Test
    @DisplayName("Создание заказа")
    public void createOrderTest() {
        OrderClient orderClient = new OrderClient();
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = orderClient.ordersResponse(orderCreateRequest);
        response.then().assertThat().statusCode(201).and().body("track", notNullValue());
    }


}
