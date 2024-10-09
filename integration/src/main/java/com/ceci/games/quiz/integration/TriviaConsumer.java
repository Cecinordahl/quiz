package com.ceci.games.quiz.integration;

import com.ceci.games.quiz.domain.TriviaQuestion;
import com.ceci.games.quiz.domain.TriviaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

/**
 * TriviaConsumer is responsible for interacting with the external Trivia API to fetch trivia questions.
 * It fetches a set of trivia questions, filters out any invalid questions, and maps them to the format used in the application.
 * The purpose of fetching more questions than required is to account for invalid entries and ensure a valid list of questions.
 */
@Component
public class TriviaConsumer {
    private static final int MAX_AMOUNT_OF_QUESTIONS = 10;

    private final String apiUrl;
    private final RestTemplate restTemplate;
    private final TriviaResponseHandler triviaResponseHandler;

    public TriviaConsumer(@Value("${trivia.api.url}") String apiUrl,
                          TriviaResponseHandler triviaResponseHandler,
                          RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.triviaResponseHandler = triviaResponseHandler;
        this.restTemplate = restTemplate;
    }

    public List<TriviaQuestion> fetchQuestionsFromExternalApi() {
        ResponseEntity<TriviaResponse> response = restTemplate.getForEntity(apiUrl, TriviaResponse.class);
        TriviaResponse triviaResponse = response.getBody();

        if (triviaResponse == null) {
            throw new RuntimeException("Failed to fetch trivia questions: Response body is null");
        }

        triviaResponseHandler.handleResponseCode(triviaResponse.responseCode());

        return triviaResponse.results().stream()
                .filter(this::isValidTriviaQuestion)
                .limit(MAX_AMOUNT_OF_QUESTIONS)
                .collect(toCollection(ArrayList::new));
    }

    private boolean isValidTriviaQuestion(TriviaQuestion question) {
        return question.question() != null &&
                question.correctAnswer() != null &&
                question.incorrectAnswers() != null &&
                !question.incorrectAnswers().contains(null);
    }


}
