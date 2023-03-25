package get_requests;

import base_urls.AutomationexerciseBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class HomeworkYasinh02 extends AutomationexerciseBaseUrl {

    /*2)
	Given
		https://automationexercise.com/api/productsList
	When
		User sends Get request
	Then
		Assert that number of "Women" usertype is 12
		(Kadın usertype sayısının 12 olduğunu doğrulayın)

  */

    @Test
    public void hmo2(){
        //https://automationexercise.com/api/productsList
        spec.pathParam("first","productsList");


        Response response=given().when().contentType(ContentType.JSON).spec(spec).get("{first}");
        response.jsonPath().prettyPrint();

        assert response.jsonPath().getList("products.findAll{it.category.usertype.usertype=='Women'}.usertype").size() == 12;
    }


//yasinHoca cozumu
@Test
public void automationExerciseTest(){
    spec.pathParam("first","productsList");
    Response response = given(spec).get("{first}");
    response.jsonPath().prettyPrint();
    JsonPath jsonPath = response.jsonPath();
    //Assert that number of "Women" usertype is 12 -->Kadın usertype sayısının 12 olduğunu doğrulayın
    int kadinUserTypeSayisi = jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}").size();
    Assert.assertEquals(12, kadinUserTypeSayisi);
}

    }


