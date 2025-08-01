package com.dsabuddy.controller;

import com.dsabuddy.entity.Problem;
import com.dsabuddy.entity.User;
import com.dsabuddy.repository.ProblemRepository;
import com.dsabuddy.service.ProblemService;
import com.dsabuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemRepository problemRepository;

    // 🟩 Dashboard page
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Problem> problems = problemService.getProblemsByUser(user);

        long easy = problems.stream().filter(p -> "easy".equalsIgnoreCase(p.getDifficulty())).count();
        long medium = problems.stream().filter(p -> "medium".equalsIgnoreCase(p.getDifficulty())).count();
        long hard = problems.stream().filter(p -> "hard".equalsIgnoreCase(p.getDifficulty())).count();

        model.addAttribute("problem", new Problem());
        model.addAttribute("problems", problems);
        model.addAttribute("easyCount", easy);
        model.addAttribute("mediumCount", medium);
        model.addAttribute("hardCount", hard);
        model.addAttribute("total", problems.size());

        return "dashboard/index";
    }

    // 🟩 Add a problem
    @PostMapping("/add-problem")
    public String addProblem(
            @RequestParam String url,
            @RequestParam String difficulty,
            @RequestParam String topic,
            @RequestParam String questionName,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        User user = userService.getUserByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Problem problem = new Problem();
        problem.setQuestionName(questionName);
        problem.setUrl(url);
        problem.setDifficulty(difficulty);
        problem.setTopic(topic);
        problem.setUser(user);
        problem.setRevised(false);
        problem.setDateAdded(LocalDate.now());

        problemService.saveProblem(problem);

        // Redirect back to topic page with anchor so it stays on same section
        return "redirect:/user/topic/" + URLEncoder.encode(topic, StandardCharsets.UTF_8);
    }

    // 🟩 View problems by topic
    @GetMapping("/topic/{topic}")
    public String viewTopic(@PathVariable String topic, Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Problem> problems = problemService.getProblemsByUserAndTopic(user, topic);
        model.addAttribute("problems", problems);
        model.addAttribute("topic", topic);
        model.addAttribute("problem", new Problem());
        model.addAttribute("totalCount", problems.size());

        return "dashboard/topic-view";
    }

    // 🟩 Delete problem by ID
    @GetMapping("/delete-problem/{id}")
    public String deleteProblem(@PathVariable("id") Long id, Principal principal) {
        Problem problem = problemRepository.findById(id).orElse(null);
        if (problem != null) {
            String topic = problem.getTopic();
            problemRepository.delete(problem);
            return "redirect:/user/topic/" + URLEncoder.encode(topic, StandardCharsets.UTF_8);
        }
        return "redirect:/user/dashboard";
    }

    // 🟩 Toggle revised status
    @GetMapping("/toggle-revised/{id}")
    public String toggleRevised(@PathVariable("id") Long id, Principal principal) {
        Problem problem = problemRepository.findById(id).orElse(null);
        if (problem != null) {
            problem.setRevised(!problem.isRevised());
            problemRepository.save(problem);
            return "redirect:/user/topic/" + URLEncoder.encode(problem.getTopic(), StandardCharsets.UTF_8);
        }
        return "redirect:/user/dashboard";
    }
    private long countProblemsByDifficulty(List<Problem> problems, String difficulty) {
        return problems.stream()
                .filter(p -> difficulty.equalsIgnoreCase(p.getDifficulty()))
                .count();
    }
 // 🟪 Show new DSA dashboard page
    @GetMapping("/dsa")
    public String dsaRoadmapPage() {
        return "dashboard/dsa";  // Maps to templates/dashboard/dsa.html
    }

}
