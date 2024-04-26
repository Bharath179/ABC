package cookies;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class HandleCookies {

	@Test(priority = 1)
	void test1() {
        given()
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.log().all();
	}
	
	@Test(priority = 2)
	void test2() {
		 Response res = given()
			
			.when()
			.get("https://www.google.com/");
			String cookie_value = res.getCookie("AEC");
		 System.out.println("The value of cookie is:"+cookie_value);
	}
	
	@Test(priority = 3)
	void test3() {
		Response res = given()
				
				.when()
				.get("https://www.google.com/");
		Map<String, String> cookies_values = res.getCookies();
		//System.out.println(cookies_values);
		for (String k : cookies_values.keySet()) {
			System.out.println(k+"-------->"+cookies_values.values());
		}
	}
}
