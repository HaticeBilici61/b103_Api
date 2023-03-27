package get_requests;

import base_urls.GmiBankBaseUrl;
import gmibank.pojos.Country;
import gmibank.pojos.States;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Homework2603_countryPost extends GmiBankBaseUrl {
    //https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1 dokümanını kullanarak
    // en az 3 "state" içeren bir "country" oluşturan bir otomasyon testi yazınız.
    /*
    Given
        https://gmibank.com/api/tp-countries
    And
                 {
          "name": "Banana Republic",
          "states": [
            {
              "id": 1,
              "name": "Apple"
            },
            {
              "id": 2,
              "name": "Orange"
            },
            {
              "id": 3,
              "name": "Pear"
            }
          ]
         }
    When
        User send Post request
    Then
        Status code should be 201
    And
        Body should be
        {
            "id": 176677,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 1,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 3,
                    "name": "Pear",
                    "tpcountry": null
                }
            ]
        }

     */



    //https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1 dokümanını kullanarak en az 3 "state" içeren bir "country"
    // oluşturan bir otomasyon testi yazınız.

    @Test
    public void countryPost(){
        //https://gmibank.com/api/tp-countries  Set the Url
        spec.pathParams("first","api","second","tp-countries");

        //Set the expected Data
        States state1=new States(1,"Apple",null);
        States state2=new States(2,"Orange",null);
        States state3=new States(3,"Pear",null);

        ArrayList<States> statesList = new ArrayList<>();//States data tipinde bir list oluşturup states objelerimizi buna ekliyoruz.
        statesList.add(state1);
        statesList.add(state2);
        statesList.add(state3);

       Country expectedData = new Country("Banana Republic",statesList);//country adını ve state listini ekleyerek Country objesi oluşturuyoruz.(Pojo class'ta id olmamalı)
        System.out.println("countryPost = " + expectedData);

        //send the request and get the response
       Response response= given(spec).body(expectedData).post("/{first}/{second}");
       response.prettyPrint();

       //Do assertion
      Country actualData=  ObjectMapperUtils.convertJsonToJava(response.asString(),Country.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.getName(),actualData.getName());
        Assert.assertEquals(state1.getId(),actualData.getStates().get(0).getId());
        Assert.assertEquals(state1.getName(),actualData.getStates().get(0).getName());
        Assert.assertEquals(state1.getTpcountry(),actualData.getStates().get(0).getTpcountry());
        Assert.assertEquals(state2.getId(),actualData.getStates().get(1).getId());
        Assert.assertEquals(state2.getName(),actualData.getStates().get(1).getName());
        Assert.assertEquals(state2.getTpcountry(),actualData.getStates().get(1).getTpcountry());
        Assert.assertEquals(state3.getId(),actualData.getStates().get(2).getId());
        Assert.assertEquals(state3.getName(),actualData.getStates().get(2).getName());
        Assert.assertEquals(state3.getTpcountry(),actualData.getStates().get(2).getTpcountry());


    }

    }

