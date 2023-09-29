package com.harshagunawardane.quizapp.service;

import com.harshagunawardane.quizapp.dao.QuestionDao;
import com.harshagunawardane.quizapp.model.Question;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

     public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return ResponseEntity.ok("Success");
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public Optional<Question> getQuestion(String id) {
        return questionDao.findById(Integer.parseInt(id));
    }

    public Question updateQuestion(String id, Question udatedQuestion) {
        Question existQuestion = questionDao.findById(Integer.parseInt(id))
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        existQuestion.setQuestionTitle(udatedQuestion.getQuestionTitle());
        existQuestion.setCategory(udatedQuestion.getCategory());
        existQuestion.setOption1(udatedQuestion.getOption1());
        existQuestion.setOption2(udatedQuestion.getOption2());
        existQuestion.setOption3(udatedQuestion.getOption3());
        existQuestion.setOption4(udatedQuestion.getOption4());
        existQuestion.setDifficultyLevel(udatedQuestion.getDifficultyLevel());

        return questionDao.save(existQuestion);
    }

    public void deleteQuestion(String id) {
        questionDao.deleteById(Integer.parseInt(id));
    }
}
