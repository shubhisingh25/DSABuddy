package com.dsabuddy.service;

import com.dsabuddy.entity.Problem;
import com.dsabuddy.entity.User;
import com.dsabuddy.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public List<Problem> getProblemsByUser(User user) {
        return problemRepository.findByUser(user);
    }

    public List<Problem> getProblemsByUserAndTopic(User user, String topic) {
        return problemRepository.findByUserAndTopic(user, topic);
    }

    public Problem saveProblem(Problem problem) {
        return problemRepository.save(problem);
    }
    public String getProblemTopic(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"))
                .getTopic();
    }

    public void deleteProblem(Long id) {
        problemRepository.deleteById(id);
    }

    public void toggleRevised(Long id, User user) {
        // First get the Optional<Problem> from repository
        Optional<Problem> problemOptional = problemRepository.findByIdAndUser(id, user);
        
        // Then unwrap it with orElseThrow
        Problem problem = problemOptional.orElseThrow(() -> 
            new RuntimeException("Problem not found with id: " + id + " for current user"));
        
        // Toggle the revised status
        problem.setRevised(!problem.isRevised());
        problemRepository.save(problem);
    }
}