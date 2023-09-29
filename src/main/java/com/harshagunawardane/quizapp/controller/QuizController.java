package com.harshagunawardane.quizapp.controller;

import com.harshagunawardane.quizapp.model.Question;
import com.harshagunawardane.quizapp.model.QuestionWrapper;
import com.harshagunawardane.quizapp.model.Response;
import com.harshagunawardane.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping
    public ResponseEntity<String> createQuiz
            (@RequestParam String category, @RequestParam int numQ, @RequestParam String quizTitle){
        return quizService.createQuiz(category, numQ, quizTitle);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable String id){
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable String id, @RequestBody List<Response> responses){
        return quizService.submitQuiz(id, responses);
    }
}
