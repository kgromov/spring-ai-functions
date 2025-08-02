package guru.springframework.springaifunctions.functions;

import guru.springframework.springaifunctions.config.StockQuoteClient;
import guru.springframework.springaifunctions.model.StockPriceRequest;
import guru.springframework.springaifunctions.model.StockPriceResponse;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class StockQuoteFunction implements Function<StockPriceRequest, StockPriceResponse> {
    public final StockQuoteClient stockClient;

    @Override
    public StockPriceResponse apply(StockPriceRequest request) {
        try {
            return stockClient.getStockPrice(request.getTicker());
        } catch (Exception e) {
            return new StockPriceResponse(null, null, null, null, null );
        }
    }
}