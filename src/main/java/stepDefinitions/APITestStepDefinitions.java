package stepDefinitions;


import  apis.CandidateAPI;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.AddCandidate;

public class APITestStepDefinitions {

    CandidateAPI candidateAPI = new CandidateAPI();
    AddCandidate candidate;
    Response response;
    int candidateId;


    @Given("I have the candidate details with first name {string}, middle name {string}, last name {string}, email {string}, contact number {string}, keywords {string}, comment {string}, date of application {string}")
    public void i_have_the_candidate_details(String firstName, String middleName, String lastName, String email, String contactNumber, String keywords, String comment, String dateOfApplication) {
        // Store candidate details in a Candidate object or log them for reporting

        candidate = new AddCandidate(firstName, middleName, lastName, email, contactNumber, keywords, comment, dateOfApplication);

    }
    @When("I add the candidate using API")
    public void i_add_the_candidate_using_api() {
        response = candidateAPI.addCandidate(candidate);
    }

    @Then("I verify the candidate is added successfully")
    public void i_verify_the_candidate_is_added_successfully() {
        Assert.assertEquals(response.statusCode(), 200);
        candidateId = response.jsonPath().getInt("data.id");


    }

    @When("I delete the candidate using API")
    public void i_delete_the_candidate_using_api() {
        response = candidateAPI.deleteCandidate(candidateId);
    }

    @Then("I verify the candidate is deleted successfully")
    public void i_verify_the_candidate_is_deleted_successfully() {
        Assert.assertEquals(response.statusCode(), 200);
    }
}
