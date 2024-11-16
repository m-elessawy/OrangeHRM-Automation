package apis;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.AddCandidate;

import java.util.HashMap;
import java.util.Map;

import static utilities.PropertyReaderHelper.getCustomProperty;

public class CandidateAPI {

    private static final Logger LOGGER = LogManager.getLogger(CandidateAPI.class);

    private static final String BASE_URL = getCustomProperty("apiBaseUrlKey");
    private static final String TOKEN = getCustomProperty("authorizationTokenKey");
    private static final String END_POINT = getCustomProperty("candidatesEndPointKey");

    private final RequestSpecification requestSpecification;

    public CandidateAPI() {
        RestAssured.baseURI = BASE_URL;

        // Create a reusable RequestSpecification
        this.requestSpecification = RestAssured.given()
                .header("Cookie", "orangehrm=" + TOKEN)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json, text/plain, */*");

        LOGGER.info("Initialized CandidateAPI with Base URL: {}", BASE_URL);
    }

    public Response addCandidate( AddCandidate candidate) {
        // Send POST request
        Response response = requestSpecification
                .body(candidate)
                .post(END_POINT);

        LOGGER.info("Add Candidate Response: Status Code: {}, Body: {}", response.statusCode(), response.body().asString());
        return response;
    }

    public Response deleteCandidate(int candidateId) {
        LOGGER.info("Deleting candidate with ID: {}", candidateId);

        // Create payload
        String payload = "{\"ids\":[" + candidateId + "]}";

        // Send DELETE request
        Response response = requestSpecification
                .body(payload)
                .delete(END_POINT);

        LOGGER.info("Delete Candidate Response: Status Code: {}, Body: {}", response.statusCode(), response.body().asString());
        return response;
    }
}
