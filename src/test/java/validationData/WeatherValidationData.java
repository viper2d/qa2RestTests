package validationData;

import models.WeatherValidation;

import java.util.HashMap;

public class WeatherValidationData {

    public WeatherValidation getWeatherValidationData(Integer id) {

        HashMap<Integer, WeatherValidation> weatherConditions = new HashMap<Integer, WeatherValidation>();

        weatherConditions.put(300, new WeatherValidation(300, "Drizzle", "light intensity drizzle", "09d"));
        weatherConditions.put(301, new WeatherValidation(301, "Drizzle", "main desctiption", "09d"));
        weatherConditions.put(302, new WeatherValidation(302, "Drizzle", "main desctiption", "09d"));
        weatherConditions.put(303, new WeatherValidation(303, "Drizzle", "main desctiption", "09d"));
        weatherConditions.put(304, new WeatherValidation(304, "Drizzle", "main desctiption", "09d"));
        weatherConditions.put(305, new WeatherValidation(305, "Drizzle", "main desctiption", "09d"));

        return weatherConditions.getOrDefault(id, new WeatherValidation());
    }

    public HashMap<String, Integer> getPressureRange() {

        HashMap<String, Integer> pressureRange = new HashMap<>();

        pressureRange.put("min", 950);
        pressureRange.put("max", 1100);

        return pressureRange;
    }


}
