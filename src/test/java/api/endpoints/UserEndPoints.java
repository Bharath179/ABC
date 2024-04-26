
package api.endpoints;
import static io.restassured.RestAssured.given;


import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	
	public static Response createUser(User payload) {
		
		Response res=given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		return res;
		
	}
	public static Response GetUser(String userName) {
		Response res=given()
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				
				.when()
				.get(Routes.get_url);
				
				return res;
	}
	
	public static Response UpdateUser(String userName,User payload) {
		Response res=given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				
				.when()
				.put(Routes.put_url);
				
				return res;
	}
	
	public static Response DeleteUser(String userName) {
		Response res=given()
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				
				.when()
				.delete(Routes.delete_url);
				
				return res;
}
}
