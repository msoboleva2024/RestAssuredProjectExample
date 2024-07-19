package api;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import payload.DeletePlacePayload;
import payload.Place;
import responses.AddPlaceResponse;

public class CoreMethods {

	public RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(Routes.base_URL)
			.addHeader("Content-Type", "application/json").addQueryParam("key", "qaclick123").build();

	@Step("AddPlace Step: API Call for creation a place on the resource. Return Custom POJO Response")
	public AddPlaceResponse addPlace(Place payload) {

		AddPlaceResponse response = given().log().all().spec(requestSpec).body(payload).when().post(Routes.post_URL)
				.then().log().all().extract().response().as(AddPlaceResponse.class);

		return response;
	}

	@Step("AddPlace Step: API Call for creation a place on the resource.")
	public Response addPlace(String payload) {

		Response response = given().log().all().spec(requestSpec).body(payload).when().post(Routes.post_URL).then()
				.log().all().extract().response();

		return response;
	}

	@Step("DeletePlace Step: API Call for deletion of a place with PLACE-ID = {0} on the resource")
	public Response deletePlace(String place_id) {

		DeletePlacePayload payload = new DeletePlacePayload();
		payload.setPlace_id(place_id);
		Response response = given().log().all().spec(requestSpec).body(payload).when().post(Routes.delete_URL).then()
				.log().all().extract().response();

		return response;

	}

	@Step("GetPlace Step: API Call for getting a place with PLACE-ID = {0} from the resource")
	public Response getPlace(String place_id) {

		Response response = given().log().all().spec(requestSpec).queryParam("place_id", place_id).when()
				.get(Routes.get_URL).then().log().all().extract().response();

		return response;
	}

}
