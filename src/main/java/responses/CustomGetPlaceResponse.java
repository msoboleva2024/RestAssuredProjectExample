package responses;

public class CustomGetPlaceResponse {

	private GetPlaceResponse response;
	private int statusCode;

	public GetPlaceResponse getResponse() {
		return response;
	}

	public void setResponse(GetPlaceResponse response) {
		this.response = response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
