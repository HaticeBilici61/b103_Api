package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static util.AuthenticationGmiBank.gmiBankToken;

public class GmiBankBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setUp() {

        spec = new RequestSpecBuilder().setAccept(ContentType.JSON).addHeader("Authorization",gmiBankToken()).setBaseUri("https://www.gmibank.com").build();

    }

}

