package com.ceci.games.quiz.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TriviaQuestion(
        @JsonProperty("type")
        String type,
        @JsonProperty("difficulty")
        String difficulty,
        @JsonProperty("category")
        String category,
        @JsonProperty("question")
        String question,
        @JsonProperty("correct_answer")
        String correctAnswer,
        @JsonProperty("incorrect_answers")
        List<String> incorrectAnswers
) {

    private TriviaQuestion(Builder builder) {
        this(builder.type, builder.difficulty, builder.category, builder.question,
                builder.correctAnswer, builder.incorrectAnswers);
    }

    @Override
    public String toString() {
        return "Question: " + question + ", Correct Answer: " + correctAnswer +
                ", Incorrect Answers: " + incorrectAnswers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private String difficulty;
        private String category;
        private String question;
        private String correctAnswer;
        private List<String> incorrectAnswers;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder difficulty(String difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder question(String question) {
            this.question = question;
            return this;
        }

        public Builder correctAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public Builder incorrectAnswers(List<String> incorrectAnswers) {
            this.incorrectAnswers = incorrectAnswers;
            return this;
        }

        public TriviaQuestion build() {
            return new TriviaQuestion(this);
        }
    }
}
