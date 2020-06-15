package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_GET_AllEmployees extends TestBase{
	
	@BeforeClass
	void getAllEmployes() throws InterruptedException
	{
		logger.info("*********Started TC01_GET_AllEmployees ************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(3000);
	}

	@Test
	public void testResponseBody()
	{
		logger.info("****Test Response Body***************");
		String responseBody = response.getBody().asString();
		//System.out.println("Response Body is : " + responseBody);
		logger.info("Response Body is : " + responseBody);
		
		Assert.assertTrue(responseBody!=null, "Response Body is NULL");
	}
	
	@Test
	public void testStatusCode()
	{
		int statusCode = response.getStatusCode();
		logger.info("Status Code is : " + statusCode);
		
		Assert.assertEquals(statusCode, 200, "Status Code is not as expected 200");
	}
	
	@Test
	public void testResponseTime()
	{
		long responseTime = response.getTime();
		logger.info("Response Time is : " + responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is > 2000");
		
		Assert.assertEquals(responseTime<2000, "Response Time is not as expected >2000");
	}
	
	@Test
	public void testStatusLine()
	{
		String statusLine = response.statusLine();
		logger.info("Status Line is : " + statusLine);
	
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Status Line is not as expected - HTTP/1.1. 200 OK");	
	}
	
	@Test
	public void testContentType()
	{
		String contentType = response.header("Content-Type");
		logger.info("Content Type is : " + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8", "Content Type is not as expected - text/html; charset=UTF-8");	
	}
	
	@Test
	public void testServerType()
	{
		String serverType = response.header("Server");
		logger.info("Server Type is : " + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1", "Server Type is not as expected - nginx/1.14.1");	
	}
	
	@Test
	public void testContentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is : " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip", "Content Encoding Type is not as expected - gzip");	
	}
	
	@Test
	public void testContentLength()
	{
		logger.info("****Content Length***************");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is : " + contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is < 100");
		
		Assert.assertEquals(Integer.parseInt(contentLength)<100, "Content Length is not as expected - < 100");	
	}
	
	@Test
	public void testCoockies()
	{
		//Verify Header of Response
		logger.info("****Checking Coockies***************");
		String cookie = response.getCookie("PHPSESSID");
		logger.info("Cookie is : " + cookie);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********Finished TC01_GET_AllEmployees ************");
	}
}
