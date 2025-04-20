package files;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQL_Script {

	public static void main(String[] args) {
		
		//Query
		int charId = 14170;
		String episodes = "Friends";
		String response = given().log().all()
				.header("Content-Type","application/json")
				.body("{\"query\":\"query($characterId: Int!, $episode: String!){\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    type\\n    id\\n  }\\n  location(locationId: 20355) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: 14190) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: \\\"Rahul\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      name\\n      type\\n    }\\n  } \\n  episodes (filters: {episode: $episode}) {\\n    result {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n}\\n\",\"variables\":{\"characterId\":"+charId+",\"episode\":\""+episodes+"\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().response().asString();
	
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String charName = js.getString("data.character.name");
		Assert.assertEquals(charName, "Suhana");
		
		//Mutation

		String locationName = "USA";
		String characterName = "Amzada";
		String episodeName = "Friends";
		String mResponse = given().log().all().header("Content-Type", "application/json").body(
				"{\"query\":\"mutation($locationName:String!,$characterName:String!,$episodeName:String!) {\\n  createLocation(location: {name:$locationName, type:\\\"Southzone\\\", dimension:\\\"234\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name:$characterName, type:\\\"Daring\\\",status:\\\"Alive\\\",species:\\\"Fantasy\\\",gender:\\\"Female\\\",image:\\\"png\\\",originId: 20349,locationId: 20349}) {\\n    id\\n  }\\n  createEpisode(episode: {name:$episodeName,air_date:\\\"20-04-2025\\\",episode:\\\"Friends\\\"}) {\\n    id\\n  }\\n  deleteLocations(locationIds:[20353,20354]) {\\n   locationsDeleted \\n  }\\n}\",\"variables\":{\"locationName\":\""+locationName+"\",\"characterName\":\""+characterName+"\",\"episodeName\":\""+episodeName+"\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

		System.out.println(mResponse);
		js = new JsonPath(mResponse);
		String locationsDeleted = js.getString("data.deleteLocations.locationsDeleted");
		System.out.println(locationsDeleted);
	}

}
