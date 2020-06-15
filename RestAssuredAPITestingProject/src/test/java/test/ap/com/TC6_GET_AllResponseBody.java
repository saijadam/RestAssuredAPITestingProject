package test.ap.com;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC6_GET_AllResponseBody {

	@Test
	public void testJSONResponseBody()
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
		
		//Extract all the Response
		JsonPath jsonPath = response.jsonPath();
		
		System.out.println(jsonPath.get("City"));
		System.out.println(jsonPath.get("Temperature"));
		System.out.println(jsonPath.get("Humidity"));
		System.out.println(jsonPath.get("WeatherDescription"));
		System.out.println(jsonPath.get("WindSpeed"));
		System.out.println(jsonPath.get("WindDirectionDegree"));
		
		Assert.assertEquals(responseBody.contains("Hyderabad"), true, "Response Body is not as expected - Not Having Hyderabad");
		//Add for Rest Response Assertions
	}
}
