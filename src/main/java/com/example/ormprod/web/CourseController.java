package com.example.ormprod.web;

import com.example.ormprod.entity.*;
import com.example.ormprod.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    public CourseController(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseRepository.save(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
        course.getModules().size(); // лениво инициализируем коллекцию
        course.getModules().forEach(module -> module.getLessons().size());
        return ResponseEntity.ok(course);
    }

    @PostMapping("/{id}/enroll")
    public ResponseEntity<String> enrollUser(
            @PathVariable Long id,
            @RequestParam("user") Long userId) {

        Course course = courseRepository.findById(id).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        if (enrollmentRepository.findByStudentIdAndCourseId(userId, id).isPresent()) {
            return ResponseEntity.badRequest().body("User already enrolled");
        }
        Enrollment enrollment = Enrollment.builder()
                .student(user)
                .course(course)
                .status("Active")
                .build();
        enrollmentRepository.save(enrollment);
        return ResponseEntity.ok("Enrolled");
    }
}
