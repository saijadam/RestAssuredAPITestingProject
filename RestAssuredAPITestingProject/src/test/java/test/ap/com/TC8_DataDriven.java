package test.ap.com;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC8_DataDriven {
	
	@Test(dataProvider="empdataprovider")
	public void testpostEmployeeDetails(String name, String salary, String age)
	{
		//Specify base URI
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Request payload sending along with POST Request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);
		requestParams.put("salary", salary);
		requestParams.put("age", age);
		
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		
		//Request - Response Object
		Response response = httpRequest.request(Method.POST,"/create");
		
		//Print resonse in the console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is : " + responseBody);
		
		
		Assert.assertEquals(responseBody.contains(name), true, "Response Body is not as expected - Name:" + name);
		Assert.assertEquals(responseBody.contains(salary), true, "Response Body is not as expected - Salary: " + salary);
		Assert.assertEquals(responseBody.contains(age), true, "Response Body is not as expected - Age: " + age);
		
		
		
		//Verify Status Code
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is : " + statusCode);
		Assert.assertEquals(statusCode, 200, "Status Code is not as expected 200");
		
		
		
	}

	@DataProvider(name="empdataprovider")
	String[][] getEmpData()
	{
		String empdata[][] = {{"emp1","1000","30"},{"emp2","2000","31"},{"emp3","3000","33"}};
		return empdata;
	}
}
