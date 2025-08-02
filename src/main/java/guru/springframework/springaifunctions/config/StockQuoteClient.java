package guru.springframework.springaifunctions.config;

import guru.springframework.springaifunctions.model.StockPriceResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface StockQuoteClient {
    @GetExchange("https://api.api-ninjas.com/v1/stockprice")
    StockPriceResponse getStockPrice(String ticker);
}
