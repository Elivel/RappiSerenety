package stepsdefinitions;
import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class PostResponseSteps {
    private Response response;

    @Given("I prepare the POST request for the service")
    public void preparePostRequest() {
        baseURI = "https://shazam.p.rapidapi.com";
    }

    @When("I send the request to the endpoint {string}")
    public void sendRequest(String endpoint) {
        response = given()
                .header("Content-Type", "application/json")
                .header("X-RapidAPI-Key", "3b4c083e97mshed8b1fee588d1c2p1690bajsn14c29b303a29") // Reemplaza con tu clave
                .header("X-RapidAPI-Host", "shazam.p.rapidapi.com")
                .queryParam("timezone", "America/Chicago")
                .queryParam("locale", "en-US")
                .body("{\"data\": {\"is_test\": true}}")
                .when()
                .post(endpoint);
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    @Then("the response should have the status code {int}")
    public void shouldStatus(int expectedCode) {
        response.then().statusCode(expectedCode);
    }
}
