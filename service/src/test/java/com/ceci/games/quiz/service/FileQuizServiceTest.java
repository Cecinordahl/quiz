package com.ceci.games.quiz.service;


import com.ceci.games.quiz.model.QuestionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileQuizServiceTest {

    private final FileQuizService fileQuizService = new FileQuizService(new ObjectMapper());

    @Test
    void testGetQuizQuestionsFromFile_successful() {
        List<QuestionDto> questions = fileQuizService.getQuizQuestionsFromFile();

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(10, questions.size());
    }

    @Test
    void testGetQuizQuestionsFromFile_ioException() throws IOException {
        // TODO add error scenario
    }
}