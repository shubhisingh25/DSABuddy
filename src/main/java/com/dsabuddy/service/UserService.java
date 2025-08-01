package com.dsabuddy.service;

import com.dsabuddy.entity.User;
import com.dsabuddy.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser1(User user) {
        return userRepository.save(user);
    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        // Encode the raw password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

}