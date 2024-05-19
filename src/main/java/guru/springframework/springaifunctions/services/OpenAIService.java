package guru.springframework.springaifunctions.services;


import guru.springframework.springaifunctions.model.Answer;
import guru.springframework.springaifunctions.model.Question;

public interface OpenAIService {

    Answer getAnswer(Question question);

    Answer getStockPrice(Question question);
}
