package myPackage;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetails;
import pojo.Orders;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
public class ECommerceAPITest {

	public static void main(String[] args) {
		
		//Login
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
		.setContentType(ContentType.JSON).build();
		
		LoginRequest login = new LoginRequest();
		login.setUserEmail("amzadakhan2@gmail.com");
		login.setUserPassword("Amzada23*");

		RequestSpecification reqLogin = given().spec(reqSpec).body(login);
		LoginResponse loginRes = reqLogin.when().post("api/ecom/auth/login").then().extract().response()
				.as(LoginResponse.class);

		String token = loginRes.getToken();
		String userId = loginRes.getUserId();

		System.out.println(token);
		System.out.println(userId);
		
		//Add Product
		RequestSpecification addProdreq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).build();
		RequestSpecification reqSpecAddProduct = given().spec(addProdreq)
		.param("productName", "Unique Order")
		.param("productAddedBy", userId)
		.param("productCategory", "Fashion")
		.param("productSubCategory", "Numbers")
		.param("productPrice", "25000")
		.param("productDescription", "Just a Description on Product")
		.param("productFor", "Women")
		.multiPart("productImage", new File("/Users/amzad/Downloads/Dress.jpg"));
		
		String addProdResponse = reqSpecAddProduct.when().post("api/ecom/product/add-product")
		.then().extract().response().asString();
		
		JsonPath js = new JsonPath(addProdResponse);
		String prodId = js.getString("productId");
		System.out.println(prodId);
	
		//Create Order
		OrderDetails orderDetail = new OrderDetails();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(prodId);
		
		List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
		orderDetailList.add(orderDetail);
		
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		RequestSpecification createOrderReqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		RequestSpecification reqSpecCreateOrder = given().spec(createOrderReqSpec).body(orders);
		
		String createOrderresponse = reqSpecCreateOrder.when().post("api/ecom/order/create-order").then().extract().response().asString();
		System.out.println(createOrderresponse);
		
		JsonPath js1 = new JsonPath(createOrderresponse);
		List<String> orderID = js1.getList("orders");
		String orderID1 = orderID.get(0);
		System.out.println(orderID1);
		//View Order Details
		RequestSpecification viewOrderReqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).addQueryParam("id", orderID1).build();
		
		String viewOrderresponse = given().spec(viewOrderReqSpec).when().get("api/ecom/order/get-orders-details").then().extract().response().asString();
		System.out.println(viewOrderresponse);
		
		//DeleteProduct
		RequestSpecification deleteProdReqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		RequestSpecification reqSpecdeleteProd = given().spec(deleteProdReqSpec).pathParam("productId", prodId);
		
		String deleteProdresponse = reqSpecdeleteProd.when().delete("api/ecom/product/delete-product/{productId}").then().extract().response().asString();
		System.out.println(deleteProdresponse);
		
		JsonPath js2 = new JsonPath(deleteProdresponse);
		Assert.assertEquals("Product Deleted Successfully", js2.get("message"));
		
	}

}
