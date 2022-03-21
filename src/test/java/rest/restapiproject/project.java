package rest.restapiproject;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class project {
	@Test(enabled = true)
	public void createUser(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		obj.put("username", "KK");
		obj.put("firstName", "Krishnakumar");
		obj.put("lastName", "Kunnath,");
		obj.put("email", "kk1968@shi.com");
		obj.put("password", "kk124");
		obj.put("phone", "8825364789");
		
		String u_name="KK";
		
		
		given()
		.contentType(ContentType.JSON)
		.body(obj.toJSONString()).
	when()
		.post("/user").
	then()
		.statusCode(200)
		.log()
		.all();
		
		val.setAttribute("username", u_name);
	}
	
	@Test(enabled = true, dependsOnMethods="createUser")
	public void login()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given().
		when()
			.queryParam("username","KK")
			.queryParam("password","kk124")
			.get("/user/login").
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(enabled = true, dependsOnMethods= "login")
	public void edit(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		obj.put("username", "SK");
		obj.put("firstName", "Shivam");
		obj.put("lastName", "Singh");
		obj.put("email", "shivam78@gmail.com");
		obj.put("password", "singh1234");
		obj.put("phone", "9835925389");
		
		String u_name="SK";
		
		given()
		.contentType(ContentType.JSON)
		.body(obj.toJSONString()).
	when()
		.put("/user/"+val.getAttribute("username")).
	then()
		.statusCode(200)
		.log()
		.all();
		val.setAttribute("username", u_name);
		
	}
	
	@Test(enabled = true, dependsOnMethods= "edit")
	public void logout()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given().
		when()
			.get("/user/logout").
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(enabled = true, dependsOnMethods="logout")
	public void delete(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		given().
		when()
		.delete("/user/"+val.getAttribute("username").toString()).
	then()
		.statusCode(200)
		.log()
		.all();
		

	}
	
}

