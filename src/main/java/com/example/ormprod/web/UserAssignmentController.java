package com.example.ormprod.web;

import com.example.ormprod.repository.*;
import com.example.ormprod.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAssignmentController {
    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;

    public UserAssignmentController(UserRepository userRepository, SubmissionRepository submissionRepository) {
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
    }

    @GetMapping("/{id}/assignments")
    public List<Submission> getUserAssignments(@PathVariable Long id) {
        return submissionRepository.findAll()
                .stream().filter(s -> s.getStudent().getId().equals(id)).toList();
    }
}
