package com.example.ormprod.web;

import com.example.ormprod.entity.*;
import com.example.ormprod.repository.*;
import com.example.ormprod.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @GetMapping
    public List<Quiz> getQuizzes(){
        return quizService.getAllQuizzes();
    }

    @PostMapping("/{id}/submit")
    public QuizSubmission submitQuiz(@PathVariable Long id, @RequestBody QuizSubmission submission) {
        submission.setQuiz(quizService.getAllQuizzes().stream()
                .filter(q -> q.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Quiz not found")));
        return quizService.submitQuiz(submission);
    }
}
