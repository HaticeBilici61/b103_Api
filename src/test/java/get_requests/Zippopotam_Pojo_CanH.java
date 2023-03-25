package get_requests;


import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import pojos.ZippoPotamPlaces;
import pojos.ZippoPotamPojos;
import util.ObjectMapperUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zippopotam_Pojo_CanH {
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
    public void test() {
        String url = "http://api.zippopotam.us/ES/01001";
        ZippoPotamPlaces zippoPotamPlaces = new ZippoPotamPlaces("Vitoria-Gasteiz", "-2.6667", "Pais Vasco", "PV", "42.85");
        ZippoPotamPojos zippoPotamPojos = new ZippoPotamPojos("01001", "Spain", "ES", zippoPotamPlaces);
        System.out.println("Expected Data: " + zippoPotamPojos);

        Response response = given().
                when().
                get(url);
        response.prettyPrint();

        ZippoPotamPojos  actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),ZippoPotamPojos.class);
        System.out.println("actualData = " + actualData);

//        Map<String, Object> actualData = response.as(HashMap.class);
//
//        assertEquals(zippoPotamPojos.getCountry(), actualData.get("country"));
//        assertEquals(zippoPotamPojos.getPostCode(), actualData.get("post code"));
//        assertEquals(zippoPotamPojos.getCountryAbbreviation(), actualData.get("country abbreviation"));
//        assertEquals(zippoPotamPojos.getZippoPotamPlaces().getPlaceName(), (((Map) ((List) actualData.get("places")).get(0))).get("place name"));
//        assertEquals(zippoPotamPojos.getZippoPotamPlaces().getLongitude(), (((Map) ((List) actualData.get("places")).get(0)).get("longitude")));
//        assertEquals(zippoPotamPojos.getZippoPotamPlaces().getState(), (((Map) ((List) actualData.get("places")).get(0)).get("state")));
//        assertEquals(zippoPotamPojos.getZippoPotamPlaces().getStateAbbreviation(), (((Map) ((List) actualData.get("places")).get(0)).get("state abbreviation")));
//        assertEquals(zippoPotamPojos.getZippoPotamPlaces().getLatitude(), (((Map) ((List) actualData.get("places")).get(0)).get("latitude")));

    }
}