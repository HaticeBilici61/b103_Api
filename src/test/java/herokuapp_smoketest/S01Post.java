package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class S01Post extends HerOkuAppBaseUrl {
     /*
   "https://restful-booker.herokuapp.com/apidoc/index.html" dökümanını kullanarak;
    Bir booking oluşturan, o booking'i güncelleyen ve sonra silen ve bu adımları doğrulayan positif ve negatif testler içeren bir otomasyon testi yazınız.
    */

    /*
    Given
       1- https://restful-booker.herokuapp.com/booking
            2-{
         "firstname" : "Jim",
         "lastname" : "Brown",
         "totalprice" : 111,
         "depositpaid" : true,
         "bookingdates" : {
             "checkin" : "2018-01-01",
             "checkout" : "2019-01-01"
         },
         "additionalneeds" : "Breakfast"

     When
       Send post request
     Then
        Status Code should be 200
      And
     body shoul be like :
                           {
    "bookingid": 4773,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2018-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}

}
     */

    static int bookingId;

    @Test
    public void Post01(){
        //Set the Url
        spec.pathParam("first","booking");

        //Set The expected data
        BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2018-01-01");
        BookingPojo expectedData=new BookingPojo("Jim","Brown",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
       Response response= given().when().spec(spec).body(expectedData).post("{first}");
       response.prettyPrint();

       //Do assertion
       BookingResponsePojo actualdata=  ObjectMapperUtils.convertJsonToJava(response.asString(),BookingResponsePojo.class);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getFirstname(),actualdata.getBooking().getFirstname());
        Assert.assertEquals(expectedData.getLastname(),actualdata.getBooking().getLastname());
        Assert.assertEquals(expectedData.getTotalprice(),actualdata.getBooking().getTotalprice());
        Assert.assertEquals(expectedData.getDepositpaid(),actualdata.getBooking().getDepositpaid());
        Assert.assertEquals(bookingDatesPojo.getCheckin(),actualdata.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(bookingDatesPojo.getCheckout(),actualdata.getBooking().getBookingdates().getCheckout());
        Assert.assertEquals(expectedData.getAdditionalneeds(),actualdata.getBooking().getAdditionalneeds());

         bookingId=actualdata.getBookingid();
    }
}
