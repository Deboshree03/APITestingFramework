package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestDemo {
	
	@Test
	public void getWeatherDetails(){
	
		//Specify the Base URI
		RestAssured.baseURI = "https://samples.openweathermap.org/data/2.5/weather";
		//?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22
		
		//Initializing object to send request to the server.
		RequestSpecification httpRequest = RestAssured.given();
		
		//Creating the Response object.
		Response response = httpRequest.request(Method.GET, "?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		//We have to get the response from the httpRequest so we specify the type of API call that is Method.GET in this case.
		//In the second argument, we are passing the query parameter to hit the End Point.
		//So the query parameter will be appended with the BaseURI and it will hit the End Point.
		
		//Extracting the response body from the response and Printing the response in Console Window to check.
		String responseString = response.getBody().asString();
		System.out.println("The Response Body is :---> " + responseString);
		
		//Validation of Status Code.
		int statusCode = response.getStatusCode();
		System.out.println("Response Status Code is :---> " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//Validation of Status Line.
		String statusLine = response.getStatusLine();
		System.out.println("Response Status Line is :---> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}

}
