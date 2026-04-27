package guru.springframework.springaifunctions.services;


import guru.springframework.springaifunctions.functions.StockQuoteFunction;
import guru.springframework.springaifunctions.functions.WeatherServiceFunction;
import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;
import guru.springframework.springaifunctions.model.StockPriceRequest;
import guru.springframework.springaifunctions.model.WeatherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AIServiceImpl implements AIService {
    private final ChatModel chatModel;
    private final StockQuoteFunction stockQuoteFunction;
    private final WeatherServiceFunction currentWeatherFunction;

    @Override
    public Answer getStockPrice(Question question) {
        var callback = FunctionToolCallback.builder("apply", stockQuoteFunction)
                .description("Get the current stock price for a stock symbol")
                .inputType(StockPriceRequest.class)
                .build();
        return new Answer(
                ChatClient.builder(chatModel)
                        .defaultTools(callback)
                        .defaultSystem("You are an agent which returns back a stock price for the given stock symbol (or ticker)")
                        .build()
                        .prompt()
                        .user(question.question())
                        .call()
                        .content()
        );
    }

    @Override
    public Answer getAnswer(Question question) {
        var callback = FunctionToolCallback.builder("apply", currentWeatherFunction)
                .description("Get the current weather for a location")
                .inputType(WeatherRequest.class)
                .build();
        return new Answer(
                ChatClient.builder(chatModel)
                        .defaultToolCallbacks(callback)
                        .defaultSystem("""
                                You are a weather service. You receive weather information from a service which gives you the information based on the metrics system. "
                                "When answering the weather in an imperial system country, you should convert the temperature to Celsius and the wind speed to km per hour.
                                """)
                        .build()
                        .prompt()
                        .user(question.question())
                        .call()
                        .content()
        );
    }
}