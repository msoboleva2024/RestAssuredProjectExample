package baseComponent;

import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import api.CoreMethods;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import payload.Location;
import payload.Place;
import payload.PlacePreconditions;
import responses.AddPlaceResponse;
import testData.TestDataGeneration;


public class Preconditions extends TestDataGeneration {
	
	
	@Step("PreconditionStep: create Place on the resource")
	public PlacePreconditions addPlace() {

		Place payload = setPlaceDataAsPayload();
		PlacePreconditions responsePreconditions = new PlacePreconditions();

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		responsePreconditions.setPayload(payload);
		responsePreconditions.setPlace_id(response.getPlace_id());

		return responsePreconditions;

	}

	@Step("Step for generating of payload for creation of a new place on the resource")
	public Place setPlaceDataAsPayload() {

		Place payload = new Place();
		payload = new Place();
		int accuracy = generateRandomInt(1, 100);
		payload.setAccuracy(accuracy);
		String addresse = generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity();
		payload.setAddress(addresse);
		String language = generateLanguage();
		payload.setLanguage(language);
		Location location = new Location();
		double lat = generateRandomDouble(1.0, 9999.99);
		double lng = generateRandomDouble(1.0, 9999.99);
		location.setLat(lat);
		location.setLng(lng);
		payload.setLocation(location);
		String name = generateCity() + " name";
		payload.setName(name);
		String phone = generatePhoneNumber();
		payload.setPhone_number(phone);
		String website = generateWebSite();
		payload.setTypes(generateRandomArrayList(3));
		payload.setWebsite(website);

		Allure.addAttachment("The following data was generated as Payload: ",
				"accuracy: " + accuracy + "\n" + "language: " + language + "\n" + "addresse:" + addresse + "\n" + "lat:"
						+ lat + "\n" + "lng:" + lng + "\n" + "name:" + name + "\n" + "phone:" + phone + "\n"
						+ "website:" + website);
		return payload;
	}

	@Step("Generate invalid payload for creation of place")
	public String getInvalidPayloadStructure() {

		return "{\n" + " \n" + " \n" + "    	\"lat\" : -38.383494,\n" + " \n" + "    	\"lng\" : 33.427362\n"
				+ " \n" + "    	},\n" + " \n" + "    	\"accuracy\":50,\n" + " \n"
				+ "    	\"name\":\"Frontline house\",\n" + " \n" + "    	\"phone_number\":\"(+91) 983 893 3937\",\n"
				+ " \n" + "    	\"address\" : \"29, side layout, cohen 09\",\n" + " \n"
				+ "    	\"types\": [\"shoe park\",\"shop\"],\n" + " \n" + "    	\"website\" : \"http://google.com\",\n"
				+ " \n" + "    	\"language\" : \"French-IN\"\n" + " \n" + "}\n" + "";

	}


	
	@BeforeSuite
	public void setup() {
		
		RestAssured.filters(new AllureRestAssuredFilter());
		
	      
	    }
	

}
