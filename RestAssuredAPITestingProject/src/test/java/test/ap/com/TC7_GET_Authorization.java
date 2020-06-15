package test.ap.com;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC7_GET_Authorization {

	@Test
	public void testJSONResponseBody()
	{
		//Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
				
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response = httpRequest.request(Method.GET,"/");
		
		
		
		RestAssured.authentication = authScheme;
		
		//Print resonse in the console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		//Verify Status Code
		//Verify Status Code
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 200, "Status Code is not as expected 200");
				
					
		
	}
}
