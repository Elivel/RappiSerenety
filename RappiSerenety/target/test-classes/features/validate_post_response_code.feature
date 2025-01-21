Feature: Validate POST API response code

  Scenario: Validate that the response code is 200
    Given I have the endpoint "https://shazam.p.rapidapi.com/songs/v2/detect?timezone=America%2FChicago&locale=en-US"
    And I have the request body
    When I send a POST request
    Then the response code should be 204

  Scenario: Validate that the 'id' field contains 1217912247
    Given I also have another endpoint "https://shazam.p.rapidapi.com/songs/v2/get-details?id=1217912247&l=en-US"
    When I send a GET request
    Then the response should contain the 'id' field with value 1217912247
