package test;

import java.util.List;

import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.CoreMethods;

import baseComponent.Preconditions;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.PlacePreconditions;
import responses.GetPlaceResponse;

@Epic("GoogleMap API")
@Feature("Google Maps Get API (GET)")
public class GetPlaceTests extends Preconditions {

	@Epic("GoogleMap API")
	@Feature("Google Maps Get API (GET)")
	@Test(priority = 0, description = "Checking of getting possibility for already existed place")
	@Description("Test-case will check that it is possible to get data from the resource about already existed place")
	public void getPlacePositiv() {

		// preconditions: create Place to get place_id
		PlacePreconditions responsePreconditions = addPlace();
		CoreMethods apiMethods = new CoreMethods();
		Response customResponse = apiMethods.getPlace(responsePreconditions.getPlace_id());
		GetPlaceResponse response = customResponse.as(GetPlaceResponse.class);

		List<String> types = responsePreconditions.getPayload().getTypes();
		String expectedTypes = "";

		for (int i = 0; i < types.size(); i++) {

			if (i > 0)
				expectedTypes = expectedTypes + "," + types.get(i);
			else
				expectedTypes = types.get(i);
		}

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getAccuracy(), responsePreconditions.getPayload().getAccuracy());
		softAssert.assertEquals(response.getAddress(), responsePreconditions.getPayload().getAddress());
		softAssert.assertEquals(response.getLanguage(), responsePreconditions.getPayload().getLanguage());
		softAssert.assertEquals(response.getName(), responsePreconditions.getPayload().getName());
		softAssert.assertEquals(response.getPhone_number(), responsePreconditions.getPayload().getPhone_number());
		softAssert.assertEquals(response.getWebsite(), responsePreconditions.getPayload().getWebsite());
		softAssert.assertEquals(response.getTypes(), expectedTypes);
		softAssert.assertEquals(response.getLocation().getLatitude(),
				String.valueOf(responsePreconditions.getPayload().getLocation().getLat()));
		softAssert.assertEquals(response.getLocation().getLongitude(),
				String.valueOf(responsePreconditions.getPayload().getLocation().getLng()));
		softAssert.assertAll();

		// postconditions: delete created Place
		apiMethods.deletePlace(responsePreconditions.getPlace_id());

	}

	@Epic("GoogleMap API")
	@Feature("Google Maps Get API (GET)")
	@Test(priority = 1, description = "Checking of getting possibility for not-existed place")
	@Description("Test-case will check that it is impossible to get data from the resource about the not-existed place")

	public void getPlaceNotExistedId() {

		CoreMethods apiMethods = new CoreMethods();
		Response response = apiMethods.getPlace(generatePlaceId(8));
		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 404);
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.getString("msg"), "Get operation failed, looks like place_id  doesn't exists");

	}

	@Epic("GoogleMap API")
	@Feature("Google Maps Get API (GET)")
	@Test(priority = 2, description = "Checking of getting possibility for already existed place with long place_id")
	@Description("Test-case will check that it is possible to get data from the resource about already existed place with long place_id")

	public void getPlaceLongId() {

		CoreMethods apiMethods = new CoreMethods();
		Response response = apiMethods.getPlace(generatePlaceId(256));
		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 404);
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.getString("msg"), "Get operation failed, looks like place_id  doesn't exists");

	}
}
