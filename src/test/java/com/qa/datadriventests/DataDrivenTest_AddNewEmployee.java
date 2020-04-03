package com.qa.datadriventests;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployee {
	
	
	@Test(dataProvider="EmployeeDataProvider")  //We have to specify from where the data is coming (Mention the dataProvider name).
	public void postNewEmployee(String empName, String empSal, String empAge){
		
			//Specify the Base URI
			RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
			//Initializing Object to send request to the URL
			RequestSpecification httpRequest = RestAssured.given();
			
			//Defining the Request Parameters to be sent as JSON PayLoad
			//This data is in HashMap format.
			JSONObject requestParameters = new JSONObject();
			requestParameters.put("name", empName);
			requestParameters.put("salary", empSal);
			requestParameters.put("age", empAge);
			
			//Add a header stating the Request Body is JSON.
			httpRequest.header("Content-Type", "application/json");
			
			//Add the JSON to the body of the Request.
			//We have added the data in requestParameters in the form of HashMap. 
			//To add these parameters to the request we need to convert it into JSONString. So we have used the method toJSONString.
			httpRequest.body(requestParameters.toJSONString());
				
			//Create the response object
			Response response = httpRequest.request(Method.POST,"/create");
			
			//Extracting the Response body in String Format.
			String responseBody = response.getBody().asString();
			System.out.println("The response from the API is :---> " + responseBody);
			
			Assert.assertEquals(responseBody.contains(empName), true);
			Assert.assertEquals(responseBody.contains(empSal), true);
			Assert.assertEquals(responseBody.contains(empAge), true);
			
			int statusCode = response.getStatusCode();
			System.out.println("The Status Code is :---> " + statusCode);
			Assert.assertEquals(statusCode, 200);
			
				
	}
	
	@DataProvider(name="EmployeeDataProvider")
	String[][] getEmpData() throws IOException{
		
		//Read Data from Excel.
		//Specify the location of the excel file from which we will take the data.
		String path = "C:\\Users\\deupadhyay\\workspace\\RestAssuredApiTesting\\src\\test\\java\\com\\qa\\datadriventests\\EmployeeData.xlsx";
		
		//The below line stores the path of the project in the variable dirPath.
		//String dirPath = System.getProperty("user.dir");
		
		int rowCount = XLUtils.getRowCount(path, "Sheet1");
		int columnCount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		//String empData[][] = {{"Sushree","50000","23"}, {"Nivedita","40000","24"}, {"Raksha","45000","25"}};
		//We were hardcoding the values previously. Now we will take data from excel fiel.
		String empData[][] = new String[rowCount][columnCount];
		for(int i = 1; i < rowCount; i++){
			
			for(int j = 0; j < columnCount; j++){
				
				empData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return(empData);
		
	}

}
