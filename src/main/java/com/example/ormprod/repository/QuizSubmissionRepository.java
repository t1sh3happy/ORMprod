package com.example.ormprod.repository;

import com.example.ormprod.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
}
