package com.ceci.games.quiz.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TriviaResponse(
        @JsonProperty("response_code") int responseCode,
        @JsonProperty("results") List<TriviaQuestion> results
) {

    private TriviaResponse(Builder builder) {
        this(builder.responseCode, builder.results);
    }

    @Override
    public String toString() {
        return "Response Code: " + responseCode + ", Results: " + results;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int responseCode;
        private List<TriviaQuestion> results;

        public Builder responseCode(int responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder results(List<TriviaQuestion> results) {
            this.results = results;
            return this;
        }

        public TriviaResponse build() {
            return new TriviaResponse(this);
        }
    }
}

