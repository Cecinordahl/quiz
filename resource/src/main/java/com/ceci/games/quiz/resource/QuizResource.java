package com.ceci.games.quiz.resource;

import com.ceci.games.quiz.service.ExternalApiQuizService;
import com.ceci.games.quiz.model.QuestionDto;
import com.ceci.games.quiz.service.FileQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz/questions")
public class QuizResource {

    private final ExternalApiQuizService externalApiQuizService;
    private final FileQuizService fileQuizService;

    @Autowired
    public QuizResource(ExternalApiQuizService externalApiQuizService,
                        FileQuizService fileQuizService) {
        this.externalApiQuizService = externalApiQuizService;
        this.fileQuizService = fileQuizService;
    }

    @GetMapping("/external-api")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions() {
        List<QuestionDto> questions = externalApiQuizService.fetchQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/file")
    public ResponseEntity<List<QuestionDto>> getQuizQuestionsFromFile() {
        List<QuestionDto> questions = fileQuizService.getQuizQuestionsFromFile();
        return ResponseEntity.ok(questions);
    }
}
