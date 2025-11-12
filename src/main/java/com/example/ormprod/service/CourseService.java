package com.example.ormprod.service;

import com.example.ormprod.entity.Course;
import com.example.ormprod.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }
}
