package com.ceci.games.quiz.integration;

import com.ceci.games.quiz.domain.TriviaQuestion;
import com.ceci.games.quiz.domain.TriviaResponse;
import com.ceci.games.quiz.model.QuestionDto;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

/**
 * TriviaConsumer is responsible for interacting with the external Trivia API to fetch trivia questions.
 * It fetches a set of trivia questions, filters out any invalid questions, and maps them to the format used in the application.
 * The purpose of fetching more questions than required is to account for invalid entries and ensure a valid list of questions.
 */
@Component
public class TriviaConsumer {
    private static final int MAX_AMOUNT_OF_QUESTIONS = 10;
    private final String API_URL = "https://opentdb.com/api.php?amount=10&type=multiple";

    private final RestTemplate restTemplate;

    public TriviaConsumer() {
        this.restTemplate = new RestTemplate();
    }

    public List<TriviaQuestion> fetchQuestionsFromExternalApi() {
        ResponseEntity<TriviaResponse> response = restTemplate.getForEntity(API_URL, TriviaResponse.class);
        TriviaResponse triviaResponse = response.getBody();

        if (triviaResponse == null) {
            throw new RuntimeException("Failed to fetch trivia questions: Response body is null");
        }

        switch (triviaResponse.responseCode()) {
            case 0:
                // Success
                break;
            case 1:
                throw new RuntimeException("No Results: The API doesn't have enough questions for your query.");
            case 2:
                throw new RuntimeException("Invalid Parameter: Arguments passed in aren't valid.");
            case 3:
                throw new RuntimeException("Token Not Found: Session token does not exist.");
            case 4:
                throw new RuntimeException("Token Empty: Session token has returned all possible questions. Resetting the token is necessary.");
            case 5:
                throw new RuntimeException("Rate Limit: Too many requests have occurred. Each IP can only access the API once every 5 seconds.");
            default:
                throw new RuntimeException("Unexpected response code: " + triviaResponse.responseCode());
        }

        List<TriviaQuestion> triviaQuestions = triviaResponse.results();

        return triviaQuestions.stream()
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
