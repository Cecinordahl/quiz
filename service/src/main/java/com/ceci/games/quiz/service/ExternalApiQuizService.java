package com.ceci.games.quiz.service;

import com.ceci.games.quiz.domain.TriviaQuestion;
import com.ceci.games.quiz.integration.TriviaConsumer;
import com.ceci.games.quiz.integration.TriviaMapper;
import com.ceci.games.quiz.model.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalApiQuizService {

    private final TriviaConsumer triviaConsumer;
    private final TriviaMapper triviaMapper;

    @Autowired
    public ExternalApiQuizService(TriviaConsumer triviaConsumer,
                                  TriviaMapper triviaMapper) {
        this.triviaConsumer = triviaConsumer;
        this.triviaMapper = triviaMapper;
    }

    public List<QuestionDto> fetchQuestions() {
        List<TriviaQuestion> triviaQuestions = triviaConsumer.fetchQuestionsFromExternalApi();
        return triviaMapper.mapToListOfQuestionDto(triviaQuestions);
    }
}
