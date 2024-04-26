package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void generateTestData() {
		faker=new Faker();
		
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUserName(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhno(faker.phoneNumber().cellPhone());
		
	}
	@Test(priority = 1)
	public void testCreateUser() {
		Response res=UserEndPoints.createUser(userpayload);
		
		//log respose
		res.then().log().all();
		
		//validation
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	@Test(priority = 2)
	public void testGetUser() {
		Response res = UserEndPoints.GetUser(this.userpayload.getUserName());
		
		//log response
		res.then().log().all();
		
		//validation
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	@Test(priority = 3)
	public void testUpdateUser() {
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response res=UserEndPoints.UpdateUser(this.userpayload.getUserName(),userpayload);
		
		//log respose
		res.then().log().all();
		
		//validation
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	@Test(priority = 4)
	public void testDeleteUser() {
		Response res = UserEndPoints.DeleteUser(this.userpayload.getUserName());
		
		//log response
		res.then().log().all();
		
		//validation
		Assert.assertEquals(res.getStatusCode(), 200);
	}
}
