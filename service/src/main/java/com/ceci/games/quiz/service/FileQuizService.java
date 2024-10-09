package com.ceci.games.quiz.service;

import com.ceci.games.quiz.model.QuestionDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class FileQuizService {

    private static final String QUIZ_QUESTIONS_FILE = "quiz_questions.json";
    private static final int QUESTION_LIMIT = 10;

    private final ObjectMapper objectMapper;

    public FileQuizService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<QuestionDto> getQuizQuestionsFromFile() {
        try {
            ClassPathResource resource = new ClassPathResource(QUIZ_QUESTIONS_FILE);

            List<QuestionDto> questions = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<QuestionDto>>() {}
            );

            Collections.shuffle(questions);
            return questions.size() > QUESTION_LIMIT ? questions.subList(0, QUESTION_LIMIT) : questions;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading quiz questions from JSON file", e);
        }
    }
}
