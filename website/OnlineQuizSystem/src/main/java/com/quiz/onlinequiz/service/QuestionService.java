package com.quiz.onlinequiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quiz.onlinequiz.entity.Question;
import com.quiz.onlinequiz.repository.QuestionRepository;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    // ADD / UPDATE QUESTION
    public Question addQuestion(Question question) {

        return questionRepository.save(question);
    }


    // GET ALL QUESTIONS
    public List<Question> getAllQuestions() {

        return questionRepository.findAll();
    }


    // GET QUESTION BY ID
    public Question getQuestionById(Long id) {

        return questionRepository
                .findById(id)
                .orElse(null);
    }


    // DELETE QUESTION
    public void deleteQuestion(Long id) {

        questionRepository.deleteById(id);
    }
}