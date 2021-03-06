package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.module.AttemptsAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptsAnswersRepository extends JpaRepository<AttemptsAnswers, Long> {
    AttemptsAnswers findByAttemptAnswerId(Long attemptAnswerId);
    List<AttemptsAnswers> findAllByQuizAttempts_QuizAttemptIdAndQuestion_QuestionId(Long quizAttemptId, Long questionId);
}
