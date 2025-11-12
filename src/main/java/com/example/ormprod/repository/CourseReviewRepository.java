package com.example.ormprod.repository;

import com.example.ormprod.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {
}
