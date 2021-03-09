package com.Rest.Sample;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.internal.path.ObjectConverter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssure {
	
	@Test
	public void GetBookDetails()
	 {   
	 // Specify the base URL to the RESTful web service
	 RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1/Books";
	 
	 // Get the RequestSpecification of the request that you want to sent
	 // to the server. The server is specified by the BaseURI that we have
	 // specified in the above step.
	 RequestSpecification httpRequest = RestAssured.given();
	 
	 // Make a request to the server by specifying the method Type and the method URL.
	 // This will return the Response from the server. Store the response in a variable.
	 Response response = httpRequest.get();
	 ///System.out.println("Responcecode is ::::::::::::"+response.toString());
	 int responcecode=response.statusCode();
	 System.out.println("Responcecode is ::::::::::::"+responcecode);
	 // Now let us print the body of the message to see what response
	 // we have recieved from the server
	 Assert.assertEquals(responcecode, 200);
	 if(responcecode!=200)
			 {
		 System.out.println("Responcecode is not valid::::::::::::"+responcecode);
			 }
	 String responseBody = response.getBody().asString();
	 System.out.println("Response Body is =>  " + responseBody);
	//JsonObject lo=new JsonObject();
	 int count=response.jsonPath().getList("books").size();
	 JsonPath jp=response.jsonPath();
	 
	 //JSONArray  jo=new JSONArray ();
	 //Sttoken = JsonPath.from(responseBody).get("token");
	 //Set<String> ob= response.jsonPath().get("isbn");
	 for(int i=0;i<=count;i++)
	 {
		 String id=jp.getJsonObject("books["+i+"].isbn");
		jp.get("books["+i+"].isbn");
		 
		 if(id.equals("9781449325862"))
		 {
			 System.out.println(jp.getChar("books["+i+"].isbn"));
			 System.out.println(jp.getInt("books["+i+"].pages"));
			 
			/* "isbn": "9781449325862",
			 "title": "Char",
			 "subTitle": "Char",
			 "author": "Char",
			 "publish_date": "Date",
			 "publisher": "Char",
			 "pages": Num,
			 "description": "String",
			 "Website": Link*/
			 //JsonPrimitive value = json.getAsJsonPrimitive("key");
		     //Object ob=jp.getChar(jp.getJsonObject("books["+i+"]"));
			 Object ob1=jp.getJsonObject("books["+i+"].isbn");
			 //JsonPrimitive jp=
			/* JsonPrimitive primitive = jp.getJsonObject("books["+i+"].isbn");
			 System.out.println(primitive.isJsonPrimitive());*/
			// System.out.println(ObjectConverter.convertObjectTo(get(jp.getJsonObject("books["+i+"].isbn")), String.class));
			 System.out.println(jp.getJsonObject("books["+i+"].isbn").toString());
			 
			 System.out.println(jp.getJsonObject("books["+i+"].title").toString());
			 System.out.println(jp.getJsonObject("books["+i+"].subTitle").toString());
			 System.out.println(jp.getJsonObject("books["+i+"].author").toString());
			 System.out.println(jp.getJsonObject("books["+i+"].publish_date").toString());
			 System.out.println(jp.getJsonObject("books["+i+"].pages").toString());
			 System.out.println(jp.getJsonObject("books["+i+"].description").toString());
			 System.out.println(jp.getJsonObject("books["+i+"].website").toString());
			 break;
		 }
	 }
	// JsonArray ja=new JsonArray();
	   
	 }

	private Object get(JsonPath jp) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	}




