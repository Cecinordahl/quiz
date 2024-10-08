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
            // Load the JSON file from src/main/resources/
            ClassPathResource resource = new ClassPathResource(QUIZ_QUESTIONS_FILE);

            // Parse the JSON content to List<QuestionDto>
            List<QuestionDto> questions = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<QuestionDto>>() {}
            );

            // Shuffle the list of questions to get random order
            Collections.shuffle(questions);

            // Return the first 10 questions (or less if there are fewer than 10 questions available)
            return questions.size() > QUESTION_LIMIT ? questions.subList(0, QUESTION_LIMIT) : questions;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading quiz questions from JSON file", e);
        }
    }
}
