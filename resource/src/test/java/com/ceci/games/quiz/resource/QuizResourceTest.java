package com.ceci.games.quiz.resource;

import com.ceci.games.quiz.model.QuestionDto;
import com.ceci.games.quiz.service.ExternalApiQuizService;
import com.ceci.games.quiz.service.FileQuizService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@Disabled // Mockito cannot mock this class: class com.ceci.games.quiz.service.ExternalApiQuizService.
@ExtendWith(MockitoExtension.class)
public class QuizResourceTest {

    @Mock
    private ExternalApiQuizService externalApiQuizService;

    @Mock
    private FileQuizService fileQuizService;

    @InjectMocks
    private QuizResource quizResource;

    @Test
    void getQuizQuestionsFromApi() {
        // Arrange
        List<QuestionDto> mockQuestions = createQuestionList();

        when(externalApiQuizService.fetchQuestions()).thenReturn(mockQuestions);

        // Act
        ResponseEntity<List<QuestionDto>> actualResponse = quizResource.getQuizQuestionsFromApi();

        // Assert
        assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
        assertEquals(mockQuestions, actualResponse.getBody());
    }

    @Test
    void getQuizQuestionsFromFile() {
        // Arrange
        List<QuestionDto> mockQuestions = createQuestionList();

        when(fileQuizService.getQuizQuestionsFromFile()).thenReturn(mockQuestions);

        // Act
        ResponseEntity<List<QuestionDto>> actualResponse = quizResource.getQuizQuestionsFromFile();

        // Assert
        assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
        assertEquals(mockQuestions, actualResponse.getBody());
    }

    /* Helper method */

    private List<QuestionDto> createQuestionList() {
        return singletonList(QuestionDto.builder()
                .question("Sample question")
                .correctAnswer("Correct answer")
                .incorrectAnswers(List.of("Wrong Answer 1", "Wrong Answer 2"))
                .build());
    }
}