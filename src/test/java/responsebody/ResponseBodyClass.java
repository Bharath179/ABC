package responsebody;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ResponseBodyClass {

	@Test
	void getBodydetails() {
		
		RestAssured.baseURI="https://reqres.in/api/users";
		
		RequestSpecification http=RestAssured.given();
		Response response = http.request(Method.GET,"/2");
//		String responseObj = response.getBody().asString();
//		System.out.println("My response is:"+responseObj);
		
		ResponseBody body = response.getBody();
		String bodyObj = body.asString();
		System.out.println(bodyObj);
		
		Assert.assertEquals(bodyObj.contains("email"), true);
		
		JsonPath js=response.jsonPath();
		String value1 = js.get("data.email");
		Assert.assertEquals(value1.equals("janet.weaver@reqres.in"), true);
	}
}
