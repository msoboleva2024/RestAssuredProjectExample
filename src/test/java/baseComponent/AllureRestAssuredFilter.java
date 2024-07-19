package baseComponent;

import io.qameta.allure.Attachment;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AllureRestAssuredFilter implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		// TODO Auto-generated method stub
		// Log request
		String requestLog = requestSpec.getMethod() + " " + requestSpec.getURI() + "\n" + requestSpec.getBody();
		saveRequestLog(requestLog);

		Response response;

		response = ctx.next(requestSpec, responseSpec);

		// Log response
		String responseLog = response.getStatusLine() + "\n" + response.getBody().asString();
		saveResponseLog(responseLog);

		return response;
	}

	@Attachment(value = "Request", type = "text/plain")
	public String saveRequestLog(String log) {
		return log;
	}

	@Attachment(value = "Response", type = "text/plain")
	public String saveResponseLog(String log) {
		return log;
	}

}
