package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.GoRestDataPojo2;
import pojos.GoRestPojo;
import pojos.GoRestPojo2;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class CanhocaHomework01 extends GoRestBaseUrl {
    /*
        Given
           https://gorest.co.in/public/v2/posts/174
       When
            Kullanıcı GET Methodu ile Request Gönder
        Then
             Status Code un "200" olduğunu Assert et
        And
            Response body nin bu şekilde olduğunu doğrula
 {
    "id": 174,
    "user_id": 612,
    "title": "Tertius valeo sint est vesica doloribus rerum casus cetera nisi sapiente vigor pecus voluptas asperiores et sto vapulus.",
    "body": ""Et demens tergo. Cohors copia adeptio. Cotidie speciosus coaegresco. Concido crastinus degero. Tristis fugit supellex. Sustineo cogo odit. Pel patria crur. Amplus comitatus modi. Ater omnis solutio. Voluptatem acies unde. Taceo delinquo ustilo. Alo possimus vaco. Sit cognomen thesaurus. Appositus vel amicitia. Cetera textus defungo."

     */
   @Test
    public void test01(){
       spec.pathParams("first","posts","second",174);
      Response response= given().when().spec(spec).get("/{first}/{second}");

      GoRestDataPojo2 expecteddata= new GoRestDataPojo2(612,"Tertius valeo sint est vesica doloribus rerum casus cetera nisi sapiente vigor pecus voluptas asperiores et sto vapulus."
              ,"Et demens tergo. Cohors copia adeptio. Cotidie speciosus coaegresco. Concido crastinus degero. Tristis fugit supellex. Sustineo cogo odit. Pel patria crur. Amplus comitatus modi. Ater omnis solutio. Voluptatem acies unde. Taceo delinquo ustilo. " +
              "Alo possimus vaco. Sit cognomen thesaurus. Appositus vel amicitia. Cetera textus defungo.");

       GoRestPojo2 actualdata= ObjectMapperUtils.convertJsonToJava(response.asString(), GoRestPojo2.class);
       Assert.assertEquals(200,response.statusCode());
       Assert.assertEquals(expecteddata.getUser_id(),actualdata.getData().getUser_id() );
       Assert.assertEquals(expecteddata.getTitle(),actualdata.getData().getTitle());
       Assert.assertEquals(expecteddata.getBody(),actualdata.getData().getBody());

}}


