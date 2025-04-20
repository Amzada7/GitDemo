package myPackage;

import java.util.Iterator;

import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJSONParse {

	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(payload.coursePrices());
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
	
		String course1 = js.getString("courses[0].title");
		System.out.println(course1);
		
		String course;
		int price;
		for (int i=0; i<count; i++) {
			course = js.getString("courses[" + i + "].title");
			price = js.getInt("courses[" + i + "].price");
			System.out.println("Course" + (i+1) + ": " + course + " and Price" + (i+1) + ": " + price);
		}
		
		int rpaCopies=0;
		for (int i=0; i<count; i++) {
			course = js.getString("courses[" + i + "].title");
			if (course.equals("RPA")) {
				rpaCopies = js.getInt("courses[" + i + "].copies");
			}
			System.out.println("RPA Copies" + rpaCopies);
			break;
		}
		int copies;
		int sum=0;
		for (int i=0; i<count; i++) {
			price = js.getInt("courses[" + i + "].price");
			copies = js.getInt("courses[" + i + "].copies");
			sum = sum + (price * copies);
		}
		System.out.println("Sum of Copies: " + sum);
		Assert.assertEquals(purchaseAmount, sum);
		
	}

}
