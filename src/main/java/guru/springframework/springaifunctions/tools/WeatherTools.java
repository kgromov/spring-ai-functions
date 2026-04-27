package guru.springframework.springaifunctions.tools;

import guru.springframework.springaifunctions.config.WeatherServiceClient;
import guru.springframework.springaifunctions.model.WeatherRequest;
import guru.springframework.springaifunctions.model.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class WeatherTools {
    private final WeatherServiceClient weatherClient;

    @Tool(name = "get_current_weather", description = "Get the current weather for a location")
    public WeatherResponse getCurrentWeather(WeatherRequest request) {
        return weatherClient.getWeather(request);
    }
}
