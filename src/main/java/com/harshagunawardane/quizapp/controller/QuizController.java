package com.harshagunawardane.quizapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quiz")
public class QuizController {

    @PostMapping
    public ResponseEntity<String> createQuiz
            (@RequestParam String category, @RequestParam int numQ, @RequestParam String quizTitle){
        return new ResponseEntity<>("Quiz is created", HttpStatus.CREATED);
    }
}
