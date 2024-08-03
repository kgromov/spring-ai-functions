package guru.springframework.springaifunctions.functions;

import guru.springframework.springaifunctions.model.WeatherRequest;
import guru.springframework.springaifunctions.model.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse> {
    private final RestClient restClient;

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        return restClient.get()
                .uri(uriBuilder -> {
                    log.info("Building URI for weather request: {}", weatherRequest);
                    uriBuilder.queryParam("city", weatherRequest.location());
                    if (isNotBlank(weatherRequest.state())) {
                        uriBuilder.queryParam("state", weatherRequest.state());
                    }
                    if (isNotBlank(weatherRequest.country())) {
                        uriBuilder.queryParam("country", weatherRequest.country());
                    }
                    return uriBuilder.build();
                }).retrieve()
                .body(WeatherResponse.class);
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }
}























