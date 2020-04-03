package com.qa.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExtractNodeValueFromJson {
	
	@Test
	public void extractNodeValueFromJson(){
	
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
	
	JsonPath jsonPath = response.jsonPath();
	//The method jsonPath will store the complete JSON response in the object jsonPath.
	
	System.out.println(jsonPath.get("coord"));
	System.out.println(jsonPath.get("weather"));
	//Weather is a JSONArray. We can print the values in the following way. weather[0] indicates the index number.
	//And the value after the . indicates the attribute name. the get method will get the value of the mentioned attribute.
	System.out.println(jsonPath.get("weather[0].id"));       
	System.out.println(jsonPath.get("weather[0].main"));
	System.out.println(jsonPath.get("weather[0].description"));
	System.out.println(jsonPath.get("weather[0].icon"));
	
	}

}
	
	
