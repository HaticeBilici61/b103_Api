package get_requests;
import io.restassured.response.Response;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class RequestResponse {
    /*
    1-Postman manuel Api testi icin kullanilir.
    2-API otomasyonu icin Rest Assured Library kullanacagiz
    3-otomasyon kodlarinin yazilimi icin su adimlari izliyoruz:
       a) Gereksinimleri anlama

       b)Test Case'i yazma
         !!!!Test case i yazmak icin "Gherkin Language" kullaniriyoruz.
         *)Given :on kosullar--->Endpoint,body
         *)When :islemler--->Get,Put ,Delete
         *)Then:donutler--->Assert
         *)And : bir den fazla yapilacak islemde coklu islemlerde kullanilir.

       c) Test kodunu yazarken bu adimlari izleriz.
          1-Set the URL
          2_Set the expected data
          3-Send the request and get the response
          4-Do assertion

     */
    public static void main(String[] args) {
        String url="https://restful-booker.herokuapp.com/booking/55";
        //Get request nasil yapilir
      // get();
     Response response=  given().when().get(url);

     //prettyPrint() methodu responce datayi yazdirir.
      response.prettyPrint();

      //Status Code nasil yazdirilir
      System.out.println("status code:" +response.statusCode());

      //Content Type nasil yazdirilir
        System.out.println("Content ytpe :" + response.contentType());

      // statusline nasil yazdirlir
        System.out.println("getstatusline :" +response.getStatusLine());

      //Header nasil yazdirilir
        System.out.println("Header  :" + response.header("Connection"));

      //Headers nasil yazdirlir
        System.out.println(response.headers());

        //Time nasil yazdirilir
        System.out.println(response.time());
    }
}
