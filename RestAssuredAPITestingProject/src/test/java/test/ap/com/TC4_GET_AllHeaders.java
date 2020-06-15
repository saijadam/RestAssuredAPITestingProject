package test.ap.com;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC4_GET_AllHeaders {
	
	@Test
	public void testAllHeader()
	{
		//Specify base URI
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
		
		//Print resonse in the console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		
		//Capture all the headers from response
		Headers allHeaders = response.headers();
		
		for(Header header:allHeaders)
		{
			System.out.println(header.getName() + " - " + header.getValue());
		}
		
				
	}

}
