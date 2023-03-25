package get_requests;

import base_urls.Covid19BaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CanhocaHomework03 extends Covid19BaseUrl {

    /*
        Given
           https://api.covid19api.com/world/total
       When
            Kullanıcı GET Methodu ile Request Gönder
        Then
             Status Code un "200" olduğunu Assert et
        And
            Response body nin bu şekilde olduğunu doğrula
 {
    "TotalConfirmed": 674300771,
    "TotalDeaths": 6793224,
    "TotalRecovered": 0
}
     */
    @Test
    public void hm03(){

            spec.pathParams("ilk","world","iki","total");


            Map<String, Integer> expectedData = covid19(674300771,6793224,0);
            System.out.println("expectedData = " + expectedData);

            Response response = given().spec(spec).get("/{ilk}/{iki}");
            response.prettyPrint();
            Map<String, Integer> actualData =  ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
            System.out.println("actualData = " + actualData);

            Assert.assertEquals(200,response.statusCode());
            Assert.assertEquals(expectedData.get("TotalConfirmed"),actualData.get("TotalConfirmed"));
           Assert. assertEquals(expectedData.get("TotalDeaths"),actualData.get("TotalDeaths"));
           Assert. assertEquals(expectedData.get("TotalRecovered"),actualData.get("TotalRecovered"));



        }


//yukardaki data icin bir method olusturdum
        public Map<String, Integer> covid19 (Integer TotalConfirmed,Integer TotalDeaths,Integer TotalRecovered){
            Map<String, Integer> covid19 = new HashMap<>();
            covid19.put("TotalConfirmed",TotalConfirmed);
            covid19.put("TotalDeaths",TotalDeaths);
            covid19.put("TotalRecovered",TotalRecovered);
            return covid19;
        }

    }

