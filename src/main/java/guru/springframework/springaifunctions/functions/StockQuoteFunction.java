package guru.springframework.springaifunctions.functions;

import guru.springframework.springaifunctions.model.StockPriceRequest;
import guru.springframework.springaifunctions.model.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@RequiredArgsConstructor
public class StockQuoteFunction implements Function<StockPriceRequest, StockPriceResponse> {
    public final RestClient restClient;

    @Override
    public StockPriceResponse apply(StockPriceRequest request) {
        try {
            return restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder.queryParam("ticker", request.ticker()).build())
                    .retrieve()
                    .body(StockPriceResponse.class);
        } catch (Exception e) {
            return new StockPriceResponse(null, null, null, null, null );
        }
    }
}