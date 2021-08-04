package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.QuizAttempts;

import java.util.List;

public interface QuizAttemptsServices {
    QuizAttempts getAttemptsById(Long quizAttemptId);
    QuizAttempts getOnProgressAttempts(String username, Long quizId);
    List<QuizAttempts> getAllQuizAttempts(String username, Long quizId);
    List<QuizAttempts> getAllFinishedQuizAttempts(String username, Long quizId);
    QuizAttempts addQuizAttempts(String username, Long quizId, List<Long> listQuestionsID);
    boolean makeFinished(Long quizAttemptId);
}
