package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {

	@Test(dataProvider="BookData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json")
		.body(payload.addBook(isbn, aisle))
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = ReusableMethods.jsonMethods(response);
		String id = js.get("ID");
		System.out.println(id);
		System.out.println("UserX code changes");
	
	//Delete
	
	
	  given()
	  .body(payload.deleteBook(isbn+aisle))
	  .when()
	  .delete("/Library/DeleteBook.php") //
	  .then().assertThat().log().all().statusCode(200).body("msg", equalTo("book is successfully deleted"));
	 	 	
}
	@DataProvider(name="BookData")
	public Object[][] getData() {
		
		return new Object[][] {{"abcd1","1234"}, {"abcd2","12345"}, {"abcd3","123456"}};
	}
}
