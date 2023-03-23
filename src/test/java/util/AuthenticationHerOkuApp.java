package util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {



    public static String generateToken(){
        String url="https://restful-booker.herokuapp.com/auth";
        Map<String,String> token=new HashMap<>();
        token.put("username","admin");
        token.put("password","password123");
       Response response= given().contentType(ContentType.JSON).body(token).when().post(url);

        /*
        {
    "username" : "admin",
    "password" : "password123"
}
         */

      return   response.jsonPath().getString("token");
    }
}
