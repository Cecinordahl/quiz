package com.ceci.games.quiz.integration;

import com.ceci.games.quiz.domain.TriviaQuestion;
import com.ceci.games.quiz.model.QuestionDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Component
public class TriviaMapper {

    public List<QuestionDto> mapToListOfQuestionDto(List<TriviaQuestion> triviaQuestions) {
        return triviaQuestions.stream()
                .map(this::mapToQuestionDto)
                .collect(toCollection(ArrayList::new));
    }

    private QuestionDto mapToQuestionDto(TriviaQuestion triviaQuestion) {
        return QuestionDto.builder()
                .question(Jsoup.parse(triviaQuestion.question()).text())
                .correctAnswer(Jsoup.parse(triviaQuestion.correctAnswer()).text())
                .incorrectAnswers(mapIncorrectAnswers(triviaQuestion))
                .build();
    }

    private ArrayList<String> mapIncorrectAnswers(TriviaQuestion triviaQuestion) {
        return triviaQuestion.incorrectAnswers().stream()
                .map(Jsoup::parse)
                .map(Element::text)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
