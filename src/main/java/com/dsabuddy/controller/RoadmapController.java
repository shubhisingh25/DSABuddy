package com.dsabuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
	@RequestMapping("/roadmap")
	public class RoadmapController {

	    @GetMapping("/dsa")
	    public String dsaRoadmapPage() {
	        return "roadmap/dsa"; // This looks for templates/roadmap/dsa.html
	    }

	    @GetMapping("/java")
	    public String javaRoadmapPage() {
	        return "roadmap/java"; // for Java Roadmap link
	    }
	}


