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
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

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
    HttpServiceProxyFactory proxyFactory(RestClient.Builder restClientBuilder) {
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClientBuilder.build()))
                .build();
    }

    @Bean
    WeatherServiceClient weatherServiceClient(HttpServiceProxyFactory proxyFactory) {
        return proxyFactory.createClient(WeatherServiceClient.class);
    }

    @Bean
    StockQuoteClient stockQuoteClient(HttpServiceProxyFactory proxyFactory) {
        return proxyFactory.createClient(StockQuoteClient.class);
    }

    @Bean
    @Description("Get the current weather for a location")
    Function<WeatherRequest, WeatherResponse> currentWeatherFunction(WeatherServiceClient weatherClient) {
        return new WeatherServiceFunction(weatherClient);
    }

    @Bean
    StockQuoteFunction stockQuoteFunction(StockQuoteClient stockClient) {
        return new StockQuoteFunction(stockClient);
    }
}
