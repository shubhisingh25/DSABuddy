package com.dsabuddy.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String url;
    private String difficulty;
    private String topic;
    private boolean revised;
    private LocalDate dateAdded;
    @Column(length = 500)
    private String questionName;

    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

  
    // Getters and Setters
    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id; }
    
    public String getUrl() { return url; }
    
    public void setUrl(String url) { this.url = url; }
    public String getDifficulty() { return difficulty; }
 // Getter and Setter
    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public boolean isRevised() { return revised; }
    public void setRevised(boolean revised) { this.revised = revised; }
    public LocalDate getDateAdded() { return dateAdded; }
    public void setDateAdded(LocalDate dateAdded) { this.dateAdded = dateAdded; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}