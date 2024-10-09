package com.ceci.games.quiz.integration;

import com.ceci.games.quiz.domain.TriviaQuestion;
import com.ceci.games.quiz.domain.TriviaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TriviaConsumerTest {

    private static final String API_URL = "https://mocked-api.com/questions";

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TriviaResponseHandler triviaResponseHandler;

    @InjectMocks
    private TriviaConsumer triviaConsumer;

    @BeforeEach
    void setUp() {
        triviaConsumer = new TriviaConsumer(API_URL, triviaResponseHandler, restTemplate);
    }

    @Test
    void testFetchQuestionsFromExternalApi_successfulResponse() {
        // Arrange
        TriviaResponse triviaResponse = createTriviaResponse();
        ResponseEntity<TriviaResponse> responseEntity = ResponseEntity.ok(triviaResponse);

        when(restTemplate.getForEntity(API_URL, TriviaResponse.class))
                .thenReturn(responseEntity);
        doNothing().when(triviaResponseHandler).handleResponseCode(triviaResponse.responseCode());

        // Act
        List<TriviaQuestion> actualQuestions = triviaConsumer.fetchQuestionsFromExternalApi();

        // Assert
        assertNotNull(actualQuestions);
        assertEquals(1, actualQuestions.size());
        assertEquals("What is the capital of France?", actualQuestions.get(0).question());
        assertEquals("Paris", actualQuestions.get(0).correctAnswer());
    }

    @Test
    void testFetchQuestionsFromExternalApi_nullResponseBody() {
        ResponseEntity<TriviaResponse> responseEntity = ResponseEntity.ok(null);

        when(restTemplate.getForEntity(API_URL, TriviaResponse.class))
                .thenReturn(responseEntity);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> triviaConsumer.fetchQuestionsFromExternalApi());
        assertEquals("Failed to fetch trivia questions: Response body is null", exception.getMessage());
    }

    /* Helper method */

    private TriviaResponse createTriviaResponse() {
        TriviaQuestion question1 = TriviaQuestion.builder()
                .question("What is the capital of France?")
                .correctAnswer("Paris")
                .incorrectAnswers(Arrays.asList("London", "Berlin", "Madrid"))
                .build();

        return TriviaResponse.builder()
                .responseCode(0)
                .results(singletonList(question1))
                .build();
    }
}