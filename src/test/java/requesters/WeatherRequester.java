package requesters;

import models.WeatherResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class WeatherRequester {

    private static final String URL = "http://samples.openweathermap.org/data/2.5/weather";
    private static final String APPID = "b1b15e88fa797225412429c1c50c122a1";

    public WeatherResponse getWeather(String cityName){

        String uri = UriComponentsBuilder
                .fromUriString(URL)
                .queryParam("q", cityName)
                .queryParam("appid", APPID)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(uri, WeatherResponse.class);
    }
}
