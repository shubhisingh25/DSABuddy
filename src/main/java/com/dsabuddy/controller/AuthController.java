package com.dsabuddy.controller;

import com.dsabuddy.entity.User;
import com.dsabuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Home Page
  
    @GetMapping("/")
    public String indexRedirect() {
        return "redirect:/home";
    }
    // Login Page (GET)
    @GetMapping("/signin")
    public String loginPage(Model model) {
        model.addAttribute("title", "Login Page");
        return "auth/login";  // maps to templates/auth/login.html
    }

    // Login Submission (POST)
    @PostMapping("/dologin")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes) {
        // You can integrate authentication logic here (Spring Security usually handles this)
        // Placeholder redirection
        return "redirect:/user/dashboard";
    }

    // Register Page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";  // maps to templates/auth/register.html
    }

    // Register Form Submission
    @PostMapping("/do-register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "Registration successful!");
        return "redirect:/signin";
    }
}
