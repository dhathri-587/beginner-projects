package com.quiz.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.onlinequiz.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}