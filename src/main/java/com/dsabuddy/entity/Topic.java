package com.dsabuddy.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 2000)
    private String definition;
    
    @Column(nullable = false)
    private String category;
    
    @ElementCollection
    private List<String> subtopics;
    
    private String youtubeUrl;
    private String sheetLink;

    // Default constructor
    public Topic() {
    }

    // Parameterized constructor
    public Topic(String name, String definition, String category, 
                List<String> subtopics, String youtubeUrl, String sheetLink) {
        this.name = name;
        this.definition = definition;
        this.category = category;
        this.subtopics = subtopics;
        this.youtubeUrl = youtubeUrl;
        this.sheetLink = sheetLink;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getSubtopics() {
        return subtopics;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public String getSheetLink() {
        return sheetLink;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubtopics(List<String> subtopics) {
        this.subtopics = subtopics;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public void setSheetLink(String sheetLink) {
        this.sheetLink = sheetLink;
    }

    // Static categories
    public static final String[] CATEGORIES = {
        "Basics",
        "Sorting Techniques", 
        "Searching",
        "Array",
        "String",
        "LinkedList",
        "Stack",
        "Queue",
        "Recursion",
        "Bit Manipulation",
        "Greedy Algorithm",
        "Tree",
        "Graph",
        "Dynamic Programming"
    };

    // toString method for debugging
    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", definition='" + definition + '\'' +
                ", category='" + category + '\'' +
                ", subtopics=" + subtopics +
                ", youtubeUrl='" + youtubeUrl + '\'' +
                ", sheetLink='" + sheetLink + '\'' +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return id != null && id.equals(topic.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}