package jsonparsing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;


public class ResponseBodyValidation {

	@Test(priority = 1)
	void test1() {
		
		given()
		.contentType("Content-Type.JSON")
		
		.when()
		.get("http://localhost:3000/store")
		
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json")
		.body("book[3].title", equalTo("The lord of the Rings"));
		
	}
	
	@Test(priority = 2)
	void test2() {
		Response res = given()
		.contentType("Content-Type.JSON")
		
		.when()
		.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The lord of the Rings");
	}
	
	@Test(priority = 3)
	void test3() {
		Response res = given()
				.contentType("Content-Type.JSON")
				
				.when()
				.get("http://localhost:3000/store");
		
		JSONObject obj=new JSONObject(res.asString());
		for(int i=0;i<obj.getJSONArray("book").length();i++) {
			String bookstitle = obj.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookstitle);
		}
		
	}
	
	@Test(priority = 4)
	void test4() {
		
		Response res = given()
				.contentType("Content-Type.JSON")
				
				.when()
				.get("http://localhost:3000/store");
		
		JSONObject obj=new JSONObject(res.asString());
		
		boolean status=false;
		
		for(int i=0;i<obj.getJSONArray("book").length();i++) {
			String booktitle = obj.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(booktitle.equals("The lord of the Rings")) {
				status=true;
			break;
			}
	}
	Assert.assertEquals(status, true);
}
	
	@Test(priority = 5)
	void test5() {
		
		Response res = given()
				.contentType("Content-Type.JSON")
				
				.when()
				.get("http://localhost:3000/store");
		
		JSONObject obj=new JSONObject(res.asString());
		
		double totalPrice=0;
		
		for(int i=0;i<obj.getJSONArray("book").length();i++) {
			String booksPrice = obj.getJSONArray("book").getJSONObject(i).get("price").toString();
			totalPrice=totalPrice+Double.parseDouble(booksPrice);
	}
		System.out.println("The total price of books is:"+totalPrice);
		Assert.assertEquals(totalPrice, 526.0);
	}
}
