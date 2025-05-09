package myPackage;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
public class SpecBuilderTest {

	public static void main(String[] args) {
	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// POJO - Plain Old Java Object
		// Serialization - POJO Object to JSON/XML Object
		//Deserialization - JSON/XML Object to POJO Object
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("30, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 7013775107");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline House");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		RequestSpecification r = given().spec(req)
				.body(p);
		
				Response res = r.when().post("/maps/api/place/add/json")
				.then().spec(resSpec).extract().response();
		
		String responseString = res.asString();
		System.out.println(responseString);
				
	}

}
