package com.example.ormprod.service;

import com.example.ormprod.entity.Enrollment;
import com.example.ormprod.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment enrollStudent(Enrollment enrollment) {

        return enrollmentRepository.save(enrollment);
    }

    public Optional<Enrollment> findByStudentAndCourse(Long studentId, Long courseId) {
        return enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    public List<Enrollment> findAllByStudent(Long studentId) {
        return enrollmentRepository.findAllByStudentId(studentId);
    }

    public List<Enrollment> findAllByCourse(Long courseId) {
        return enrollmentRepository.findAllByCourseId(courseId);
    }
}
