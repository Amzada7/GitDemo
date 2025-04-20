package myPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) throws IOException {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(GenerateStringFromResource("C:\\Users\\amzad\\eclipse-workspace\\RestAssuredProject\\src\\files\\addPlace.json"))
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
		System.out.println(response);
		//Parsing JSON
		JsonPath js = new JsonPath(response);
		String placeID= js.getString("place_id");
		System.out.println(placeID);
		
		//Update API
		String address = "Summer Walk, Atlanta";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "	\"place_id\": \"" + placeID + "\",\r\n"
				+ "    \"address\": \"" + address + "\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get API
				String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();

				JsonPath js1 = ReusableMethods.jsonMethods(getPlaceResponse);
				String actualAddress = js1.getString("address");
				Assert.assertEquals(address, actualAddress);
	}

	public static String GenerateStringFromResource(String path) throws IOException {



	    return new String(Files.readAllBytes(Paths.get(path)));



	}

}
