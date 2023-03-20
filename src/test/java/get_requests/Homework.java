package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Homework extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"

     */

    @Test
    public void homework01(){
        spec.pathParam("first","booking").queryParams("firstname","Almedin","lastname","Alikadic");

     Response response= given().when().spec(spec).get("/{first}");
     response.prettyPrint();

     //1.yol
     response.then().statusCode(200).body("firstname",equalTo("Almedin"),"lastname",equalTo("Alikadic"));

     //2.yol
         JsonPath jsnpth=response.jsonPath();
        Assert.assertEquals("Almedin",jsnpth.getString("firstname"));
        Assert.assertEquals("Alikadic",jsnpth.getString("lastname"));

    }


}
