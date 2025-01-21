package stepsdefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.equalTo;

public class ApiStepDefinitions {
    private String endpoint = "https://shazam.p.rapidapi.com/songs/v2/get-details?id=1217912247&l=en-US";

    @Given("I send a GET request to {string}")
    public void iHaveTheEndpoint(String url) {
        SerenityRest.given()
                .header("x-rapidapi-key", "3b4c083e97mshed8b1fee588d1c2p1690bajsn14c29b303a29")
                .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                .header("Content-Type", "application/json")
                //.header("x-rapidapi-ua","RapidAPI-Playground")
                //.header("specificMethodHeaders","[object Object]")
                .get(url);
       // Imprimir el código de respuesta para depuración
        int actualStatusCode = SerenityRest.lastResponse().getStatusCode();
        System.out.println("Actual Response Code: " + actualStatusCode);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int expectedCode) {
        SerenityRest.then().statusCode(expectedCode);
    }
    @Then("the \"id\" in the response data should be {string}")
    public void theIdInTheResponseDataShouldBe(String expectedId) {
        SerenityRest.then()
                .body("data.id[0]", equalTo(expectedId));  // Verifica que el valor del campo 'id' sea 1217912247
        System.out.println(SerenityRest.then().extract().body().asString());
    }
}
