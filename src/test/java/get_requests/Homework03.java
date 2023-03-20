package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.PetStorePojo;

import static io.restassured.RestAssured.given;

public class Homework03 extends PetStoreBaseUrl {
    /*

     "https://petstore.swagger.io/" dökümanını kullanarak 'user' oluşturacak bir otomasyon testi yazınız
    Not: Test Case'i gherkin language ile yazınız.
    Given
            1) https://petstore.swagger.io/

            2){
        "id": 35,
                "username": "Pati Lisa",
                "firstName": "Kubra",
                "lastName": "Tanribuyurdu",
                "email": "abc@gmail.com",
                "password": "12345",
                "phone": "123454321",
                "userStatus": 0
    }

    When
    I send POST Request to the Url
            Then
    Status code is 200
    And response body should be like {
        "id": 0,
                "username": "Pati Lisa",
                "firstName": "Kubra",
                "lastName": "Tanribuyurdu",
                "email": "abc@gmail.com",
                "password": "12345",
                "phone": "123454321",
                "userStatus": 0



     */



@Test
    public void Homework03mit_Post(){
    spec.pathParam("first","user");

    PetStorePojo obj=new PetStorePojo("Pati Lisa","Kubra","Tanribuyurdu","abc@gmail.com","12345","123454321",0);

    Response response=given().spec(spec).body(obj).post("/{first}");
    response.prettyPrint();


}

@Test
    public void Homework03mit_Get(){


}


    }





