package guru.springframework.springaifunctions.tools;

import guru.springframework.springaifunctions.config.StockQuoteClient;
import guru.springframework.springaifunctions.model.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class StockTools {
    private final StockQuoteClient stockQuoteClient;

    @Tool(name = "get_stock_price", description = "Get the current stock price for a stock symbol")
    public StockPriceResponse getStockPrice(String ticker) {
        return stockQuoteClient.getStockPrice(ticker);
    }
}
