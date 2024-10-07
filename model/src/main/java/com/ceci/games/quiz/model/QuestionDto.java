package com.ceci.games.quiz.model;

import java.util.List;

public record QuestionDto(
        String question,
        String correctAnswer,
        List<String> incorrectAnswers
) {
    private QuestionDto(Builder builder) {
        this(builder.question, builder.correctAnswer, builder.incorrectAnswers);
    }

    @Override
    public String toString() {
        return "Question: " + question + ", Correct Answer: " + correctAnswer + ", Incorrect Answers: " + incorrectAnswers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String question;
        private String correctAnswer;
        private List<String> incorrectAnswers;

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

        public QuestionDto build() {
            return new QuestionDto(this);
        }
    }
}