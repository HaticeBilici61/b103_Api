package get_requests;

import base_urls.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Homework01 extends RegresBaseUrl {
    /*
 Given
            https://reqres.in/api/users/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void Homework01() {

        //  parametre olustur https://reqres.in/api/users/3
        spec.pathParam("id", "3");

        //set the URL
        Response response = given().when().spec(spec).get("/{id}");
        response.prettyPrint();


        // //Do Assertion
        response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");




 /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */

    }
   @Test
           public void homework02(){
       //  parametre olustur https://reqres.in/api/users/23
       spec.pathParam("id", "23");

       //set the URL
       Response response = given().when().spec(spec).get("/{id}");
       response.prettyPrint();

       //Do Assert
       response.then().statusCode(404).
               statusLine("HTTP/1.1 404 Not Found").
               header("Server","cloudflare");
            //  assertFalse(response.asString().isEmpty());//????????????????????
             assertEquals("{}",response.getBody().asString());
//       assertEquals(404, response.statusCode());
//       assertEquals("HTTP/1.1 404 Not Found", response.statusLine());
//       assertEquals("cloudflare", response.getHeader("Server"));
//       assertEquals(2, response.asString().replaceAll("\\s","").length());




   }
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           "email" is "janet.weaver@reqres.in",
       And
           "first_name" is "Janet"
       And
           "last_name" is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */
 @Test
    public void homework03(){
     spec.pathParam("id","2");
     Response response= given().when().spec(spec).get("/{id}");
     response.prettyPrint();

     response.then().statusCode(200).
             contentType(ContentType.JSON).body("data.email",equalTo("janet.weaver@reqres.in"),"data.first_name",equalTo("Janet"),
                     "data.last_name",equalTo("Weaver"),"support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));


 }
    @Test  //Yasin hoca cozum
    public void get02(){
//Set the Url
        spec.pathParams("first","users","second",23);

//Set The Expected Data

//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

//Do Assertion

        assertEquals(404, response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(2, response.asString().replaceAll("\\s","").length());
    }

    }

