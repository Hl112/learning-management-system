package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.Question;
import com.wfh.sp21.lms.model.module.QuizQuestion;

import java.util.List;

public interface QuizQuestionServices {
    List<Question> getAllQuestionsByQuizId(Long quizId);
    List<QuizQuestion> getAllByQuizId(Long quizId);
}
