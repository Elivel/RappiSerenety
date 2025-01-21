package stepsdefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.equalTo;

public class ApiStepDefinitions {
    private String endpoint1;
    private String endpoint2;
    private String requestBody;

    @Given("I have the endpoint {string}")
    public void iHaveTheEndpoint(String url) {
        endpoint1 = url;
    }

    @And("I have the request body")
    public void iHaveTheRequestBody(String body) {
        requestBody = body;
    }

    @When("I send a POST request")
    public void iSendAPOSTRequest() {
         SerenityRest
                .given()
                .header("x-rapidapi-key", "3b4c083e97mshed8b1fee588d1c2p1690bajsn14c29b303a29")
                .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(endpoint1)
                .getStatusCode();

        // Imprimir el código de respuesta para depuración
        int actualStatusCode = SerenityRest.lastResponse().getStatusCode();
        System.out.println("Actual Response Code: " + actualStatusCode);

        SerenityRest.given()
                .header("x-rapidapi-key", "3b4c083e97mshed8b1fee588d1c2p1690bajsn14c29b303a29")
                .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .get(endpoint2);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int expectedCode) {
        SerenityRest.then().statusCode(expectedCode);
    }
    @Given("I also have another endpoint {string}")
    public void iAlsoHaveAnotherEndpoint(String url) {
        endpoint2 = url;  // Segundoendpoint
    }

    @When("I send a GET request")
    public void iSendAGETRequest() {
        SerenityRest
                .given()
                .header("x-rapidapi-key", "3b4c083e97mshed8b1fee588d1c2p1690bajsn14c29b303a29")
                .header("x-rapidapi-host","shazam.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .get(endpoint2);
        System.out.println(SerenityRest.then().extract().body().asString());
    }
    @Then("the response should contain the 'id' field with value 1217912247")
    public void theResponseShouldContainIdFieldWithValue() {
        SerenityRest.then()
                .body("data.id", equalTo(1217912247));  // Verifica que el valor del campo 'id' sea 1217912247
    }
}
