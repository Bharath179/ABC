package headers;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ValidateHeaders {

	@Test(priority = 1)
	void test1() {
		Response res = given()
				
				.when()
				.get("https://www.google.com/");
		String header_value = res.getHeader("Content-Type");
		System.out.println("The value of header is:"+header_value);
	}
	
	@Test(priority = 2)
	void test2() {
            Response res = given()
				
				.when()
				.get("https://www.google.com/");
           Headers headers = res.getHeaders();
           for (Header hd : headers) {
        	   System.out.println(hd.getName()+"------->"+hd.getValue());
		}
           
	}
}
