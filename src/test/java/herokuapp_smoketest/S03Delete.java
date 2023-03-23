package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static herokuapp_smoketest.S01Post.bookingId;
import static io.restassured.RestAssured.given;
import static util.AuthenticationHerOkuApp.generateToken;

public class S03Delete extends HerOkuAppBaseUrl {
    /*
    Given
       https://restful-booker.herokuapp.com/booking/14531

     When
         send delete request
      Then
        statucCode should be 201
       And
        body should be "Created"
     */
    @Test
    public void delete01(){
        spec.pathParams("first","booking","second",bookingId);

        //Set the expected data
        String expecteddata="Created";

        //Send teh request get the response
       Response response= given().spec(spec).when().delete("/{first}/{second}");
       response.prettyPrint();

//        statucCode should be 201
//        body should be "Created"
        Assert.assertEquals(201,response.statusCode());
        Assert.assertEquals(expecteddata,response.asString());
    }
}
