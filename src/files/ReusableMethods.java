package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

	public static JsonPath jsonMethods(String response) {
		
		JsonPath js1 = new JsonPath(response);
		return js1;
	}

}
