package guru.springframework.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;


@JsonClassDescription("Stock price request")
public class StockPriceRequest {
    @JsonPropertyDescription("ticker name of the stock to quote") String ticker;

    public StockPriceRequest() {
    }

    public StockPriceRequest(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
