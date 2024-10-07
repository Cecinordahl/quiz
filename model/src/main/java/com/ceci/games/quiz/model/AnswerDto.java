package com.ceci.games.quiz.model;

public record AnswerDto(
        String answer,
        boolean isCorrect
) {

    private AnswerDto(Builder builder) {
        this(builder.answer, builder.isCorrect);
    }

    @Override
    public String toString() {
        return "Answer: " + answer + ", Correct: " + isCorrect;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String answer;
        private boolean isCorrect;

        public Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder isCorrect(boolean isCorrect) {
            this.isCorrect = isCorrect;
            return this;
        }

        public AnswerDto build() {
            return new AnswerDto(this);
        }
    }
}
