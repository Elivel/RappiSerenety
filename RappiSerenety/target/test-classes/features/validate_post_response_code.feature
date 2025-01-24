Feature: Validate  API response code
Background: Run api
  Given I send a GET request to "https://shazam.p.rapidapi.com/songs/v2/get-details?id=1217912247&l=en-US"

  Scenario: Validate that the response code is 200
    Then the response code should be 200

  Scenario: Validate that the 'id' field contains 1217912247
    Then the "id" in the response data should be "1217912247"


  Scenario: Validate the artist name in the response
    Then the field "data[0].attributes.artistName" should contain "Gorillaz"

  Scenario: Validate the album id in the response
    Then the album id in the response should be "1217911994"
