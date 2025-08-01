package com.dsabuddy.service;

import com.dsabuddy.entity.Topic;
import com.dsabuddy.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicByName(String name) {
        return topicRepository.findByNameIgnoreCase(name);
    }

    public List<Topic> getTopicsByCategory(String category) {
        return topicRepository.findByCategory(category);
    }
}