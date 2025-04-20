package myPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
public class SerializeTest {

	public static void main(String[] args) {
	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("70 Summer walk, USA");
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
		Response r = given().log().all().queryParam("key", "qaclick123")
				.body(p)
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();
		
		String responseString = r.asString();
		System.out.println(responseString);
				
	}

}
