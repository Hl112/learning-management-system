package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.Quiz;

public interface QuizServices {
    Quiz getQuizById(Long quizId);
    boolean makeStarted(Long quizId);
}
