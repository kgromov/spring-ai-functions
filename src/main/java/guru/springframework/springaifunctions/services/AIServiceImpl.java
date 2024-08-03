package guru.springframework.springaifunctions.services;


import guru.springframework.springaifunctions.functions.StockQuoteFunction;
import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AIServiceImpl implements AIService {
    private final ChatModel chatModel;
    private final StockQuoteFunction stockQuoteFunction;

    @Override
    public Answer getStockPrice(Question question) {
        return new Answer(
                ChatClient.builder(chatModel)
                        .defaultFunction(
                                "CurrentStockPrice",
                                "Get the current stock price for a stock symbol",
                                stockQuoteFunction
                        )
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
        return new Answer(
                ChatClient.builder(chatModel)
                        .defaultFunctions("currentWeatherFunction")
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