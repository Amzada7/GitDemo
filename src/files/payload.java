package files;

import myPackage.Basics;

public class payload {
	
	public static String AddPlace() {
		
		return "{\r\n"
				+ "	\"location\": {\r\n"
				+ "        \"lat\": -38.38,\r\n"
				+ "        \"lng\": 33.47\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Frontline House\",\r\n"
				+ "    \"phone_number\": \"(+91) 7013775107\",\r\n"
				+ "    \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String UpdatePlace() {
		
		return "{\r\n"
				+ "	\"place_id\": \"68539bcd442c90967ef601b186097b81\",\r\n"
				+ "    \"address\": \"70 Summer walk, USA\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}";
	}
	
	public static String coursePrices() {
		
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
	public static String addBook(String isbn, String aisle) {
		
		String payLoad = "{\r\n"
			+ "\"name\": \"Learn Appium Automation with Java\",\r\n"
			+ "\"isbn\": \"" + isbn + "\",\r\n"
			+ "\"aisle\": \"" + aisle + "\",\r\n"
			+ "\"author\": \"John foer\"\r\n"
			+ "}\r\n"
			+ "";
		return payLoad;
		
	}
	public static String deleteBook(String id) {
		
		String payLoad = "{\r\n"
				+ "    \"ID\": \"" + id + "\"\r\n"
				+ "}";
		return payLoad;
		
	}

}