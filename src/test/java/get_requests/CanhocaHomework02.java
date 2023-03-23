package get_requests;

import base_urls.ApiZippopotamBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.ZipopotamTestData;
import util.ObjectMapperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CanhocaHomework02 extends ApiZippopotamBaseUrl {

/*
        Given
            http://api.zippopotam.us/ES/1001
       When
            Kullanıcı GET Methodu ile Request Gönder
        Then
             Status Code un "200" olduğunu Assert et
        And
            Response body nin bu şekilde olduğunu doğrula
            {
    "post code": "01001",
    "country": "Spain",
    "country abbreviation": "ES",
    "places": [
        {
            "place name": "Vitoria-Gasteiz",
            "longitude": "-2.6667",
            "state": "Pais Vasco",
            "state abbreviation": "PV",
            "latitude": "42.85"
        }
    ]
}
     */


    @Test
    public void hm07() {

        spec.pathParams("first","ES","second","01001");
        Response response =given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        ZipopotamTestData obj=new ZipopotamTestData();
        List<Map<String,String>> icmap= obj.expectedInMethod("Vitoria-Gasteiz","-2.6667","Pais Vasco","PV","42.85");
        Map<String,Object> expectedData= obj.expectedDataMethod("01001","Spain","ES",icmap);
        System.out.println("expectedData = " + expectedData);
        Map<String, Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.get("post code"),actualData.get("post code"));
        Assert.assertEquals(expectedData.get("country"),actualData.get("country"));
        Assert.assertEquals(expectedData.get("country abbreviation"),actualData.get("country abbreviation"));
        Assert.assertEquals(expectedData.get("icmap.place name"),actualData.get("icmap.place name"));
        Assert.assertEquals(expectedData.get("icmap.longitude"),actualData.get("icmap.longitude"));
        Assert.assertEquals(expectedData.get("icmap.state"),actualData.get("icmap.state"));
        Assert.assertEquals(expectedData.get("icmap.state abbreviation"),actualData.get("icmap.state abbreviation"));
        Assert.assertEquals(expectedData.get("icmap.state latitude"),actualData.get("icmap.latitude"));
    }
}
