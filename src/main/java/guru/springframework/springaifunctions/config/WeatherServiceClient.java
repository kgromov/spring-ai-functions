package guru.springframework.springaifunctions.config;

import guru.springframework.springaifunctions.model.WeatherRequest;
import guru.springframework.springaifunctions.model.WeatherResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface WeatherServiceClient {
    @GetExchange("https://api.api-ninjas.com/v1/stockprice")
    WeatherResponse getStockPrice(WeatherRequest weatherRequest);
}
