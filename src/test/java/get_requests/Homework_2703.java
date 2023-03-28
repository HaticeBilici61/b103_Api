package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import util.ObjectMapperUtils;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class Homework_2703 extends HerOkuAppBaseUrl {

   /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                  {
    "firstname": "Alex",
    "lastname": "Rincon",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
                (TestNG Soft assertion yapınız)
     */

    @Test
    public void hm01(){
        //set the url
        spec.pathParams("first","booking","second",22);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Alex","Rincon",111,true,bookingDatesPojo,"Breakfast");

      Response response=  given().when().spec(spec).get("/{first}/{second}");
      response.prettyPrint();

     BookingPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);

       SoftAssert softAssert=new SoftAssert();


        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        softAssert.assertEquals(expectedData.getLastname(),actualData.getLastname());
        softAssert.assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        softAssert.assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        softAssert.assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        softAssert.assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
        softAssert.assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        softAssert.assertAll();




    }
}
