package com.ceci.games.quiz.integration;

import com.ceci.games.quiz.domain.TriviaQuestion;
import com.ceci.games.quiz.model.QuestionDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriviaMapperTest {

    private final TriviaMapper triviaMapper = new TriviaMapper();

    @Test
    void testMapToListOfQuestionDto() {
        // Arrange
        TriviaQuestion question1 = TriviaQuestion.builder()
                .question("Who directed the movie &quot;Alien&quot;?")
                .correctAnswer("Ridley Scott")
                .incorrectAnswers(Arrays.asList("James Cameron", "Steven Spielberg", "George Lucas"))
                .build();

        TriviaQuestion question2 = TriviaQuestion.builder()
                .question("What is the capital of &lt;France&gt;?")
                .correctAnswer("Paris")
                .incorrectAnswers(Arrays.asList("London", "Berlin", "Madrid"))
                .build();

        List<TriviaQuestion> triviaQuestions = Arrays.asList(question1, question2);

        // Act
        List<QuestionDto> questionDtos = triviaMapper.mapToListOfQuestionDto(triviaQuestions);

        // Assert
        assertEquals(2, questionDtos.size());
        assertEquals("Who directed the movie \"Alien\"?", questionDtos.get(0).question());
        assertEquals("Ridley Scott", questionDtos.get(0).correctAnswer());
        assertEquals(Arrays.asList("James Cameron", "Steven Spielberg", "George Lucas"), questionDtos.get(0).incorrectAnswers());

        assertEquals("What is the capital of <France>?", questionDtos.get(1).question());
        assertEquals("Paris", questionDtos.get(1).correctAnswer());
        assertEquals(Arrays.asList("London", "Berlin", "Madrid"), questionDtos.get(1).incorrectAnswers());
    }
}