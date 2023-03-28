package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.DummyRestApiDataPojo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HomeworkDummy extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language

           Assert:  i) Status code is 200
                    ii) There are 24 employees
                   (HamcrestMatcher kullanarak 24 employees olduğunu doğrulayınız)
                    iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   (HamcrestMatcher kullanarak "Tiger Nixon" ve "Garrett Winters"'ın employees arasında olduğunu doğrulayınız' )
                    iv) The greatest age is 66
                   (En büyük yaşın 66 olduğunu doğrulayınız)
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   (En düşük yaşın isminin Tatyana Fitzpatrick olduğunu doğrulayınız)
                    vi) Total salary of all employees is 6,644,770
                   (Tüm employees salary toplamının  6,644,770 olduğunu doğrulayınız)
    */

    @Test
    public void dummy(){
        //Set the URL
        spec.pathParam("first","employees");


        DummyRestApiDataPojo icpojo=new DummyRestApiDataPojo();


        //Send the request and get the response
       Response response= given().when().spec(spec).get("{first}");
       response.prettyPrint();
        response.then().statusCode(200).body("data.id",hasSize(24),"data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        //  (En büyük yaşın 66 olduğunu doğrulayınız)
        List<Integer> age=response.jsonPath().getList("data.employee_age");
     // age.stream().sorted().forEach(t-> System.out.println(age.get(age.size()-1)));
        Collections.sort(age);
        int enbuyukyas= age.get(age.size()-1);
        System.out.println(enbuyukyas);
        Assert.assertEquals(66,enbuyukyas);

        //en kucuk yas
       int enkucukyas= age.get(0);


       // (En düşük yaşın isminin Tatyana Fitzpatrick olduğunu doğrulayınız)
        List<String> isimlist=response.jsonPath().getList("data.findAll{it.employee_age==19}.employee_name");
        Assert.assertEquals("Tatyana Fitzpatrick",isimlist.get(0));

        //(Tüm employees salary toplamının  6,644,770 olduğunu doğrulayınız)
      List<Object> salaryList=  response.jsonPath().getList("data.employee_salary");
        long toplamSalary =0;
        for (Object w:salaryList) {
            toplamSalary+=(Integer)w;
        }
        System.out.println("toplamSalary = " + toplamSalary);
        Assert.assertEquals(6644770,toplamSalary);

        //  ageList.stream().sorted().skip(ageList.size() - 1).forEach(t-> System.out.println(t));

        }


        }





