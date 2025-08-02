package guru.springframework.springaifunctions.functions;

import guru.springframework.springaifunctions.config.WeatherServiceClient;
import guru.springframework.springaifunctions.model.WeatherRequest;
import guru.springframework.springaifunctions.model.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse> {
    private final WeatherServiceClient weatherClient;

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        return weatherClient.getStockPrice(weatherRequest);
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }
}























