package com.harshagunawardane.quizapp.dao;

import com.harshagunawardane.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
