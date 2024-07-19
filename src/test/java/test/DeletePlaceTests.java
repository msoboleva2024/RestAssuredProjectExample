package test;

import org.testng.Assert;

import org.testng.annotations.Test;

import api.CoreMethods;

import baseComponent.Preconditions;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload.PlacePreconditions;

@Epic("GoogleMap API")
@Feature("Google Maps Delete API (POST)")
public class DeletePlaceTests extends Preconditions {

	@Epic("GoogleMap API")
	@Feature("Google Maps Delete API (POST)")
	@Test(priority = 0, description = "Positiv scenario for testing of 'Deletion' a place from the resource: successfull deletion ofa  place")
	@Description("Test-case will check that it is possible to delete an alerady created place on the resource. Test-Data will be automatically generated: at first new place will be created and than based on its place_id it will be deleted.")
	public void deletePlacePositiv() {

		// preconditions: create Place to get place_id
		PlacePreconditions responsePreconditions = addPlace();

		CoreMethods apiMethods = new CoreMethods();
		Response response = apiMethods.deletePlace(responsePreconditions.getPlace_id());

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.getString("status"), "OK");

	}

	@Epic("GoogleMap API")
	@Feature("Google Maps Delete API (POST)")
	@Test(priority = 1, description = "Test will check that it is not possible to delete the same place twice")
	@Description("Test-case will check that it is not possible to delete an alerady deleted place  on the resource. Test-Data will be automatically generated: at first new place will be created and than based on its place_id it will be deleted two times.")

	public void deleteSameIdTwoTimes() {

		// preconditions: create Place to get place_id
		PlacePreconditions responsePreconditions = addPlace();

		CoreMethods apiMethods = new CoreMethods();
		Response response = apiMethods.deletePlace(responsePreconditions.getPlace_id());

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.getString("status"), "OK");

		response = apiMethods.deletePlace(responsePreconditions.getPlace_id());
		resp = response.asString();
		js = new JsonPath(resp);
		Assert.assertEquals(js.getString("msg"), "Delete operation failed, looks like the data doesn't exists");

	}

	@Epic("GoogleMap API")
	@Feature("Google Maps Delete API (POST)")
	@Test(priority = 1, description = "Checking of deletion possibility for not-existed place")
	@Description("Test-case will check that it is impossible to delete not-existed place ")
	public void deleteNotExistingPlace() {

		CoreMethods apiMethods = new CoreMethods();
		Response response = apiMethods.deletePlace(generatePlaceId(8));

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.getString("msg"), "Delete operation failed, looks like the data doesn't exists");

	}

	@Epic("GoogleMap API")
	@Feature("Google Maps Delete API (POST)")
	@Test(priority = 1, description = "Check that it is impossible to delete anything without place_id")
	@Description("Test-case will check that it if we dont set place_id nothing will be deleted on the resource.")
	public void deletePlaceWithoutPlace_id() {

		CoreMethods apiMethods = new CoreMethods();
		Response response = apiMethods.deletePlace("");

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.getString("msg"), "Delete operation failed, looks like the data doesn't exists");

	}

}
