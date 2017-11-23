Feature: City coordinates test

  Scenario: Test city coordinates
    Given city name: London
    When requesting weather information
    Then expected coordinates: Lon:-0.13; Lat:51.51;

  Scenario: Test weather data
    Given city name: London
    When requesting weather information
    Then validating fetched "weather" data

  Scenario: Test main data
    Given city name: London
    When requesting weather information
    Then validating fetched temperature data
    And validating fetched humidity data
    And validating fetched pressure data