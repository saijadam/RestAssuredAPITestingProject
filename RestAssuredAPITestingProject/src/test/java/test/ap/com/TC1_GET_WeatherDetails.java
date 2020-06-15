package test.ap.com;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC1_GET_WeatherDetails {
	
	@Test
	public void testWeatherDetails()
	{
		//Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response = httpRequest.request(Method.GET,"/Hyderabad");
		
		//Print resonse in the console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		
		//Verify Status Code
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 200, "Status Code is not as expected 200");
		
		//Status Line verification
		String statusLine = response.statusLine();
		System.out.println("Status Code is : " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Status Line is not as expected - HTTP/1.1. 200 OK");
		
	}

}
