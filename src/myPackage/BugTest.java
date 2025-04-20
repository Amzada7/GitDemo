package myPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class BugTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://amzadakhan2.atlassian.net/";
		
		String createIssueResponse = given()
		.header("Content-Type", "application/json")
		.header("Authorization", "Basic YW16YWRha2hhbjJAZ21haWwuY29tOkFUQVRUM3hGZkdGMHVkUTg1TG9acEdmZDlUdUJQclN3Wng2eVpRaEtMWEN1RGt6OWZBSllEWGhRQ2kyc1VLSERHVXlBeVZHTEo5NFdaMkYtWWZuU1pLTUNuZ3Z0U19DdkFJc2I0Q3JfWFVqR3RULWNfUFhhYmk1SlMzSlRkV2hndGVyaGY3QU9tcmxmRm9zREhNYnZkRVIyanZzVmxpd3FqdHhUbXpDRkRvVjhBVzRqaGYwOGU4ND02NzIyNEM1Rg==")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"RM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"New Icons are not working - Automation Code\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all()
		.post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueID = js.getString("id");
		
		System.out.println(issueID);
		//String fileResponse = 
		given()
		.pathParam("key", issueID)
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization", "Basic YW16YWRha2hhbjJAZ21haWwuY29tOkFUQVRUM3hGZkdGMHVkUTg1TG9acEdmZDlUdUJQclN3Wng2eVpRaEtMWEN1RGt6OWZBSllEWGhRQ2kyc1VLSERHVXlBeVZHTEo5NFdaMkYtWWZuU1pLTUNuZ3Z0U19DdkFJc2I0Q3JfWFVqR3RULWNfUFhhYmk1SlMzSlRkV2hndGVyaGY3QU9tcmxmRm9zREhNYnZkRVIyanZzVmxpd3FqdHhUbXpDRkRvVjhBVzRqaGYwOGU4ND02NzIyNEM1Rg==")
		.multiPart("file", new File("/Users/amzad/Downloads/NumberOne.png")).log().all()
		.post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);		
		 //.extract().response().asString();
		  
		//Ex for multiple files:
//		File myFile1 = new File("C:\\Test1.txt");
//		File myFile2 = new File("C:\\Test2.txt");
//		
//		File farr[] = {myFile1, myFile2};
//		given ()
//			.multiPart("files", farr)
//			.contentType("multipart/form-data")
		
//		 JsonPath js1 = new JsonPath(fileResponse);  
//		 String fileName =
//		 js1.getString("filename"); 
//		 System.out.println(fileName.toString()); 
//		 Assert.assertEquals("NumberOne.png", fileName);
		
		given()
		.pathParam("key", issueID)
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization", "Basic YW16YWRha2hhbjJAZ21haWwuY29tOkFUQVRUM3hGZkdGMHVkUTg1TG9acEdmZDlUdUJQclN3Wng2eVpRaEtMWEN1RGt6OWZBSllEWGhRQ2kyc1VLSERHVXlBeVZHTEo5NFdaMkYtWWZuU1pLTUNuZ3Z0U19DdkFJc2I0Q3JfWFVqR3RULWNfUFhhYmk1SlMzSlRkV2hndGVyaGY3QU9tcmxmRm9zREhNYnZkRVIyanZzVmxpd3FqdHhUbXpDRkRvVjhBVzRqaGYwOGU4ND02NzIyNEM1Rg==")
		.get("rest/api/3/issue/{key}")
		.then().log().all().assertThat().statusCode(200);
		
		 }

}
