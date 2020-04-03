package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestValidateHeaders {
	
	@Test
	public void getRequestWithHeaders(){
		
		//Specify the Base URI
		RestAssured.baseURI = "https://samples.openweathermap.org/data/2.5/weather";
		
		//Initializing object to send request to the server.
		RequestSpecification httpRequest = RestAssured.given();
		
		//Creating the Response object.
		Response response = httpRequest.request(Method.GET, "?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		//We have to get the response from the httpRequest so we specify the type of API call that is Method.GET in this case.
		//In the second argument, we are passing the query parameter to hit the End Point.
		//So the query parameter will be appended with the BaseURI and it will hit the End Point.
		
		//Extracting the response body from the response and Printing the Response Body in the console.
		String responseString = response.getBody().asString();
		System.out.println("The Response Body is :---> " + responseString);
		
		//Validating Headers from Response.
		//Capturing the value of header "Content-Type" and validating.
		String contentType = response.header("Content-Type");
		System.out.println("The Content-Type is :---> " + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		//Capturing the value of header "Content-Encoding" and validating.
		String contentEncoding = response.header("Content-Encoding");
		System.out.println("The Content-Encoding is :---> " + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
		//Capturing the value of header "Server" and validating.
		String server = response.header("Server");
		System.out.println("The value of Server is :---> " + server);
		Assert.assertEquals(server, "openresty/1.9.7.1");
			
	}

}
