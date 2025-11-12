package com.example.ormprod.web;

import com.example.ormprod.entity.*;
import com.example.ormprod.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;

    public AssignmentController(AssignmentRepository assignmentRepository, SubmissionRepository submissionRepository, UserRepository userRepository) {
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<Submission> submitAssignment(
            @PathVariable Long id,
            @RequestParam("user") Long userId,
            @RequestBody String submissionText) {

        Assignment assignment = assignmentRepository.findById(id).orElseThrow();
        User student = userRepository.findById(userId).orElseThrow();

        Submission submission = Submission.builder()
                .assignment(assignment)
                .student(student)
                .content(submissionText)
                .build();
        return ResponseEntity.ok(submissionRepository.save(submission));
    }
}
