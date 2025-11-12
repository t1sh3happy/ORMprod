package com.example.ormprod;

import com.example.ormprod.entity.*;
import com.example.ormprod.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.ormprod.entity.Module;

@SpringBootApplication
public class OrmProdApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrmProdApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            UserRepository userRepository,
            ProfileRepository profileRepository,
            CategoryRepository categoryRepository,
            TagRepository tagRepository,
            CourseRepository courseRepository,
            ModuleRepository moduleRepository,
            LessonRepository lessonRepository,
            AssignmentRepository assignmentRepository,
            EnrollmentRepository enrollmentRepository,
            SubmissionRepository submissionRepository,
            QuizRepository quizRepository,
            QuestionRepository questionRepository,
            AnswerOptionRepository answerOptionRepository,
            QuizSubmissionRepository quizSubmissionRepository,
            CourseReviewRepository courseReviewRepository
    ) {
        return args -> {
            User teacher = User.builder()
                    .name("Ivan Ivanovs")
                    .email("ivan@lmss.com")
                    .role(User.Role.TEACHER)
                    .build();
            userRepository.save(teacher);

            User student = User.builder()
                    .name("Petr Petrosv")
                    .email("petr@lmss.com")
                    .role(User.Role.STUDENT)
                    .build();
            userRepository.save(student);

            Profile tProfile = Profile.builder()
                    .user(teacher)
                    .bio("Java-разработчик. 10 лет преподавания.")
                    .avatarUrl("https://avatars.com/ivan")
                    .build();
            profileRepository.save(tProfile);

            Category programming = Category.builder().name("Programming").build();
            categoryRepository.save(programming);

            Tag javaTag = Tag.builder().name("Java").build();
            Tag hibernateTag = Tag.builder().name("Hibernate").build();
            tagRepository.save(javaTag);
            tagRepository.save(hibernateTag);

            Course course = Course.builder()
                    .title("Основы Hibernate")
                    .description("Подробный курс по ORM и Hibernate")
                    .category(programming)
                    .teacher(teacher)
                    .duration(40)
                    .startDate(LocalDate.now())
                    .tags(Set.of(javaTag, hibernateTag))
                    .build();
            courseRepository.save(course);

            Module module1 = Module.builder()
                    .course(course)
                    .title("Введение в ORM")
                    .orderIndex(1)
                    .description("История ORM, основные понятия")
                    .build();
            moduleRepository.save(module1);

            Module module2 = Module.builder()
                    .course(course)
                    .title("Практика Hibernate")
                    .orderIndex(2)
                    .description("Работа с Hibernate, JPA аннотации")
                    .build();
            moduleRepository.save(module2);

            Lesson lesson1 = Lesson.builder()
                    .module(module1)
                    .title("Что такое ORM?")
                    .content("ORM — это ...")
                    .videoUrl("https://video.com/orm")
                    .build();
            lessonRepository.save(lesson1);

            Assignment assignment1 = Assignment.builder()
                    .lesson(lesson1)
                    .title("Реферат по истории ORM")
                    .description("Написать короткий доклад по теме")
                    .dueDate(LocalDate.now().plusDays(7))
                    .maxScore(100)
                    .build();
            assignmentRepository.save(assignment1);

            Enrollment enrollment = Enrollment.builder()
                    .student(student)
                    .course(course)
                    .enrollDate(LocalDate.now())
                    .status("Active")
                    .build();
            enrollmentRepository.save(enrollment);

            Submission submission = Submission.builder()
                    .assignment(assignment1)
                    .student(student)
                    .content("Доклад отправлен")
                    .submittedAt(LocalDateTime.now())
                    .score(90)
                    .feedback("Отлично!")
                    .build();
            submissionRepository.save(submission);

            Quiz quiz = Quiz.builder()
                    .module(module1)
                    .title("Тест: Введение")
                    .timeLimit(20)
                    .build();
            quizRepository.save(quiz);

// Вопрос и варианты ответа
            Question question = Question.builder()
                    .quiz(quiz)
                    .text("Что означает ORM?")
                    .type("SINGLE_CHOICE")
                    .build();
            questionRepository.save(question);

            AnswerOption opt1 = AnswerOption.builder()
                    .question(question)
                    .text("Object-relational mapping")
                    .isCorrect(true)
                    .build();
            AnswerOption opt2 = AnswerOption.builder()
                    .question(question)
                    .text("Online Resource Manager")
                    .isCorrect(false)
                    .build();
            answerOptionRepository.saveAll(List.of(opt1, opt2));

            QuizSubmission quizSubmission = QuizSubmission.builder()
                    .quiz(quiz)
                    .student(student)
                    .score(100)
                    .takenAt(LocalDateTime.now())
                    .build();
            quizSubmissionRepository.save(quizSubmission);

            CourseReview review = CourseReview.builder()
                    .course(course)
                    .student(student)
                    .rating(5)
                    .comment("Отличный курс по Hibernate!")
                    .createdAt(LocalDateTime.now())
                    .build();
            courseReviewRepository.save(review);
        };
    }
}

