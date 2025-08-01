package com.dsabuddy.repository;

import com.dsabuddy.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByCategory(String category);
    Topic findByName(String name);
    Topic findByNameIgnoreCase(String name);
}