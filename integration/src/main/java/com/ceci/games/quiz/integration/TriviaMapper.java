package com.ceci.games.quiz.integration;

import com.ceci.games.quiz.domain.TriviaQuestion;
import com.ceci.games.quiz.model.QuestionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Component
public class TriviaMapper {

    public List<QuestionDto> mapToListOfQuestionDto(List<TriviaQuestion> triviaQuestions) {
        return triviaQuestions.stream()
                .map(this::mapToQuestionDto)
                .collect(toCollection(ArrayList::new));
    }

    // TODO use StringEscapeUtils.unescapeHtml4 to decode the answers
    private QuestionDto mapToQuestionDto(TriviaQuestion triviaQuestion) {
        return QuestionDto.builder()
                .question(triviaQuestion.question())
                .correctAnswer(triviaQuestion.correctAnswer())
                .incorrectAnswers(mapIncorrectAnswers(triviaQuestion))
                .build();
    }

    private static ArrayList<String> mapIncorrectAnswers(TriviaQuestion triviaQuestion) {
        return new ArrayList<>(triviaQuestion.incorrectAnswers());
    }
}
