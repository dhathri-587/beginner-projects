package com.quiz.onlinequiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    // SHOW ADMIN LOGIN PAGE
    @GetMapping("/admin-login")
    public String showLoginPage() {
        return "admin-login";
    }

    // CHECK ADMIN PASSWORD
    @PostMapping("/admin-login")
    public String login(
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // ADMIN PASSWORD
        if ("shanthi123".equals(password)) {

            session.setAttribute("adminLoggedIn", true);

            return "redirect:/admin";
        }

        model.addAttribute(
                "error",
                "❌ Wrong password! Please try again."
        );

        return "admin-login";
    }

    // ADMIN LOGOUT
    @GetMapping("/admin-logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/shanthi-dharma-quiz";
    }
}