package get_requests;

import base_urls.ZippopotamBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.ZippoPotamPlaces;
import pojos.ZippoPotamPojos;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class ZippoPotam_Pojo extends ZippopotamBaseUrl {

  /*
        Given
            http://api.zippopotam.us/ES/01001
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
    public void Zippo_Pojo(){
        spec.pathParams("first","ES","second","01001");
        ZippoPotamPlaces zippoPotamPlaces=new ZippoPotamPlaces("Vitoria-Gasteiz","-2.6667","Pais Vasco","PV","42.85");
        ZippoPotamPojos expectedData=new ZippoPotamPojos(  "01001","Spain", "ES",zippoPotamPlaces);
        System.out.println("expectedData = " + expectedData);

       Response response= given().when().spec(spec).get("/{first}/{second}");
       response.prettyPrint();

        ZippoPotamPojos actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),ZippoPotamPojos.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.getPostCode(),actualData.getPostCode());
        Assert.assertEquals(expectedData.getCountry(),actualData.getCountry());
        Assert.assertEquals(expectedData.getCountryAbbreviation(),actualData.getCountryAbbreviation());
        Assert.assertEquals(expectedData.getZippoPotamPlaces().getPlaceName(),actualData.getZippoPotamPlaces().getPlaceName());
        Assert.assertEquals(expectedData.getZippoPotamPlaces().getLongitude(),actualData.getZippoPotamPlaces().getLongitude());
        Assert.assertEquals(expectedData.getZippoPotamPlaces().getState(),actualData.getZippoPotamPlaces().getState());
        Assert.assertEquals(expectedData.getZippoPotamPlaces().getStateAbbreviation(),actualData.getZippoPotamPlaces().getStateAbbreviation());
        Assert.assertEquals(expectedData.getZippoPotamPlaces().getLatitude(),actualData.getZippoPotamPlaces().getLatitude());


    }
}
