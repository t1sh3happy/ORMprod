package com.example.ormprod.service;

import com.example.ormprod.entity.Course;
import com.example.ormprod.entity.Quiz;
import com.example.ormprod.entity.QuizSubmission;
import com.example.ormprod.repository.CourseRepository;
import com.example.ormprod.repository.QuizRepository;
import com.example.ormprod.repository.QuizSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepo;
    private final QuizSubmissionRepository submissionRepo;

    public Quiz createQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }

    public QuizSubmission submitQuiz(QuizSubmission submission) {
        return submissionRepo.save(submission);
    }
}
