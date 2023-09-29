package com.harshagunawardane.quizapp.service;

import com.harshagunawardane.quizapp.dao.QuestionDao;
import com.harshagunawardane.quizapp.dao.QuizDao;
import com.harshagunawardane.quizapp.model.Question;
import com.harshagunawardane.quizapp.model.QuestionWrapper;
import com.harshagunawardane.quizapp.model.Quiz;
import com.harshagunawardane.quizapp.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String quizTitle) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(quizTitle);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(String id) {
        Optional<Quiz> quiz = quizDao.findById(Integer.parseInt(id));

        if(quiz.isPresent()) {
            List<Question> questions = quiz.get().getQuestions();

            List<QuestionWrapper> questionsForUser = questionsWrapper(questions);
            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    private List<QuestionWrapper> questionsWrapper(List<Question> questions){
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4());

            questionsForUser.add(qw);
        }
        return questionsForUser;
    }

    public ResponseEntity<Integer> submitQuiz(String id, List<Response> responses) {
        Quiz quiz = quizDao.findById(Integer.parseInt(id)).get();
        List<Question> questions = quiz.getQuestions();

        int result = calculateResult(responses, questions);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Integer calculateResult(List<Response> responses, List<Question> questions){
        int result = 0;
        int i = 0;

        if(questions == null || questions.isEmpty() ||
                responses.size() != questions.size())
            return result;

        for (Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                result++;

            i++;
        }
        return result;
    }
}
