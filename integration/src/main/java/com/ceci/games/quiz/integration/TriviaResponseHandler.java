package com.ceci.games.quiz.integration;

import org.springframework.stereotype.Component;

@Component
public class TriviaResponseHandler {

        public void handleResponseCode(int responseCode) {
            switch (responseCode) {
                case 0:
                    return;
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
                    throw new RuntimeException("Unexpected response code: " + responseCode);
            }
        }
}
