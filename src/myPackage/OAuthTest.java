package myPackage;

import io.restassured.path.json.JsonPath;
import pojo.API;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

public class OAuthTest {

	public static void main(String[] args) {
		
		String courseTitles[] = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		String response = given()
				.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParams("grant_type", "client_credentials")
				.formParams("scope", "trust")
				.when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
			
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String token = js.getString("access_token");
		
//		String response2 = given()
//				.queryParams("access_token", token)
//				.when().log().all()
//				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
//		System.out.println(response2);
		
		GetCourse gc = given()
				.queryParams("access_token", token)
				.when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
	
		List<API> apiCourses = gc.getCourses().getApi();
		for (int i=0; i<apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		ArrayList<String> a = new ArrayList<String>();
		List<WebAutomation> autCourses = gc.getCourses().getWebAutomation();
		for (int j=0; j<autCourses.size(); j++) {
			a.add(autCourses.get(j).getCourseTitle());
		}
		
		List<String> expectedList = Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expectedList));
	}

}
