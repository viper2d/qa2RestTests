package validationData;

import models.WeatherValidation;

import java.util.HashMap;

public class ForecastValidationData {

    public WeatherValidation getWeatherValidationData(Integer id) {

        //Expected "weather" data, according to documentation.

        HashMap<Integer, WeatherValidation> weatherConditions = new HashMap<Integer, WeatherValidation>();

        weatherConditions.put(300, new WeatherValidation(300, "Drizzle", "light intensity drizzle", "09d"));
        weatherConditions.put(301, new WeatherValidation(301, "Drizzle", "drizzle", "09d"));
        weatherConditions.put(302, new WeatherValidation(302, "Drizzle", "heavy intensity drizzle ", "09d"));
        weatherConditions.put(310, new WeatherValidation(310, "Drizzle", "light intensity drizzle rain ", "09d"));
        weatherConditions.put(311, new WeatherValidation(311, "Drizzle", "drizzle rain", "09d"));
        weatherConditions.put(312, new WeatherValidation(312, "Drizzle", "heavy intensity drizzle rain", "09d"));

        return weatherConditions.getOrDefault(id, new WeatherValidation());
    }

    public HashMap<String, Integer> getPressureRange() {

        //Expected "pressure" range, according to 3 year statistics (expanded).

        HashMap<String, Integer> pressureRange = new HashMap<>();

        pressureRange.put("min", 950);
        pressureRange.put("max", 1100);

        return pressureRange;
    }


}
