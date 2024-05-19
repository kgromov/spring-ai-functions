package guru.springframework.springaifunctions.config;

import guru.springframework.springaifunctions.functions.StockQuoteFunction;
import guru.springframework.springaifunctions.functions.WeatherServiceFunction;
import guru.springframework.springaifunctions.model.WeatherRequest;
import guru.springframework.springaifunctions.model.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Configuration
public class AiFunctionsConfig {
    private final ApiNinjaSettings settings;

    @Bean
    RestClient.Builder restClientBuilder() {
        return RestClient.builder()
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", settings.apiKey());
                    httpHeaders.set(HttpHeaders.ACCEPT, APPLICATION_JSON_VALUE);
                    httpHeaders.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);
                });
    }

    @Bean
    @Description("Get the current weather for a location")
    Function<WeatherRequest, WeatherResponse> currentWeatherFunction(RestClient.Builder restClientBuilder) {
        return new WeatherServiceFunction(restClientBuilder.baseUrl(settings.weatherUrl()).build());
    }

    @Bean
    StockQuoteFunction stockQuoteFunction(RestClient.Builder restClientBuilder) {
        return new StockQuoteFunction(restClientBuilder.baseUrl(settings.stockUrl()).build());
    }
}
