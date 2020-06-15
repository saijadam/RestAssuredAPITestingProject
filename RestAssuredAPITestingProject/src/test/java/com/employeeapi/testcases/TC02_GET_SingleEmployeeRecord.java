package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC02_GET_SingleEmployeeRecord extends TestBase{

	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("*********Started TC02_GET_SingleEmployeeRecord ************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees/"+empID);
		
		Thread.sleep(3000);
	}
	
	@Test
	public void testResponseBody()
	{
		String responseBody = response.getBody().asString();
		//System.out.println("Response Body is : " + responseBody);
		//logger.info("Response Body is : " + responseBody);
		
		Assert.assertEquals(responseBody.contains(empID),true,"Response Body is Not having Employee ID : " + empID);
	}
}
