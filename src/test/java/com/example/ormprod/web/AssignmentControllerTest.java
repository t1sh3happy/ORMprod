package com.example.ormprod.web;

import com.example.ormprod.entity.Assignment;
import com.example.ormprod.entity.Submission;
import com.example.ormprod.repository.AssignmentRepository;
import com.example.ormprod.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void testSubmitAssignment() throws Exception {
        Assignment assignment = assignmentRepository.findAll().get(0);
        Long assignmentId = assignment.getId();
        Long userId = userRepository.findAll().stream().findFirst().get().getId();

        String solution = "\"Моё решение на задание\"";
        mockMvc.perform(post("/api/assignments/" + assignmentId + "/submit")
                        .param("user", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(solution))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Моё решение на задание"));
    }
}
