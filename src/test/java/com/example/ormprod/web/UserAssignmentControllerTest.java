package com.example.ormprod.web;

import com.example.ormprod.repository.UserRepository;
import com.example.ormprod.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserAssignmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetUserAssignments() throws Exception {
        User user = userRepository.findAll().get(0);
        Long id = user.getId();

        mockMvc.perform(get("/api/users/" + id + "/assignments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
