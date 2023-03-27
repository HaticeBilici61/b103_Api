package util;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;



public class AuthenticationGmiBank {
    public static void main(String[] args) {
        System.out.println("gmiBankToken() = " + gmiBankToken());
    }

    public static String gmiBankToken(){
        String url="https://gmibank.com/api/authenticate";
        Map<String,Object> token=new HashMap<>();
        token.put("password","Batch.103");
        token.put("rememberMe",true);
        token.put("username","batch_yuzuc");

        Response response= given().contentType(ContentType.JSON).body(token).when().post(url);
        return   response.jsonPath().getString("id_token");
    }
}
