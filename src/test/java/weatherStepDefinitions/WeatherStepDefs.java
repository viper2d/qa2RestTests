package weatherStepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import models.Main;
import models.Weather;
import models.WeatherResponse;
import models.WeatherValidation;
import requesters.WeatherRequester;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import validationData.WeatherValidationData;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class WeatherStepDefs {

    private String cityName;
    private WeatherRequester weatherRequester = new WeatherRequester();
    private WeatherResponse weatherResponse;
    private Weather fetchedWeatherData;
    private Main fetchedMainData;
    private Integer fetchedCityId;
    private WeatherValidationData weatherValidationData = new WeatherValidationData();
    private WeatherValidation validationData;
    private Scenario scenario;
    final HashMap<String, Integer> pressureRange = weatherValidationData.getPressureRange();

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("city name: (.*)")
    public void city_name(String name) {

        scenario.write("Given city: " + name);

        cityName = name;
    }

    @When("requesting weather information")
    public void request_weather_info() {

        scenario.write("Requesting weather information for city: " + cityName);

        weatherResponse = weatherRequester.getWeather(cityName);
        fetchedWeatherData = weatherResponse.getWeather().get(0);
        fetchedCityId = fetchedWeatherData.getId();
        fetchedMainData = weatherResponse.getMain();
    }


    @Then("expected coordinates: Lon:(.*); Lat:(.*);")
    public void check_coordinates(Double lon, Double lat) {

        scenario.write("Checking coordinates.");

        assertEquals(lon, weatherResponse.getCoord().getLon());
        assertEquals(lat, weatherResponse.getCoord().getLat());
    }

    @Then("validating fetched \"weather\" data")
    public void check_weather_data() {

        scenario.write("Validating fetched \"weather\" data information, if in accordance with expected.");

        validationData = weatherValidationData.getWeatherValidationData(fetchedCityId);

        assertFalse("Weather group is empty!", weatherResponse.getWeather().isEmpty());

        assertEquals(validationData.getId(), fetchedWeatherData.getId());
        assertEquals(validationData.getMain(), fetchedWeatherData.getMain());
        assertEquals(validationData.getDescription(), fetchedWeatherData.getDescription());
        assertEquals(validationData.getIcon(), fetchedWeatherData.getIcon());
    }

    @Then("validating fetched temperature data")
    public void check_temperature_data() {

        scenario.write("Validating fetched \"temperature\" data, if not abnormal.");

        assertTrue("Received Min temperature value is higher then Max.",
                fetchedMainData.getTemp_min() < fetchedMainData.getTemp_max());

        assertTrue("Current temperature value is out of received Min-Max range.",
                fetchedMainData.getTemp() >= fetchedMainData.getTemp_min() &&
                        fetchedMainData.getTemp() <= fetchedMainData.getTemp_max());
    }

    @Then("validating fetched humidity data")
    public void check_humidity_data() {

        scenario.write("Validating fetched \"humidity\" data, if in percentage range.");

        assertTrue("Humidity value is out of percentage range.",
                fetchedMainData.getHumidity() >= 0 &&
                        fetchedMainData.getHumidity() <= 100);
    }

    @Then("validating fetched pressure data")
    public void check_pressure_data() {

        scenario.write("Validating fetched \"pressure\" data, if not abnormal.");

        assertTrue("Pressure data is abnormal.",
                fetchedMainData.getPressure() >= pressureRange.get("min") &&
                        fetchedMainData.getPressure() <= pressureRange.get("max"));

    }

}
