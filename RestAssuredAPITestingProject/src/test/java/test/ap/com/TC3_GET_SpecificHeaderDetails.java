package test.ap.com;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC3_GET_SpecificHeaderDetails {

	@Test
	public void testGoogleMap()
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
		
		//Verify Header of Response
		String contentType = response.header("Content-Type");
		System.out.println("Content Type is : " + contentType);
		Assert.assertEquals(contentType, "application/xml; charset=UTF-8", "Content Type is not as expected - application/xml; charset=UTF-8");
		
		//Verify Header of Response
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content Encoding is : " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip", "Content Encoding is not as expected - gzip");
		
		//Like this can verify all the Response Header Stuff
				
	}
}
