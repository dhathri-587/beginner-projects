package com.quiz.onlinequiz.repository;

import com.quiz.onlinequiz.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}