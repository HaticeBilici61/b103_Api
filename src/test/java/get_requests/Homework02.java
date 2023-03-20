package get_requests;

import base_urls.RegresBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Homework02 extends RegresBaseUrl {
     /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then
            1)Status code is 200
            2)Print all pantone_values
              (Tüm pantone_value değerlerini yazdırınız)
            3)Print all ids greater than 3 on the console
              (3'ten büyük id'leri yazdırınız)
              Assert that there are 3 ids greater than 3
              (3'ten büyük 3 adet id olduğunu doğrulayınız)
            4)Print all names whose ids are less than 3 on the console
              (id'si 3'ten küçük isimleri yazdırınız)
              Assert that the number of names whose ids are less than 3 is 2
              (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)
    */

    @Test
    public void Homework01(){



    }









    //2)
  /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void Homework02() {
        spec.pathParam("first","users");
        Map<String,String> expecteddata=new RegresTestData().expectedMethod("morpheus","leader");
        Response response= given().spec(spec).get("/{first}");
        response.prettyPrint();

        Map<String ,String> actualdata=response.as(HashMap.class);
        System.out.println(actualdata);

        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(expecteddata.get("name"),actualdata.get("name"));
        Assert.assertEquals(expecteddata.get("job"),actualdata.get("job"));



    }


}