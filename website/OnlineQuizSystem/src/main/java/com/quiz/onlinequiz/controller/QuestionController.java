package com.quiz.onlinequiz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.onlinequiz.entity.Question;
import com.quiz.onlinequiz.service.QuestionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // ==========================================
    // CHECK ADMIN LOGIN
    // ==========================================

    private boolean isAdminLoggedIn(HttpSession session) {

        return Boolean.TRUE.equals(
                session.getAttribute("adminLoggedIn")
        );
    }

    // ==========================================
    // SHOW QUIZ
    // ==========================================

    @GetMapping("/quiz")
    public String showQuiz(Model model) {

        model.addAttribute(
                "questions",
                questionService.getAllQuestions()
        );

        return "quiz";
    }

    // ==========================================
    // SHOW ADMIN PAGE
    // ==========================================

    @GetMapping("/admin")
    public String showAdminPage(
            Model model,
            HttpSession session) {

        // BLOCK UNAUTHORIZED USERS
        if (!isAdminLoggedIn(session)) {

            return "redirect:/admin-login";
        }

        model.addAttribute(
                "questions",
                questionService.getAllQuestions()
        );

        return "admin";
    }

    // ==========================================
    // ADD QUESTION
    // ==========================================

    @PostMapping("/admin/add")
    public String addQuestion(
            Question question,
            HttpSession session) {

        // BLOCK UNAUTHORIZED USERS
        if (!isAdminLoggedIn(session)) {

            return "redirect:/admin-login";
        }

        questionService.addQuestion(question);

        return "redirect:/admin";
    }

    // ==========================================
    // SHOW UPDATE PAGE
    // ==========================================

    @GetMapping("/admin/edit/{id}")
    public String showEditPage(
            @PathVariable Long id,
            Model model,
            HttpSession session) {

        // BLOCK UNAUTHORIZED USERS
        if (!isAdminLoggedIn(session)) {

            return "redirect:/admin-login";
        }

        Question question =
                questionService.getQuestionById(id);

        model.addAttribute(
                "question",
                question
        );

        return "edit-question";
    }

    // ==========================================
    // UPDATE QUESTION
    // ==========================================

    @PostMapping("/admin/update")
    public String updateQuestion(
            Question question,
            HttpSession session) {

        // BLOCK UNAUTHORIZED USERS
        if (!isAdminLoggedIn(session)) {

            return "redirect:/admin-login";
        }

        questionService.addQuestion(question);

        return "redirect:/admin";
    }

    // ==========================================
    // DELETE QUESTION
    // ==========================================

    @GetMapping("/admin/delete/{id}")
    public String deleteQuestion(
            @PathVariable Long id,
            HttpSession session) {

        // BLOCK UNAUTHORIZED USERS
        if (!isAdminLoggedIn(session)) {

            return "redirect:/admin-login";
        }

        questionService.deleteQuestion(id);

        return "redirect:/admin";
    }

    // ==========================================
    // SUBMIT QUIZ
    // ==========================================

    @PostMapping("/quiz/submit")
    public String submitQuiz(
            @RequestParam Map<String, String> answers,
            Model model) {

        List<Question> questions =
                questionService.getAllQuestions();

        int score = 0;

        for (Question question : questions) {

            String userAnswer =
                    answers.get(
                            "question_" + question.getId()
                    );

            if (userAnswer != null &&
                    userAnswer.equals(
                            question.getCorrectAnswer()
                    )) {

                score++;
            }
        }

        int total = questions.size();

        model.addAttribute(
                "score",
                score
        );

        model.addAttribute(
                "total",
                total
        );

        return "result";
    }
}