package com.qa.tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestDemo {
	
	@Test
	public void postRequest(){
		
		//Specify the base URI
		RestAssured.baseURI = "https://reqres.in";
		
		//Initializing object to send request to the server.
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		//In case of POST, we have to send the JSON PayLoad.
		JSONObject requestParameters = new JSONObject();
		requestParameters.put("name", "jennifer");
		requestParameters.put("job", "architect");
		
		//Adds JSON to the body of the request.
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParameters.toJSONString());
		
		//Creating the Response Object.
		Response response = httpRequest.request(Method.POST, "/api/users");
		
		//Extracting the response body from the response and Print response in Console Window.
		String responseString = response.getBody().asString();
		System.out.println("The Response Body is :---> " + responseString);
		
		//Validation of Status Code.
		int statusCode = response.getStatusCode();
		System.out.println("Response Status Code is :---> " + statusCode);
		Assert.assertEquals(statusCode, 201);
		
		//Validation of Status Line.
		String statusLine = response.getStatusLine();
		System.out.println("Response Status Line is :---> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
		
		//Validating data from the JSON Response.
		String createdAt = response.jsonPath().get("createdAt");
		System.out.println("The created time is :---> " + createdAt);
		Assert.assertEquals(createdAt, "2020-01-21T10:25:15.694Z");
	}

}
