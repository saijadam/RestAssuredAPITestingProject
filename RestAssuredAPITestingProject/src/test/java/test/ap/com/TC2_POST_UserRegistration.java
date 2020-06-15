package test.ap.com;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC2_POST_UserRegistration {

	@Test
	public void testCustomerRegistration()
	{
		//Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Request payload sending along with POST Request
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Sai");
		requestParams.put("LastName", "Kumar");
		requestParams.put("UserName", "saimtech");
		requestParams.put("Password", "password");
		requestParams.put("Email", "saimtech@yahoo.com");
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		
		//Request - Response Object
		Response response = httpRequest.request(Method.POST,"/register");
		
		//Print resonse in the console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		
		//Verify Status Code
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 201, "Status Code is not as expected 201");
		
		//Response verification
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS", "Success Code is not as expected - OPERATION_SUCCESS");
	}
}
