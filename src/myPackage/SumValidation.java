package myPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourses() {
		
		JsonPath js = new JsonPath(payload.coursePrices());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		int copies;
		int sum=0;
		int price;
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		for (int i=0; i<count; i++) {
			price = js.getInt("courses[" + i + "].price");
			copies = js.getInt("courses[" + i + "].copies");
			sum = sum + (price * copies);
		}
		System.out.println("Sum of Copies: " + sum);
		Assert.assertEquals(purchaseAmount, sum);
		
	}
}
