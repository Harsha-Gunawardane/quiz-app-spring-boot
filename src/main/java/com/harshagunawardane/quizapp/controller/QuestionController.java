package com.harshagunawardane.quizapp.controller;

import com.harshagunawardane.quizapp.model.Question;
import com.harshagunawardane.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    // get all questions
    @Autowired
    QuestionService questionService;
    @GetMapping("/all")
    public List<Question> getALlQuestions(){
        return questionService.getAllQuestions();
    }

    // get one question
    @GetMapping("{id}")
    public Optional<Question> getQuestion(@PathVariable String id){
        return questionService.getQuestion(id);
    }

    // get questions by category
    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Question updateQuestion(@PathVariable String id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteQuestion(@PathVariable String id){
        questionService.deleteQuestion(id);
        return "Question was deleted";
    }
}
