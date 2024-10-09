package com.ceci.games.quiz.integration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TriviaResponseHandlerTest {

    private final TriviaResponseHandler triviaResponseHandler = new TriviaResponseHandler();

    @ParameterizedTest
    @CsvSource({
            "1, No Results: The API doesn't have enough questions for your query.",
            "2, Invalid Parameter: Arguments passed in aren't valid.",
            "3, Token Not Found: Session token does not exist.",
            "4, Token Empty: Session token has returned all possible questions. Resetting the token is necessary.",
            "5, Rate Limit: Too many requests have occurred. Each IP can only access the API once every 5 seconds.",
            "999, Unexpected response code: 999"
    })
    void handleResponseCode_throwsException(int responseCode, String expectedMessage) {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                triviaResponseHandler.handleResponseCode(responseCode));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource("0")
    void handleResponseCode_success(int responseCode) {
        triviaResponseHandler.handleResponseCode(responseCode);
    }
}