import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookingApiTests {
    private RequestSpecification requestSpecification = RestAssured.given()
            .baseUri("https://restful-booker.herokuapp.com")
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    @Test
    @DisplayName(value = "GET BOOKING BY ID")
    public void getBookingByIdTest() {
        requestSpecification.when()
                .and().pathParam("bookingid", 20)
                .get("/booking/{bookingid}")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    @DisplayName(value = "GET ALL BOOKING")
    public void getAllBookingTest() {
        requestSpecification.when()
                .get("/booking")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    @DisplayName(value = "CREATE A NEW BOOKING")
    public void createNewBookingTest() {
        requestSpecification.when()
                .body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .header("Content-Type", ContentType.JSON)
                .post("/booking")
                .then()
                .assertThat().statusCode(200);
    }
}
