// repository/ProblemRepository.java
package com.dsabuddy.repository;

import com.dsabuddy.entity.Problem;
import com.dsabuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
	List<Problem> findByUser(User user);
    List<Problem> findByUserAndTopic(User user, String topic);
    Optional<Problem> findByIdAndUser(Long id, User user);
    Optional<Problem> findById(Long id); // already exists in JpaRepository

	
}
