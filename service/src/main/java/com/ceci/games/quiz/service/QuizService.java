package com.ceci.games.quiz.service;

import com.ceci.games.quiz.integration.TriviaConsumer;
import com.ceci.games.quiz.model.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final TriviaConsumer triviaConsumer;

    @Autowired
    public QuizService(TriviaConsumer triviaConsumer) {
        this.triviaConsumer = triviaConsumer;
    }

    public List<QuestionDto> getQuizQuestions() {
        return triviaConsumer.fetchQuestionsFromApi();
    }
}
