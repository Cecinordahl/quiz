package com.ceci.games.quiz.integration;

import com.ceci.games.quiz.domain.TriviaQuestion;
import com.ceci.games.quiz.domain.TriviaResponse;
import com.ceci.games.quiz.model.QuestionDto;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Component
public class TriviaConsumer {

    // Fetch more questions than necessary, so you can filter out invalid ones
    private final String API_URL = "https://opentdb.com/api.php?amount=50&type=multiple"; // Fetching 50 questions to filter out invalid ones

    private final RestTemplate restTemplate;

    public TriviaConsumer() {
        this.restTemplate = new RestTemplate();
    }

    public List<QuestionDto> fetchQuestionsFromApi() {
        // Fetch the questions from the API
        ResponseEntity<TriviaResponse> response = restTemplate.getForEntity(API_URL, TriviaResponse.class);
        List<TriviaQuestion> triviaQuestions = response.getBody().results();

        // Filter out invalid questions and map to QuestionDto
        return triviaQuestions.stream()
                .filter(this::isValidTriviaQuestion) // Filter out questions with null fields
                .map(this::mapToQuestionDto) // Map valid TriviaQuestion to QuestionDto
                .limit(10) // Limit the result to 10 questions
                .collect(Collectors.toList());
    }

    // Validate that all necessary fields are non-null
    private boolean isValidTriviaQuestion(TriviaQuestion question) {
        return question.question() != null &&
                question.correctAnswer() != null &&
                question.incorrectAnswers() != null &&
                !question.incorrectAnswers().contains(null);
    }

    // Map TriviaQuestion to QuestionDto, handling HTML entity decoding
/*    private QuestionDto mapToQuestionDto(TriviaQuestion triviaQuestion) {
        return QuestionDto.builder()
                .question(StringEscapeUtils.unescapeHtml4(triviaQuestion.question())) // Decode HTML entities
                .correctAnswer(StringEscapeUtils.unescapeHtml4(triviaQuestion.correctAnswer())) // Decode HTML entities
                .incorrectAnswers(triviaQuestion.incorrectAnswers().stream()
                        .map(StringEscapeUtils::unescapeHtml4) // Decode HTML entities for incorrect answers
                        .collect(Collectors.toList()))
                .build();
    }*/

    private QuestionDto mapToQuestionDto(TriviaQuestion triviaQuestion) {
        return QuestionDto.builder()
                .question(triviaQuestion.question())
                .correctAnswer(triviaQuestion.correctAnswer())
                .incorrectAnswers(new ArrayList<>(triviaQuestion.incorrectAnswers()))
                .build();
    }
}
