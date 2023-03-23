package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import util.ObjectMapperUtils;

import static herokuapp_smoketest.S01Post.bookingId;
import static io.restassured.RestAssured.given;
import static util.AuthenticationHerOkuApp.generateToken;

public class S02Put extends HerOkuAppBaseUrl {
    /*
    Given
       1) https://restful-booker.herokuapp.com/booking/{id}
                2) {
             "firstname" : "Hatice",
             "lastname" : "Bil",
             "totalprice" : 111,
             "depositpaid" : true,
             "bookingdates" : {
                 "checkin" : "2018-01-01",
                 "checkout" : "2019-01-01"
             },
             "additionalneeds" : "Breakfast"

        When
          Send Put request

       Then
          Status code should be 200
      And
         {
              "firstname" : "Hatice",
              "lastname" : "Bil",
              "totalprice" : 111,
              "depositpaid" : true,
              "bookingdates" : {
                  "checkin" : "2018-01-01",
                  "checkout" : "2019-01-01"
              },
              "additionalneeds" : "Breakfast"
}

}
     */

    @Test
    public void put01(){
        System.out.println(bookingId);
        spec.pathParams("first","booking","second",bookingId);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("Ali","Can",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

       Response response= given().spec(spec).body(expectedData).put("/{first}/{second}");
       response.prettyPrint();

        //Do assertion
        BookingPojo actualdata=ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);
        System.out.println("actualdata = " + actualdata);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getFirstname(),actualdata.getFirstname());
        Assert.assertEquals(expectedData.getLastname(),actualdata.getLastname());
        Assert.assertEquals(expectedData.getTotalprice(),actualdata.getTotalprice());
        Assert.assertEquals(expectedData.getDepositpaid(),actualdata.getDepositpaid());
        Assert.assertEquals(bookingDatesPojo.getCheckin(),actualdata.getBookingdates().getCheckin());
        Assert.assertEquals(bookingDatesPojo.getCheckout(),actualdata.getBookingdates().getCheckout());
        Assert.assertEquals(expectedData.getAdditionalneeds(),actualdata.getAdditionalneeds());



    }




}

