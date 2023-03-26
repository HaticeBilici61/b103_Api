package gmibank;

import base_urls.GmiBankBaseUrl;
import gmibank.pojos.Account;
import gmibank.pojos.Country;
import gmibank.pojos.Customer;
import gmibank.pojos.User;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Assert;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCustomer extends GmiBankBaseUrl {
       /*
    Given
        https://www.gmibank.com/api/tp-customers/110472
    When
        User sends Get request
    Then
        Status code should be 200
     And
        Response body should be like:
    {
    "id": 110472,
    "firstName": "Melva",
    "lastName": "Bernhard",
    "middleInitial": "A",
    "email": "chas.kuhlman@yahoo.com",
    "mobilePhoneNumber": "192-580-3408",
    "phoneNumber": "192-580-3408",
    "zipCode": "40207",
    "address": "Apt. 634 579 Eliseo Rapids, Deanaside, AZ 53872",
    "city": "New Jordanhaven",
    "ssn": "523-50-1191",
    "createDate": "2021-11-30T21:00:00Z",
    "zelleEnrolled": false,
    "country": {
        "id": 24105,
        "name": "San Jose",
        "states": null
    },
    "state": "",
    "user": {
        "id": 111206,
        "login": "delilah.metz",
        "firstName": "Melva",
        "lastName": "Bernhard",
        "email": "chas.kuhlman@yahoo.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    },
    "accounts": [
        {
            "id": 2327,
            "description": "omermusteri01 hesap1",
            "balance": 1020600,
            "accountType": "SAVING",
            "accountStatusType": "ACTIVE",
            "createDate": "2020-11-06T23:00:00Z",
            "closedDate": "2024-11-07T23:00:00Z",
            "employee": null,
            "accountlogs": null
        },
        {
            "id": 107250,
            "description": "New Account_6thGenQA_01",
            "balance": 11190,
            "accountType": "CHECKING",
            "accountStatusType": "ACTIVE",
            "createDate": "2021-11-24T23:00:00Z",
            "closedDate": "2022-11-24T23:00:00Z",
            "employee": null,
            "accountlogs": null
        }
    ]
}
     */

    @Test
    public void gmiBankCustomer() {
//        i)   Set the URL
        spec.pathParams("first", "api", "second", "tp-customers", "third", 110472);

//        ii)  Set the expected data
        Country country = new Country(24105, "San Jose", null);
        User user = new User(111206, "delilah.metz", "Melva", "Bernhard", "chas.kuhlman@yahoo.com", true, "en", null, null);
        Account account1 = new Account(2327, "omermusteri01 hesap1", 1020600, "SAVING", "ACTIVE", "2020-11-06T23:00:00Z", "2024-11-07T23:00:00Z", null, null);
        Account account2 = new Account(107250, "New Account_6thGenQA_01", 11190, "CHECKING", "ACTIVE", "2021-11-24T23:00:00Z", "2022-11-24T23:00:00Z", null, null);
        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);

        Customer expectedData = new Customer(110472, "Melva", "Bernhard", "A", "chas.kuhlman@yahoo.com", "192-580-3408", "192-580-3408", "40207", "Apt. 634 579 Eliseo Rapids, Deanaside, AZ 53872", "New Jordanhaven", "523-50-1191", "2021-11-30T21:00:00Z", false, country, "", user, accountList);
        System.out.println("expectedData = " + expectedData);
//        iii) Send the request and get the response
        Response response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();

//        iv)  Do assertion

       Customer actualData= ObjectMapperUtils.convertJsonToJava(response.asString(), Customer.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        Assert.assertEquals(expectedData.getLastName(),actualData.getLastName());
        Assert.assertEquals(expectedData.getMiddleInitial(),actualData.getMiddleInitial());
        Assert.assertEquals(expectedData.getEmail(),actualData.getEmail());
        Assert.assertEquals(expectedData.getMobilePhoneNumber(),actualData.getMobilePhoneNumber());
        Assert.assertEquals(expectedData.getZipCode(),actualData.getZipCode());
        Assert.assertEquals(expectedData.getAddress(),actualData.getAddress());
        Assert.assertEquals(expectedData.getCity(),actualData.getCity());
        Assert.assertEquals(expectedData.getSsn(),actualData.getSsn());
        Assert.assertEquals(expectedData.getCreateDate(),actualData.getCreateDate());
        Assert.assertEquals(country.getId(),actualData.getCountry().getId());
        Assert.assertEquals(country.getName(),actualData.getCountry().getName());
        Assert.assertEquals(country.getStates(),actualData.getCountry().getStates());
        Assert.assertEquals(user.getId(),actualData.getUser().getId());
        Assert.assertEquals(user.getLogin(),actualData.getUser().getLogin());
        Assert.assertEquals(user.getFirstName(),actualData.getUser().getFirstName());
        Assert.assertEquals(user.getLastName(),actualData.getUser().getLastName());
        Assert.assertEquals(user.getEmail(),actualData.getUser().getEmail());
        Assert.assertEquals(user.getLangKey(),actualData.getUser().getLangKey());
        Assert.assertEquals(user.getImageUrl(),actualData.getUser().getImageUrl());
        Assert.assertEquals(user.getResetDate(),actualData.getUser().getResetDate());
        Assert.assertEquals(expectedData.getAccounts().get(0).getId(),actualData.getAccounts().get(0).getId());
        Assert.assertEquals(expectedData.getAccounts().get(0).getDescription(),actualData.getAccounts().get(0).getDescription());
        Assert.assertEquals(expectedData.getAccounts().get(0).getBalance(),actualData.getAccounts().get(0).getBalance());
        Assert.assertEquals(expectedData.getAccounts().get(0).getAccountType(),actualData.getAccounts().get(0).getAccountType());
        Assert.assertEquals(expectedData.getAccounts().get(0).getAccountStatusType(),actualData.getAccounts().get(0).getAccountStatusType());
        Assert.assertEquals(expectedData.getAccounts().get(0).getCreateDate(),actualData.getAccounts().get(0).getCreateDate());
        Assert.assertEquals(expectedData.getAccounts().get(0).getClosedDate(),actualData.getAccounts().get(0).getClosedDate());
        Assert.assertEquals(expectedData.getAccounts().get(0).getEmployee(),actualData.getAccounts().get(0).getEmployee());
        Assert.assertEquals(expectedData.getAccounts().get(0).getAccountlogs(),actualData.getAccounts().get(0).getAccountlogs());
        Assert. assertEquals(expectedData.getAccounts().get(1).getId(),actualData.getAccounts().get(1).getId());
        Assert.assertEquals(expectedData.getAccounts().get(1).getDescription(),actualData.getAccounts().get(1).getDescription());
        Assert.assertEquals(expectedData.getAccounts().get(1).getBalance(),actualData.getAccounts().get(1).getBalance());
        Assert.assertEquals(expectedData.getAccounts().get(1).getAccountType(),actualData.getAccounts().get(1).getAccountType());
        Assert.assertEquals(expectedData.getAccounts().get(1).getAccountStatusType(),actualData.getAccounts().get(1).getAccountStatusType());
        Assert.assertEquals(expectedData.getAccounts().get(1).getCreateDate(),actualData.getAccounts().get(1).getCreateDate());
        Assert.assertEquals(expectedData.getAccounts().get(1).getClosedDate(),actualData.getAccounts().get(1).getClosedDate());
        Assert.assertEquals(expectedData.getAccounts().get(1).getEmployee(),actualData.getAccounts().get(1).getEmployee());
        Assert.assertEquals(expectedData.getAccounts().get(1).getAccountlogs(),actualData.getAccounts().get(1).getAccountlogs());
    }
}