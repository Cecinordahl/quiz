package com.ceci.games.quiz.resource;

import com.ceci.games.quiz.service.QuizService;
import com.ceci.games.quiz.model.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizResource {

    private final QuizService quizService;

    @Autowired
    public QuizResource(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions() {
        List<QuestionDto> questions = quizService.getQuizQuestions();
        return ResponseEntity.ok(questions);
    }
}
