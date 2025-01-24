package stepsdefinitions;

import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.*;

public class ApiStepDefinitions {
    private String endpoint;

    @Given("I send a GET request to {string}")
    public void iHaveTheEndpoint(String url) {
        this.endpoint = "https://shazam.p.rapidapi.com/songs/v2/get-details?id=1217912247&l=en-US";
        SerenityRest.given()
                .header("x-rapidapi-key", "3b4c083e97mshed8b1fee588d1c2p1690bajsn14c29b303a29")
                .header("x-rapidapi-host", "shazam.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .get(endpoint);
        int actualStatusCode = SerenityRest.lastResponse().getStatusCode();
        // Imprimir el código de respuesta
        System.out.println("Actual Response Code: " + actualStatusCode);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int expectedCode) {
        SerenityRest.then().statusCode(expectedCode);
    }
    @Then("the \"id\" in the response data should be {string}")
    public void theIdInTheResponseDataShouldBe(String expectedId) {
        SerenityRest.then()
                .body("data.id[0]", equalTo(expectedId));
        System.out.println(SerenityRest.then().extract().body().asString());
    }
    @Then("the field {string} should contain {string}")
    public void the_field_should_contain(String field, String expectedValue) {
        // Validamos que el campo contenga el valor esperado
        SerenityRest.then()
                .body(field, containsString(expectedValue)); // Verifica que el valor contiene la cadena esperada
                //.body("$",hasKey(field.split("\\[")[0])) // Verifica que la raíz contiene la clave principal
        System.out.println("Validation passed"+field+"contains"+expectedValue);

    }
    @Then("the album id in the response should be {string}")
    public void the_album_id_in_the_response_should_be(String expectedAlbumId) {
        // Validamos que el campo id en relationships.albums.data[0] sea igual al esperado
        SerenityRest.then()
                .body("data[0].relationships.albums.data[0].id", equalTo(expectedAlbumId));
        System.out.println("Album ID validated successfully: " + expectedAlbumId);
    }

}
