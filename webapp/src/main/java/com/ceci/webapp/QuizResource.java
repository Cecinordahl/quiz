package com.ceci.webapp;

public class QuizResource {

    public List<TriviaQuestion> getTriviaQuestions(){
        TriviaApiConsumer consumer = new TriviaApiConsumer();
            String response = consumer.getTriviaQuestions();
            return consumer.parseTriviaQuestions(response);
    }
}
