package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.CoreMethods;

import baseComponent.Preconditions;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import io.restassured.response.Response;
import payload.Location;
import payload.Place;
import responses.AddPlaceResponse;

@Epic("GoogleMap API")
@Feature("Google Maps Add API (POST)")
public class AddPlaceTests extends Preconditions {

	private Place payload;

	@Epic("GoogleMap API")
	@Feature("Google Maps Add API (POST)")
	@Test(priority = 0, description = "Positiv scenario for testing of 'Add' resource: successfull creation of place")
	@Description("Test-case will check that it is possible to create a new place on the resource. Data will be automatically generated.")
	public void addPlacePositiv() {

		// preconditions
		Preconditions preconditions = new Preconditions();
		payload = preconditions.setPlaceDataAsPayload();

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());

	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty accuracy")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty accuracy in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyAccurancy() {

		Place payload = new Place();
		payload = new Place();

		payload.setAddress(generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity());
		payload.setLanguage(generateLanguage());
		Location location = new Location();
		location.setLat(generateRandomDouble(1.0, 9999.99));
		location.setLng(generateRandomDouble(1.0, 9999.99));
		payload.setLocation(location);
		payload.setName(generateCity() + " name");
		payload.setPhone_number(generatePhoneNumber());

		payload.setTypes(generateRandomArrayList(3));
		payload.setWebsite(generateWebSite());

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());
	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty name")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty name in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyName() {

		Place payload = new Place();
		payload = new Place();
		payload.setAccuracy(generateRandomInt(1, 100));
		payload.setAddress(generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity());
		payload.setLanguage(generateLanguage());
		Location location = new Location();
		location.setLat(generateRandomDouble(1.0, 9999.99));
		location.setLng(generateRandomDouble(1.0, 9999.99));
		payload.setLocation(location);
		payload.setName("");
		payload.setPhone_number(generatePhoneNumber());

		payload.setTypes(generateRandomArrayList(3));
		payload.setWebsite(generateWebSite());

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());
	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty language")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty language in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyLanguage() {

		Place payload = new Place();
		payload = new Place();

		payload.setAddress(generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity());
		payload.setLanguage("");
		payload.setAccuracy(generateRandomInt(1, 100));
		Location location = new Location();
		location.setLat(generateRandomDouble(1.0, 9999.99));
		location.setLng(generateRandomDouble(1.0, 9999.99));
		payload.setLocation(location);
		payload.setName(generateCity() + " name");
		payload.setPhone_number(generatePhoneNumber());

		payload.setTypes(generateRandomArrayList(3));
		payload.setWebsite(generateWebSite());

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());
	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty address")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty address in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyAddress() {

		Place payload = new Place();
		payload = new Place();

		payload.setAddress("");
		payload.setLanguage(generateLanguage());
		payload.setAccuracy(generateRandomInt(1, 100));
		Location location = new Location();
		location.setLat(generateRandomDouble(1.0, 9999.99));
		location.setLng(generateRandomDouble(1.0, 9999.99));
		payload.setLocation(location);
		payload.setName(generateCity() + " name");
		payload.setPhone_number(generatePhoneNumber());

		payload.setTypes(generateRandomArrayList(3));
		payload.setWebsite(generateWebSite());

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());
	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty latitude")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty latitude in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyLatitude() {

		Place payload = new Place();
		payload = new Place();

		payload.setAddress(generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity());
		payload.setLanguage(generateLanguage());
		payload.setAccuracy(generateRandomInt(1, 100));
		Location location = new Location();

		location.setLng(generateRandomDouble(1.0, 9999.99));
		payload.setLocation(location);
		payload.setName(generateCity() + " name");
		payload.setPhone_number(generatePhoneNumber());

		payload.setTypes(generateRandomArrayList(3));
		payload.setWebsite(generateWebSite());

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());
	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty lng")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty lng in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyLng() {

		Place payload = new Place();
		payload = new Place();

		payload.setAddress(generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity());
		payload.setLanguage(generateLanguage());
		payload.setAccuracy(generateRandomInt(1, 100));
		Location location = new Location();
		location.setLat(generateRandomDouble(1.0, 9999.99));

		payload.setLocation(location);
		payload.setName(generateCity() + " name");
		payload.setPhone_number(generatePhoneNumber());

		payload.setTypes(generateRandomArrayList(3));
		payload.setWebsite(generateWebSite());

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());

	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty phone")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty phone in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyPhone() {

		Place payload = new Place();
		payload = new Place();

		payload.setAddress(generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity());
		payload.setLanguage(generateLanguage());
		payload.setAccuracy(generateRandomInt(1, 100));
		Location location = new Location();
		location.setLat(generateRandomDouble(1.0, 9999.99));
		location.setLat(generateRandomDouble(1.0, 9999.99));

		payload.setLocation(location);

		payload.setName(generateCity() + " name");

		payload.setPhone_number("");

		payload.setTypes(generateRandomArrayList(3));
		payload.setWebsite(generateWebSite());

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());
	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty types")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty types in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyTypes() {

		Place payload = new Place();
		payload = new Place();

		payload.setAddress(generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity());
		payload.setLanguage(generateLanguage());
		payload.setAccuracy(generateRandomInt(1, 100));
		Location location = new Location();
		location.setLat(generateRandomDouble(1.0, 9999.99));
		location.setLat(generateRandomDouble(1.0, 9999.99));

		payload.setLocation(location);

		payload.setName(generateCity() + " name");

		payload.setPhone_number(generatePhoneNumber());

		payload.setWebsite(generateWebSite());

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());
	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with empty website")
	@Description("Test-case will check that it is possible to create a new place on the resource with empty website in the payload. Data will be automatically generated.")

	public void addPlaceWithEmptyWebSite() {

		Place payload = new Place();
		payload = new Place();

		payload.setAddress(generateStreetName(10) + " " + generateRandomInt(1, 1000) + ", "
				+ generateRandomInt(11111, 99999) + " " + generateCity());
		payload.setLanguage(generateLanguage());
		payload.setAccuracy(generateRandomInt(1, 100));
		Location location = new Location();
		location.setLat(generateRandomDouble(1.0, 9999.99));
		location.setLat(generateRandomDouble(1.0, 9999.99));

		payload.setLocation(location);

		payload.setName(generateCity() + " name");

		payload.setPhone_number(generatePhoneNumber());
		payload.setTypes(generateRandomArrayList(3));

		payload.setWebsite("");

		CoreMethods apiMethods = new CoreMethods();
		AddPlaceResponse response = apiMethods.addPlace(payload);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatus(), "OK");
		softAssert.assertEquals(response.getScope(), "APP");
		softAssert.assertNotNull(response.getId());
		softAssert.assertNotNull(response.getPlace_id());
		softAssert.assertNotNull(response.getReference());
		softAssert.assertAll();

		// postconditions
		apiMethods.deletePlace(response.getPlace_id());
	}

	@Test(priority = 1, description = "Negative scenario for testing of 'Add' resource:  creation of place with Invalid payload structure")
	@Description("Test-case will check that it is NOT possible to create a new place on the resource with invalid structure of the payload. Data will be automatically generated.")

	public void addPlaceWithInvalidPayloadStructure() {

		String payload = getInvalidPayloadStructure();
		CoreMethods apiMethods = new CoreMethods();
		Response response = apiMethods.addPlace(payload);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatusCode(), 200);
		softAssert.assertEquals(response.getBody(), "");

	}

}
