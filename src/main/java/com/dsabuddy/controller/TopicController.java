package com.dsabuddy.controller;

import com.dsabuddy.entity.Topic;
import com.dsabuddy.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/list")
    public String listTopics(Model model) {
        model.addAttribute("topics", topicService.getAllTopics());
        return "topics/list";
    }

    @GetMapping("/category/{category}")
    public String getTopicsByCategory(@PathVariable String category, Model model) {
        model.addAttribute("topics", topicService.getTopicsByCategory(category));
        model.addAttribute("category", category);
        return "topics/list";
    }

    @GetMapping("/{topicName}")
    public String getTopicDetails(@PathVariable String topicName, Model model) {
        Topic topic = topicService.getTopicByName(topicName);
        if (topic == null) {
            return "error/404";
        }
        model.addAttribute("topic", topic);
        return "topics/detail";
    }
}